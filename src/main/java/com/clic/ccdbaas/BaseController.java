package com.clic.ccdbaas;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.BusinessException;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.ReflectUtils;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author cjh
 */
@RestControllerAdvice
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected JsonResult renderSuccess() {
        return renderSuccess("success");
    }

    protected JsonResult renderSuccess(String msg) {
        return renderSuccess(null, msg);
    }

    protected JsonResult renderSuccess(Object obj) {
        return renderSuccess(obj, "success");
    }

    protected JsonResult renderSuccess(Object obj, String msg) {
        return new JsonResult(HttpStatus.OK.value(), obj, msg);
    }


    protected JsonResult renderError(Integer code, Object obj, String msg) {
        return new JsonResult(code, obj, msg);
    }

    protected JsonResult renderError(Integer code, String msg) {
        return renderError(code, null, msg);
    }

    protected JsonResult renderError(Integer code) {
        return renderError(code, null, "failed");
    }

    protected JsonResult renderError(String msg) {
        return renderError(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    protected JsonResult renderError(String code, String msg) {
        return renderError(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    protected JsonResult rendBusinessError(int code, String msg) {
        return new JsonResult(code, null, msg);
    }

    @ExceptionHandler(value = Exception.class)
    public JsonResult errorHandler(Exception ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public JsonResult businessErrorHandler(BusinessException ex) {
        logger.error(ex.getMessage());
        return rendBusinessError(ex.getCode(), ex.getMsg());
    }


    //未登录认证
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResult authenticationExceptionHandler(AuthenticationException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());
    }

//    //未授权
//    @ExceptionHandler(AuthorizationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public JsonResult authorizationExceptionHandler(AuthorizationException ex) {
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return  renderError(HttpStatus.FORBIDDEN.value(),ex.getMessage());
//    }

//    //参数错误异常
//    @ExceptionHandler(ParameterInvalidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//
//    public JsonResult parameterInvalidExceptionHandler(ParameterInvalidException ex) {
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return  renderError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
//    }

//    //数据找不到异常
//    @ExceptionHandler(DataNotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//
//    public JsonResult dataNotFoundExceptionHandler(DataNotFoundException ex) {
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return  renderError(ex.getMessage());
//    }


    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)

    public JsonResult runtimeExceptionHandler(RuntimeException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(1000, null);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public JsonResult nullPointerExceptionHandler(NullPointerException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());

    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult classCastExceptionHandler(ClassCastException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(1002, null);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult iOExceptionHandler(IOException ex) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return renderError(ex.getMessage());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        logger.error("noSuchMethodExceptionHandler");
        ex.printStackTrace();
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(1004, null);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error("--------indexOutOfBoundsExceptionHandler-------");
        ex.printStackTrace();
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(1005, null);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult requestNotReadable(HttpMessageNotReadableException ex) {
        logger.error("--------400..requestNotReadable--------------");
        ex.printStackTrace();
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(400, null);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult requestTypeMismatch(TypeMismatchException ex) {
        logger.error("400..TypeMismatchException");
        ex.printStackTrace();

        return renderError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        //return ReturnFormat.retParam(400, null);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
        logger.error("---------------400..MissingServletRequest--------------");
        ex.printStackTrace();
        return renderError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        //return ReturnFormat.retParam(400, null);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public JsonResult request405(HttpRequestMethodNotSupportedException ex) {
        logger.error("--------------405----------------------");
        ex.printStackTrace();
        return renderError(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());

    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public JsonResult request406(HttpMediaTypeNotAcceptableException ex) {
        logger.error("-------------------------406..----------------");
        return renderError(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
        //return ReturnFormat.retParam(406, null);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult server500(RuntimeException ex) {
        logger.error("..................500........................");
        return renderError(ex.getMessage());
        //return ReturnFormat.retParam(406, null);
    }

    protected <T> JsonResult renderUploadExcelFileResult(MultipartFile file, BaseReadByLineListener<T> readListener, Class<T> c) {
        try {
            ExcelUtil.checkExcelFile(file);
            InputStream excelInputStream = file.getInputStream();
            EasyExcel.read(excelInputStream, c, readListener).sheet().doRead();
            return renderSuccess(readListener.getResult());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.info("Excel文件存在错误");
            return renderError("Excel文件存在错误");
        }
    }

    protected <T> JsonResult renderClassInfo(Class clazz) {
        Map<String, String> classInfoMap = ReflectUtils.getClassAttrsInfo(clazz);
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<String, String> entry : classInfoMap.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", entry.getKey());
            jsonObject.put("type", entry.getValue());
            jsonArray.add(jsonObject);
        }
        JSONObject result = new JSONObject();
        result.put("name", clazz.getSimpleName());
        result.put("member", jsonArray);
        return renderSuccess(result);
    }

    protected <T> JsonResult renderUploadExcelFileResult(MultipartFile file, BaseReadByLineListener<T> readListener, Class<T> c, Integer sheetNo, Integer headRowNumber) {
        try {
            ExcelUtil.checkExcelFile(file);
            ExcelReader excelReader = EasyExcelFactory.read(file.getInputStream()).build();
            //构造器
            ReadSheet dealerSheet = EasyExcelFactory
                    .readSheet(sheetNo)
                    .head(c)
                    .registerReadListener(readListener)
                    .headRowNumber(headRowNumber)
                    .build();
            excelReader.read(dealerSheet);
            return renderSuccess(readListener.getResult());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.info("Excel文件存在错误");
            return renderError("Excel文件存在错误");
        }
    }
}
