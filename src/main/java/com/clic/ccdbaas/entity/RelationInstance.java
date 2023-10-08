package com.clic.ccdbaas.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RelationInstance {

    private String resId;
    private String sourceInstanceId;
    private String targetInstanceId;
    private String relationId;
    private Date lastModified;
    private String description;
    private String remark;
    private String extraSpec;
}
