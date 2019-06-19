package com.github.vole.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@SpringBootApplication
public class VoleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoleDemoApplication.class, args);
    }
}
