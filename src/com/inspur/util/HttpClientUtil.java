package com.inspur.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {
    public static final Log logger = LogFactory.getLog(HttpClientUtil.class);
    
    /**
     * 封装httpGet请求
     * 
     * @param url
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String httpGet(String url, Map headerMap, String code) {
        logger.info("GetPage:" + url);
        
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setContentCharset(code);// 设置编码
        
        GetMethod method = new GetMethod(url);
        
        // 设置GET请求的Header信息
        method = setMethodReqHeader(method, headerMap);
        
        String result = null;
        try {
            httpClient.executeMethod(method);
            int status = method.getStatusCode();
            if (status == HttpStatus.SC_OK) {
                result = method.getResponseBodyAsString();
            }
            else {
                logger.error("Method failed: " + method.getStatusLine());
            }
        }
        catch (HttpException e)// 发生致命的异常，可能是协议不对或者返回的内容有问题
        {
            logger.error("Please check your provided http address!");
            e.printStackTrace();
        }
        catch (IOException e)// 发生网络异常
        {
            logger.error("发生网络异常！");
            e.printStackTrace();
        }
        finally
        // 释放连接
        {
            if (method != null)
                method.releaseConnection();
            method = null;
            httpClient = null;
        }
        return result;
    }
    
    /**
     * 封装httpPost请求
     * 
     * @param url post地址
     * @param paramMap 请求参数
     * @param bodyMap 页面参数
     * @param code 编码方式
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String httpPost(String url, Map paramMap, Map bodyMap, String code) {
        logger.info("GetPage:" + url);
        String content = null;
        if (url == null || url.trim().length() == 0 || paramMap == null || paramMap.isEmpty())
            return null;
        HttpClient httpClient = new HttpClient();
        
        /*
         * if (IS_USE_PROXY) { // 是否使用代理 { String PROXY_URL, PROXY_USER, PROXY_PASSWORD; int PROXY_PORT;
         * httpClient.getHostConfiguration().setProxy(PROXY_URL, PROXY_PORT); httpClient.getParams().setAuthenticationPreemptive(true); //
         * 如果代理需要密码验证，这里设置用户名密码 httpClient.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials(PROXY_USER,
         * PROXY_PASSWORD)); }
         */
        
        PostMethod method = new PostMethod(url);
        // 设置 Post.parameter
        method.addParameters(mapToNameValuePair(paramMap));
        // 设置 Post.Body
        method.setRequestBody(mapToNameValuePair(bodyMap));
        
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + method.getStatusLine());
            }
            
            logger.info("StatusLine" + method.getStatusLine());
            
            content = new String(method.getResponseBody(), code);
            Header[] header = method.getResponseHeaders();
            String charSet = method.getRequestCharSet();
            String body = method.getResponseBodyAsString();
            int StatusCode = method.getStatusCode();
            System.out.println("StatusCode" + "  :  " + StatusCode);
            // System.out.println("header" + "  :  " + header);
            // System.out.println("body" + "  :  " + body);
            
        }
        catch (Exception e) {
            logger.error("time out! URL is : " + url);
            e.printStackTrace();
        }
        finally {
            if (method != null) {
                method.releaseConnection();
            }
            method = null;
            httpClient = null;
        }
        return content;
    }
    
    /**
     * 封装httpPost请求
     * 
     * @param url post地址
     * @param paramMap 请求参数
     * @param bodyMap 页面参数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String httpPost(String url, Map paramMap, Map bodyMap) {
        return HttpClientUtil.httpPost(url, paramMap, bodyMap, "GB2312");
    }
    
    /**
     * 设置GET请求的Header信息
     * 
     * @param method
     * @param headerMap
     * @return
     */
    private static GetMethod setMethodReqHeader(GetMethod method, Map headerMap) {
        if (null == headerMap || headerMap.isEmpty()) {
            return method;
        }
        
        Iterator it = headerMap.keySet().iterator();
        List<NameValuePair> lnv = new ArrayList<NameValuePair>();
        while (it.hasNext()) {
            String key = it.next() + "";
            Object o = headerMap.get(key);
            if (o != null && o instanceof String) {
                method.setRequestHeader(key, o.toString());
            }
        }
        return method;
    }
    
    /**
     * 将map转换为NameValuePair
     * 
     * @param paramMap
     * @return
     */
    private static NameValuePair[] mapToNameValuePair(Map paramMap) {
        if (null == paramMap || paramMap.isEmpty()) {
            return null;
        }
        Iterator it = paramMap.keySet().iterator();
        List<NameValuePair> lnv = new ArrayList<NameValuePair>();
        while (it.hasNext()) {
            String key = it.next() + "";
            Object o = paramMap.get(key);
            if (o != null && o instanceof String) {
                lnv.add(new NameValuePair(key, o.toString()));
            }
            if (o != null && o instanceof String[]) {
                String[] s = (String[])o;
                if (s != null) {
                    for (int i = 0; i < s.length; i++) {
                        lnv.add(new NameValuePair(key, s[i]));
                    }
                }
            }
        }
        
        NameValuePair[] nvps = new NameValuePair[lnv.size()];
        for (int i = 0; i < lnv.size(); i++) {
            nvps[i] = lnv.get(i);
        }
        System.out.println(nvps.toString());
        return nvps;
    }
    
    /**
     * 将 InputStream 转换为 String
     * 
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}