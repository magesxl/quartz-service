package com.example.pay.delay;

public class RetMessage {
    /**
     * 回调地址
     */
    private String url;
    /**
     * 报文
     */
    private String reqData;
    /**
     * 已重试次数
     */
    private int times;
    /**
     * 是否成功
     */
    private boolean success;

    public RetMessage(String url, String reqData) {
        super();
        this.url = url;
        this.reqData = reqData;
        this.times = 1;
        this.success = false;
    }

    public RetMessage(String url, String reqData, int times, boolean success) {
        super();
        this.url = url;
        this.reqData = reqData;
        this.times = times;
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
