package com.clic.ccdbaas.model;

import lombok.Data;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/6 15:29
 * @email chenjianhua@bmsoft.com.cn
 */
@Data
public class ComparbleResult {
    /**
     * 变更字段
     */
    private String fieldName;

    /**
     * 变更前类的内容容
     */
    private Object fieldContent;
    /**
     * 变更后类的内容容
     */
    private Object newFieldContent;
    /**
     * 变更的枚举类型
     */
    private String handerType;


}
