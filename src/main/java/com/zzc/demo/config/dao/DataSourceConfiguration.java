package com.zzc.demo.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

import java.beans.PropertyVetoException;

//这个代表配置需要写到spring ioc这个容器里面
@Configuration
//配置mybatis mapper的扫描路径  这个扫描位置配置好就行 不需要进行其他配置  会自动扫描mapper的
@MapperScan("com.zzc.demo.dao")
public class DataSourceConfiguration {
    //这里通过这种方式获取到application.properties里面定义的相关配置
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 生成与spring-dao.xml对应的bean  dataSource
     * @return
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        //创建DataSource实例
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        //配置c3p0连接池的私有属性
        //连接池的最大线程数量
        dataSource.setMaxPoolSize(30);
        //连接池的最小线程数量
        dataSource.setMinPoolSize(30);
        //连接池不自动commit
        dataSource.setAutoCommitOnClose(false);
        //连接超时时间
        dataSource.setCheckoutTimeout(10000);
        //连接失败自动重试次数
        dataSource.setAcquireRetryAttempts(2);

        return dataSource;
    }
}