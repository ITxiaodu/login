package com.du.dao;


import com.du.domain.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * Created by dell1 on 2017/7/8.
 */
public interface UserDao extends Dao<User>{
    int add(User user);

    int delete(User user);

    int update(User user);

    User findOneById(Serializable Id);

    void updateLoginSession(@Param("sessionId") String sessionId, @Param("loginId") String loginId);

}
