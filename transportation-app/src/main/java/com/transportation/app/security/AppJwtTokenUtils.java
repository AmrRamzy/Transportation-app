package com.transportation.app.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author 
 *
 */
public class AppJwtTokenUtils {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "iat";

	private static String secret;
	// time in seconds
	private static Long expiration ;
	private static String secretFilePath;
	
	static{
		secretFilePath=loadProperties("jwt.secret.properties.file","application.properties");
		secret=loadProperties("jwt.secret",secretFilePath);
		expiration=Long.valueOf(loadProperties("jwt.expiration","application.properties"));
	}

	/**
	 * @param authentication
	 * @return
	 */
	public static String generateToken(Authentication authentication) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);

		Claims claims = Jwts.claims().setSubject(authentication.getName());

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * @param token
	 * @return
	 */
	public static boolean validateToken(String token) {
		boolean valid = false;
		try {
			Claims claim = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			valid = claim != null && !isTokenExpired(token) ? true : false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}

	/**
	 * @param token
	 * @return
	 */
	public static String getUsernameFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.getSubject();
	}

	/**
	 * @param token
	 * @return
	 */
	public static Date getIssuedAtDateFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.getIssuedAt();
	}

	/**
	 * @param token
	 * @return
	 */
	public static Date getExpirationDateFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.getExpiration();
	}

	/**
	 * @param token
	 * @return
	 */
	public static List<String> getPermissions(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return (List<String>) claims.get("permissions");
	}
	
	/**
	 * @param token
	 * @return
	 */
	public static List<String> getRoles(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return (List<String>) claims.get("roles");
	}

	/**
	 * @param token
	 * @param claimsResolver
	 * @return
	 */
	public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	private static Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration!=null && expiration.before(new Date());
	}

	/**
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private static Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration * 1000);
	}
	
	/**
	 * @param key
	 * @param propertiesFile
	 * @return
	 */
	public static String loadProperties(String key,String propertiesFile) {
        Properties configuration = new Properties();
        InputStream inputStream = AppJwtTokenUtils.class.getClassLoader()
          .getResourceAsStream(propertiesFile);
       
			try {
				configuration.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		
        
        return configuration.getProperty(key);
    }
}
