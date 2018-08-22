package com.study.user.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.study.user.entiry.User;
import com.study.user.repository.UserRepository;
import com.study.user.service.UserService;

@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService{
	
	private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
    
    @Cacheable(key ="#p0") 
	public User getUserById(String id) {
		User user = userRepository.findById(Long.parseLong(id));
		return user;
	}

    @Override
    public User findUserById(long id) {
    	// 从缓存中获取城市信息
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	User user = operations.get(key);
            LOGGER.info("从缓存中获取了用户 >> " + user.toString());
            return user;
        }
        User user = userRepository.findById(id);
        // 插入缓存
        operations.set(key, user, 10000, TimeUnit.SECONDS);
        LOGGER.info("用户插入缓存 >> " + user.toString());
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}


