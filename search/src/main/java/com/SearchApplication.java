package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.Scanner;

/**
 * API网关
 * @author YuXingZh
 */
@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SearchApplication {

    public static void main(String[] args) {
        System.out.println("请指定elasticsearch服务端口号:");

        Scanner scanner = new Scanner(System.in);
        //让用户指定端口号
        String port = scanner.nextLine();
        //启动项目
        new SpringApplicationBuilder(SearchApplication.class).properties("server.port=" + port).run(args);
    }
}
