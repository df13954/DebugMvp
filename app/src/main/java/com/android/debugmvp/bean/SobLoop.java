package com.android.debugmvp.bean;

/**
 * @author: 123
 * @date: 2020/12/31
 * @description $
 */
public class SobLoop {

    /**
     * id : 785987843854958592
     * title : 测试轮播
     * order : 1
     * state : 1
     * targetUrl : https://www.bilibili.com/
     * imageUrl : http://moonlightshadow.cn:2020/admin/image/1607439877905_786005346610053120.jpg
     * createTime : 2020-12-08T21:55:05.000+0000
     * updateTime : 2020-12-13T01:36:53.000+0000
     */

    private String id;
    private String title;
    private int order;
    private String state;
    private String targetUrl;
    private String imageUrl;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
