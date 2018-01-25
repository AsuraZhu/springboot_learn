package com.myredis.demo.dao;

import com.myredis.demo.bean.RoncooUser;
import com.myredis.demo.util.base.Page;


public interface RoncooUserDao {

    int insert(RoncooUser roncooUser);

    int deleteById(int id);

    int updateById(RoncooUser roncooUser);

    RoncooUser selectById(int id);

    Page<RoncooUser> queryForPage(int i, int j, String string);
}
