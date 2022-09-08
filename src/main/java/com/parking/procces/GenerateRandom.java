package com.parking.procces;

import org.springframework.stereotype.Component;

@Component
public class GenerateRandom {
	// function to generate a random string of length n
	public String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (alphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public String getNumericString(int n) {
		// chose a Character random from this String
		String numericString = "0123456789";

		// create StringBuffer size of NumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to NumericString variable length
			int index = (int) (numericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(numericString.charAt(index));
		}

		return sb.toString();
	}

}
