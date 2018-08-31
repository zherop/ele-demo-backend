package com.demo.ele;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 曾鹏
 * @mail zp@zving.com
 * @date 2018年4月22日
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		String dbPath = new File("appdata/demodb").getAbsolutePath();
		System.setProperty("demodb", dbPath);
		SpringApplication.run(Application.class, args);
	}
}
