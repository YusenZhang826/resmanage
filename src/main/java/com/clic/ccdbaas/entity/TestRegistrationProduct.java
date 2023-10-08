package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@TableName(value = "t_test_acs_product_info")
public class TestRegistrationProduct implements Serializable {
    private String resId;
    private String description;
    private String detailDesc;
    private String dmUids;
    private String dtmUids;
    private String name;
    private String omUids;
    private String productionToken;
    private String owner;
    private String smUids;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestRegistrationProduct that = (TestRegistrationProduct) o;
        return Objects.equals(description, that.description) && Objects.equals(detailDesc, that.detailDesc) && Objects.equals(dmUids, that.dmUids) && Objects.equals(dtmUids, that.dtmUids) && Objects.equals(name, that.name) && Objects.equals(omUids, that.omUids) && Objects.equals(productionToken, that.productionToken) && Objects.equals(owner, that.owner) && Objects.equals(smUids, that.smUids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, detailDesc, dmUids, dtmUids, name, omUids, productionToken, owner, smUids);
    }

    public String getProductionToken() {
        return productionToken;
    }

    public void setProductionToken(String productionToken) {
        this.productionToken = productionToken;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDmUids() {
        return dmUids;
    }

    public void setDmUids(String dmUids) {
        this.dmUids = dmUids;
    }

    public String getDtmUids() {
        return dtmUids;
    }

    public void setDtmUids(String dtmUids) {
        this.dtmUids = dtmUids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOmUids() {
        return omUids;
    }

    public void setOmUids(String omUids) {
        this.omUids = omUids;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSmUids() {
        return smUids;
    }

    public void setSmUids(String smUids) {
        this.smUids = smUids;
    }
}
