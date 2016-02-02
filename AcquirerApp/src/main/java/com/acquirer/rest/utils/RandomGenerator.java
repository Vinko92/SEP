package com.acquirer.rest.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomGenerator {

	private SecureRandom random = new SecureRandom();
	
	public String generateAntiForgeryToken()
	{
		return new BigInteger(130, random).toString(32);
	}
}
