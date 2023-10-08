package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_cloud_volume")
public class CloudVolume implements Serializable {
    private String resId;

    private String nativeid;

    private String name;

    private String status;

    private String imageid;

    private String projectid;

    private String userid;

    private String paramValue;
    private Integer size;

    private Boolean encrypted;

    private Boolean bootable;

    private String description;

    private Date createdAt;

    private String azoneid;

    private List<CloudVmare> cloudVmArr;

    private String azoneName;

    private String sharetype;

    private String sanpshotid;

    private String id;

    private String bizRegionId;

    private String bizRegionName;

    private Date lastmodified;

    private static final long serialVersionUID = 1L;

    private String vmId;


}