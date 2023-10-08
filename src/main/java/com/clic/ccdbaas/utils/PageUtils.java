package com.clic.ccdbaas.utils;
import com.clic.ccdbaas.model.HttpStatus;
import com.clic.ccdbaas.page.PageDomain;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.page.TableSupport;
import com.clic.ccdbaas.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    public static TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
