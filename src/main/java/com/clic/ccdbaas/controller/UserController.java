package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.service.UserService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/3/6 10:47
 * @email chenjianhua@bmsoft.com.cn
 */
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping(value = "/initUser")
    public JsonResult initUsers() {
        try {
            return renderSuccess(userService.initUserInfo());
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
    }

    @GetMapping("/initDept")
    public JsonResult initDepts() {
        return renderSuccess(userService.initDept());
    }

    @GetMapping("/card")
    public JsonResult getUserCard(@RequestParam("employeeNo") String empNo) {
        return renderSuccess(userService.getUserCardInfoByEmpNo(empNo));
    }
}
