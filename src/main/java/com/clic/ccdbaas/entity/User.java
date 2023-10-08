package com.clic.ccdbaas.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private long userId;
    private String employeeNo;
    private String password;
}
