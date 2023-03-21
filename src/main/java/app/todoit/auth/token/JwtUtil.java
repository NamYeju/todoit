package app.todoit.auth.token;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.todoit.auth.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

	private final Key key;
	private final static long ACCESS_TOKEN_EXPIRES = 1000L * 60 * 60 * 24; // 하루
	private final static long REFRESH_TOKEN_EXPIRES = 1000L * 60 * 60 * 24 * 14; // 2주

	public final static String ACCESS_TOKEN = "atk";
	public final static String REFRESH_TOKEN = "rtk";

	public JwtUtil(@Value("${jwt.secret-key}") String SECRET_KET){
		String base64EncodedSecretKey = Base64.getEncoder().encodeToString(SECRET_KET.getBytes());
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKey));
	}

	/** atk 발급 */
	public String generateAccessToken(User user){

		return Jwts.builder()
			.setSubject(user.getId().toString())
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES))
			.claim("type", ACCESS_TOKEN)
			.claim("email", user.getEmail())
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	/** rtk 발급 */
	public String generateRefreshToken(User user){ 

		return Jwts.builder()
			.setSubject(user.getId().toString())
			.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES))
			.claim("type", REFRESH_TOKEN)
			.claim("email", user.getEmail())
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	/** 토큰검증 */
	public boolean validateToken(String token) {
		try {
			JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
			parser.parseClaimsJws(token);
			return true;
		}catch (SignatureException e){
			log.info("Invalid JWT Signature ", e);
		}catch (MalformedJwtException e){
			log.info("Invalid JWT token ", e);
		}catch (ExpiredJwtException e){
			log.info("Expired JWT token ", e);
		}catch (UnsupportedJwtException e){
			log.info("Unsupported JWT token ", e);
		}catch (IllegalArgumentException e){
			log.info("Jwt claims are Empty ", e);
		}catch (IllegalStateException e){

		}
		return false;
	}
}
