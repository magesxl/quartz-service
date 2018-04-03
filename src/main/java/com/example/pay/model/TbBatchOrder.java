package com.example.pay.model;

import java.math.BigDecimal;
import java.util.Date;

public class TbBatchOrder {
    private Long batchSeqNo;

    private String batchNo;

    private String payBatchNo;

    private String batchType;

    private String memberId;

    private String memberName;

    private String transType;

    private String processSeqNo;

    private BigDecimal amount;

    private BigDecimal fee;

    private String storeFile;

    private String storeAddress;

    private Long totalNum;

    private Long endNum;

    private String callbackAddr;

    private String callbackStatus;

    private Integer callbackCount;

    private Date callbackNext;

    private String status;

    private String memo;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getBatchSeqNo() {
        return batchSeqNo;
    }

    public void setBatchSeqNo(Long batchSeqNo) {
        this.batchSeqNo = batchSeqNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getPayBatchNo() {
        return payBatchNo;
    }

    public void setPayBatchNo(String payBatchNo) {
        this.payBatchNo = payBatchNo == null ? null : payBatchNo.trim();
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType == null ? null : batchType.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    public String getProcessSeqNo() {
        return processSeqNo;
    }

    public void setProcessSeqNo(String processSeqNo) {
        this.processSeqNo = processSeqNo == null ? null : processSeqNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStoreFile() {
        return storeFile;
    }

    public void setStoreFile(String storeFile) {
        this.storeFile = storeFile == null ? null : storeFile.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getEndNum() {
        return endNum;
    }

    public void setEndNum(Long endNum) {
        this.endNum = endNum;
    }

    public String getCallbackAddr() {
        return callbackAddr;
    }

    public void setCallbackAddr(String callbackAddr) {
        this.callbackAddr = callbackAddr == null ? null : callbackAddr.trim();
    }

    public String getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus == null ? null : callbackStatus.trim();
    }

    public Integer getCallbackCount() {
        return callbackCount;
    }

    public void setCallbackCount(Integer callbackCount) {
        this.callbackCount = callbackCount;
    }

    public Date getCallbackNext() {
        return callbackNext;
    }

    public void setCallbackNext(Date callbackNext) {
        this.callbackNext = callbackNext;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}