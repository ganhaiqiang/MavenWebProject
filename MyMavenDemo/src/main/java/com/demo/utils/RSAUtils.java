package com.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	public static void main(String[] args) {
		try {
			FileInputStream inpriv = new FileInputStream("E:\\openssl\\bin\\rsa_private_key.pem");
			byte[] bufferPriv = new byte[inpriv.available()];
			inpriv.read(bufferPriv);
			inpriv.close();

			KeyFactory constructor_claves = KeyFactory.getInstance("RSA");
			KeySpec clave_raw = new PKCS8EncodedKeySpec(bufferPriv);

			RSAPrivateKey privateKey2 = (RSAPrivateKey) constructor_claves.generatePrivate(clave_raw);
			System.out.println(privateKey2.getModulus());
			System.out.println(privateKey2.getPrivateExponent());

			// Map<String, Object> key=genKeyPair();
			// for (Entry<String, Object> entry : key.entrySet()) {
			// System.out.println(entry.getKey()+"===>"+entry.getValue());
			// }
			// RSAPublicKey pubKey=(RSAPublicKey) key.get(PUBLIC_KEY);
			// System.out.println("rsa_e"+pubKey.getPublicExponent().toString(16));
			// System.out.println("rsa_m"+pubKey.getModulus().toString(16));
			// System.out.println("私钥："+getPrivateKey(key));
			// System.out.println("公钥："+getPublicKey(key));
			// ===============================
			//
			// byte[]
			// bs=decryptByPrivateKey("f0mMtPLx75UnCkFQuI35xs0SZgtTT/KcP794941pTdthKxYRMRH8AzO1+02zIupf8Fc+dM332cxA1CyUFLEroTRJ6byr7uFcqPBOMsOGRnJdJeYloF37xbfq5bWQgY3lRImoUo//LTX9v0HYpn6NYRTy/e7+RGqRtpnuEx003+k=".getBytes(),
			// "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJhtPaDTftPLtRJYfW1hf2fMx8/MIfvt5NB1D5TL01tv4+eqM7TKoyHJRY/2KzlNVxAdKp34culhSOxRbp/3mplJ3gD/eR7dAtOj/U4qCjRUMCHRw8WDjsGk03qRzxR4lhHZo6uTYiRow1tidlxUoJB8oQtiZTDAYr3Mgfrmq1g5AgMBAAECgYBJ8v2qL9FhCXxAeUPotBtujXAHBT8bjU+sPo6fnr9cpg0IkI9jT8gTFSW1cTWJKFdrzuqlFysQxIzvcZZc73BF21/7VK/HLutyGaBiaZjUTvx2sLmqVchwYEyaAHWa+fH8rgGK2vHRyjqW+0ld6qJhRZiIIAVRGycWTOqByZAfHQJBANq+Cw9nKDgp5nFHtKGD9CWAIN4T9RKeEAtfhn7qVeOPWj37tcW/Ug6Oo86KPwU0+C6el4NLx+lLzjW0BcmpJP8CQQCyY5ipss+YoMhgrASelXPubf/dCOmbV3Z8oOMJQkrR8+2HjrxV0KU2/klmp03WTEh5N0gClDoxkEsxH2bPImrHAkA2kWQ28ngBEsrPBaFVDZLxa/ZHEFl5zH6aJ7londLRUuijP9DHxQOWaBULKEeyZ9Vl4+BOO7BiTavij8dF2HFHAkAPG1wcUuY9uK//b9HcVC/DK1GVuGGAJEIGf+F6MUEA0rV022ObqNW0+Ifix5xR35frnADfJwpelCep+0zLkdCVAkBfnt7rgutcmD2f7wRkpVvtYQlzIWzRYKLW0mcYyTJR0eMqUx3TykdGgOr2bYxCEQqagrA3JOpR4Y41dKQUhnXT");
			// System.out.println(new String(bs));

			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI0cTXMyzZIXnMu39SBiU0clCMIXO70npgqsjkDq2zIuoKSnFhNVpv2gLMoyAO+gWbgwVHTsrIhN9WYIiCIHv+5Nao6utrbOb6p30A59VRQTykzMqnhPOxEmW0eY9DzWdPyxG0n3IsKI3y5WYkcIurU5eIwwJJn6abTQ4omNm1TwIDAQAB";
			String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIjRxNczLNkhecy7f1IGJTRyUIwhc7vSemCqyOQOrbMi6gpKcWE1Wm/aAsyjIA76BZuDBUdOysiE31ZgiIIge/7k1qjq62ts5vqnfQDn1VFBPKTMyqeE87ESZbR5j0PNZ0/LEbSfciwojfLlZiRwi6tTl4jDAkmfpptNDiiY2bVPAgMBAAECgYBuI5d2uV6QCC+5jPJIgGPw3VlUBRXai+wx5ynC1MIlo6SN1jGBVGbSBckdHPAQvlpHPui/54C8fKIhMaKSRj4p3Hiv3wYW+TCXYjdfncTllr8TC/7TJl0FReNaSiQoG9VMjUUBWdDqBTzx0ayJGW7RsUEXYTC+0FpX4R0A2FinMQJBAMAMsNEPBxhm5WuQUdSnOD9ghMfVuW+6SxJnSfyxFYizZRsIeUqTfwmo5a/CRKSdBFiwxKUwnAawuHETuxfjc7cCQQC2YPhv7wGBBgbd8FpncK0ddRHtc8HnyQDSf8pv/MOxwSXA63p8ol73cw7RSj72dthRvd8b9cBIpGcmKHLORzspAkEArxXizXWJ3nFQjzPPprOc0ArKgvUSTg3lCy4ngbxgCXSjpHWhPqAhnUjhvc66jeioYcMmRAOA+4h5Xn3CgBDZGQJAaKbAKEq3TDgPHK9c0NphG/Qfc2fnLn+yjxdxLE2YZZl1dnwo7velEszdBc9azcZoXo9bRQoBai2cWtL+/cr18QJBAKN0BcuOq5IVXLZ2ZDAl8kiRImWVWugtyxxIr92DbUhYwyX5dGygU344es3hg2WETPks2yLDmACdhwiwtzOfW9Q=";
			// byte[] encryptedData=encryptByPublicKey("甘海强".getBytes(),
			// publicKey);
			byte[] encryptedData = encryptByPrivateKey("甘海强".getBytes(), privateKey);
			String sign = sign(encryptedData, privateKey);
			System.out.println(sign);
			//
			boolean flag = verify(encryptedData, publicKey, sign);
			System.out.println("公钥验证签名：" + flag);
			// byte[] decrypt=decryptByPrivateKey(encryptedData, privateKey);
			// System.out.println(StringUtils.newStringUtf8(decrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}

	/**
	 * <p>
	 * 私钥签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密私钥
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey2);
		signature.update(data);

		return Base64.encodeBase64String(signature.sign());
	}

	/**
	 * <p>
	 * 公钥校验签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密公钥
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取公钥匙对象
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey2);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(Base64.decodeBase64(sign));
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}

	public static String sign(String pfxFile, String pfxPwd, String str, String signature, String coding) {
		try {
			// 获取pfx文件的prikey
			Security.addProvider(new BouncyCastleProvider());
			KeyStore e = KeyStore.getInstance("PKCS12", "BC");
			FileInputStream fis = new FileInputStream(pfxFile);
			e.load(fis, pfxPwd.toCharArray());
			Enumeration aliases = e.aliases();
			String keyAlias = null;
			PrivateKey priKey = null;
			if (aliases != null) {
				while (aliases.hasMoreElements()) {
					keyAlias = (String) aliases.nextElement();
					priKey = (PrivateKey) e.getKey(keyAlias, pfxPwd.toCharArray());
					if (priKey != null) {
						break;
					}
				}
			}
			// 使用Signature加密
			Signature signet = Signature.getInstance(signature);
			signet.initSign(priKey);
			signet.update(str.getBytes(coding));
			byte[] signed = signet.sign();
			String strSign = new String(Base64.decodeBase64(signed));
			return strSign;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
		// System.out.println(sign("C://xxx.pfx","123456","123","MD5withRSA","UTF-8"));
	}


	// cer公钥代码：
	public void cer(String msg, String check) {
		// 通过cer文件获取到publickey
		String e = "c://xxx.cer";
		// logger.debug("公钥证书路径："+e);
		try {
			FileInputStream in = new FileInputStream(e);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cac = cf.generateCertificate(in);
			PublicKey pubKey = cac.getPublicKey();
			in.close();
			// 通过pubkey加密
			Signature signetcheck = Signature.getInstance("MD5withRSA");
			signetcheck.initVerify(pubKey);
			signetcheck.update(Base64.decodeBase64(msg));
			// 验证是否一样
			if (!signetcheck.verify(Base64.decodeBase64(check))) {
				throw new Exception("VerifySignatureError");
			}
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (CertificateException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (SignatureException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
