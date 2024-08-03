package keys.generators;

import java.util.Base64;

import org.apache.tomcat.util.buf.HexUtils;

/**
 * 
 * Initial Data:     Metaphorce2024
 * Hexadecimal Data: 4d65746170686f72636532303234
 * Encoded Data:     NGQ2NTc0NjE3MDY4NmY3MjYzNjUzMjMwMzIzNA==
 * Decoded Data:     4d65746170686f72636532303234
 * Recovered Data:   Metaphorce2024
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class StrEncoders {

	private static String jwtSecret = "Metaphorce2024";
	
	public static void main(String args[]) throws Exception {
		
		System.out.println("Initial Data:     " + jwtSecret);
		
		String strToHex = HexUtils.toHexString( jwtSecret.getBytes() );
		System.out.println("Hexadecimal Data: " + strToHex);

		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString( strToHex.getBytes() );
		System.out.println("Encoded Data:     " + encoded);

		Base64.Decoder decoder = Base64.getDecoder();
		String decoded = new String(decoder.decode(encoded));
		System.out.println("Decoded Data:     " + decoded);
		
		byte[] bytesFromHex = HexUtils.fromHexString( decoded );
		jwtSecret = new String( bytesFromHex );
		System.out.println("Recovered Data:   " + jwtSecret);
		
	}
	
}
