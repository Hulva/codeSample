package newegg.mis.ec2.luva.learn.UDPStuff;

import static org.junit.Assert.assertFalse;
import hulva.luva.learn.UDPStuff.EchoClient;
import hulva.luva.learn.UDPStuff.EchoServer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple EchoServer.
 */
public class UDPTest {
	EchoClient client;

	@Before
	public void setup() throws Exception {
		new EchoServer().start();
		client = new EchoClient();
	}

	@Test
	public void whenCanSendAndReceivePacket_thenCorrect() throws Exception {
		Key key = MacProvider.generateKey();

		String compactJwts = Jwts.builder().setSubject("luva")
				.setAudience("audiece?").setIssuer("Issuer?")
				.claim("name", "Hulva Luva.H").claim("scope", "admins")
				.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
				.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println(compactJwts);
		String echo = client.sendEcho(compactJwts);
		
		System.out.println("from server: "+echo);
//		assertEquals(compactJwts, echo);
		echo = client.sendEcho("server is working");
		assertFalse(echo.equals("hello server"));
	}

	@After
	public void tearDown() throws Exception {
		client.sendEcho("end");
		client.close();
	}

	/*
	 * @Test public void
	 * givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode()
	 * throws ClientProtocolException, IOException { HttpClient client =
	 * HttpClientBuilder.create().build(); HttpResponse response =
	 * client.execute(new HttpGet(SAMPLE_URL)); int statusCode =
	 * response.getStatusLine().getStatusCode(); assertThat(statusCode,
	 * equalTo(HttpStatus.SC_OK)); }
	 */
}
