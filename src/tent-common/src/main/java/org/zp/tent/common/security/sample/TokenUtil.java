package org.zp.tent.common.security.sample;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.zp.tent.common.security.digest.HmacCoder;

import com.alibaba.fastjson.JSONObject;

public class TokenUtil {
	private static final String SEPARATOR = "::";

	private static final String[] policyFields = new String[] {
			/**
			 * 上传文件所属空间
			 */
			"namespace",

			/**
			 * 令牌有效的截止时间。用Unix时间表示。单位秒
			 */
			"e",

			/**
			 * 文件上传后保留时间。单位天。默认值-1，表示永久保留
			 */
			"deleteAfterDays",

			/**
			 * 上传文件大小上限。单位Byte。默认100 * 1024B
			 */
			"fsizeMin",

			/**
			 * 上传文件大小上限。单位Byte。默认2 * 1024 * 1024B
			 */
			"fsizeMax",

			/**
			 * 允许上传文件类型
			 */
			"mimeLimit",

			/**
			 * 存储类型
			 */
			"storeType",

			/**
			 * 是否认证令牌。0表示认证，1表示不认证。默认为0
			 */
			"verifyToken" };

	/**
	 * @title getToken
	 * @description 获取令牌
	 * @author zhangpeng
	 * @date 2016年7月22日
	 * @return String 令牌
	 * @param policyBase64
	 * @param accessKeyBase64
	 * @throws Exception
	 */
	public static String getToken(String policyBase64, String accessKeyBase64) throws Exception {
		if (!Base64.isBase64(policyBase64) || !Base64.isBase64(accessKeyBase64)) {
			throw new Exception("policy or accessKey is not encoded by Base64");
		}
		byte[] policy = Base64.decodeBase64(policyBase64);
		byte[] accessKey = Base64.decodeBase64(accessKeyBase64);

		JSONObject policyJson = JSONObject.parseObject(new String(policy));
		UploadPolicy policyDto = JSONObject.toJavaObject(policyJson, UploadPolicy.class);
		if (!UploadPolicy.isValid(policyDto)) {
			throw new Exception("The policy is not conform to the system specfictation.");
		}

		// TODO 查找数据库，找到与accessKey匹配的secretKey
		String secretKey = "Secret_Key";

		// 1. 根据accessKey和policy生成消息摘要(使用基于口令编码的HMAC算法)
		byte[] digest = HmacCoder.encode(policy, secretKey.getBytes(), HmacCoder.HmacTypeEn.HmacSHA256);

		// 2. Token = AK::Digest::Policy。数据拼接之前都要做URL安全的Base64编码
		String token = accessKeyBase64 + SEPARATOR + Base64.encodeBase64URLSafeString(digest) + SEPARATOR
				+ policyBase64;

		return token;
	}

	public static UploadPolicy authorization(String token) throws Exception {
		String[] params = token.split(SEPARATOR);
		byte[] accessKey = Base64.decodeBase64(params[0]);
		String digestBase64 = params[1];
		String policy = new String(Base64.decodeBase64(params[2]));

		// TODO 查找数据库，找到与accessKey匹配的secretKey
		String secretKey = "Secret_Key";

		// 1. 根据accessKey和policy生成消息摘要(使用基于口令编码的HMAC算法)
		byte[] checkDigest = HmacCoder.encode(policy.getBytes(), secretKey.getBytes(), HmacCoder.HmacTypeEn.HmacSHA256);
		String checkDigestBase64 = Base64.encodeBase64URLSafeString(checkDigest);
		if (!checkDigestBase64.equals(digestBase64)) {
			throw new Exception("Policy is changed.");
		}

		JSONObject json = JSONObject.parseObject(policy);
		UploadPolicy uploadPolicy = JSONObject.toJavaObject(json, UploadPolicy.class);
		long life = uploadPolicy.getE() - System.currentTimeMillis() / 1000;
		if (life < 0) {
			throw new Exception("Token is overdue.");
		}
		return uploadPolicy;
	}

	public static void main(String[] args) throws InterruptedException {
		String ACCESS_KEY = "test_access";
		String SECRET_KEY = "test_secret";

		long deadline = System.currentTimeMillis() / 1000 + 3600;
		JSONObject uploadPolicy = new JSONObject();
		uploadPolicy.put("namespace", "apollo"); // 所属空间。必填
		uploadPolicy.put("verifyToken", 0); // 认证开关。0表示需要认证，1表示不需要认证。必填
		uploadPolicy.put("e", deadline); // 令牌有效期。必填
		uploadPolicy.put("deleteAfterDays", 1);
		uploadPolicy.put("fsizeMax", 2 * 1024 * 1024L);
		uploadPolicy.put("fsizeMin", 1 * 1024L);
		// uploadPolicy.put("mimeLimit", "application/pdf&application/zip"); //
		// 上传文件类型限制。允许设置多个，以&分开
		// uploadPolicy.put("storeType", "FFS");

		String policyBase64 = Base64.encodeBase64URLSafeString(uploadPolicy.toJSONString().getBytes());
		String accessKeyBase64 = Base64.encodeBase64URLSafeString(ACCESS_KEY.getBytes());

		System.out.println("policyBase64:" + policyBase64);
		System.out.println("accessKeyBase64:" + accessKeyBase64);

		String token = null;
		try {
			token = getToken(policyBase64, accessKeyBase64);
			if (StringUtils.isBlank(token)) {
				System.err.println("获取令牌失败");
				return;
			}
		} catch (Exception e) {
			System.err.println("获取令牌失败");
			return;
		}

		UploadPolicy policy = null;
		try {
			// 验证令牌，并获得策略
			policy = authorization(token);
			if (null == policy) {
				System.err.println("令牌无效");
				return;
			}
		} catch (Exception e) {
			System.err.println("令牌无效");
			return;
		}
		System.out.println("令牌有效");

		// System.out.println("Access Key:" + ACCESS_KEY);
		// System.out.println("Policy:" + uploadPolicy.toJSONString());
		// System.out.println("Digest:" +
		// Base64.encodeBase64URLSafeString(digest));
		System.out.println("Token:" + token);
	}
}
