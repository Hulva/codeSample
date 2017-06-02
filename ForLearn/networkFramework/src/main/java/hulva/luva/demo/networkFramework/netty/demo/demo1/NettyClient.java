/**
 * 
 */
package hulva.luva.demo.networkFramework.netty.demo.demo1;

import hulva.luva.demo.networkFramework.netty.demo.demo1.handler.AsAClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年2月8日
 *
 */
public class NettyClient {
  public static void main(String[] args) throws Exception {
    String host;
    int port;
    if (args.length > 0) {
      host = args[0];
      port = Integer.parseInt(args[1]);
    } else {
      host = "localhost";
      port = 8080;
    }
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      Bootstrap b = new Bootstrap(); // (1)
      b.group(workerGroup); // (2)
      b.channel(NioSocketChannel.class); // (3)
      b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
      b.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          ch.pipeline().addLast(new AsAClientHandler());
        }
      });

      // Start the client.
      ChannelFuture f = b.connect(host, port).sync(); // (5)

      // Wait until the connection is closed.
      f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }
}
