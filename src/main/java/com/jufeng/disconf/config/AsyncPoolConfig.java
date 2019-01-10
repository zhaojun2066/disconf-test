package com.jufeng.disconf.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.jufeng.disconf.pool.RequestPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2019/1/9
 * \* Time: 15:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:  异步请求连接池可以动态修改的参数
 * \
 */

@Service
@Scope("singleton")
@ConfigurationProperties(prefix = "async.request.pool")
@PropertySource("classpath:/asyncrquestpool.properties")
@DisconfFile(filename = "asyncrquestpool.properties")
public class AsyncPoolConfig implements IDisconfUpdate {

    @Autowired
    private RequestPool requestPool;

    private Integer corePoolSize=100;
    private Integer queueCapacity=600;
    private Integer maxPoolSize=2000;
    private String threadNamePrefix="async-callable-";
    private Integer keepAliveSeconds=60;


    @DisconfFileItem(name = "async.request.pool.corePoolSize", associateField = "corePoolSize")
    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }


    @DisconfFileItem(name = "async.request.pool.queueCapacity", associateField = "queueCapacity")
    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    @DisconfFileItem(name = "async.request.pool.maxPoolSize", associateField = "maxPoolSize")
    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }


    @DisconfFileItem(name = "async.request.pool.threadNamePrefix", associateField = "threadNamePrefix")
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }


    @DisconfFileItem(name = "async.request.pool.keepAliveSeconds", associateField = "keepAliveSeconds")
    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    @Override
    public void reload() throws Exception {
        requestPool.reInitAsyncThreadPoolTaskExecutor();
    }
}