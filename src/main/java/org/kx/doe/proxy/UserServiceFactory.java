package org.kx.doe.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * create by sunkx on 2018/3/26
 */
public class UserServiceFactory {

    static  UserServiceFactory userServiceFactory = new UserServiceFactory();

    static  UserServiceFactory  getUserServiceFactory(){
        return userServiceFactory ;
    }


    /**
     * 创建代理对象
     */
    public Object getUserService() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);

        enhancer.setCallback(new UserInterceptor());
        return enhancer.create();

    }
}
