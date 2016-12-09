/**
 * 
 */
package hulva.luva.learn.UDPStuff;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author lh10
 *
 */
public class EchoClient {

	private DatagramSocket socket;
	private InetAddress address;

	private byte[] buf;

	public EchoClient() throws SocketException, UnknownHostException {
		super();
		this.socket = new DatagramSocket();
		this.address = InetAddress.getByName("localhost");
	}

	public String sendEcho(String msg) throws IOException {
		buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				4445);
		socket.send(packet);
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		return received;
	}

	public void close() {
		socket.close();
	}

}
