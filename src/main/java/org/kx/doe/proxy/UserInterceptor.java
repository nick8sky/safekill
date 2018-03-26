package org.kx.doe.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * create by sunkx on 2018/3/26
 */
public class UserInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("UserInterceptor, 调用开始 method: " + method +
                ", methodProxy: " + methodProxy);
        methodProxy.invokeSuper(o, objects);
        System.out.println("UserInterceptor, 调用结束");
        return null;
    }
}
