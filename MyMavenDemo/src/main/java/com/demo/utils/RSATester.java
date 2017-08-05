package com.demo.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class RSATester {
	static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCh0UAzMGJKYmKR5wTVbkTfG+UsdE7WjpVo18tQOkXwuMzlnQ/NPkoTcRr398wvDst/c0pqRax+zXiO/ZcCPVAfvQVPMu8CqOi/QsrcNj5LGKqLYsxBpWy/Qb/Xhxov9lXxsGNFGOlh2dojUJ8IVg0/Y45Q+2XWX8HGJKTlHG4AUQIDAQAB";
	static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKHRQDMwYkpiYpHnBNVuRN8b5Sx0TtaOlWjXy1A6RfC4zOWdD80+ShNxGvf3zC8Oy39zSmpFrH7NeI79lwI9UB+9BU8y7wKo6L9Cytw2PksYqotizEGlbL9Bv9eHGi/2VfGwY0UY6WHZ2iNQnwhWDT9jjlD7ZdZfwcYkpOUcbgBRAgMBAAECgYAJ5CaOeninn1fzHzH2ceUX1jat/vYKu3B6liZBYy5/RmyP5Ifgvdv9UD6bxbPfdIIP6lY0JMokfh7ObsXaRzEsho2mKsi0wprpeCkXIg283XhLIH67RXd8o6wdskCv9BdoR8fM9PK5HldhxVHk3JgsFNXLk46GfalWZSQ5+vNNuQJBANhMVlHs4tT7+mfsV27khVGeZSGaRTsCjApcqarO5AQVqLlFBkuQJCzewj2aJUzFDaXKxWP6Krvke9zVyBcw5gMCQQC/hOh95qy3cdAIlrxNXa0IigCg7sYVoSad+B27rbIkpf8k3ktHjcQP36M78G0Juif2pXvpmEXD09QnsH7YreobAkApiRc2aj/w2GZwmHD9yYJUdw9CkaZju4x63Opz84DTy/j3guq/jfF0cF5qh/RLtrd1M+gN19dm92xVrvPaiDhTAkEApsIrTbTVHsXSxie5vgOa7px6/6XohFzVKycnfIs5Wf/PsWJ9WchutwObNk2aI6Ybtf9RbYxQ8ro3OHfNew2zkQJAG3E62yzK73cF/LlTCm3RIHlMwVTyyF5anYZY8RIgDmYKwi/vK5M3FOfAeXOdb/+7jJWfx9481LUTyvCDDEfCRQ==";

	// static {
	// try {
	// Map<String, Object> keyMap = RSAUtils.genKeyPair();
	// publicKey = RSAUtils.getPublicKey(keyMap);
	// privateKey = RSAUtils.getPrivateKey(keyMap);
	// System.err.println("公钥: \n\r" + publicKey);
	// System.err.println("私钥： \n\r" + privateKey);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static void main(String[] args) throws Exception {
		test();
		// testSign();
	}

	public static String getString(String base64String) {
		byte[] encodedData = Base64.decodeBase64(base64String);
		byte[] decodedData;
		String target = null;
		try {
			decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
			target = new String(decodedData, "UTF-8");
			return target;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

	public static String getString2(String base64String) throws Exception {
		String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJcfyp8pVeXgxTcrulg4KKcjsJOEFwqg2mmaCrzE2wbocqoOKUyCUb7M0VFKPQdOjTBXBuzSfQOdB8EHH+XhOM69BDj1r/A+hvLgDNScaF4wtZRK9PcBIkdJNMZqLTNcSV1sK5Oa+JXrH+IJoWF2PI/Kz7U2fKrgqJvA1qwTQa7PAgMBAAECgYAjhnetPpxQs6voskDV6C22TK9/nfeEqhdt56ZKhcQvNYlu7HFM6P2qap9yCo+aHh8Wk/6uwTReGxXtjG01LuKZacg3BVYJA9cGaUbK0siSjrMYZmI7RpP/Ee4PK1/I8QTKo2DbPzuSAufvhV1vq8rWv+B4DcP3d9qUVZ7bNPKVkQJBANw+4rhDifk6J6pqKtSJP21Y9W2t2GgC8Et7ugUvQB0PP3FP6fUpNgoUQ2g+euWL0f/oR6k/66VMG21W4+kqXY0CQQCvqE93uzDmN6clVLHuGbjp4rNg1wcOHhUwfD7aUx+IxyJDpgNPnwgqysX9qSPC7nKSlfCWtWYAiWWi13jfL4DLAkEApxSZv/mUkNoaP0B9L3DTCiwDO+o3EmiwTEMXUlmPBLyNf3OFY7OYyDqpDErhxoO0krNusanJJUPARsk06qx1/QJAYtiUt2yYDMS+gwxRAAprevm+NT1rU+9rvxf7ioICGcbtKiIudMyqHOJ0XdSjMhtaT9CeTUCQwLaQgf08oGMr1wJABd/t+gBKuHtGCKiLFcEMN28w8CcnjyloCBCXFm3LjCOg98KmVK2l6T83Xc7N+KWc+1I41GNTHTScgTZA0urG4g==";
		// String
		// pubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXH8qfKVXl4MU3K7pYOCinI7CThBcKoNppmgq8xNsG6HKqDilMglG+zNFRSj0HTo0wVwbs0n0DnQfBBx/l4TjOvQQ49a/wPoby4AzUnGheMLWUSvT3ASJHSTTGai0zXEldbCuTmviV6x/iCaFhdjyPys+1Nnyq4KibwNasE0GuzwIDAQAB";
		byte[] keyBytes = Base64.decodeBase64(priKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding", new BouncyCastleProvider());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		StringBuilder sb = new StringBuilder(new String(cipher.doFinal(Hex.decode(base64String)), "UTF-8"));
		return sb.reverse().toString();
	}

	static void test() throws Exception {
		// System.err.println("公钥加密——私钥解密");
		// String source = "甘海强";
		// System.out.println("\r加密前文字：\r\n" + source);
		// byte[] data = source.getBytes();
		// byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
		byte[] encodedData = Base64.decodeBase64(
				"ZHk12Euj9D2NCHZGI2TruZ7agq6e2PXrxxUn+oq4V+vEWwp4cDGubBhV40LVkYaFHV0oEqgVLhqx0L/7xkvbJgp12hPh8j3wOqe+Gm9FFR7BzZvJkytd4M6jNbvSdS7+IQbjnllLckpZUpnWtxfb09Kgs3JIu7ldlNRbZGvSOMA=");
		System.out.println("加密后文字：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
		String target = new String(decodedData);
		System.out.println("解密后文字: \r\n" + target);
	}

	static void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String source = "这是一行测试RSA数字签名的无意义文字";
		System.out.println("原文字：\r\n" + source);
		byte[] data = source.getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		System.out.println("加密后：\r\n" + new String(encodedData));
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		System.out.println("解密后: \r\n" + target);
		System.err.println("私钥签名——公钥验证签名");
		String sign = RSAUtils.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);
		boolean status = RSAUtils.verify(encodedData, publicKey, sign);
		System.err.println("验证结果:\r" + status);
	}
}
