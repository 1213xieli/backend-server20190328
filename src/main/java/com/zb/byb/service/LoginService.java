package com.zb.byb.service;

import com.zb.byb.entity.UserInfo;

public interface LoginService {
    /**
     * 绑定
     * @param userInfo 用户信息
     * @param openId
     * @return
     * @throws Exception
     */
    String bind(UserInfo userInfo,String openId) throws Exception;

    /**
     * 解除绑定
     * @param openId 唯一id
     * @return
     * @throws Exception
     */
    String unBind(String openId)throws Exception;
    /**
     * 登入绑定用户
     * @param userInfo 用户信息
     * @return
     */
    String login(UserInfo userInfo,String openId) throws Exception;

    /**
     * 注册开户
     * @param userInfo 用户信息
     * @return
     */
    String register(UserInfo userInfo,String openId) throws Exception;

    /**
     * 获取介绍人及所在服务部
     * @param instroducer 介绍人姓名
     * @return
     */
    String getIntroducer(String instroducer) throws Exception;
}
