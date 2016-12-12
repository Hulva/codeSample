package hulva.luva.learn.UDPStuff;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Hello world!
 *
 */
public class EchoServer extends Thread {

	private DatagramSocket socket;
	private boolean running;
	private byte[] buf;

	public EchoServer() throws SocketException {
		super();
		this.socket = new DatagramSocket(4445);
	}

	public void run() {
		running = true;

		while (running) {
			buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);

				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0,
						packet.getLength());

				System.out.println(received);
				if (received.equals("end")) {
					running = false;
					continue;
				}
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		socket.close();
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
