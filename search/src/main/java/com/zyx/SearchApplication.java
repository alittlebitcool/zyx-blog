package com.zyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;
/**
 * API网关
 * @author YuXingZh
 */

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SearchApplication {
    public static void main(String[] args) {
//        System.out.println("请指定search的启动端口号：");
//
//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine();
//        new SpringApplicationBuilder(SearchApplication.class).properties("server.port=" + port).run(args);
        SpringApplication.run(SearchApplication.class, args);
    }
}
