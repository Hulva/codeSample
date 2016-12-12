/**
 * 
 */
package hulva.luva.learn.UDPStuff;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * @author lh10
 *
 */
public class JWTStuff {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Key key = MacProvider.generateKey();
		
		String compactJwts = Jwts.builder()
				.setSubject("luva")
				.setAudience("audiece?")
				.setIssuer("Issuer?")
				.claim("name", "Hulva Luva.H")
				.claim("scope", "admins")
				.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
				.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
		
		System.out.println(compactJwts);
		
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwts).getBody().get("name"));
		
//		assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwts).getBody().getSubject().equals("luva");
	}

}
