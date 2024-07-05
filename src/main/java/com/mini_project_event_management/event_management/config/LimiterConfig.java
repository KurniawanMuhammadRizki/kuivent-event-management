package com.mini_project_event_management.event_management.config;


import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class LimiterConfig {
//    RateLimiterConfig config = RateLimiterConfig.custom()
//            .limitRefreshPeriod(Duration.ofMillis(1))
//            .limitForPeriod(10)
//            .timeoutDuration(Duration.ofMillis(25))
//            .build();
//
//    // Create registry
//    RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
//
//    // Use registry
//    RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry
//            .rateLimiter("default");
    @Bean
    public RateLimiter defaultRateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(10)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(0))
                .build();

        return RateLimiter.of("default", config);
    }
}