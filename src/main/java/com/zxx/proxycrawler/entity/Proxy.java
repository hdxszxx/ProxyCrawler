package com.zxx.proxycrawler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 代理实体类
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 20:45
 */
@Data
@EqualsAndHashCode
@ToString
public class Proxy implements Serializable {
    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 是否是高匿
     */
    private String isAnon;

    /**
     * 类型
     */
    private String type;

    /**
     * 地区
     */
    private String country;

    /**
     * 速度
     */
    private String speed;

    /**
     * 最后验证时间
     */
    private String endTime;
}
