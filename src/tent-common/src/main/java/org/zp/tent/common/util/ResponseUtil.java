package org.zp.tent.common.util;

import org.zp.tent.common.constant.ErrorCodeEn;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {
	private int code;
	private String msg;
	private Object result;

	public ResponseUtil() {
		this.code = 0;
		this.msg = null;
		this.result = null;
	}

	public ResponseUtil(int code, String msg, Object result) {
		this.code = code;
		this.msg = msg;
		if (null == result) {
			this.result = new JSONObject();
		} else {
			this.result = result;
		}
	}

	public ResponseUtil(ErrorCodeEn errorCode, Object result) {
		this.code = errorCode.getCode();
		this.msg = errorCode.getDesc();
		if (null == result) {
			this.result = new JSONObject();
		} else {
			this.result = result;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}