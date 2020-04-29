package com.hqyj.springBoot2001.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * localhost/account/login
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}

	/**
	 * localhost/account/register
	 */
	@RequestMapping("/register")
	public String registerPage() {
		return "indexSimple";
	}
}