package com.demo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.demo.excel.ViewExcel;
import com.demo.pojo.Student;
import com.demo.utils.FreeMarkerUtils;
import com.demo.utils.PropertieUtils;
import com.demo.utils.PropertiesUtil;
import com.demo.utils.RSATester;
import com.demo.utils.UrlMappingUtils;
import com.demo.utils.VerifyCodeUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
@RequestMapping(value = "/stu")
public class DemoController {
	private static final Logger LOGGER = Logger.getLogger(DemoController.class);
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/getAllUrl", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllUrl(HttpServletRequest request) {
		// DefaultAnnotationHandlerMapping
		// mapping=SpringUtils.getBean(DefaultAnnotationHandlerMapping.class);
		// Map<String, Object> map=mapping.getHandlerMap();
		// com.alibaba.fastjson.JSONObject object=new
		// com.alibaba.fastjson.JSONObject();
		// for (Entry<String, Object> en : map.entrySet()) {
		// System.out.println(en.getKey()+"<===>"+en.getValue().getClass());
		// object.put(en.getKey(), en.getValue().getClass());
		// }

		// ServletContext servletContext =
		// request.getSession().getServletContext();
		// if (servletContext == null) {
		// return null;
		// }
		// WebApplicationContext appContext =
		// WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// Map<String, HandlerMapping> allRequestMappings =
		// BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,
		// HandlerMapping.class, true, false);
		// System.out.println(JSON.toJSONString(allRequestMappings, true));
		return UrlMappingUtils.getUrlMap();
	}

	@RequestMapping(value = "/getSource")
	@ResponseBody
	public Object getSource() {
		String enMsg = messageSource.getMessage("name_not_null", new Object[] {}, Locale.US);
		System.out.println("英文环境：" + enMsg);
		String cnMsg = messageSource.getMessage("name_not_null", new Object[] {}, Locale.CHINA);
		System.out.println("中文环境：" + cnMsg);
		return "";
	}

	@RequestMapping(value = "/cas/url")
	@ResponseBody
	public Object showCommandResult(String param, HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println(param);
		System.out.println(URLDecoder.decode(param, "utf-8"));
		System.out.println("getAuthType：" + JSON.toJSONString(request.getParameterMap(), true));
		System.out.println("getMethod：" + request.getMethod());
		System.out.println("getPathInfo：" + request.getPathInfo());
		System.out.println("getRequestURI：" + request.getRequestURI());
		return param;
	}

	@RequestMapping(value = "/execute/{command}")
	public void showCommandResult(@PathVariable("command") String command, HttpServletResponse response) {
		InputStream stream;
		try {
			stream = Runtime.getRuntime().exec(command).getInputStream();
			FileCopyUtils.copy(stream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			try {
				response.getWriter().write(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * ajax上传文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		try {
			MultipartHttpServletRequest mServletRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fMap = mServletRequest.getFileMap();
			for (Entry<String, MultipartFile> e : fMap.entrySet()) {
				MultipartFile file = e.getValue();
				System.out.println(file.getContentType());
				System.out.println(file.getName());
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getSize());
				System.out.println("======================");
			}

			Iterator<String> names = mServletRequest.getFileNames();
			while (names.hasNext()) {
				System.out.println(names.next());
			}

			// MultipartFile file = mServletRequest.getFile("file_name");
			// String extension =
			// FilenameUtils.getExtension(file.getOriginalFilename());
			// if ("xls".equals(extension)) {
			// ExcelUtils.readExcel2003(file.getInputStream());
			// } else if ("xlsx".equals(extension)) {
			// ExcelUtils.readExcel2007(file.getInputStream());
			// }
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void DomainCombiner(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "https://ssom.sit.sf-express.com:8443");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		response.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		try {
			response.getWriter().write("hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getHtml", method = RequestMethod.GET)
	public void getHtml(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter pw = response.getWriter();
			pw.write("function hehe(){alert('呵呵，执行了');}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getProp", method = RequestMethod.GET)
	@ResponseBody
	public Object getProp(String column, String tableName) {
		String value = PropertieUtils.getValue("jdbc.url");
		System.out.println(value);
		return value;
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ModelAndView downExcel(ModelMap model) {
		// Student student=new Student();
		// student.setId(new Random().nextInt(1000));
		// student.setAddress("湖北武汉");
		// student.setAge(25D);
		// student.setName("康有为");
		// student.setPhone("15842365541");
		// student.setPicture("abcefc");
		// student.setSex("男");
		// model.put("student", student);
		// return new ModelAndView(new ExcelView(),model);

		System.out.println(PropertiesUtil.getProperty("jdbc.url"));
		return null;
	}

	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	@ResponseBody
	public Object getSession(HttpSession session) {
		return "";
		// return session.getAttribute("mr");
	}

	@RequestMapping(value = "/getFtl", method = RequestMethod.GET)
	public Object getFtl(HttpServletResponse response) {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("flag", "===================================");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(FreeMarkerUtils.getHTML("test.ftl", rootMap));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ie,chrom,firfox下处理文件名显示乱码
	 * 
	 * @param request
	 * @param fileNames
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String fileNames) {
		String downfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
				String name = java.net.URLEncoder.encode(fileNames, "UTF8");
				downfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				downfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return downfilename;
	}

	@RequestMapping(value = "/downFile")
	public Object Download(HttpServletRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", processFileName(request, "甘海强111.pdf"));
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File("D:\\user\\80001267\\Downloads\\doc\\DCN-V6.8.pdf")),
					headers, HttpStatus.OK);
		} catch (IOException e) {
			LOGGER.error("文件不存在：", e);
			headers.clear();
			headers.setContentType(MediaType.TEXT_HTML);
			return new ResponseEntity<byte[]>("文件不存在！可能已被删除或移动。".getBytes("utf-8"), headers, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/decryptLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request) {
		String user = request.getParameter("user_name");
		String pwd = request.getParameter("user_pwd");
		System.out.println("用户名密文：" + user);
		System.out.println("密    码密文：" + pwd);
		System.out.println("===================解密后==============");
		String user2 = RSATester.getString(user);
		String pwd2 = RSATester.getString(pwd);
		System.out.println("用户名：" + user2);
		System.out.println("密    码：" + pwd2);
		if ("甘海强".equals(user2) && "123456".equals(pwd2)) {
			return user2 + "=======" + pwd2;
		}
		return "验证失败";
	}

	@RequestMapping(value = "/decryptLogin2", method = RequestMethod.POST)
	@ResponseBody
	public Object login2(String user, String pwd) {
		System.out.println("用户名密文：" + user);
		System.out.println("密    码密文：" + pwd);
		System.out.println("===================解密后==============");
		try {
			String user2 = RSATester.getString2(user);
			String pwd2 = RSATester.getString2(pwd);
			user2 = URLDecoder.decode(user2, "UTF-8");
			pwd2 = URLDecoder.decode(pwd2, "UTF-8");
			System.out.println("用户名解码：" + user2);
			System.out.println("密    码解码：" + pwd2);
			return user2 + "=======" + pwd2;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getForm", method = RequestMethod.POST)
	@ResponseBody
	public Object getForm(MultipartHttpServletRequest request) {
		Map<String, String[]> paramsMap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<String, String[]> entry : paramsMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		System.out.println("====================================");
		System.out.println("请求地址：" + request.getRequestURI());
		System.out.println("请求参数：");
		for (Entry<String, Object> sEntry : map.entrySet()) {
			System.out.println(sEntry.getKey() + "==>" + sEntry.getValue());
		}

		Map<String, MultipartFile> files = request.getFileMap();
		for (Entry<String, MultipartFile> file : files.entrySet()) {
			MultipartFile mFile = file.getValue();
			System.out.println(file.getKey() + ">>>>>>>>>" + mFile.getOriginalFilename());
		}

		// Iterator<String> names = request.getFileNames();
		// while (names.hasNext()) {
		// MultipartFile file = request.getFile(names.next());
		// System.out.println(file.getOriginalFilename());
		// }
		return map;
	}

	@RequestMapping(value = "/getAjaxForm", method = RequestMethod.POST)
	@ResponseBody
	public String getAjaxForm(HttpServletRequest request) {
		Map<String, String[]> paramsMap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<String, String[]> entry : paramsMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		System.out.println("====================================");
		System.out.println("请求地址：" + request.getRequestURI());
		System.out.println("请求参数：");
		for (Entry<String, Object> sEntry : map.entrySet()) {
			System.out.println(sEntry.getKey() + "==>" + sEntry.getValue());
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping("/excel")
	public ModelAndView viewExcel(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		model.put("list", getStudents());
		return new ModelAndView(new ViewExcel(), model);
	}

	private List getStudents() {
		List stuList = new ArrayList();
		// 构造数据
		Student stu1 = new Student(111, "gaoxiang1", "m", 22, "20060101", "xxxxxxxxxxxxxx", "--");
		Student stu2 = new Student(111, "gaoxiang2", "m", 22, "20060101", "xxxxxxxxxxxxxx", "--");
		Student stu3 = new Student(111, "gaoxiang3", "f", 22, "20060101", "xxxxxxxxxxxxxx", "--");
		Student stu4 = new Student(111, "gaoxiang4", "f", 22, "20060101", "xxxxxxxxxxxxxx", "--");
		Student stu5 = new Student(111, "gaoxiang5", "f", 22, "20060101", "xxxxxxxxxxxxxx", "--");
		stuList.add(stu1);
		stuList.add(stu2);
		stuList.add(stu3);
		stuList.add(stu4);
		stuList.add(stu5);
		return stuList;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public String test() {
		return " hello springMVC";
	}

	@RequestMapping(value = "/error")
	public ModelAndView errorPage(ModelAndView mv) {
		mv.setViewName("error");
		mv.addObject("user", "sfit1267");
		return mv;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllStudent(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		String sex = request.getParameter("sex");
		/************************************************************************/
		Map map = new HashedMap();
//		long count = studentService.getCount();
//		long pageSize = Integer.valueOf(rows);
//		long total = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//		long pageNo = Integer.valueOf(page);
//		pageNo = pageNo > total ? total : pageNo;
//		long pageStart = pageSize * (pageNo - 1);
//		LOGGER.info("数据总数：" + count + "，页面条数：" + pageSize + "，总页数：" + total + "，当前页码：" + pageNo);
//		map.put("pageSize", pageSize);
//		map.put("pageStart", pageStart);
//		map.put("sidx", sidx);
//		map.put("sord", sord);
//		map.put("sex", sex);
//		List list = studentService.getByPage(map);
//		map.put("page", pageNo);
//		map.put("total", total);
//		map.put("records", count);
//		map.put("rows", list);
		return map;
	}

	@RequestMapping(value = "/getImg", method = RequestMethod.GET)
	public void getGP(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		try {
			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(750, 30, BufferedImage.TYPE_INT_RGB);
			int per = 0;
			per = Integer.parseInt(request.getParameter("size"));// 接收参数，表示进度
			per = ServletRequestUtils.getIntParameter(request, "size", 0);
			Graphics graphics = image.getGraphics();
			graphics.setColor(Color.green);
			graphics.fillRect(0, 0, 750, 30);
			graphics.setColor(Color.yellow);
			graphics.fillRect(0, 0, 750 * per / 100, 30);
			ImageIO.write(image, "jpg", out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getPic", method = RequestMethod.GET)
	public void getPic(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		try {
			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(750, 300, BufferedImage.TYPE_INT_RGB);
			// int per = 0;
			// per = Integer.parseInt((String) request.getParameter("size"));//
			// 接收参数，表示进度
			Graphics graphics = image.getGraphics();
			graphics.setColor(Color.green);
			graphics.fillRect(0, 0, 750, 30);
			graphics.setColor(Color.yellow);
			graphics.fillRect(0, 0, 700, 300);
			graphics.drawLine(0, 0, 80, 80);
			ImageIO.write(image, "jpg", out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getBarcode", method = RequestMethod.GET)
	public void getBarcode(HttpServletRequest request, HttpServletResponse response) {
		String keycode = request.getParameter("keycode");
		if (keycode != null && !"".equals(keycode)) {
			ServletOutputStream out = null;
			try {
				Code128Writer writer = new Code128Writer();
				int width = 280;
				int height = 60;
				String mwidth = request.getParameter("mwidth");
				if (mwidth != null && !"".equals(mwidth.trim())) {
					try {
						width = Integer.valueOf(mwidth);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				String mheight = request.getParameter("mheight");
				if (mheight != null && !"".equals(mheight.trim())) {
					try {
						height = Integer.valueOf(mheight);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				out = response.getOutputStream();
				BitMatrix m = writer.encode(keycode, BarcodeFormat.CODE_128, width, height);
				MatrixToImageWriter.writeToStream(m, "png", out);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@RequestMapping(value = "/getBarcode2D", method = RequestMethod.GET)
	public void getBarcode2D(HttpServletRequest request, HttpServletResponse response) {
		String keycode = request.getParameter("keycode");
		if (keycode != null && !"".equals(keycode)) {
			ServletOutputStream out = null;
			try {
				int size = 140;
				String msize = request.getParameter("msize");
				if (msize != null && !"".equals(msize.trim())) {
					try {
						size = Integer.valueOf(msize);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				out = response.getOutputStream();
				QRCodeWriter writer = new QRCodeWriter();
				BitMatrix m = writer.encode(keycode, BarcodeFormat.QR_CODE, size, size);
				MatrixToImageWriter.writeToStream(m, "png", out);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@RequestMapping(value = "/getVerify", method = RequestMethod.GET)
	public void getQQVerify(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		session.setAttribute("rand", verifyCode.toLowerCase());
		// 生成图片
		int w = 200, h = 80;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		int width = 70, height = 18;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		g.setColor(getRandColor(160, 200));

		// 画随机线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(String.valueOf(ctmp), 15 * i + 7, 16);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("rand", sRand);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
