package com.nsb.practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@SpringBootApplication
@RestController
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/test")
	public String uploadPDFTemplate(
			@RequestParam(value = "fileName", required = false, defaultValue = "tripartite_non-exclusive_contract.pdf") String fileName)
			throws IOException {
		try {
			byte[] copyToByteArray = FileCopyUtils
					.copyToByteArray(ClassUtils.class.getClassLoader().getResourceAsStream(fileName));
			LOGGER.info("file size:{}", copyToByteArray.length);
//			File file = ResourceUtils.getFile("classpath:" + fileName);
//			FileInputStream fileInputStream = new FileInputStream(file);
//			FileChannel channel = fileInputStream.getChannel();
//			ByteBuffer allocate = ByteBuffer.allocate((int) file.getTotalSpace());
//			int read = channel.read(allocate);
//			byte[] copyToByteArray = allocate.array();
			
			FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Dorae\\Desktop\\testme.pdf"));
			FileCopyUtils.copy(copyToByteArray, fileOutputStream);
		} catch (Exception e) {
			LOGGER.error("EsignServiceBridge#uploadPDFTemplate 上传模板文件error! ", e);
		}
		//123
		return "success1";
	}
}
