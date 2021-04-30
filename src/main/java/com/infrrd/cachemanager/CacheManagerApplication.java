package com.infrrd.cachemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.infrrd.cachemanager.model.Users;

@SpringBootApplication
public class CacheManagerApplication {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, Users> redisTemplate() {
		RedisTemplate<String, Users> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
 	}

	public static void main(String[] args) {
		SpringApplication.run(CacheManagerApplication.class, args);
	}

}
