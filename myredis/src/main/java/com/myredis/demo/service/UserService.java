package com.myredis.demo.service;

import com.myredis.demo.bean.RoncooUser;
import com.myredis.demo.bean.RoncooUserLog;
import com.myredis.demo.dao.RoncooUserDao;
import com.myredis.demo.dao.RoncooUserLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private RoncooUserDao roncooUserDao;

    @Autowired
    private RoncooUserLogDao roncooUserLogDao;

    /**
     * 用户注册
     *
     * @return
     */
    @Transactional
    public String register(String name, String ip) {
        //1.添加用户
        RoncooUser roncooUser = new RoncooUser();
        roncooUser.setName(name);
        roncooUser.setCreateTime(new Date());
        roncooUserDao.insert(roncooUser);

        RoncooUserLog roncooUserLog = new RoncooUserLog();
        roncooUserLog.setUserName(name);
        roncooUserLog.setUserIp(ip);
        roncooUserLog.setCreateTime(new Date());
        roncooUserLogDao.save(roncooUserLog);


        return null;
    }
}
