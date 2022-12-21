package com.spring.boot.service;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Service层
 *
 * @author 代码的路
 * @date 2022/11/14
 */

@Slf4j
@Service
@EnableScheduling
public class MyService {

    @Resource
    private RestTemplate restTemplate;

    String userId = "101";
    String userName = "张三";
    String getURL1 = "http://localhost:8081/homepage/provideGet";
    String getURL2 = "http://localhost:8081/homepage/provideGetByMap";
    String postURL = "http://localhost:8081/homepage/providePostByMap";

    public Map<String, Object> provideGet() {
        Map<String, Object> res = new HashMap<>();
        res.put("id", userId);
        res.put("name", userName);
        log.info("provideGet res1:" + res);
        return res;
    }

    public Map<String, Object> provideGetByMap(Map<String, Object> map) {
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        HashMap<String, Object> res = new HashMap<>();
        System.out.println("id:" + id);
        System.out.println("name:" + name);
        System.out.println("userId:" + userId);
        System.out.println("userName:" + userName);
        if (id.equals(userId) && name.equals(userName)) {
            res.put("id", id);
            res.put("name", name + " get success");
        } else {
            res.put("id", id);
            res.put("name", name + " get fail");
        }
        log.info("provideGet res2:" + res);
        return res;
    }

    public Map<String, Object> providePost() {
        Map<String, Object> res = new HashMap<>();
        res.put("id", userId);
        res.put("name", userName);
        log.info("providePost res1:" + res);
        return res;
    }

    public Map<String, Object> providePostByMap(Map<String, Object> map) {
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        HashMap<String, Object> res = new HashMap<>();
        if (id.equals(userId) && name.equals(userName)) {
            res.put("id", id);
            res.put("name", name + " post success");
        } else {
            res.put("id", id);
            res.put("name", name + " post fail");
        }
        log.info("providePost res2:" + res);
        return res;
    }

    public Map<String, Object> useGet(Map<String, Object> map) {
        // RestTemplate在getForObject时，用HashMap
        HashMap<String, String> sendData = new HashMap<>();
        sendData.put("id", (String) map.get("id"));
        sendData.put("name", (String) map.get("name"));
        Map<String, Object> returnData1 = new HashMap<>();
        Map<String, Object> returnData2 = new HashMap<>();
        Map<String, Object> returnData3 = new HashMap<>();

        try {
            // getForObject返回值是HTTP协议的响应体，无参方式调用
            String strObject1 = restTemplate.getForObject(getURL1, String.class);
            JSONObject jsonObject1 = JSONObject.parseObject(strObject1);
            log.info("useGet jsonObject1:" + jsonObject1);
        } catch (Exception e) {
            log.info("useGet1 出错：" + e.getMessage());
        }

        try {
            // getForObject返回值是HTTP协议的响应体，带参方式调用
            String strObject2 = restTemplate.getForObject(getURL2 + "?id={id}&name={name}", String.class, sendData);
            JSONObject jsonObject2 = JSONObject.parseObject(strObject2);
            log.info("useGet jsonObject2:" + jsonObject2);
        } catch (Exception e) {
            log.info("useGet2 出错：" + e.getMessage());
        }

        try {
            // getForEntity返回的是ResponseEntity，无参方式调用
            ResponseEntity<ResponseResult> responseData1 = restTemplate.getForEntity(getURL1, ResponseResult.class);
            returnData1.put("StatusCode:", responseData1.getStatusCode());
            returnData1.put("Body:", responseData1.getBody());
            log.info("useGet responseData1:" + responseData1);
            log.info("useGet returnData1:" + returnData1);
        } catch (Exception e) {
            log.info("useGet3 出错：" + e.getMessage());
        }

        try {
            // getForEntity返回的是ResponseEntity，带参方式调用
            ResponseEntity<ResponseResult> responseData2 = restTemplate.getForEntity(getURL2 + "?id={id}&name={name}", ResponseResult.class, sendData);
            returnData2.put("StatusCode:", responseData2.getStatusCode());
            returnData2.put("Body:", responseData2.getBody());
            log.info("useGet responseData1:" + responseData2);
            log.info("useGet returnData2:" + returnData2);
        } catch (Exception e) {
            log.info("useGet3 出错：" + e.getMessage());
        }

        try {
            // getForEntity方法增加密码认证
            RestTemplateBuilder builder = new RestTemplateBuilder();
            RestTemplate restTemplate = builder.basicAuthentication("username", "password").build();
            ResponseEntity<ResponseResult> responseData3 = restTemplate.getForEntity(getURL1, ResponseResult.class);
            returnData3.put("StatusCode:", responseData3.getStatusCode());
            returnData3.put("Body:", responseData3.getBody());
            log.info("useGetByPsw responseData2:" + responseData3);
            log.info("useGetByPsw returnData2:" + returnData3);
        } catch (Exception e) {
            log.info("useGet4 出错：" + e.getMessage());
        }
        return returnData2;
    }

    public Map<String, Object> usePost(Map<String, Object> map) {
        // RestTemplate在postForObject时，用MultiValueMap
        MultiValueMap<String, String> sendData = new LinkedMultiValueMap<>();
        sendData.add("id", (String) map.get("id"));
        sendData.add("name", (String) map.get("name"));
        Map<String, Object> returnData = new HashMap<>();

        try {
            // getForObject返回值是HTTP协议的响应体
            String strObject = restTemplate.postForObject(postURL, sendData, String.class);
            JSONObject jsonObject = JSONObject.parseObject(strObject);
            log.info("usePost jsonObject:" + jsonObject);
        } catch (Exception e) {
            log.info("usePost1 出错：" + e.getMessage());
        }

        try {
            // getForEntity返回的是ResponseEntity，是对HTTP响应的封装
            ResponseEntity<ResponseResult> responseData = restTemplate.postForEntity(postURL, sendData, ResponseResult.class);
            returnData.put("StatusCode:", responseData.getStatusCode());
            returnData.put("Body:", responseData.getBody());
            log.info("usePost responseData:" + responseData);
            log.info("usePost returnData:" + returnData);
        } catch (Exception e) {
            log.info("usePost2 出错：" + e.getMessage());
        }

        return returnData;
    }

}
