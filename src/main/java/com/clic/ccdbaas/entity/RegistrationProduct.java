package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@TableName("t_acs_product_info")
public class RegistrationProduct implements Serializable {
    private String resId;
    @Excel(name = "简称")
    private String abbr;
    @Excel(name = "产品头像")
    private String avatar;
    @Excel(name = "产品状态")
    private String changingInfo;
    @Excel(name = "数据安全等级")
    private Integer dataLevel;
    @Excel(name = "产品等级")
    private String productLevel;
    @Excel(name = "产品概述")
    private String desc;
    @Excel(name = "产品详细描述")
    private String detailDesc;
    @Excel(name = "需求管理员")
    private String dmUids;
    @Excel(name = "开发测试管理员")
    private String dtmUids;
    @Excel(name = "产品入口信息描述")
    private String entrance;
    @Excel(name = "等保级别")
    private Integer gpLevel;
    @Excel(name = "群聊机器人Id")
    private String groupRobotId;
    @Excel(name = "群聊机器人名称")
    private String groupRobotName;
    @Excel(name = "产品建设单位idpath")
    private String mainUgIdpath;
    @Excel(name = "产品建设单位名称")
    private String mainUgIdpathInfo;
    @Excel(name = "产品名称")
    private String name;
    @Excel(name = "发布对象描述")
    private String object;
    @Excel(name = "运营人员")
    private String omUids;
    @Excel(name = "产品经理")
    private String owner;
    @Excel(name = "父产品简称")
    private String parentAbbr;
    @Excel(name = "父产品建设单位idpath")
    private String parentMainUgIdpath;
    @Excel(name = "父产品建设单位名称")
    private String parentMainUgIdpathInfo;
    @Excel(name = "父产品名称")
    private String parentName;
    @Excel(name = "父产品Token")
    private String parentToken;
    @Excel(name = "产品告警群群号")
    private String productMonitorWarnDisposalGroupId;
    @Excel(name = "产品安全群号")
    private String productSafeAnswerGroupId;
    @Excel(name = "发布状态（0-新建的 1-审核中 2-已发布")
    private Integer publish;
    @Excel(name = "大产品团队idpath")
    private String secondaryUgIdpath;
    @Excel(name = "大产品团队名称")
    private String secondaryUgIdpathInfo;
    @Excel(name = "单聊机器人id")
    private String singleRobotId;
    @Excel(name = "单聊机器人名称")
    private String singleRobotName;
    @Excel(name = "系统管理员")
    private String smUids;
    private String relatedEmpNo;
    @Excel(name = "子团队名称")
    private String teamName;
    private String thirdUgIdpath;
    private String thirdUgIdpathInfo;
    @Excel(name = "产品token")
    private String productToken;
    @Excel(name = "产品用户群")
    private String userGroupId;
    @Excel(name = "最终更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date lastModified;
    private static final long serialVersionUID = 1L;

    private int status;

    private List statusArr;

    private List resIdArr;



}