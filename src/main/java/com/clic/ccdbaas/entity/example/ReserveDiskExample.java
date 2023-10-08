package com.clic.ccdbaas.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReserveDiskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReserveDiskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andResIdIsNull() {
            addCriterion("resId is null");
            return (Criteria) this;
        }

        public Criteria andResIdIsNotNull() {
            addCriterion("resId is not null");
            return (Criteria) this;
        }

        public Criteria andResIdEqualTo(String value) {
            addCriterion("resId =", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdNotEqualTo(String value) {
            addCriterion("resId <>", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdGreaterThan(String value) {
            addCriterion("resId >", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdGreaterThanOrEqualTo(String value) {
            addCriterion("resId >=", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdLessThan(String value) {
            addCriterion("resId <", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdLessThanOrEqualTo(String value) {
            addCriterion("resId <=", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdLike(String value) {
            addCriterion("resId like", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdNotLike(String value) {
            addCriterion("resId not like", value, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdIn(List<String> values) {
            addCriterion("resId in", values, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdNotIn(List<String> values) {
            addCriterion("resId not in", values, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdBetween(String value1, String value2) {
            addCriterion("resId between", value1, value2, "resId");
            return (Criteria) this;
        }

        public Criteria andResIdNotBetween(String value1, String value2) {
            addCriterion("resId not between", value1, value2, "resId");
            return (Criteria) this;
        }

        public Criteria andDiskSizeIsNull() {
            addCriterion("diskSize is null");
            return (Criteria) this;
        }

        public Criteria andDiskSizeIsNotNull() {
            addCriterion("diskSize is not null");
            return (Criteria) this;
        }

        public Criteria andDiskSizeEqualTo(Integer value) {
            addCriterion("diskSize =", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeNotEqualTo(Integer value) {
            addCriterion("diskSize <>", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeGreaterThan(Integer value) {
            addCriterion("diskSize >", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("diskSize >=", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeLessThan(Integer value) {
            addCriterion("diskSize <", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeLessThanOrEqualTo(Integer value) {
            addCriterion("diskSize <=", value, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeIn(List<Integer> values) {
            addCriterion("diskSize in", values, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeNotIn(List<Integer> values) {
            addCriterion("diskSize not in", values, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeBetween(Integer value1, Integer value2) {
            addCriterion("diskSize between", value1, value2, "diskSize");
            return (Criteria) this;
        }

        public Criteria andDiskSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("diskSize not between", value1, value2, "diskSize");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andUsesIsNull() {
            addCriterion("uses is null");
            return (Criteria) this;
        }

        public Criteria andUsesIsNotNull() {
            addCriterion("uses is not null");
            return (Criteria) this;
        }

        public Criteria andUsesEqualTo(String value) {
            addCriterion("uses =", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesNotEqualTo(String value) {
            addCriterion("uses <>", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesGreaterThan(String value) {
            addCriterion("uses >", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesGreaterThanOrEqualTo(String value) {
            addCriterion("uses >=", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesLessThan(String value) {
            addCriterion("uses <", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesLessThanOrEqualTo(String value) {
            addCriterion("uses <=", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesLike(String value) {
            addCriterion("uses like", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesNotLike(String value) {
            addCriterion("uses not like", value, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesIn(List<String> values) {
            addCriterion("uses in", values, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesNotIn(List<String> values) {
            addCriterion("uses not in", values, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesBetween(String value1, String value2) {
            addCriterion("uses between", value1, value2, "uses");
            return (Criteria) this;
        }

        public Criteria andUsesNotBetween(String value1, String value2) {
            addCriterion("uses not between", value1, value2, "uses");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassIsNull() {
            addCriterion("interfaceClass is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassIsNotNull() {
            addCriterion("interfaceClass is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassEqualTo(String value) {
            addCriterion("interfaceClass =", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassNotEqualTo(String value) {
            addCriterion("interfaceClass <>", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassGreaterThan(String value) {
            addCriterion("interfaceClass >", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassGreaterThanOrEqualTo(String value) {
            addCriterion("interfaceClass >=", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassLessThan(String value) {
            addCriterion("interfaceClass <", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassLessThanOrEqualTo(String value) {
            addCriterion("interfaceClass <=", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassLike(String value) {
            addCriterion("interfaceClass like", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassNotLike(String value) {
            addCriterion("interfaceClass not like", value, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassIn(List<String> values) {
            addCriterion("interfaceClass in", values, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassNotIn(List<String> values) {
            addCriterion("interfaceClass not in", values, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassBetween(String value1, String value2) {
            addCriterion("interfaceClass between", value1, value2, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andInterfaceClassNotBetween(String value1, String value2) {
            addCriterion("interfaceClass not between", value1, value2, "interfaceClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassIsNull() {
            addCriterion("mediaClass is null");
            return (Criteria) this;
        }

        public Criteria andMediaClassIsNotNull() {
            addCriterion("mediaClass is not null");
            return (Criteria) this;
        }

        public Criteria andMediaClassEqualTo(String value) {
            addCriterion("mediaClass =", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassNotEqualTo(String value) {
            addCriterion("mediaClass <>", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassGreaterThan(String value) {
            addCriterion("mediaClass >", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassGreaterThanOrEqualTo(String value) {
            addCriterion("mediaClass >=", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassLessThan(String value) {
            addCriterion("mediaClass <", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassLessThanOrEqualTo(String value) {
            addCriterion("mediaClass <=", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassLike(String value) {
            addCriterion("mediaClass like", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassNotLike(String value) {
            addCriterion("mediaClass not like", value, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassIn(List<String> values) {
            addCriterion("mediaClass in", values, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassNotIn(List<String> values) {
            addCriterion("mediaClass not in", values, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassBetween(String value1, String value2) {
            addCriterion("mediaClass between", value1, value2, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andMediaClassNotBetween(String value1, String value2) {
            addCriterion("mediaClass not between", value1, value2, "mediaClass");
            return (Criteria) this;
        }

        public Criteria andServerIdIsNull() {
            addCriterion("serverId is null");
            return (Criteria) this;
        }

        public Criteria andServerIdIsNotNull() {
            addCriterion("serverId is not null");
            return (Criteria) this;
        }

        public Criteria andServerIdEqualTo(String value) {
            addCriterion("serverId =", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotEqualTo(String value) {
            addCriterion("serverId <>", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThan(String value) {
            addCriterion("serverId >", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdGreaterThanOrEqualTo(String value) {
            addCriterion("serverId >=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThan(String value) {
            addCriterion("serverId <", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLessThanOrEqualTo(String value) {
            addCriterion("serverId <=", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdLike(String value) {
            addCriterion("serverId like", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotLike(String value) {
            addCriterion("serverId not like", value, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdIn(List<String> values) {
            addCriterion("serverId in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotIn(List<String> values) {
            addCriterion("serverId not in", values, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdBetween(String value1, String value2) {
            addCriterion("serverId between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andServerIdNotBetween(String value1, String value2) {
            addCriterion("serverId not between", value1, value2, "serverId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("createBy is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("createBy is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("createBy =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("createBy <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("createBy >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("createBy >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("createBy <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("createBy <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("createBy like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("createBy not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("createBy in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("createBy not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("createBy between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("createBy not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("updateBy is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("updateBy is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("updateBy =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("updateBy <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("updateBy >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("updateBy >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("updateBy <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("updateBy <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("updateBy like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("updateBy not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("updateBy in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("updateBy not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("updateBy between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("updateBy not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}