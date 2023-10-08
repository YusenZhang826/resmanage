package com.clic.ccdbaas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelationDbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RelationDbExample() {
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

        public Criteria andResidIsNull() {
            addCriterion("resId is null");
            return (Criteria) this;
        }

        public Criteria andResidIsNotNull() {
            addCriterion("resId is not null");
            return (Criteria) this;
        }

        public Criteria andResidEqualTo(String value) {
            addCriterion("resId =", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidNotEqualTo(String value) {
            addCriterion("resId <>", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidGreaterThan(String value) {
            addCriterion("resId >", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidGreaterThanOrEqualTo(String value) {
            addCriterion("resId >=", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidLessThan(String value) {
            addCriterion("resId <", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidLessThanOrEqualTo(String value) {
            addCriterion("resId <=", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidLike(String value) {
            addCriterion("resId like", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidNotLike(String value) {
            addCriterion("resId not like", value, "resid");
            return (Criteria) this;
        }

        public Criteria andResidIn(List<String> values) {
            addCriterion("resId in", values, "resid");
            return (Criteria) this;
        }

        public Criteria andResidNotIn(List<String> values) {
            addCriterion("resId not in", values, "resid");
            return (Criteria) this;
        }

        public Criteria andResidBetween(String value1, String value2) {
            addCriterion("resId between", value1, value2, "resid");
            return (Criteria) this;
        }

        public Criteria andResidNotBetween(String value1, String value2) {
            addCriterion("resId not between", value1, value2, "resid");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNull() {
            addCriterion("agentId is null");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNotNull() {
            addCriterion("agentId is not null");
            return (Criteria) this;
        }

        public Criteria andAgentidEqualTo(String value) {
            addCriterion("agentId =", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotEqualTo(String value) {
            addCriterion("agentId <>", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThan(String value) {
            addCriterion("agentId >", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThanOrEqualTo(String value) {
            addCriterion("agentId >=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThan(String value) {
            addCriterion("agentId <", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThanOrEqualTo(String value) {
            addCriterion("agentId <=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLike(String value) {
            addCriterion("agentId like", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotLike(String value) {
            addCriterion("agentId not like", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidIn(List<String> values) {
            addCriterion("agentId in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotIn(List<String> values) {
            addCriterion("agentId not in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidBetween(String value1, String value2) {
            addCriterion("agentId between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotBetween(String value1, String value2) {
            addCriterion("agentId not between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNull() {
            addCriterion("hostname is null");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNotNull() {
            addCriterion("hostname is not null");
            return (Criteria) this;
        }

        public Criteria andHostnameEqualTo(String value) {
            addCriterion("hostname =", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotEqualTo(String value) {
            addCriterion("hostname <>", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThan(String value) {
            addCriterion("hostname >", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanOrEqualTo(String value) {
            addCriterion("hostname >=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThan(String value) {
            addCriterion("hostname <", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanOrEqualTo(String value) {
            addCriterion("hostname <=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLike(String value) {
            addCriterion("hostname like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotLike(String value) {
            addCriterion("hostname not like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameIn(List<String> values) {
            addCriterion("hostname in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotIn(List<String> values) {
            addCriterion("hostname not in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameBetween(String value1, String value2) {
            addCriterion("hostname between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotBetween(String value1, String value2) {
            addCriterion("hostname not between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andDisplayipIsNull() {
            addCriterion("displayIp is null");
            return (Criteria) this;
        }

        public Criteria andDisplayipIsNotNull() {
            addCriterion("displayIp is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayipEqualTo(String value) {
            addCriterion("displayIp =", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipNotEqualTo(String value) {
            addCriterion("displayIp <>", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipGreaterThan(String value) {
            addCriterion("displayIp >", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipGreaterThanOrEqualTo(String value) {
            addCriterion("displayIp >=", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipLessThan(String value) {
            addCriterion("displayIp <", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipLessThanOrEqualTo(String value) {
            addCriterion("displayIp <=", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipLike(String value) {
            addCriterion("displayIp like", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipNotLike(String value) {
            addCriterion("displayIp not like", value, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipIn(List<String> values) {
            addCriterion("displayIp in", values, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipNotIn(List<String> values) {
            addCriterion("displayIp not in", values, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipBetween(String value1, String value2) {
            addCriterion("displayIp between", value1, value2, "displayip");
            return (Criteria) this;
        }

        public Criteria andDisplayipNotBetween(String value1, String value2) {
            addCriterion("displayIp not between", value1, value2, "displayip");
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

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andBinpathIsNull() {
            addCriterion("binPath is null");
            return (Criteria) this;
        }

        public Criteria andBinpathIsNotNull() {
            addCriterion("binPath is not null");
            return (Criteria) this;
        }

        public Criteria andBinpathEqualTo(String value) {
            addCriterion("binPath =", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathNotEqualTo(String value) {
            addCriterion("binPath <>", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathGreaterThan(String value) {
            addCriterion("binPath >", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathGreaterThanOrEqualTo(String value) {
            addCriterion("binPath >=", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathLessThan(String value) {
            addCriterion("binPath <", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathLessThanOrEqualTo(String value) {
            addCriterion("binPath <=", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathLike(String value) {
            addCriterion("binPath like", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathNotLike(String value) {
            addCriterion("binPath not like", value, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathIn(List<String> values) {
            addCriterion("binPath in", values, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathNotIn(List<String> values) {
            addCriterion("binPath not in", values, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathBetween(String value1, String value2) {
            addCriterion("binPath between", value1, value2, "binpath");
            return (Criteria) this;
        }

        public Criteria andBinpathNotBetween(String value1, String value2) {
            addCriterion("binPath not between", value1, value2, "binpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathIsNull() {
            addCriterion("configPath is null");
            return (Criteria) this;
        }

        public Criteria andConfigpathIsNotNull() {
            addCriterion("configPath is not null");
            return (Criteria) this;
        }

        public Criteria andConfigpathEqualTo(String value) {
            addCriterion("configPath =", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathNotEqualTo(String value) {
            addCriterion("configPath <>", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathGreaterThan(String value) {
            addCriterion("configPath >", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathGreaterThanOrEqualTo(String value) {
            addCriterion("configPath >=", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathLessThan(String value) {
            addCriterion("configPath <", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathLessThanOrEqualTo(String value) {
            addCriterion("configPath <=", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathLike(String value) {
            addCriterion("configPath like", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathNotLike(String value) {
            addCriterion("configPath not like", value, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathIn(List<String> values) {
            addCriterion("configPath in", values, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathNotIn(List<String> values) {
            addCriterion("configPath not in", values, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathBetween(String value1, String value2) {
            addCriterion("configPath between", value1, value2, "configpath");
            return (Criteria) this;
        }

        public Criteria andConfigpathNotBetween(String value1, String value2) {
            addCriterion("configPath not between", value1, value2, "configpath");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeIsNull() {
            addCriterion("udpateTime is null");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeIsNotNull() {
            addCriterion("udpateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeEqualTo(Date value) {
            addCriterion("udpateTime =", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeNotEqualTo(Date value) {
            addCriterion("udpateTime <>", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeGreaterThan(Date value) {
            addCriterion("udpateTime >", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("udpateTime >=", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeLessThan(Date value) {
            addCriterion("udpateTime <", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeLessThanOrEqualTo(Date value) {
            addCriterion("udpateTime <=", value, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeIn(List<Date> values) {
            addCriterion("udpateTime in", values, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeNotIn(List<Date> values) {
            addCriterion("udpateTime not in", values, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeBetween(Date value1, Date value2) {
            addCriterion("udpateTime between", value1, value2, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andUdpatetimeNotBetween(Date value1, Date value2) {
            addCriterion("udpateTime not between", value1, value2, "udpatetime");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPrototypeIsNull() {
            addCriterion("protoType is null");
            return (Criteria) this;
        }

        public Criteria andPrototypeIsNotNull() {
            addCriterion("protoType is not null");
            return (Criteria) this;
        }

        public Criteria andPrototypeEqualTo(String value) {
            addCriterion("protoType =", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeNotEqualTo(String value) {
            addCriterion("protoType <>", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeGreaterThan(String value) {
            addCriterion("protoType >", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeGreaterThanOrEqualTo(String value) {
            addCriterion("protoType >=", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeLessThan(String value) {
            addCriterion("protoType <", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeLessThanOrEqualTo(String value) {
            addCriterion("protoType <=", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeLike(String value) {
            addCriterion("protoType like", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeNotLike(String value) {
            addCriterion("protoType not like", value, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeIn(List<String> values) {
            addCriterion("protoType in", values, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeNotIn(List<String> values) {
            addCriterion("protoType not in", values, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeBetween(String value1, String value2) {
            addCriterion("protoType between", value1, value2, "prototype");
            return (Criteria) this;
        }

        public Criteria andPrototypeNotBetween(String value1, String value2) {
            addCriterion("protoType not between", value1, value2, "prototype");
            return (Criteria) this;
        }

        public Criteria andBindipIsNull() {
            addCriterion("bindIp is null");
            return (Criteria) this;
        }

        public Criteria andBindipIsNotNull() {
            addCriterion("bindIp is not null");
            return (Criteria) this;
        }

        public Criteria andBindipEqualTo(String value) {
            addCriterion("bindIp =", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipNotEqualTo(String value) {
            addCriterion("bindIp <>", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipGreaterThan(String value) {
            addCriterion("bindIp >", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipGreaterThanOrEqualTo(String value) {
            addCriterion("bindIp >=", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipLessThan(String value) {
            addCriterion("bindIp <", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipLessThanOrEqualTo(String value) {
            addCriterion("bindIp <=", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipLike(String value) {
            addCriterion("bindIp like", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipNotLike(String value) {
            addCriterion("bindIp not like", value, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipIn(List<String> values) {
            addCriterion("bindIp in", values, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipNotIn(List<String> values) {
            addCriterion("bindIp not in", values, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipBetween(String value1, String value2) {
            addCriterion("bindIp between", value1, value2, "bindip");
            return (Criteria) this;
        }

        public Criteria andBindipNotBetween(String value1, String value2) {
            addCriterion("bindIp not between", value1, value2, "bindip");
            return (Criteria) this;
        }

        public Criteria andConfpathIsNull() {
            addCriterion("confPath is null");
            return (Criteria) this;
        }

        public Criteria andConfpathIsNotNull() {
            addCriterion("confPath is not null");
            return (Criteria) this;
        }

        public Criteria andConfpathEqualTo(String value) {
            addCriterion("confPath =", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathNotEqualTo(String value) {
            addCriterion("confPath <>", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathGreaterThan(String value) {
            addCriterion("confPath >", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathGreaterThanOrEqualTo(String value) {
            addCriterion("confPath >=", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathLessThan(String value) {
            addCriterion("confPath <", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathLessThanOrEqualTo(String value) {
            addCriterion("confPath <=", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathLike(String value) {
            addCriterion("confPath like", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathNotLike(String value) {
            addCriterion("confPath not like", value, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathIn(List<String> values) {
            addCriterion("confPath in", values, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathNotIn(List<String> values) {
            addCriterion("confPath not in", values, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathBetween(String value1, String value2) {
            addCriterion("confPath between", value1, value2, "confpath");
            return (Criteria) this;
        }

        public Criteria andConfpathNotBetween(String value1, String value2) {
            addCriterion("confPath not between", value1, value2, "confpath");
            return (Criteria) this;
        }

        public Criteria andLogpathIsNull() {
            addCriterion("logPath is null");
            return (Criteria) this;
        }

        public Criteria andLogpathIsNotNull() {
            addCriterion("logPath is not null");
            return (Criteria) this;
        }

        public Criteria andLogpathEqualTo(String value) {
            addCriterion("logPath =", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathNotEqualTo(String value) {
            addCriterion("logPath <>", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathGreaterThan(String value) {
            addCriterion("logPath >", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathGreaterThanOrEqualTo(String value) {
            addCriterion("logPath >=", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathLessThan(String value) {
            addCriterion("logPath <", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathLessThanOrEqualTo(String value) {
            addCriterion("logPath <=", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathLike(String value) {
            addCriterion("logPath like", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathNotLike(String value) {
            addCriterion("logPath not like", value, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathIn(List<String> values) {
            addCriterion("logPath in", values, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathNotIn(List<String> values) {
            addCriterion("logPath not in", values, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathBetween(String value1, String value2) {
            addCriterion("logPath between", value1, value2, "logpath");
            return (Criteria) this;
        }

        public Criteria andLogpathNotBetween(String value1, String value2) {
            addCriterion("logPath not between", value1, value2, "logpath");
            return (Criteria) this;
        }

        public Criteria andDatadirIsNull() {
            addCriterion("dataDir is null");
            return (Criteria) this;
        }

        public Criteria andDatadirIsNotNull() {
            addCriterion("dataDir is not null");
            return (Criteria) this;
        }

        public Criteria andDatadirEqualTo(String value) {
            addCriterion("dataDir =", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirNotEqualTo(String value) {
            addCriterion("dataDir <>", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirGreaterThan(String value) {
            addCriterion("dataDir >", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirGreaterThanOrEqualTo(String value) {
            addCriterion("dataDir >=", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirLessThan(String value) {
            addCriterion("dataDir <", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirLessThanOrEqualTo(String value) {
            addCriterion("dataDir <=", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirLike(String value) {
            addCriterion("dataDir like", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirNotLike(String value) {
            addCriterion("dataDir not like", value, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirIn(List<String> values) {
            addCriterion("dataDir in", values, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirNotIn(List<String> values) {
            addCriterion("dataDir not in", values, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirBetween(String value1, String value2) {
            addCriterion("dataDir between", value1, value2, "datadir");
            return (Criteria) this;
        }

        public Criteria andDatadirNotBetween(String value1, String value2) {
            addCriterion("dataDir not between", value1, value2, "datadir");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("`user` is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("`user` is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("`user` =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("`user` <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("`user` >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("`user` >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("`user` <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("`user` <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("`user` like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("`user` not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("`user` in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("`user` not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("`user` between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("`user` not between", value1, value2, "user");
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