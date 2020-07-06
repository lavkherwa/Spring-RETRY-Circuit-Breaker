package com.example.demo.retry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.retry.exceptions.ValueException;
import com.example.demo.retry.service.TestService;
import com.example.demo.retry.service.TestService2;

@RestController
@RequestMapping("/api")
public class TestController {

	private TestService service1;
	private TestService2 service2;

	public TestController(TestService service1, TestService2 service2) {
		this.service1 = service1;
		this.service2 = service2;
	}

	@GetMapping("/test/{id}")
	public ResponseEntity<String> test(@PathVariable String id) throws ValueException {
		return new ResponseEntity<String>(service1.getResult(id), HttpStatus.OK);
	}

	@GetMapping("/test2/{id}")
	public ResponseEntity<String> test2(@PathVariable String id) throws ValueException {
		return new ResponseEntity<String>(service2.getResult(id), HttpStatus.OK);
	}

}
