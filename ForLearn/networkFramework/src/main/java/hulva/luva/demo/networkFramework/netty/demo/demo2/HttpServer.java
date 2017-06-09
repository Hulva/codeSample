package hulva.luva.demo.networkFramework.netty.demo.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月7日
 * @description
 *
 */
public class HttpServer {
    private static Logger LOG = LoggerFactory.getLogger(HttpServer.class);

    public void start(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // server 端发送的是 httpResponse， 所以要使用HttpResponseEncoder 进行编码
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            // server 端接收到的是 httpRequest， 所以要用HttpRequestDecoder 进行解码
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new HttpServerInboundHandler());
                        }

                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        System.out.println("Http Server listening on 8844...");
        LOG.info("Http Server listening on 8844...");
        server.start(8844);
    }
}
