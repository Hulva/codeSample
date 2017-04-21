package hulva.luva.learn.kafka.server.socketserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年4月20日
 * @description
 *
 */
public class Channel {

    SocketChannel socketChannel = null;

    public void init() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 80));
        Socket socket = socketChannel.socket();
        socket.setKeepAlive(true);
    }
}
