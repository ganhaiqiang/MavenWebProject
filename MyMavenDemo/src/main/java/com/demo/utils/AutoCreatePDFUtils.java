package com.demo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class AutoCreatePDFUtils {

	protected final static Logger logger = LoggerFactory.getLogger(AutoCreatePDFUtils.class);

	/**
	 * 根据PDF模板(表单)生成PDF文档
	 * 
	 * @param dataMap
	 *            PDF数据填充Map
	 * 
	 * @param tempatePath
	 *            PDF模板路径
	 * 
	 * @param outPutFilePath
	 *            生成的PDF输出路径
	 */
	public static void createPDFWithTemplate(Map<String, String> dataMap, String tempatePath, String outPutFilePath) {

		PdfReader reader = null;
		PdfStamper ps = null;
		FileOutputStream outPDFStream = null;
		BaseFont bf = null;
		AcroFields form = null;

		try {

			reader = new PdfReader(tempatePath);

			//createOutPutDir(outPutFilePath);

			outPDFStream = new FileOutputStream(outPutFilePath);

			ps = new PdfStamper(reader, outPDFStream);

			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

			form = ps.getAcroFields();

			Iterator<Entry<String, String>> iter = dataMap.entrySet().iterator();

			while (iter.hasNext()) {

				Entry<String, String> entry = iter.next();
				String key = entry.getKey();
				Object val = entry.getValue();
				form.setFieldProperty(key, "textfont", bf, null);
				form.setField(key, String.valueOf(val));
			}

			ps.setFormFlattening(true);

		} catch (FileNotFoundException e) {

			logger.error("AutoCreatePDFUtils fail : " + e.getMessage());

		} catch (DocumentException e) {

			logger.error("AutoCreatePDFUtils fail : " + e.getMessage());

		} catch (IOException e) {

			logger.error("AutoCreatePDFUtils fail : " + e.getMessage());
		} finally {
			try {
				if (ps!=null) {
					ps.close();
				}
			} catch (DocumentException e) {
				logger.error("AutoCreatePDFUtils fail : " + e.getMessage());
			} catch (IOException e) {
				logger.error("AutoCreatePDFUtils fail : " + e.getMessage());
			}
			reader.close();
		}

	}

	public static void createOutPutDir(String outPutFilePath) {

		File outFile = new File(outPutFilePath);

		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}

		if (!outFile.exists()) {
			try {
				outFile.createNewFile();
			} catch (IOException e) {
				logger.error("AutoCreatePDFUtils fail : " + e.getMessage());
			}
		}
	}
}
