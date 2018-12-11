package com.example.pay.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Configuration
public class CommonRestTemplateService {

    public static final Logger logger = LoggerFactory.getLogger(CommonRestTemplateService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 本方法主要用于http中get和delete
     *
     * @param params
     * @param url
     * @param httpMethod
     * @param pathParam
     * @return
     */
    public String getDelReq(MultiValueMap<String, String> params, String url, HttpMethod httpMethod, Map<String, Object> pathParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        //组装请求参数
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
        String content;
        if (MapUtils.isNotEmpty(pathParam)) {
            content = restTemplate.exchange(uriComponents.toUriString(), httpMethod, request, String.class, pathParam).getBody();
        } else {
            content = restTemplate.exchange(uriComponents.toUriString(), httpMethod, request, String.class).getBody();
        }
        logger.info("返回参数为：{}", JSON.toJSONString(content));
        return content;
    }

    public String postPutReq(Map<String, Object> params, String url, HttpMethod httpMethod, Map<String, Object> pathParam) {
        logger.info("请求参数json：{}", JSON.toJSONString(params));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(params), headers);
        String content;
        if (MapUtils.isNotEmpty(pathParam)) {
            content = restTemplate.exchange(url, httpMethod, request, String.class, pathParam).getBody();
        } else {
            content = restTemplate.exchange(url, httpMethod, request, String.class).getBody();
        }
        logger.info("返回参数为：{}", JSON.toJSONString(content));
        return content;
    }

    /**
     * 本方式针对综合体
     * @param multiValueMap
     * @param bodyParams
     * @param url
     * @param httpMethod
     * @param pathParam
     * @return
     */
    public String postPutUrlAssemReq(MultiValueMap<String, String> multiValueMap, Map<String, Object> bodyParams, String url, HttpMethod httpMethod, Map<String, Object> pathParam) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(multiValueMap).build();
        return postPutReq(bodyParams, uriComponents.toUriString(), httpMethod, pathParam);
    }
}
