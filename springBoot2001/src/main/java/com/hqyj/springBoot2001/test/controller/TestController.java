package com.hqyj.springBoot2001.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.springBoot2001.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/getAppDesc")
	@ResponseBody
	public String getAppDesc() {
		return "Hello,this is my first create SpringBoot project!";
	}

	/* 读取全局配置文件的信息 */
	@Value("${com.config.login_name}")
	private String login_name;
	@Value("${com.config.password}")
	private int password;

	@RequestMapping("/getConfigTest")
	@ResponseBody
	public String getConfigTest() {
		String username = login_name;
		int psd = password;
		return username + "----" + psd;
	}

	/* 读取非全局配置文件的信息 */
	@Autowired//加载配置类
	private ConfigBean configBean;
	
	/**
	 * 文件下载
	 * localhost/test/download
	 */
	@RequestMapping("/download")
	public ResponseEntity<Resource> downLoadFile(@RequestParam String fileName){
		
		try {
			Resource resource = new UrlResource(Paths.get("F:\\Download\\" + fileName).toUri());
			//包装状态值
			return ResponseEntity.ok()
					//包装响应头信息
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, 
							String.format("attachment; filename=\"%s\"", resource.getFilename()))
					//包装文件信息
					.body(resource);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "F:/Download" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
					String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "F:/Download" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

	@RequestMapping("/getConfigBeanTest")
	@ResponseBody
	public String getConfigBeanTest() {
		String name = configBean.getName();
		String psd = configBean.getPsd();
		String sex = configBean.getSex();
		int age = configBean.getAge();
		return "姓名：" + name + "<br>" + "密码：" + psd + "<br>" + "性别：" + sex + "<br>" + "年龄：" + age + "<br>";
	}

	/* 测试日志文件 */
	// 1、引入Logger，注意jar包：org.slf4j.Logger;
	// 2、变量名称全大写，如：LOGGER
	// 3、LoggerFactory 注意jar包：org.slf4j.LoggerFactory;
	// 4、getLogger(当前类.class)
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/getLogTest")
	@ResponseBody
	public String getLogTest() {
		/*
		 * 	level: TRACE<DEBUG<INFO<WARN<ERROR 
		 * 	我们在 appender file 的时候，并没有指定日志级别，在此由 root来控制，输出指定级别及之上级别的日志
		 */
		LOGGER.trace("this is TRACE log.");
		LOGGER.debug("this is DEBUG log.");
		LOGGER.info("this is INFO log.");
		LOGGER.warn("this is WARN log.");
		LOGGER.error("this is ERROR log.");
		return "this is log test!!!";
	}
}
