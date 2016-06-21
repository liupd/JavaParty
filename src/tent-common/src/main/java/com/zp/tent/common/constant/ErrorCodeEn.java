package com.zp.tent.common.constant;

public enum ErrorCodeEn {
	OK(0, "成功"),

	FAIL_UPLOAD_FILE(1001, "上传文件失败"),

	FAIL_DOWNLOAD_FILE(1002, "下载文件失败"),

	NO_AUTHORITY(2001, "没有权限执行操作"),

	UNSUPPORTED_FILE_TYPE(2002, "不支持的文件类型"),

	EXCEED_FILE_SIZE(2003, "超过文件大小上限"),

	FILE_NOT_EXIST(2004, "文件不存在"),

	FAIL(-1, "失败");

	private int code;
	private String desc;

	private ErrorCodeEn(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return String.format("[code:%d, name:%s, desc:%s]", this.code, this.name(), this.desc);
	}

	public static String getAll() { // 静态方法
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		for (ErrorCodeEn item : ErrorCodeEn.values()) {
			sb.append(item.toString() + "\n");
		}
		sb.append("}\n");
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getAll());
	}
}
