package com.nekkoripay.membership.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

	@GetMapping("/test")
	void test() {
		System.out.println("TestController.test");
	}
}
