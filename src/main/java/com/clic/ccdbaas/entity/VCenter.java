package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class VCenter {
    @TableId(type = IdType.ASSIGN_UUID)
    private String resId;
    private String version;
    private String name;
    private String host;
    private String username;

    @TableField(exist = false)
    private String password;
    private String remark;

    private String secret;
}
