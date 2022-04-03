package com.jpamp.jdk.entity;

/**
 * @author xyb
 * @Description
 * @Date 2022/4/1 22:35
 */
public class DataRecord {
    private String id;
    private String name;

    public DataRecord(String id, String name){
        this.id = id;
        this.name = name;
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
}
