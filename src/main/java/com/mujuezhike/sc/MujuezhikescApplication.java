package com.mujuezhike.sc;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MujuezhikescApplication {

	public static void main(String[] args) {
		SpringApplication.run(MujuezhikescApplication.class, args);
		try {
			TianYanChaTest.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
