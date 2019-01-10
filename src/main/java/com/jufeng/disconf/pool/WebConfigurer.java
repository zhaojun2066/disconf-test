package com.jufeng.disconf.pool;

import com.jufeng.disconf.config.AsyncPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/12/26
 * \* Time: 9:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description: web 相关配置
 *   拦截器、异步callable 拦截器、跨域、视图、异步线程池、
 * \
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private AsyncPoolConfig asyncPoolConfig;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private RequestPool requestPool;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET")
                .allowedOrigins("*");//.allowedMethods("POST","GET")
    }

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        //处理 callable超时
        configurer.setDefaultTimeout(60*1000);
        // 设置一些拦截，具体方法 顺序 所属线程看打印日志
        configurer.registerCallableInterceptors(asyncCallableProcessor());
        configurer.setTaskExecutor(taskExecutor);
    }

    @Bean
    public AsyncCallableProcessor asyncCallableProcessor() {
        return new AsyncCallableProcessor();
    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }*/

}