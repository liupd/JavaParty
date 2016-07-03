package com.zp.tent.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:redirectFinal";
	}

	@RequestMapping(value = "/redirectFinal", method = RequestMethod.GET)
	public String finalPage() {
		return "redirectFinal";
	}
}