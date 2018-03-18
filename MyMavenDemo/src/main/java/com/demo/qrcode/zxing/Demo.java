package com.demo.qrcode.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Demo {
	private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

	public static void main(String[] args) {
		String path = "D:/zxing.png";
		create(path, 240, 240);
		analyze(path);
	}

	/**
	 * 去除白边
	 * 
	 * @param matrix
	 * @return
	 */
	public static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 1; i < resWidth; i++) {
			for (int j = 1; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

	/**
	 * 生成二维码
	 * 
	 * @param path
	 */
	public static void create(String path, int width, int height) {
		String text = "速度放阿斯顿发生打工的萨嘎是打发打发收到罚单";
		String format = "png";
		EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// hints.put(EncodeHintType.MARGIN, 0);//控制白边的宽度0~4
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			bitMatrix = deleteWhite(bitMatrix);
			Path file = new File(path).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);
		} catch (WriterException | IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void analyze(String path) {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			Result result = formatReader.decode(binaryBitmap, hints);
			LOG.info("解析结果：" + result.getText());
		} catch (IOException | NotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
