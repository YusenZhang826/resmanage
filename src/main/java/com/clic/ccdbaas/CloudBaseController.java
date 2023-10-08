package com.clic.ccdbaas;

import com.alibaba.fastjson.JSONArray;
import com.clic.ccdbaas.model.AjaxResult;
import com.clic.ccdbaas.model.HttpStatus;
import com.clic.ccdbaas.page.PageDomain;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.page.TableSupport;
import com.clic.ccdbaas.utils.*;
import com.clic.ccdbaas.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;


/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class CloudBaseController extends  BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
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
        return new JsonResult(org.springframework.http.HttpStatus.OK.value(), obj, msg);
    }
    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage()
    {
        PageUtils.clearPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(Map<String, Object> resultInfo)
    {
        TableDataInfo rspData = new TableDataInfo();
        for (Map.Entry<String, Object> entry : resultInfo.entrySet()) {
            if (entry.getKey().equals("rows")) rspData.setRows((List) entry.getValue());
            else rspData.setTotal((Integer) entry.getValue());
        }
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getOcDataTable(List<?> list,int totalNum)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(totalNum);
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getOcDataTable(JSONArray jsonArr, int totalNum)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(jsonArr);
        rspData.setTotal(totalNum);
        return rspData;
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return com.clic.ccdbaas.model.AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public com.clic.ccdbaas.model.AjaxResult error()
    {
        return com.clic.ccdbaas.model.AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public com.clic.ccdbaas.model.AjaxResult success(String message)
    {
        return com.clic.ccdbaas.model.AjaxResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public com.clic.ccdbaas.model.AjaxResult success(Object data)
    {
        return com.clic.ccdbaas.model.AjaxResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public com.clic.ccdbaas.model.AjaxResult error(String message)
    {
        return com.clic.ccdbaas.model.AjaxResult.error(message);
    }

    /**
     * 返回警告消息
     */
    public com.clic.ccdbaas.model.AjaxResult warn(String message)
    {
        return com.clic.ccdbaas.model.AjaxResult.warn(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected com.clic.ccdbaas.model.AjaxResult toAjax(int rows)
    {
        return rows > 0 ? com.clic.ccdbaas.model.AjaxResult.success() : com.clic.ccdbaas.model.AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected com.clic.ccdbaas.model.AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }



}
