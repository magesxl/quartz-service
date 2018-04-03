package com.example.pay.dao;


import com.example.pay.model.TbBatchOrder;

public interface TbBatchOrderMapper {
    int deleteByPrimaryKey(Long batchSeqNo);

    int insert(TbBatchOrder record);

    int insertSelective(TbBatchOrder record);

    TbBatchOrder selectByPrimaryKey(Long batchSeqNo);

    int updateByPrimaryKeySelective(TbBatchOrder record);

    int updateByPrimaryKey(TbBatchOrder record);
}