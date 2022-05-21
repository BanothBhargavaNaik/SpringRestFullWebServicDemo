package com.example.demo.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {
	
	@Autowired
	private  MessageSource messageSource;
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s",name));
	}
	
	@GetMapping("/hello-world-internationalization")
	public String helloWorldInternationalization( ) {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
}
