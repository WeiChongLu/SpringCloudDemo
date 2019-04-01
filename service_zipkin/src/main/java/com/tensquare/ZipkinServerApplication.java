
package com.tensquare;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;


@Deprecated
@EnableEurekaClient
@SpringBootApplication
public class ZipkinServerApplication {
    private static Logger logger = LoggerFactory.getLogger(ZipkinServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
        System.out.println("---ZipkinServerApplication：启动成功---");
    }
}