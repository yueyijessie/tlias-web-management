package com.jessie.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component // 交给ioc容器管理
@ConfigurationProperties(prefix = "aliyun.oss") // 添加endpoint，bucketName的前缀
public class AliOSSProperties {

    private String endpoint;
    private String bucketName;
}
