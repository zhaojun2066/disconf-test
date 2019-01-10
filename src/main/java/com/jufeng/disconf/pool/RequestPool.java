package com.jufeng.disconf.pool;

import com.jufeng.disconf.config.AsyncPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2019/1/9
 * \* Time: 16:10
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Component
public class RequestPool {

    @Autowired
    private AsyncPoolConfig asyncPoolConfig;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(asyncPoolConfig.getCorePoolSize());
        taskExecutor.setMaxPoolSize(asyncPoolConfig.getMaxPoolSize());
        taskExecutor.setQueueCapacity(asyncPoolConfig.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(asyncPoolConfig.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix(asyncPoolConfig.getThreadNamePrefix());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    public void reInitAsyncThreadPoolTaskExecutor(){
        taskExecutor.setCorePoolSize(asyncPoolConfig.getCorePoolSize());
        taskExecutor.setMaxPoolSize(asyncPoolConfig.getMaxPoolSize());
        taskExecutor.setQueueCapacity(asyncPoolConfig.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(asyncPoolConfig.getKeepAliveSeconds());
    }
}