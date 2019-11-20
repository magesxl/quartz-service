package com.example.pay.design.proxy.cglib;

import com.example.pay.dao.CityDao;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.List;

public class CglibMainTest {

    public static void main(String[] args) throws NoSuchMethodException, IntrospectionException {
        CglibEngineer engineer = (CglibEngineer) CglibProxy.getProxy(new CglibEngineer());
        engineer.eat();
        System.out.println(CityDao.class.getModifiers());
        System.out.println(CityDao.class.getMethod("findAllCity").getModifiers());
        System.out.println(1&3);
        System.out.println(23&25);
        System.out.println(35&39);
        Double a=0.1;
        System.err.println(Long.toBinaryString(Double.doubleToLongBits(a)));
        System.err.println(Double.doubleToLongBits(a));
        BeanInfo beanInfo = Introspector.getBeanInfo(List.class);

    }
}
