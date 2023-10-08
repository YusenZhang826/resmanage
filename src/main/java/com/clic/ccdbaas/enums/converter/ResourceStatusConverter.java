package com.clic.ccdbaas.enums.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.clic.ccdbaas.enums.ResourceStatus;

public class ResourceStatusConverter implements Converter<ResourceStatus> {
    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public ResourceStatus convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        ResourceStatus resourceStatus = ResourceStatus.getEnum(cellData.getStringValue());
        if (resourceStatus == null) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return resourceStatus;
    }

    /**
     * 将从数据库中查到的数据转换为 Excel 展示的数据
     *
     * @param value 枚举对象
     */
    @Override
    public WriteCellData convertToExcelData(ResourceStatus value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 将枚举类型按照 key 传值
        return new WriteCellData(value.getValue());
    }
}
