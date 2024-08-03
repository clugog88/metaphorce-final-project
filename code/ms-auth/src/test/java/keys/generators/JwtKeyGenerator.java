package keys.generators;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Algorithm: HmacSHA384
 * Secret:    Metaphorce2024
 * Message:   Ing. Christhian Lugo Govea
 * Encoded:   ipTr1ALshTEaepV0eQRaQh8FTfABk5FKj4M57bLTs1EPIH1Ro6AIjzTKWy94mJBF 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class JwtKeyGenerator {

	private static String ALGORITHM  = "HmacSHA384";
	
	private static String secret  = "Metaphorce2024";
	private static String message = "Ing. Christhian Lugo Govea";

	public static void main(String args[]) throws Exception {

		Mac hmacSHA256 = Mac.getInstance( ALGORITHM );
		SecretKeySpec secretKeySpec = new SecretKeySpec( secret.getBytes(), ALGORITHM);
		hmacSHA256.init( secretKeySpec );
		byte[] signatureBytes = hmacSHA256.doFinal( message.getBytes() );
		
		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString( signatureBytes );
		System.out.println("Encoded Data: " + encoded);
		
	}

}
