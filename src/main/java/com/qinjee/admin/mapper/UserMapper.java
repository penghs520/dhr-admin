package com.qinjee.admin.mapper;

import com.qinjee.admin.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface UserMapper {

    User selectByUser(User user);

    List<User> listByUser(User user);

    int updateById(User user);

    int insert(User user);

    int deleteById(Integer userId);

}
