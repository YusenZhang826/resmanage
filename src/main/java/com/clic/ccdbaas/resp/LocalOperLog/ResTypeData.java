package com.clic.ccdbaas.resp.LocalOperLog;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResTypeData {
    String resType;
    List<OneDayDetail> detail = new ArrayList<>();
}
