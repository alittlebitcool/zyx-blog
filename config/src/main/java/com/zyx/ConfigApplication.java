package com.zyx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

/**
 * 配置中心
 */
@EnableConfigServer //开启配置中心服务
@EnableEurekaClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ConfigApplication {

    public static void main(String[] args) {
        System.out.println("请制定配置中心的端口号：");

        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(ConfigApplication.class).properties("server.port=" + port).run(args);//启动项目

        System.out.println("================================================== Config Server配置中心服务启动成功 =============================================================");

    }
}
