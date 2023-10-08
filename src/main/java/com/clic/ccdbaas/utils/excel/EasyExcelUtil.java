package com.clic.ccdbaas.utils.excel;


import com.alibaba.excel.EasyExcel;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EasyExcelUtil {
    private static final EasyExcelUtil INSTANCE = new EasyExcelUtil();

    private EasyExcelUtil() {
    }

    public static EasyExcelUtil getInstance() {
        return INSTANCE;
    }

    private void setResponse(HttpServletResponse response, String title) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + StringUtils.getFilename(title) + ".xlsx");
    }

    public void exportExcel(HttpServletResponse response, Class clazz, List list, String sheetName, String title) {
        if (list != null && list.get(0).getClass() != clazz) {
            throw new RuntimeException("导出数据类型不匹配");
        }
        setResponse(response, title);
        try {
            System.out.println(clazz);
            System.out.println(list);
            EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void exportExcel(HttpServletResponse response, Class clazz, List list, String title) {
        exportExcel(response, clazz, list, "sheet1", title);
    }

    public void exportExcel(HttpServletResponse response, Class clazz, List list) {
        exportExcel(response, clazz, list, "sheet1", "导出数据");
    }

    public void exportExcelTemplate(HttpServletResponse response, Class clazz, String sheetName, String title) {
        exportExcel(response, clazz, null, sheetName, title);
    }

    public void exportExcelTemplate(HttpServletResponse response, Class clazz, String title) {
        exportExcel(response, clazz, null, "sheet1", title);
    }

    public void exportExcelTemplate(HttpServletResponse response, Class clazz) {
        exportExcel(response, clazz, null, "sheet1", "导出模板");
    }
}
