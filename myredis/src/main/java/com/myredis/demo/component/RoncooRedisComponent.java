package com.myredis.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RoncooRedisComponent {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key,String value){
        ValueOperations<String,String> ops = this.stringRedisTemplate.opsForValue();
        if(this.stringRedisTemplate.hasKey(key)){
            ops.set(key,value);
            System.out.println("set key success");
        }else{
            System.out.println("this key ="+ops.get(key));
        }
    }

    public String get(String key){
         return this.stringRedisTemplate.opsForValue().get(key);
    }
}
