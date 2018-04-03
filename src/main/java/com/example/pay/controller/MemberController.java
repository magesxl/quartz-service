package com.example.pay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "订单信息1",description = "查询信息",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/ma")
public class MemberController {
//
//    @Resource
//    private TbBatchOrderMapper tbBatchOrderMapper;
//
//    @ApiImplicitParam(name = "id",value = "用户id" ,paramType = "form",dataType = "Long",required = true)
//    @ApiResponse(code = 200,message = "成功")
//    @RequestMapping(value = "/one",method = RequestMethod.POST)
//    public String  select(Long id){
// //       Long idL = Long.parseLong(id);
//        String batchNo = tbBatchOrderMapper.selectByPrimaryKey(id).getBatchNo();
//        return batchNo;
//    }
//
//    @ApiImplicitParam(name = "id",value = "用户id" ,paramType = "form",dataType = "Long",required = true)
//    @ApiResponse(code = 200,message = "成功")
//    @RequestMapping(value = "/two",method = RequestMethod.POST)
//    public TbBatchOrder selectEntity(Long id){
//        TbBatchOrder tbBatchOrder = tbBatchOrderMapper.selectByPrimaryKey(id);
//        return tbBatchOrder;
//    }
}
