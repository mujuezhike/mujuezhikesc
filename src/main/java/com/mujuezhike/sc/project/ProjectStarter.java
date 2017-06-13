package com.mujuezhike.sc.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

import com.mujuezhike.sc.SampleController;

@EnableAutoConfiguration
@Configuration
@EnableDiscoveryClient
@EnableConfigServer
public class ProjectStarter {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

}
