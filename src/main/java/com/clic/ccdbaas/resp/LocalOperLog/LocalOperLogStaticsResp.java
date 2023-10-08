package com.clic.ccdbaas.resp.LocalOperLog;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocalOperLogStaticsResp {
    List<String> x = new ArrayList<>();
    List<ResTypeData> data = new ArrayList<>();
}
