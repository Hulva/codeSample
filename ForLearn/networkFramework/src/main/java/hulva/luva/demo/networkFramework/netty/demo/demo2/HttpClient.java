package hulva.luva.demo.networkFramework.netty.demo.demo2;

import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月7日
 * @description
 *
 */
public class HttpClient {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 客户端接收到的是httpResponse 响应，所以使用HttpResponseDecoder 进行解码
                            ch.pipeline().addLast(new HttpResponseDecoder());
                            // 客户端发送的是httpRequest ,所以使用 HttpRequestEncoder 进行编码
                            ch.pipeline().addLast(new HttpRequestEncoder());
                            ch.pipeline().addLast(new HttpClientInboundHandler());
                        }

                    });
            // Start the client
            ChannelFuture future = bootstrap.connect(host, port).sync();
            URI uri = new URI("http://127.0.0.1:8844");
            String msg = "Are you OK?";
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.GET, uri.toASCIIString(),
                    Unpooled.wrappedBuffer(msg.getBytes(CharsetUtil.UTF_8)));
            // 构建Http 请求
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH,
                    request.content().readableBytes());
            // 发送 http请求
            future.channel().write(request);
            future.channel().flush();
            future.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }


    public static void main(String args[]) throws Exception {
        HttpClient client = new HttpClient();
        client.connect("127.0.0.1", 8844);
    }
}
