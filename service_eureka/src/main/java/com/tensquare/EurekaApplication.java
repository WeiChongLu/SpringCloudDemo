package com.tensquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Eureka服务端
 */
@SpringBootApplication
@EnableEurekaServer //Eureka服务端
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
        System.out.println("---EurekaServerApplication：启动成功----");
    }

}
