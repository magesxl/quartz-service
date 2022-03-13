//package com.example.pay.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration  // //类似xml中的<beans>标签,一般和@bean注解一起使用来配置一个Bean,让Spring来管理它的生命周期
//public class SchedulerConfig {
//
//    @Autowired
//    private MyJobFactory myJobFactory;
//
//    //生成数据库表
//    @Bean  //将一个方法产生为Bean并交给Spring容器管理(@Bean只能用在方法上)
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws Exception{
//        //Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        //启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
//        schedulerFactoryBean.setOverwriteExistingJobs(true);
//        schedulerFactoryBean.setDataSource(dataSource);
//        schedulerFactoryBean.setQuartzProperties(quartzProperties());
//        schedulerFactoryBean.setStartupDelay(2);  //应用启动三十秒后启动定时
//        //设置自定义Job Factory，用于Spring管理Job bean
//        schedulerFactoryBean.setJobFactory(myJobFactory);
//        return schedulerFactoryBean;
//    }
//
//    @Bean
//    public Properties quartzProperties() throws Exception{
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        return  propertiesFactoryBean.getObject();
//    }
//
//    @Bean
//    public QuartzInitializerListener executorListener(){
//        return new QuartzInitializerListener();
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:spring/sqlmap/*.xml"));
//        return sessionFactory.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
////    @Bean
////    public org.springframework.orm.ibatis.SqlMapClientFactoryBean sqlMapClientFactory(DataSource dataSource) throws Exception {
////        final SqlMapClientFactoryBean sessionFactory = new SqlMapClientFactoryBean();
////        sessionFactory.setDataSource(dataSource);
////        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:spring/sqlmap/*.xml"));
////        return sessionFactory.getObject();
////    }
//}
