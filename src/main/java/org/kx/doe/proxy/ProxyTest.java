package org.kx.doe.proxy;

import org.junit.Test;

/**
 * create by sunkx on 2018/3/26
 */
public class ProxyTest {
    @Test
    public void testProxy() throws Throwable {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();

        // 实例化InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);

        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();
        System.out.println(proxy.getClass());

        // 调用代理对象的方法
        proxy.add();

    }

    @Test
    public void testMakeProxy() throws Throwable {
        ProxyGeneratorUtils.writeProxyClassToHardDisk("/Users/sunkaixiang/IdeaProjects/safekill/target/$Proxy11.class");

    }


}
