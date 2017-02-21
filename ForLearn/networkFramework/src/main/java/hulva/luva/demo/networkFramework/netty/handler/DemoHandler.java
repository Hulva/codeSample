/**
 * 
 */
package hulva.luva.demo.networkFramework.netty.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年2月8日
 *
 */
public class DemoHandler extends ChannelInboundHandlerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(DemoHandler.class);
  private static final String authCookieName = "whirlpool";
  private static final String URI_LOGIN = "/login";
  private static final String URI_LOGOUT = "/logout";
  private static final String URI_EMPTY = "/";
  private static final Long authCookieMaxAge = 1209600L;

  private WebSocketServerHandshaker handshaker;
  private StringBuilder frameBuffer = null;
  private static final ChannelGroup channels = new DefaultChannelGroup("XXXChannelGruop", GlobalEventExecutor.INSTANCE);

  private final WebSocketMessageHandler wsMessageHandler = new DemoMessageHandler(channels);

  public DemoHandler() {}

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    String client = ctx.channel().attr(WebSocketHelper.getClientAttr()).get();
    logger.info(String.format("[INACTIVE] Channel with client %s has gone inactive", client));
    super.channelInactive(ctx);
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    logger.info("[START] New Channel has been initialzed");
    super.handlerAdded(ctx);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    logger.info("[END] A Channel has been removed");
    super.handlerRemoved(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof FullHttpRequest) {
      this.handleHttpRequest(ctx, (FullHttpRequest) msg);
    } else if (msg instanceof WebSocketFrame) {
      this.handleWebSocketFrame(ctx, (WebSocketFrame) msg);
    }
  }

  protected void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
    logger.debug("Received incoming frame [{}]", frame.getClass().getName());
    // Check for closing frame
    if (frame instanceof CloseWebSocketFrame) {
      if (frameBuffer != null) {
        handleMessageCompleted(ctx, frameBuffer.toString());
      }
      handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
      return;
    }

    if (frame instanceof PingWebSocketFrame) {
      ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
      return;
    }

    if (frame instanceof PongWebSocketFrame) {
      logger.info("Pong frame received");
      return;
    }

    if (frame instanceof TextWebSocketFrame) {
      frameBuffer = new StringBuilder();
      frameBuffer.append(((TextWebSocketFrame) frame).text());
    } else if (frame instanceof ContinuationWebSocketFrame) {
      if (frameBuffer != null) {
        frameBuffer.append(((ContinuationWebSocketFrame) frame).text());
      } else {
        logger.warn("Continuation frame received without initial frame.");
      }
    } else {
      throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
    }

    // Check if Text or Continuation Frame is final fragment and handle if needed.
    if (frame.isFinalFragment()) {
      handleMessageCompleted(ctx, frameBuffer.toString());
      frameBuffer = null;
    }
  }

  protected void handleMessageCompleted(ChannelHandlerContext ctx, String frameText) {
    wsMessageHandler.handleMessage(ctx, frameText);
  }

  protected boolean handleREST(ChannelHandlerContext ctx, FullHttpRequest req) {
    // check request path here and process any HTTP REST calls
    // return true if message has been processed

    return false;
  }

  protected void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
    String uri = req.uri();
    HttpMethod method = req.method();

    // Handle a bad request.
    if (!req.decoderResult().isSuccess()) {
      sendError(ctx, HttpResponseStatus.BAD_REQUEST);
      return;
    }

    String cookieUserName = null;
    String cookieString = req.headers().get(HttpHeaderNames.COOKIE);
    if (cookieString != null) {
      Set<Cookie> cookies = ServerCookieDecoder.STRICT.decode(cookieString);
      if (!cookies.isEmpty()) {
        // Reset the cookies if necessary.
        for (Cookie cookie : cookies) {
          if (cookie.name().equals(authCookieName)) {
            cookieUserName = cookie.value();
          }
        }
      }
    }

    final String userName = cookieUserName;

    // authenticate before upgrading
    if (HttpMethod.POST.equals(method)) {
      DefaultCookie nettyCookie = null;
      String message = null;
      String host = req.headers().get("Host");
      if (host == null) {
        host = "127.0.0.1";
      }

      int portIndex = host.indexOf(":");
      if (portIndex > -1) {
        host = host.substring(0, portIndex);
      }

      if (URI_LOGIN.equals(uri)) {
        String username = null;
        String password = null;

        try {
          HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), req);
          Map<String, String> attributes = new HashMap<>();
          List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();
          for (InterfaceHttpData data : datas) {
            if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
              try {
                String name = data.getName();
                String value = ((Attribute) data).getString();
                attributes.put(name, value);
              } catch (IOException e) {
                logger.error("Error getting HTTP attribute from POST request", e);
              }
            }
          }
          decoder.destroy();

          // Handle either form POST or JSON POST
          if (attributes.containsKey("user")) {
            username = attributes.get("user");
            password = attributes.get("password");
          } else {
            ByteBuf content = req.content();
            if (content.isReadable()) {
              String json = content.toString(CharsetUtil.UTF_8);
              try {
                JsonElement jElement = new JsonParser().parse(json);
                JsonObject jObject = jElement.getAsJsonObject();
                if (jObject.has("user")) {
                  username = jObject.get("user").toString().replaceAll("\"", "");
                }

                if (jObject.has("password")) {
                  password = jObject.get("password").toString().replaceAll("\"", "");
                }
              } catch (JsonSyntaxException e) {
                // not JSON
              }
            }
          }

          if (username != null) {
            for (Channel channel : channels) {
              String key = channel.attr(WebSocketHelper.getClientAttr()).get();
              if (key.equals(username)) {
                logger.error(String.format("Existing user '%s' found, failing login!", username));
                nettyCookie = WebSocketHelper.expireCookie(authCookieName, host);
                message = "{\"response\": \"fail\", \"reason\": \"Unauthorized, user '" + username + "' is already logged in\"}\r\n";
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED, Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
                nettyCookie = WebSocketHelper.expireCookie(authCookieName, host);

                // Close the connection as soon as the error message is sent.
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                return;
              }
            }

            nettyCookie = WebSocketHelper.createCookie(authCookieName, host, username, authCookieMaxAge);
            message = "{\"response\": \"success\"}";
          } else {
            nettyCookie = WebSocketHelper.expireCookie(authCookieName, host);
            message = "{\"response\": \"fail\"}";
          }
        } catch (Throwable t) {
          logger.error(t.getMessage(), t);
        }
        System.out.println(password);
      } else if (URI_LOGOUT.equals(uri)) {
        if (userName != null) {
          for (Channel channel : channels) {
            String key = channel.attr(WebSocketHelper.getClientAttr()).get();
            if (key.equals(userName)) {
              logger.info(String.format("Logged out, removing client '%s' from channel and removing channel", userName));
              channel.attr(WebSocketHelper.getClientAttr()).set(null);
              channels.remove(channel);
              channel.writeAndFlush(new CloseWebSocketFrame());
              break;
            }
          }
        }

        nettyCookie = WebSocketHelper.expireCookie(authCookieName, host);
        message = "{\"response\": \"success\"}";
      } else {
        message = "{\"response\": \"fail\"}";
      }
      WebSocketHelper.realWriteAndFlush(ctx.channel(), message, "application/json; charset=UTF-8", HttpUtil.isKeepAlive(req), nettyCookie);
      return;
    }

    // Allow only GET methods.
    if (!HttpMethod.GET.equals(method)) {
      sendError(ctx, HttpResponseStatus.FORBIDDEN);
      return;
    }

    // Send the demo page and favicon.ico
    if (URI_EMPTY.equals(uri)) {
      // sendRedirect(ctx, "/index.html");
      return;
    }

    // check for websocket upgrade request
    String upgradeHeader = req.headers().get("Upgrade");
    if (upgradeHeader != null && "websocket".equalsIgnoreCase(upgradeHeader)) {
      // Handshake. Ideally you'd want to configure your websocket uri
      String url = "ws://" + req.headers().get("Host") + "/wsticker";
      WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(url, null, false);
      handshaker = wsFactory.newHandshaker(req);
      if (handshaker == null) {
        WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
      } else {
        ChannelFuture future = handshaker.handshake(ctx.channel(), req);
        future.addListener(new ChannelFutureListener() {
          @Override
          public void operationComplete(ChannelFuture future) throws Exception {
            if (!future.isSuccess()) {
              logger.error("Can't handshake ", future.cause());
              return;
            }

            logger.info(String.format("Authorized, websocket upgrade complete, adding client '%s' to channel and saving channel", userName));
            future.channel().attr(WebSocketHelper.getClientAttr()).set(userName);
            channels.add(future.channel());
          }
        });
      }
    } else {
      if (!handleREST(ctx, req)) {
        // httpFileHandler.sendFile(ctx, req);
      }
    }
  }

  protected void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status + "\r\n", CharsetUtil.UTF_8));
    response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

    // Close the connection as soon as the error message is sent.
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }

}
