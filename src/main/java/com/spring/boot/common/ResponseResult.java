package com.spring.boot.common;

/**
 * 响应结果
 *
 * @author 代码的路
 * @date 2022/6/8
 */

public class ResponseResult {

    private String id;
    private String name;

    public ResponseResult(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResponseResult [id=" + id + ",name=" + name + "]";
    }
}
