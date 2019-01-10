package com.jufeng.disconf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2019/1/10
 * \* Time: 14:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description: disconf 更新配置，server得到通知，进行相关动作
 * \
 */

@ImportResource({"classpath:disconf.xml"})
@SpringBootApplication
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }
}