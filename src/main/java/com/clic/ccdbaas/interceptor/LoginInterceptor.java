package com.clic.ccdbaas.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.TokenUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        /**if(request.getHeader("Authorization") != null && request.getHeader("Authorization").length()>=6){
            String token = (request.getHeader("Authorization")).substring(5);
            if(TokenUtil.verify(token)){
                return true;
            }
        }**/
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "Bearer ");
        if(TokenUtil.verify(token)){
            return true;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        //返回的数据
        JSONObject res = new JSONObject();
        if(request.getHeader("Authorization") != null){
            res.put("msg","无效token，请重新获取！");
        }else{
            res.put("msg","请获取token！");
        }
        PrintWriter out = null ;
        out = response.getWriter();
        out.write(res.toString());
        out.flush();
        out.close();
        return false;
    }
}
