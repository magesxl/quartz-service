package com.example.pay.config;


import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 首先解释一个常见的困境：Spring容器可以管理Bean，但是Quartz的job是自己管理的，如果在Job中注入Spring管理的Bean，
 * 需要先把Quartz的Job也让Spring管理起来，因此，我们需要重写JobFactory，详细的源码分析，请参考：
 *  自定义JobFactory,使用Spring容器管理的Quartz的Bean(Job)
 * AdaptableJobFactory是Spring提供的SchedulerFactoryBean的默认实例化工厂，将由直接实例化Job，没有被Spring管理
 */
@Component
public class MyJobFactory  extends AdaptableJobFactory {

    /*
     AutowireCapableBeanFactory接口是BeanFactory的子类
     * 可以连接和填充那些生命周期不被Spring管理的已存在的bean实例
     */
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //// 实例化对象
        Object object = super.createJobInstance(bundle);
        //// 进行注入（Spring管理该Bean）
        autowireCapableBeanFactory.autowireBean(object);

        //返回对象
        return object;
    }
}
