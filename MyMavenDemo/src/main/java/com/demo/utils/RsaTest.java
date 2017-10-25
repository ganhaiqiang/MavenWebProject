package com.demo.utils;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;

public class RsaTest {
	public static void main(String[] args) throws IOException {
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(2048);
			KeyPair pair = gen.generateKeyPair();
			// rsa生成一对公私钥
			PublicKey publicKey = pair.getPublic();
			System.out.println("公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));
			PrivateKey privateKey = pair.getPrivate();
			System.out.println("私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));
			// SHA1withRSA算法进行签名
			Signature sign = Signature.getInstance("SHA1withRSA");
			sign.initSign(privateKey);
			byte[] data = "需要签名的字符串".getBytes("UTF-8");
			// 更新用于签名的数据
			sign.update(data);
			byte[] signature = sign.sign();
			Signature verifySign = Signature.getInstance("SHA1withRSA");
			verifySign.initVerify(publicKey);
			// 用于验签的数据
			verifySign.update(data);
			boolean flag = verifySign.verify(signature);
			System.out.println(flag);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
