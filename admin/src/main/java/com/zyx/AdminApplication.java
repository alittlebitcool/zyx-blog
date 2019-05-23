package com.zyx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Scanner;

/**
 * Created by YuXingZh on 19-3-19
 * 管理员 服务启动器
 */

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.zyx.dao")
@EnableFeignClients(basePackages = { "com.zyx.feign"})
public class AdminApplication {
    public static void main(String[] args) {
        System.out.println("请指定admin的启动端口号：");

        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(AdminApplication.class).properties("server.port=" + port).run(args);//启动项目

        System.out.println("================================================== admin服务启动成功 " +
                "=============================================================");
    }
}
