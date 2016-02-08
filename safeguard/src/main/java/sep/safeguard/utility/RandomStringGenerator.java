package sep.safeguard.utility;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomStringGenerator {
	
	  private static SecureRandom random = new SecureRandom();

	  public static String getRandomString() {
	    return new BigInteger(130, random).toString(32);
	  }
		
}
