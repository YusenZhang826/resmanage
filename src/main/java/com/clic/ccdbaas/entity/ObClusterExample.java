package com.clic.ccdbaas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObClusterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObClusterExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andObClusterIdIsNull() {
            addCriterion("obClusterId is null");
            return (Criteria) this;
        }

        public Criteria andObClusterIdIsNotNull() {
            addCriterion("obClusterId is not null");
            return (Criteria) this;
        }

        public Criteria andObClusterIdEqualTo(String value) {
            addCriterion("obClusterId =", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdNotEqualTo(String value) {
            addCriterion("obClusterId <>", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdGreaterThan(String value) {
            addCriterion("obClusterId >", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdGreaterThanOrEqualTo(String value) {
            addCriterion("obClusterId >=", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdLessThan(String value) {
            addCriterion("obClusterId <", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdLessThanOrEqualTo(String value) {
            addCriterion("obClusterId <=", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdLike(String value) {
            addCriterion("obClusterId like", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdNotLike(String value) {
            addCriterion("obClusterId not like", value, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdIn(List<String> values) {
            addCriterion("obClusterId in", values, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdNotIn(List<String> values) {
            addCriterion("obClusterId not in", values, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdBetween(String value1, String value2) {
            addCriterion("obClusterId between", value1, value2, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andObClusterIdNotBetween(String value1, String value2) {
            addCriterion("obClusterId not between", value1, value2, "obClusterId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andArchitectureIsNull() {
            addCriterion("architecture is null");
            return (Criteria) this;
        }

        public Criteria andArchitectureIsNotNull() {
            addCriterion("architecture is not null");
            return (Criteria) this;
        }

        public Criteria andArchitectureEqualTo(String value) {
            addCriterion("architecture =", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotEqualTo(String value) {
            addCriterion("architecture <>", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThan(String value) {
            addCriterion("architecture >", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThanOrEqualTo(String value) {
            addCriterion("architecture >=", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThan(String value) {
            addCriterion("architecture <", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThanOrEqualTo(String value) {
            addCriterion("architecture <=", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureLike(String value) {
            addCriterion("architecture like", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotLike(String value) {
            addCriterion("architecture not like", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureIn(List<String> values) {
            addCriterion("architecture in", values, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotIn(List<String> values) {
            addCriterion("architecture not in", values, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureBetween(String value1, String value2) {
            addCriterion("architecture between", value1, value2, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotBetween(String value1, String value2) {
            addCriterion("architecture not between", value1, value2, "architecture");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andObVersionIsNull() {
            addCriterion("obVersion is null");
            return (Criteria) this;
        }

        public Criteria andObVersionIsNotNull() {
            addCriterion("obVersion is not null");
            return (Criteria) this;
        }

        public Criteria andObVersionEqualTo(String value) {
            addCriterion("obVersion =", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionNotEqualTo(String value) {
            addCriterion("obVersion <>", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionGreaterThan(String value) {
            addCriterion("obVersion >", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionGreaterThanOrEqualTo(String value) {
            addCriterion("obVersion >=", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionLessThan(String value) {
            addCriterion("obVersion <", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionLessThanOrEqualTo(String value) {
            addCriterion("obVersion <=", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionLike(String value) {
            addCriterion("obVersion like", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionNotLike(String value) {
            addCriterion("obVersion not like", value, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionIn(List<String> values) {
            addCriterion("obVersion in", values, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionNotIn(List<String> values) {
            addCriterion("obVersion not in", values, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionBetween(String value1, String value2) {
            addCriterion("obVersion between", value1, value2, "obVersion");
            return (Criteria) this;
        }

        public Criteria andObVersionNotBetween(String value1, String value2) {
            addCriterion("obVersion not between", value1, value2, "obVersion");
            return (Criteria) this;
        }

        public Criteria andServerCountIsNull() {
            addCriterion("serverCount is null");
            return (Criteria) this;
        }

        public Criteria andServerCountIsNotNull() {
            addCriterion("serverCount is not null");
            return (Criteria) this;
        }

        public Criteria andServerCountEqualTo(Integer value) {
            addCriterion("serverCount =", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountNotEqualTo(Integer value) {
            addCriterion("serverCount <>", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountGreaterThan(Integer value) {
            addCriterion("serverCount >", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("serverCount >=", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountLessThan(Integer value) {
            addCriterion("serverCount <", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountLessThanOrEqualTo(Integer value) {
            addCriterion("serverCount <=", value, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountIn(List<Integer> values) {
            addCriterion("serverCount in", values, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountNotIn(List<Integer> values) {
            addCriterion("serverCount not in", values, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountBetween(Integer value1, Integer value2) {
            addCriterion("serverCount between", value1, value2, "serverCount");
            return (Criteria) this;
        }

        public Criteria andServerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("serverCount not between", value1, value2, "serverCount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTenantCountIsNull() {
            addCriterion("tenantCount is null");
            return (Criteria) this;
        }

        public Criteria andTenantCountIsNotNull() {
            addCriterion("tenantCount is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCountEqualTo(Integer value) {
            addCriterion("tenantCount =", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountNotEqualTo(Integer value) {
            addCriterion("tenantCount <>", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountGreaterThan(Integer value) {
            addCriterion("tenantCount >", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("tenantCount >=", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountLessThan(Integer value) {
            addCriterion("tenantCount <", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountLessThanOrEqualTo(Integer value) {
            addCriterion("tenantCount <=", value, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountIn(List<Integer> values) {
            addCriterion("tenantCount in", values, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountNotIn(List<Integer> values) {
            addCriterion("tenantCount not in", values, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountBetween(Integer value1, Integer value2) {
            addCriterion("tenantCount between", value1, value2, "tenantCount");
            return (Criteria) this;
        }

        public Criteria andTenantCountNotBetween(Integer value1, Integer value2) {
            addCriterion("tenantCount not between", value1, value2, "tenantCount");
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

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
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