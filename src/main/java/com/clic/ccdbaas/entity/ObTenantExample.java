package com.clic.ccdbaas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObTenantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ObTenantExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("regionId is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("regionId is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(String value) {
            addCriterion("regionId =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(String value) {
            addCriterion("regionId <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(String value) {
            addCriterion("regionId >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(String value) {
            addCriterion("regionId >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(String value) {
            addCriterion("regionId <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(String value) {
            addCriterion("regionId <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLike(String value) {
            addCriterion("regionId like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotLike(String value) {
            addCriterion("regionId not like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<String> values) {
            addCriterion("regionId in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<String> values) {
            addCriterion("regionId not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(String value1, String value2) {
            addCriterion("regionId between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(String value1, String value2) {
            addCriterion("regionId not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdIsNull() {
            addCriterion("obTenantId is null");
            return (Criteria) this;
        }

        public Criteria andObTenantIdIsNotNull() {
            addCriterion("obTenantId is not null");
            return (Criteria) this;
        }

        public Criteria andObTenantIdEqualTo(String value) {
            addCriterion("obTenantId =", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdNotEqualTo(String value) {
            addCriterion("obTenantId <>", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdGreaterThan(String value) {
            addCriterion("obTenantId >", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("obTenantId >=", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdLessThan(String value) {
            addCriterion("obTenantId <", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdLessThanOrEqualTo(String value) {
            addCriterion("obTenantId <=", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdLike(String value) {
            addCriterion("obTenantId like", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdNotLike(String value) {
            addCriterion("obTenantId not like", value, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdIn(List<String> values) {
            addCriterion("obTenantId in", values, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdNotIn(List<String> values) {
            addCriterion("obTenantId not in", values, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdBetween(String value1, String value2) {
            addCriterion("obTenantId between", value1, value2, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andObTenantIdNotBetween(String value1, String value2) {
            addCriterion("obTenantId not between", value1, value2, "obTenantId");
            return (Criteria) this;
        }

        public Criteria andClusterIdIsNull() {
            addCriterion("clusterId is null");
            return (Criteria) this;
        }

        public Criteria andClusterIdIsNotNull() {
            addCriterion("clusterId is not null");
            return (Criteria) this;
        }

        public Criteria andClusterIdEqualTo(String value) {
            addCriterion("clusterId =", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdNotEqualTo(String value) {
            addCriterion("clusterId <>", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdGreaterThan(String value) {
            addCriterion("clusterId >", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdGreaterThanOrEqualTo(String value) {
            addCriterion("clusterId >=", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdLessThan(String value) {
            addCriterion("clusterId <", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdLessThanOrEqualTo(String value) {
            addCriterion("clusterId <=", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdLike(String value) {
            addCriterion("clusterId like", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdNotLike(String value) {
            addCriterion("clusterId not like", value, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdIn(List<String> values) {
            addCriterion("clusterId in", values, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdNotIn(List<String> values) {
            addCriterion("clusterId not in", values, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdBetween(String value1, String value2) {
            addCriterion("clusterId between", value1, value2, "clusterId");
            return (Criteria) this;
        }

        public Criteria andClusterIdNotBetween(String value1, String value2) {
            addCriterion("clusterId not between", value1, value2, "clusterId");
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

        public Criteria andClusterNameIsNull() {
            addCriterion("clusterName is null");
            return (Criteria) this;
        }

        public Criteria andClusterNameIsNotNull() {
            addCriterion("clusterName is not null");
            return (Criteria) this;
        }

        public Criteria andClusterNameEqualTo(String value) {
            addCriterion("clusterName =", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotEqualTo(String value) {
            addCriterion("clusterName <>", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameGreaterThan(String value) {
            addCriterion("clusterName >", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameGreaterThanOrEqualTo(String value) {
            addCriterion("clusterName >=", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLessThan(String value) {
            addCriterion("clusterName <", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLessThanOrEqualTo(String value) {
            addCriterion("clusterName <=", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLike(String value) {
            addCriterion("clusterName like", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotLike(String value) {
            addCriterion("clusterName not like", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameIn(List<String> values) {
            addCriterion("clusterName in", values, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotIn(List<String> values) {
            addCriterion("clusterName not in", values, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameBetween(String value1, String value2) {
            addCriterion("clusterName between", value1, value2, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotBetween(String value1, String value2) {
            addCriterion("clusterName not between", value1, value2, "clusterName");
            return (Criteria) this;
        }

        public Criteria andReadOnlyIsNull() {
            addCriterion("readOnly is null");
            return (Criteria) this;
        }

        public Criteria andReadOnlyIsNotNull() {
            addCriterion("readOnly is not null");
            return (Criteria) this;
        }

        public Criteria andReadOnlyEqualTo(String value) {
            addCriterion("readOnly =", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyNotEqualTo(String value) {
            addCriterion("readOnly <>", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyGreaterThan(String value) {
            addCriterion("readOnly >", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyGreaterThanOrEqualTo(String value) {
            addCriterion("readOnly >=", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyLessThan(String value) {
            addCriterion("readOnly <", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyLessThanOrEqualTo(String value) {
            addCriterion("readOnly <=", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyLike(String value) {
            addCriterion("readOnly like", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyNotLike(String value) {
            addCriterion("readOnly not like", value, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyIn(List<String> values) {
            addCriterion("readOnly in", values, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyNotIn(List<String> values) {
            addCriterion("readOnly not in", values, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyBetween(String value1, String value2) {
            addCriterion("readOnly between", value1, value2, "readOnly");
            return (Criteria) this;
        }

        public Criteria andReadOnlyNotBetween(String value1, String value2) {
            addCriterion("readOnly not between", value1, value2, "readOnly");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("locked is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("locked is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(String value) {
            addCriterion("locked =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(String value) {
            addCriterion("locked <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(String value) {
            addCriterion("locked >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(String value) {
            addCriterion("locked >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(String value) {
            addCriterion("locked <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(String value) {
            addCriterion("locked <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLike(String value) {
            addCriterion("locked like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotLike(String value) {
            addCriterion("locked not like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<String> values) {
            addCriterion("locked in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<String> values) {
            addCriterion("locked not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(String value1, String value2) {
            addCriterion("locked between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(String value1, String value2) {
            addCriterion("locked not between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("`mode` is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("`mode` is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(String value) {
            addCriterion("`mode` =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(String value) {
            addCriterion("`mode` <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(String value) {
            addCriterion("`mode` >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(String value) {
            addCriterion("`mode` >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(String value) {
            addCriterion("`mode` <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(String value) {
            addCriterion("`mode` <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLike(String value) {
            addCriterion("`mode` like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotLike(String value) {
            addCriterion("`mode` not like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<String> values) {
            addCriterion("`mode` in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<String> values) {
            addCriterion("`mode` not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(String value1, String value2) {
            addCriterion("`mode` between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(String value1, String value2) {
            addCriterion("`mode` not between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumIsNull() {
            addCriterion("tenantZoneNum is null");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumIsNotNull() {
            addCriterion("tenantZoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumEqualTo(Integer value) {
            addCriterion("tenantZoneNum =", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumNotEqualTo(Integer value) {
            addCriterion("tenantZoneNum <>", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumGreaterThan(Integer value) {
            addCriterion("tenantZoneNum >", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("tenantZoneNum >=", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumLessThan(Integer value) {
            addCriterion("tenantZoneNum <", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumLessThanOrEqualTo(Integer value) {
            addCriterion("tenantZoneNum <=", value, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumIn(List<Integer> values) {
            addCriterion("tenantZoneNum in", values, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumNotIn(List<Integer> values) {
            addCriterion("tenantZoneNum not in", values, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumBetween(Integer value1, Integer value2) {
            addCriterion("tenantZoneNum between", value1, value2, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantZoneNumNotBetween(Integer value1, Integer value2) {
            addCriterion("tenantZoneNum not between", value1, value2, "tenantZoneNum");
            return (Criteria) this;
        }

        public Criteria andTenantCpuIsNull() {
            addCriterion("tenantCpu is null");
            return (Criteria) this;
        }

        public Criteria andTenantCpuIsNotNull() {
            addCriterion("tenantCpu is not null");
            return (Criteria) this;
        }

        public Criteria andTenantCpuEqualTo(String value) {
            addCriterion("tenantCpu =", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuNotEqualTo(String value) {
            addCriterion("tenantCpu <>", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuGreaterThan(String value) {
            addCriterion("tenantCpu >", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuGreaterThanOrEqualTo(String value) {
            addCriterion("tenantCpu >=", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuLessThan(String value) {
            addCriterion("tenantCpu <", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuLessThanOrEqualTo(String value) {
            addCriterion("tenantCpu <=", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuLike(String value) {
            addCriterion("tenantCpu like", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuNotLike(String value) {
            addCriterion("tenantCpu not like", value, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuIn(List<String> values) {
            addCriterion("tenantCpu in", values, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuNotIn(List<String> values) {
            addCriterion("tenantCpu not in", values, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuBetween(String value1, String value2) {
            addCriterion("tenantCpu between", value1, value2, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantCpuNotBetween(String value1, String value2) {
            addCriterion("tenantCpu not between", value1, value2, "tenantCpu");
            return (Criteria) this;
        }

        public Criteria andTenantMemIsNull() {
            addCriterion("tenantMem is null");
            return (Criteria) this;
        }

        public Criteria andTenantMemIsNotNull() {
            addCriterion("tenantMem is not null");
            return (Criteria) this;
        }

        public Criteria andTenantMemEqualTo(String value) {
            addCriterion("tenantMem =", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemNotEqualTo(String value) {
            addCriterion("tenantMem <>", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemGreaterThan(String value) {
            addCriterion("tenantMem >", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemGreaterThanOrEqualTo(String value) {
            addCriterion("tenantMem >=", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemLessThan(String value) {
            addCriterion("tenantMem <", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemLessThanOrEqualTo(String value) {
            addCriterion("tenantMem <=", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemLike(String value) {
            addCriterion("tenantMem like", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemNotLike(String value) {
            addCriterion("tenantMem not like", value, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemIn(List<String> values) {
            addCriterion("tenantMem in", values, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemNotIn(List<String> values) {
            addCriterion("tenantMem not in", values, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemBetween(String value1, String value2) {
            addCriterion("tenantMem between", value1, value2, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andTenantMemNotBetween(String value1, String value2) {
            addCriterion("tenantMem not between", value1, value2, "tenantMem");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneIsNull() {
            addCriterion("primaryZone is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneIsNotNull() {
            addCriterion("primaryZone is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneEqualTo(String value) {
            addCriterion("primaryZone =", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneNotEqualTo(String value) {
            addCriterion("primaryZone <>", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneGreaterThan(String value) {
            addCriterion("primaryZone >", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneGreaterThanOrEqualTo(String value) {
            addCriterion("primaryZone >=", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneLessThan(String value) {
            addCriterion("primaryZone <", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneLessThanOrEqualTo(String value) {
            addCriterion("primaryZone <=", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneLike(String value) {
            addCriterion("primaryZone like", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneNotLike(String value) {
            addCriterion("primaryZone not like", value, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneIn(List<String> values) {
            addCriterion("primaryZone in", values, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneNotIn(List<String> values) {
            addCriterion("primaryZone not in", values, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneBetween(String value1, String value2) {
            addCriterion("primaryZone between", value1, value2, "primaryZone");
            return (Criteria) this;
        }

        public Criteria andPrimaryZoneNotBetween(String value1, String value2) {
            addCriterion("primaryZone not between", value1, value2, "primaryZone");
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