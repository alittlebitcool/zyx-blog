package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.Scanner;

/**请在控制台指定zuul网关服务的端口号 —— [端口号随意指定，注意不要与本机端口号出现冲突即可]
 * API网关
 */
@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ZuulApplication {

    public static void main(String[] args) {
        System.out.println("请制定zuul的端口号：");

        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=" + port).run(args);//启动项目

        System.out.println("================================================== zuul网关服务 启动成功 =============================================================");
    }
}
