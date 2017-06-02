/**
 * 
 */
package hulva.luva.demo.networkFramework.netty.demo.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hulva.luva.demo.networkFramework.netty.demo.demo1.handler.DemoHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年2月8日
 *
 */
public class NettyServer {

  private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
  private static int PORT = 8080;

  public static void main(String args[]) throws Exception {
    if (args.length > 0) {
      PORT = Integer.parseInt(args[0]);
    }
    // Configure the server.
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          ChannelPipeline p = ch.pipeline();
          p.addLast("encoder", new HttpResponseEncoder());
          p.addLast("decoder", new HttpRequestDecoder());
          p.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
          p.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
          p.addLast("aggregator", new HttpObjectAggregator(65536));
          p.addLast("handler", new DemoHandler());
        }
      });

      // Start the server.
      ChannelFuture f = b.bind(PORT).sync();
      logger.info("Server started...");

      // Wait until the server socket is closed.
      f.channel().closeFuture().sync();
    } finally {
      logger.info("Server shutdown...");
      // Shut down all event loops to terminate all threads.
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
      logger.info("Server shutdown completed!");
    }
  }
}
