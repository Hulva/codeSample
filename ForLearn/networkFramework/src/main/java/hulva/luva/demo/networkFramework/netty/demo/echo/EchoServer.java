package hulva.luva.demo.networkFramework.netty.demo.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月2日
 * @description
 *
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        super();
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]); // 可能抛出 NumberFormatException
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group).channel(NioServerSocketChannel.class) // 指定使用Nio的传输Channel
                .localAddress(new InetSocketAddress(port)) // 设置 socket端口
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler()); // 添加EchoServerHandler
                                                                        // 到Channel的ChannelPipeline
                    }

                });

        try {
            ChannelFuture future = bootstrap.bind().sync(); // 创建一个新的Channel并绑定它；
            System.out.println(EchoServer.class.getName() + " started and listen on "
                    + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
