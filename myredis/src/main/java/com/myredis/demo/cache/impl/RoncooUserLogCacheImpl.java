package com.myredis.demo.cache.impl;

import com.myredis.demo.bean.RoncooUserLog;
import com.myredis.demo.cache.RoncooUserLogCache;
import com.myredis.demo.dao.RoncooUserLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
@CacheConfig(cacheNames = "roncooCache")
@Repository
public class RoncooUserLogCacheImpl implements RoncooUserLogCache {
    @Autowired
    private RoncooUserLogDao roncooUserLogDao;
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Cacheable(key = "#p0")
    @Override
    public RoncooUserLog selectById(Integer id) {
        logger.debug("查询功能，缓存找不到，直接读库, id=" + id);
        return roncooUserLogDao.findOne(id);
    }

    @CachePut(key = "#p0")
    @Override
    public RoncooUserLog updateById(RoncooUserLog roncooUserLog) {
        System.out.println("更新功能，更新缓存，直接写库, id=" + roncooUserLog);
        return roncooUserLogDao.save(roncooUserLog);
    }

    @CacheEvict(key = "#p0")
    @Override
    public String deleteById(Integer id) {
        System.out.println("删除功能，删除缓存，直接写库, id=" + id);
        return "清空缓存成功";
    }
}
