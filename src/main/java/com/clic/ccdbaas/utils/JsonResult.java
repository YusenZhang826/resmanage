package com.clic.ccdbaas.utils;

/**
 * @author jwj
 */
public class JsonResult {

    private Integer code;
    private Object obj;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonResult(Integer code, Object obj, String msg) {
        this.code = code;
        this.obj = obj;
        this.msg = msg;
    }

    public JsonResult() {

    }
}
