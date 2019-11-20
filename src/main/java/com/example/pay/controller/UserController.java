package com.example.pay.controller;


import com.example.pay.model.User;
import com.example.pay.service.UserService;
import io.reactivex.Observable;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@Controller：修饰class，用来创建处理http请求的对象
@RestController：Spring4之后加入的注解，原来在@Controller中返回json
需要@ResponseBody来配合，
如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，
默认返回json格式。
@RequestMapping：配置url映射
 */

@Api(value = "Swagger动态文档", description = "this is desc", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    static Map<Long,User> users = Collections.synchronizedMap(new HashMap());
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true,paramType = "form", dataType = "java.lang.Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true,paramType = "form", dataType = "java.lang.Integer")})
    @RequestMapping(value = "/queryuser", method = RequestMethod.GET)
    public List<User> getUserList() {

        Map<String, Object> map = new HashMap<>();
        return userService.findUserInfo();

    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "ipAddr", value = "ip哟", required = false, dataType = "String", paramType = "form")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@ApiIgnore User user) {
        users.put(user.getId(), user);
        return "success";
    }


    @GetMapping("/app/check/state")
    public Observable<User> getState(){
       return null;
    }

}
