package com.dialer.contactschecker.util;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


/**
 * 
 * @author Rafik
 *
 */
public class AppEncrypt implements Serializable {

	private static final long serialVersionUID = -1291851570437546382L;

	private static final String COMMON_ENCRYPT_KEY = "6|d3of6[&5]wI.^j!wpid96N!{0YG!0I";

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	private Cipher cipher;
	private SecretKey key;

	public AppEncrypt() {
		this(null);
	}
	
	public AppEncrypt(String _myEncryptionKey) {
		try {
			String myEncryptionKey = _myEncryptionKey == null ? COMMON_ENCRYPT_KEY : _myEncryptionKey;
			String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
			byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
			cipher = Cipher.getInstance(myEncryptionScheme);
			key = skf.generateSecret(ks);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.getEncoder().encode(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	
	public static String encryptDefault(String unencryptedString) {
		return new AppEncrypt().encrypt(unencryptedString);
		
	}
	
	public static String decryptDefault(String encryptedString) {
		return new AppEncrypt().decrypt(encryptedString);
	}

	public static void main(String args[]) throws Exception {

		process("root");
		/*process("Mentat2018!");
		process("MentatUser#2018");
		process("7EGxh9");
		process("FjkaA667");*/
		
	}
	
	public static void process(String value) {
		String result = AppEncrypt.encryptDefault(value);
		//System.out.println("String To Encrypt: " + value + " : " + result);
	}

}
