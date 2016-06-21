package com.zp.tent.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zp.tent.common.constant.ErrorCodeEn;
import com.zp.tent.common.util.ResponseUtil;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ResponseUtil printHello(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("name") String name) {
		log.info("call printHello");
		JSONObject result = new JSONObject();
		result.put("data", String.format("你好,%s", name));
		return new ResponseUtil(ErrorCodeEn.OK, result);
	}
}