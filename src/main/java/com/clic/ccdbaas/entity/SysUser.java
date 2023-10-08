package com.clic.ccdbaas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/3/6 12:56
 * @email chenjianhua@bmsoft.com.cn
 */
@Data
public class SysUser {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    private String employeeNo;

    private String userName;

    private String password;

    private String newPassword;

    private String phonenumber;

    private String email;

    private String createBy;

    private String status;

    private String delFlag;

    private Long groupId;

    private Long deptId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
