package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    @Autowired
    UserService userService;
    public JSONObject getToken(JSONObject requestParams){
        Map<String,Object> map = new HashMap<>();
        String employeeNo = (String) requestParams.get("employeeNo");
        String password = (String) requestParams.get("password");
        long userId = userService.getUserId(employeeNo,password);
        if(userId != 0){
            String res = TokenUtil.sign(employeeNo, String.valueOf(userId));
            map.put("employeeNo",employeeNo);
            map.put("Authorization", res);
            map.put("有效时长",(TokenUtil.getExpireTime())/60000+"分钟");
        }
        else{
            map.put("msg","工号或密码错误");
        }
        return new JSONObject(map);
    }
}
