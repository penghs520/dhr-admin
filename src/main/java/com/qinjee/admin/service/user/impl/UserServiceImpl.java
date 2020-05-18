package com.qinjee.admin.service.user.impl;

import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.entity.user.User;
import com.qinjee.admin.mapper.user.UserMapper;
import com.qinjee.admin.model.UserSession;
import com.qinjee.admin.model.ao.UserAo;
import com.qinjee.admin.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisClusterService redisClusterService;
    @Override
    public User getUserByPhone(String phone) {
        User user=new User();
        user.setPhone(phone);
        return userMapper.selectByUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        User user=new User();
        user.setUserId(userId);
        return userMapper.selectByUser(user);
    }

    @Override
    public Boolean changePassword(UserSession session, String pwd1) {
        User user = new User();
        user.setUserId(session.getUserId());
        user.setPassword(pwd1);//TODO 加密
        int i = userMapper.updateById(user);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean changePhone(UserSession session, String newPhone) {
        User user = new User();
        user.setUserId(session.getUserId());
        user.setPhone(newPhone);
        int i = userMapper.updateById(user);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean save(UserAo userAo) {
        User user = new User();
        user.setPhone(userAo.getPhone());
        user.setUserGroupId(userAo.getUserGroupId());
        user.setEmail(userAo.getEmail());
        user.setUserName(userAo.getUserName());
        int i = userMapper.insert(user);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean removeById(Integer userId) {
        int i = userMapper.deleteById(userId);
        //移除session
        StringBuffer loginKey = new StringBuffer();
        loginKey.append("SESSION_KEY").append("_").append(userId);
        redisClusterService.del(loginKey.toString());
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateById(UserAo userAo) {
        User user = new User();
        user.setUserId(userAo.getUserId());
        user.setPhone(userAo.getPhone());
        user.setUserGroupId(userAo.getUserGroupId());
        user.setEmail(userAo.getEmail());
        user.setUserName(userAo.getUserName());
        int i = userMapper.updateById(user);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByPhoneAndPassword(String phone, String password) {
        User user=new User();
        user.setPhone(phone);
        user.setPassword(password);
        return userMapper.selectByUser(user);
    }
}
