package com.example.pay.util;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * @param params
     * @param url
     * @param encoding
     * @throws IOException
     */

    public void doPost(Map<String, String> params, String url, String encoding) throws IOException {

        //创建默认的httpclient
//        HttpClient httpClient = HttpClients.createDefault();
        //失败了会重试三次
        //       HttpClient httpClient = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)).build();
//        HttpClient httpClient = HttpClients.custom().setRetryHandler(new HttpRequestRetryHandler() {
//            @Override
//            public boolean retryRequest(IOException exception, int executionCount,
//                                        HttpContext context) {
//                if (executionCount > 3) {
//                    logger.warn("Maximum tries reached for client http pool ");
//                    return false;
//                }
//                if (exception instanceof org.apache.http.NoHttpResponseException) {
//                    logger.warn("No response from server on " + executionCount + " call");
//                    return true;
//                }
//                return false;
//            }
//        }).build();
        HttpClient httpClient = HttpClients.custom().setRetryHandler((exception, executionCount, context) -> {
            if (executionCount > 3) {
                logger.warn("Maximum tries reached for client http pool ");
                return false;
            }
            if (exception instanceof NoHttpResponseException     //NoHttpResponseException 重试
                    || exception instanceof ConnectTimeoutException //连接超时重试
//              || exception instanceof SocketTimeoutException    //响应超时不重试，避免造成业务数据不一致
                    ) {
                logger.warn("No response from server on " + executionCount + " call");
                return true;
            }
            return false;
        }).build();
        //创建post请求
        HttpPost post = new HttpPost(url);


//        StringEntity s = new StringEntity(url, ContentType.APPLICATION_JSON);
//        post.setEntity();
        //装填请求参数
        //如果使用HttpPost方法提交HTTP POST请求，则需要使用HttpPost类的setEntity方法设置请求参数。参数则必须用NameValuePair[]数组存储。
        List<NameValuePair> list = setHttpParams(params);

        //设置参数到请求对象中
        post.setEntity(new UrlEncodedFormEntity(list, encoding));

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        HttpResponse response = httpClient.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        //获取返回体
        String content = IOUtils.toString(response.getEntity().getContent());

        //关流
        post.abort();
        //EntityUtils.consume(response.getEntity());
    }

    public static String doGet(Map<String, String> params, String url) throws IOException {

        //创建默认的httpclient
        HttpClient httpClient = HttpClients.createDefault();
        //创建post请求
        HttpGet get = new HttpGet();

        //装填请求参数
        List<NameValuePair> list = setHttpParams(params);

        String param = URLEncodedUtils.format(list, "UTF-8");

        get.setURI(URI.create(url + "?" + param));

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        HttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        //获取返回体
        String content = IOUtils.toString(response.getEntity().getContent());

        //关流
        EntityUtils.consume(response.getEntity());
        return content;
    }

    public static String doGet(String url) throws IOException {

        //创建默认的httpclient
        HttpClient httpClient = HttpClients.createDefault();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可
        HttpGet get = new HttpGet();

        //装填请求参数
        get.setURI(URI.create(url));

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        get.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        HttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        //获取返回体
        String content = IOUtils.toString(response.getEntity().getContent());
        get.abort();
        //关流
        // EntityUtils.consume(response.getEntity());
        return content;
    }

    /**
     * 设置请求参数
     *
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }
}
