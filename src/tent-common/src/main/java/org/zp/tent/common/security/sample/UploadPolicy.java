package org.zp.tent.common.security.sample;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangpeng0913 on 2016/7/22.
 */
public class UploadPolicy {
    public static long FILE_MIN_SIZE = 1024L; // 1KB
    public static long FILE_MAX_SIZE = 5 * 1024 * 1024L; // 5MB

    /**
     * 上传文件所属空间
     */
    private String namespace;

    /**
     * 令牌有效的截止时间。用Unix时间表示。单位秒
     */
    private Long e;

    /**
     * 文件上传后保留时间。单位天。默认值-1，表示永久保留
     */
    private Integer deleteAfterDays = -1;

    /**
     * 上传文件大小上限。单位Byte。默认100 * 1024B
     */
    private Long fsizeMin = 100 * 1024L;

    /**
     * 上传文件大小上限。单位Byte。默认2 * 1024 * 1024B
     */
    private Long fsizeMax = 2 * 1024 * 1024L;

    /**
     * 允许上传文件类型
     */
    private String mimeLimit;

    /**
     * 存储类型
     */
    private String storeType;

    /**
     * 是否认证令牌。0表示认证，1表示不认证。默认为0
     */
    private Integer verifyToken = 0;


    /**
     * @title isValid
     * @description 判断数据是否有效
     * @author zhangpeng0913
     * @date 2016年7月22日
     * @return boolean
     * @param policy
     */
    public static boolean isValid(UploadPolicy policy) {
        if (StringUtils.isBlank(policy.namespace) || null == policy.e) {
            return false;
        }

        // 令牌截止时间不能是已过期时间
        long life = policy.e - System.currentTimeMillis() / 1000;
        if (life <= 0) {
            return false;
        }

        // 判断文件大小的上限、下限是否符合系统规格
        if (policy.fsizeMin > policy.fsizeMax || policy.fsizeMin < FILE_MIN_SIZE || policy.fsizeMax > FILE_MAX_SIZE) {
            return false;
        }

        return true;
    }


    public static long getFILE_MIN_SIZE() {
        return FILE_MIN_SIZE;
    }


    public static void setFILE_MIN_SIZE(long fILE_MIN_SIZE) {
        FILE_MIN_SIZE = fILE_MIN_SIZE;
    }


    public static long getFILE_MAX_SIZE() {
        return FILE_MAX_SIZE;
    }


    public static void setFILE_MAX_SIZE(long fILE_MAX_SIZE) {
        FILE_MAX_SIZE = fILE_MAX_SIZE;
    }


    public String getNamespace() {
        return namespace;
    }


    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }


    public Long getE() {
        return e;
    }


    public void setE(Long e) {
        this.e = e;
    }


    public Integer getDeleteAfterDays() {
        return deleteAfterDays;
    }


    public void setDeleteAfterDays(Integer deleteAfterDays) {
        this.deleteAfterDays = deleteAfterDays;
    }


    public Long getFsizeMin() {
        return fsizeMin;
    }


    public void setFsizeMin(Long fsizeMin) {
        this.fsizeMin = fsizeMin;
    }


    public Long getFsizeMax() {
        return fsizeMax;
    }


    public void setFsizeMax(Long fsizeMax) {
        this.fsizeMax = fsizeMax;
    }


    public String getMimeLimit() {
        return mimeLimit;
    }


    public void setMimeLimit(String mimeLimit) {
        this.mimeLimit = mimeLimit;
    }


    public String getStoreType() {
        return storeType;
    }


    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }


    public Integer getVerifyToken() {
        return verifyToken;
    }


    public void setVerifyToken(Integer verifyToken) {
        this.verifyToken = verifyToken;
    }



}
