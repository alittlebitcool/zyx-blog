package com.zyx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

/**
 * eureka服务器
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        System.out.println("请指定在配置文件中的服务器号：");
        System.err.println("请输入 slave1 或者 slave2：");
        Scanner scanner = new Scanner(System.in);
        String profiles = scanner.nextLine();//让用户输入端口号
        new SpringApplicationBuilder(EurekaApplication.class).profiles(profiles).run(args);//启动项目

        System.out.println("============================================================= eureka服务启动成功 =============================================================");
    }
}
