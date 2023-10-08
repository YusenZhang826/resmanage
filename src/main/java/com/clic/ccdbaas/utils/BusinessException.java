package com.clic.ccdbaas.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2020/6/22/022 9:54
 * @email chenjianhua@bmsoft.com.cn
 */
@Data
public class BusinessException extends RuntimeException {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private String msg;

    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();//500
        this.msg = msg;
    }


}
