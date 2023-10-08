package com.clic.ccdbaas.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BaseReadByLineListener<T> implements ReadListener<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseReadByLineListener.class);
    /**
     * 单次缓存的数据量
     */
    public static final int BATCH_COUNT = 1;
    /**
     * 临时存储
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private Integer line = 1;
    private Integer successLineNum = 0;
    private Integer errorLineNum = 0;

    private final JSONObject jsonObject = new JSONObject();

    private final JSONArray errorLineList = new JSONArray();

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (ReflectUtils.isNullInstance(data)) return;
        cachedDataList.add(data);
        saveData();
        // 存储完成清理 list
        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        jsonObject.put("successLineNum", successLineNum);
        jsonObject.put("errorLineNum", errorLineNum);
        jsonObject.put("errorInfo", errorLineList);
        logger.info("Excel解析完成:{}", jsonObject);
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("解析第{}行成功:", line);
        T t = cachedDataList.get(0);
        try {
            //处理解析到的一行数据
            process(t);
            successLineNum += 1;
        } catch (Exception e) {
            e.printStackTrace();
            errorLineList.add(String.format("第%d行处理失败，错误信息:%s", line, e.getMessage()));
            errorLineNum++;
        } finally {
            line++;
        }
    }

    public abstract void process(T t);

    public JSONObject getResult() {
        return jsonObject;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        logger.info("解析第{}行失败，但是继续解析下一行:{}", line, exception.getMessage());
        errorLineNum += 1;

        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            errorLineList.add(String.format("第%d行,第%d列解析异常:%s", line, excelDataConvertException.getColumnIndex() + 1, excelDataConvertException.getMessage()));
        } else {
            errorLineList.add(String.format("第%d行未知错误:%s", line, exception.getMessage()));
        }
        line++;
    }
}
