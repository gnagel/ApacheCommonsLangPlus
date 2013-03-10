package org.apache.commons.lang3.encryption;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.support.v4.util.LruCache;


public final class MD5String {
	private static final LruCache<String, String>	values	= new LruCache<String, String>(20);


	public static final String encode(final String value) {
		String output = values.get(value);
		if (null == output) {
			try {
				// Create MD5 Hash
				final MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
				digest.update(value.getBytes());
				final byte messageDigest[] = digest.digest();

				// Create Hex String
				final StringBuffer hexString = new StringBuffer();
				for (final byte element : messageDigest) {
					hexString.append(Integer.toHexString(0xFF & element));
				}
				output = hexString.toString();
			}
			catch (final NoSuchAlgorithmException e) {
				output = org.apache.commons.lang3.encryption.Base64.encodeString(value);
			}
		}

		return output;
	}
}
