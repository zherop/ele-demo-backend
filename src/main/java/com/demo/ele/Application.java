package com.demo.ele;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

/**
 * @author 曾鹏
 * @mail zp@zving.com
 * @date 2018年4月22日
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		URL classPath = ResourceUtils.getURL("classpath:");
		String dbPath = new File(classPath.getPath()).getAbsolutePath() + File.separator + "demodb";
		if ("jar".equals(classPath.getProtocol())) {
			dbPath = new File("classes/demodb").getAbsolutePath();
		}
		System.setProperty("demodb", dbPath);
		SpringApplication.run(Application.class, args);
	}
}
