package com.myredis.demo.util.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        System.out.println("使用缓存");
        cacheManager.setDefaultExpiration(20000);
        Map<String,Long> expires = new HashMap<String,Long>();

        expires.put("roncooCache", 200L);
        cacheManager.setExpires(expires);
        return cacheManager;

    }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
