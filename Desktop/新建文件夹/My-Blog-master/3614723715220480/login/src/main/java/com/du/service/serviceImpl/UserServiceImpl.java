package com.du.service.serviceImpl;

import com.du.dao.UserDao;
import com.du.domain.User;
import com.du.exception.OtherThingsException;
import com.du.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 创建UserServiceImpl实现UserService接口
 * Created by dell1 on 2017/7/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
     */
    public void add(User user) throws OtherThingsException {
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }

    /*public void add(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("User can not be Null");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getLoginId())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("User name can not be Null");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(user.getPwd())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("User name can not be Null");
        }
        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
        if (StringUtils.isEmpty(user.getSex())
                || user.getAge() < 18
                || StringUtils.isEmpty(user.getCellNumber())) {
            //其他综合异常
            throw new OtherThingsException("Some use's base info can not be null");
        }
        //已经存在相同用户
        if (null != userDao.findOneById(user.getLoginId())) {
            //存在相同的用户异常
            throw new UserAireadyExistException("Register User Failed，Because the  user Aiready exist");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }*/

    /**
     * 查找用户
     * @param user 用户bean
     * @throws Exception
     */
    public User findUser(User user)  {
        return userDao.findOneById(user.getLoginId());
    }

    /**
     * 用于更新sessionId
     */
    public void updateLoginSession(String sessionId, String loginId) {
        userDao.updateLoginSession(sessionId, loginId);
    }
}
