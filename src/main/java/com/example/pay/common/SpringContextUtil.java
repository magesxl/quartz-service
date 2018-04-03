package com.example.pay.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>服务路由管理</p>
 * @author leelun
 * @version $Id: SpringContextUtil.java, v 0.1 2013-11-26 下午5:17:32 lilun Exp $
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    private Map<String, Object> routeMap = new ConcurrentHashMap<String, Object>();

    private Logger logger   = LoggerFactory.getLogger(SpringContextUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
        registerService();
    }

    /**
     * 注册服务接口
     */
    private void registerService() {
        Map<String, Object> allWebResBeans = appCtx.getBeansWithAnnotation(ServiceRoute.class);
        for (Object bean : allWebResBeans.values()) {
            String routeName = getServiceRoute(bean);
            if (routeName != null) {
                routeMap.put(routeName, bean);
                this.logger.debug("register route,routeName={},bean={}", new Object[] { routeName,
                        bean });
            }
        }
    }

    private String getServiceRoute(Object bean) {
        if (bean != null) {
            Annotation anno = AnnotationUtils.getAnnotation(bean.getClass(), ServiceRoute.class);
            if (anno != null) {
                return (String) AnnotationUtils.getValue(anno, "name");
            }
        }
        return null;
    }

    public Object getServiceByAnnoName(String name) {
        if (StringUtil.isNotEmpty(name)) {
            return routeMap.get(name);
        }
        return null;
    }
}
