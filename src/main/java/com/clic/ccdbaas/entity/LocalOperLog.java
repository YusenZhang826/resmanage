package com.clic.ccdbaas.entity;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/3/13 17:31
 * @email chenjianhua@bmsoft.com.cn
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.model.BaseEntity;
import com.clic.ccdbaas.utils.excel.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录表 oper_log
 *
 * @author cjh
 */
@Data
@TableName("local_oper_log")
public class LocalOperLog extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long operId;

    private String resId;

    private String attribute;


    private Object originVal;

    private Object resVal;

    private String resName;

    private String resType;

    private String beginTime;
    private String endTime;
    private String paramValue;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Excel(name = "业务类型", readConverterExp = "0=其它,1=新增,2=修改,3=删除")
    private Integer businessType;


    /**
     * 操作人员
     */
    @Excel(name = "操作人员")
    private String operName;


    /**
     * 操作状态（0正常 1异常）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=异常")
    private Integer status;

    /**
     * 错误消息
     */
    @Excel(name = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

}
