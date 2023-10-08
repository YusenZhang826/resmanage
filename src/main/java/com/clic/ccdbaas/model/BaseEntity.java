package com.clic.ccdbaas.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
//`createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//`updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
//`createBy` varchar(255) DEFAULT '' NOT NULL,
//`updateBy` varchar(255) DEFAULT '' NOT NULL,
//`remark` VARCHAR(255) DEFAULT '' NOT NULL,

    /**
     * 创建者
     */
    @ExcelProperty("创建者")
    protected String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    /**
     * 更新者
     */
    protected String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    /**
     * 备注
     */
    protected String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    protected Map<String, String> getClassInfo() {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String variableName = field.getName();
                if (variableName.equals("serialVersionUID") || variableName.equals("params") || variableName.equals("searchValue")) {
                    continue;
                }
                String simpleTypeName = field.getType().getSimpleName();
                map.put(variableName, simpleTypeName);
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

    public String generateKey() {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = this.getClass();
        sb.append(clazz.getSimpleName()).append(":");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) continue;
            try {
                Object value = field.get(this);
                if (value != null) {
                    sb.append(field.getName()).append("=").append(value).append("&");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
