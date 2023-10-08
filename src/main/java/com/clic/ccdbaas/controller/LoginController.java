package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

@RestController
@RequestMapping(value = {"/v1"})
public class LoginController extends BaseController {
    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/getToken")
    public JSONObject userLogin(@RequestBody JSONObject requestParams){
        return tokenService.getToken(requestParams);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "成功";
    }
    @GetMapping(value = "/test1")
    public String test1(){
        return "成功";
    }
}
