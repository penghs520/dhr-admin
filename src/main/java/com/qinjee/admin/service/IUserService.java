package com.qinjee.admin.service;

import com.qinjee.admin.entity.User;
import com.qinjee.admin.model.UserSession;
import com.qinjee.admin.model.ao.UserAo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface IUserService  {

    User getUserByPhone(String phone);

    User getUserById(Integer userId);

    Boolean changePassword(UserSession session, String pwd1);

    Boolean changePhone(UserSession session, String newPhone);

    Boolean save(UserAo userAo);

    Boolean removeById(Integer userId);

    Boolean updateById(UserAo userAo);

    User getUserByPhoneAndPassword(String phone, String password);
}
