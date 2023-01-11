package com.modubiz;

import com.modubiz.modu.ServiceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ModuApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ModuApplication.class);
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =SpringApplication.run(ModuApplication.class, args);
		ServiceManager ac = new ServiceManager();
		ac.setApplicationContext(ctx);
	}

}
