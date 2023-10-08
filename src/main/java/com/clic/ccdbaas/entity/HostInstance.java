package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用于全量主机实例视图
 */
@Data
@TableName("t_host_instance_view")
public class HostInstance extends CloudVmare {
}
