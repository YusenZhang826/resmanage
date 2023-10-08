package com.clic.ccdbaas.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReserveServerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReserveServerExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNetCardNumIsNull() {
            addCriterion("netCardNum is null");
            return (Criteria) this;
        }

        public Criteria andNetCardNumIsNotNull() {
            addCriterion("netCardNum is not null");
            return (Criteria) this;
        }

        public Criteria andNetCardNumEqualTo(Integer value) {
            addCriterion("netCardNum =", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumNotEqualTo(Integer value) {
            addCriterion("netCardNum <>", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumGreaterThan(Integer value) {
            addCriterion("netCardNum >", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("netCardNum >=", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumLessThan(Integer value) {
            addCriterion("netCardNum <", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumLessThanOrEqualTo(Integer value) {
            addCriterion("netCardNum <=", value, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumIn(List<Integer> values) {
            addCriterion("netCardNum in", values, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumNotIn(List<Integer> values) {
            addCriterion("netCardNum not in", values, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumBetween(Integer value1, Integer value2) {
            addCriterion("netCardNum between", value1, value2, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andNetCardNumNotBetween(Integer value1, Integer value2) {
            addCriterion("netCardNum not between", value1, value2, "netCardNum");
            return (Criteria) this;
        }

        public Criteria andRaidModelIsNull() {
            addCriterion("raidModel is null");
            return (Criteria) this;
        }

        public Criteria andRaidModelIsNotNull() {
            addCriterion("raidModel is not null");
            return (Criteria) this;
        }

        public Criteria andRaidModelEqualTo(String value) {
            addCriterion("raidModel =", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelNotEqualTo(String value) {
            addCriterion("raidModel <>", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelGreaterThan(String value) {
            addCriterion("raidModel >", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelGreaterThanOrEqualTo(String value) {
            addCriterion("raidModel >=", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelLessThan(String value) {
            addCriterion("raidModel <", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelLessThanOrEqualTo(String value) {
            addCriterion("raidModel <=", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelLike(String value) {
            addCriterion("raidModel like", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelNotLike(String value) {
            addCriterion("raidModel not like", value, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelIn(List<String> values) {
            addCriterion("raidModel in", values, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelNotIn(List<String> values) {
            addCriterion("raidModel not in", values, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelBetween(String value1, String value2) {
            addCriterion("raidModel between", value1, value2, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidModelNotBetween(String value1, String value2) {
            addCriterion("raidModel not between", value1, value2, "raidModel");
            return (Criteria) this;
        }

        public Criteria andRaidCountIsNull() {
            addCriterion("raidCount is null");
            return (Criteria) this;
        }

        public Criteria andRaidCountIsNotNull() {
            addCriterion("raidCount is not null");
            return (Criteria) this;
        }

        public Criteria andRaidCountEqualTo(Integer value) {
            addCriterion("raidCount =", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountNotEqualTo(Integer value) {
            addCriterion("raidCount <>", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountGreaterThan(Integer value) {
            addCriterion("raidCount >", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("raidCount >=", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountLessThan(Integer value) {
            addCriterion("raidCount <", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountLessThanOrEqualTo(Integer value) {
            addCriterion("raidCount <=", value, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountIn(List<Integer> values) {
            addCriterion("raidCount in", values, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountNotIn(List<Integer> values) {
            addCriterion("raidCount not in", values, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountBetween(Integer value1, Integer value2) {
            addCriterion("raidCount between", value1, value2, "raidCount");
            return (Criteria) this;
        }

        public Criteria andRaidCountNotBetween(Integer value1, Integer value2) {
            addCriterion("raidCount not between", value1, value2, "raidCount");
            return (Criteria) this;
        }

        public Criteria andPcieModelIsNull() {
            addCriterion("pcieModel is null");
            return (Criteria) this;
        }

        public Criteria andPcieModelIsNotNull() {
            addCriterion("pcieModel is not null");
            return (Criteria) this;
        }

        public Criteria andPcieModelEqualTo(String value) {
            addCriterion("pcieModel =", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelNotEqualTo(String value) {
            addCriterion("pcieModel <>", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelGreaterThan(String value) {
            addCriterion("pcieModel >", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelGreaterThanOrEqualTo(String value) {
            addCriterion("pcieModel >=", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelLessThan(String value) {
            addCriterion("pcieModel <", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelLessThanOrEqualTo(String value) {
            addCriterion("pcieModel <=", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelLike(String value) {
            addCriterion("pcieModel like", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelNotLike(String value) {
            addCriterion("pcieModel not like", value, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelIn(List<String> values) {
            addCriterion("pcieModel in", values, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelNotIn(List<String> values) {
            addCriterion("pcieModel not in", values, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelBetween(String value1, String value2) {
            addCriterion("pcieModel between", value1, value2, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieModelNotBetween(String value1, String value2) {
            addCriterion("pcieModel not between", value1, value2, "pcieModel");
            return (Criteria) this;
        }

        public Criteria andPcieCountIsNull() {
            addCriterion("pcieCount is null");
            return (Criteria) this;
        }

        public Criteria andPcieCountIsNotNull() {
            addCriterion("pcieCount is not null");
            return (Criteria) this;
        }

        public Criteria andPcieCountEqualTo(Integer value) {
            addCriterion("pcieCount =", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountNotEqualTo(Integer value) {
            addCriterion("pcieCount <>", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountGreaterThan(Integer value) {
            addCriterion("pcieCount >", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("pcieCount >=", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountLessThan(Integer value) {
            addCriterion("pcieCount <", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountLessThanOrEqualTo(Integer value) {
            addCriterion("pcieCount <=", value, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountIn(List<Integer> values) {
            addCriterion("pcieCount in", values, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountNotIn(List<Integer> values) {
            addCriterion("pcieCount not in", values, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountBetween(Integer value1, Integer value2) {
            addCriterion("pcieCount between", value1, value2, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPcieCountNotBetween(Integer value1, Integer value2) {
            addCriterion("pcieCount not between", value1, value2, "pcieCount");
            return (Criteria) this;
        }

        public Criteria andPowerModelIsNull() {
            addCriterion("powerModel is null");
            return (Criteria) this;
        }

        public Criteria andPowerModelIsNotNull() {
            addCriterion("powerModel is not null");
            return (Criteria) this;
        }

        public Criteria andPowerModelEqualTo(String value) {
            addCriterion("powerModel =", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelNotEqualTo(String value) {
            addCriterion("powerModel <>", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelGreaterThan(String value) {
            addCriterion("powerModel >", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelGreaterThanOrEqualTo(String value) {
            addCriterion("powerModel >=", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelLessThan(String value) {
            addCriterion("powerModel <", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelLessThanOrEqualTo(String value) {
            addCriterion("powerModel <=", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelLike(String value) {
            addCriterion("powerModel like", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelNotLike(String value) {
            addCriterion("powerModel not like", value, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelIn(List<String> values) {
            addCriterion("powerModel in", values, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelNotIn(List<String> values) {
            addCriterion("powerModel not in", values, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelBetween(String value1, String value2) {
            addCriterion("powerModel between", value1, value2, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerModelNotBetween(String value1, String value2) {
            addCriterion("powerModel not between", value1, value2, "powerModel");
            return (Criteria) this;
        }

        public Criteria andPowerNumIsNull() {
            addCriterion("powerNum is null");
            return (Criteria) this;
        }

        public Criteria andPowerNumIsNotNull() {
            addCriterion("powerNum is not null");
            return (Criteria) this;
        }

        public Criteria andPowerNumEqualTo(Integer value) {
            addCriterion("powerNum =", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumNotEqualTo(Integer value) {
            addCriterion("powerNum <>", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumGreaterThan(Integer value) {
            addCriterion("powerNum >", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("powerNum >=", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumLessThan(Integer value) {
            addCriterion("powerNum <", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumLessThanOrEqualTo(Integer value) {
            addCriterion("powerNum <=", value, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumIn(List<Integer> values) {
            addCriterion("powerNum in", values, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumNotIn(List<Integer> values) {
            addCriterion("powerNum not in", values, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumBetween(Integer value1, Integer value2) {
            addCriterion("powerNum between", value1, value2, "powerNum");
            return (Criteria) this;
        }

        public Criteria andPowerNumNotBetween(Integer value1, Integer value2) {
            addCriterion("powerNum not between", value1, value2, "powerNum");
            return (Criteria) this;
        }

        public Criteria andFanNumIsNull() {
            addCriterion("fanNum is null");
            return (Criteria) this;
        }

        public Criteria andFanNumIsNotNull() {
            addCriterion("fanNum is not null");
            return (Criteria) this;
        }

        public Criteria andFanNumEqualTo(Integer value) {
            addCriterion("fanNum =", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumNotEqualTo(Integer value) {
            addCriterion("fanNum <>", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumGreaterThan(Integer value) {
            addCriterion("fanNum >", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("fanNum >=", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumLessThan(Integer value) {
            addCriterion("fanNum <", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumLessThanOrEqualTo(Integer value) {
            addCriterion("fanNum <=", value, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumIn(List<Integer> values) {
            addCriterion("fanNum in", values, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumNotIn(List<Integer> values) {
            addCriterion("fanNum not in", values, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumBetween(Integer value1, Integer value2) {
            addCriterion("fanNum between", value1, value2, "fanNum");
            return (Criteria) this;
        }

        public Criteria andFanNumNotBetween(Integer value1, Integer value2) {
            addCriterion("fanNum not between", value1, value2, "fanNum");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("lastUpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("lastUpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("lastUpdateTime =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastUpdateTime <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("lastUpdateTime >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastUpdateTime >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("lastUpdateTime <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastUpdateTime <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("lastUpdateTime in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastUpdateTime not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastUpdateTime between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastUpdateTime not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPIsNull() {
            addCriterion("allocateBMCIP is null");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPIsNotNull() {
            addCriterion("allocateBMCIP is not null");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPEqualTo(String value) {
            addCriterion("allocateBMCIP =", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPNotEqualTo(String value) {
            addCriterion("allocateBMCIP <>", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPGreaterThan(String value) {
            addCriterion("allocateBMCIP >", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPGreaterThanOrEqualTo(String value) {
            addCriterion("allocateBMCIP >=", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPLessThan(String value) {
            addCriterion("allocateBMCIP <", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPLessThanOrEqualTo(String value) {
            addCriterion("allocateBMCIP <=", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPLike(String value) {
            addCriterion("allocateBMCIP like", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPNotLike(String value) {
            addCriterion("allocateBMCIP not like", value, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPIn(List<String> values) {
            addCriterion("allocateBMCIP in", values, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPNotIn(List<String> values) {
            addCriterion("allocateBMCIP not in", values, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPBetween(String value1, String value2) {
            addCriterion("allocateBMCIP between", value1, value2, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andAllocateBMCIPNotBetween(String value1, String value2) {
            addCriterion("allocateBMCIP not between", value1, value2, "allocateBMCIP");
            return (Criteria) this;
        }

        public Criteria andShelfPositionIsNull() {
            addCriterion("shelfPosition is null");
            return (Criteria) this;
        }

        public Criteria andShelfPositionIsNotNull() {
            addCriterion("shelfPosition is not null");
            return (Criteria) this;
        }

        public Criteria andShelfPositionEqualTo(String value) {
            addCriterion("shelfPosition =", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionNotEqualTo(String value) {
            addCriterion("shelfPosition <>", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionGreaterThan(String value) {
            addCriterion("shelfPosition >", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionGreaterThanOrEqualTo(String value) {
            addCriterion("shelfPosition >=", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionLessThan(String value) {
            addCriterion("shelfPosition <", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionLessThanOrEqualTo(String value) {
            addCriterion("shelfPosition <=", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionLike(String value) {
            addCriterion("shelfPosition like", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionNotLike(String value) {
            addCriterion("shelfPosition not like", value, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionIn(List<String> values) {
            addCriterion("shelfPosition in", values, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionNotIn(List<String> values) {
            addCriterion("shelfPosition not in", values, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionBetween(String value1, String value2) {
            addCriterion("shelfPosition between", value1, value2, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andShelfPositionNotBetween(String value1, String value2) {
            addCriterion("shelfPosition not between", value1, value2, "shelfPosition");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeIsNull() {
            addCriterion("resAllocationCode is null");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeIsNotNull() {
            addCriterion("resAllocationCode is not null");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeEqualTo(String value) {
            addCriterion("resAllocationCode =", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeNotEqualTo(String value) {
            addCriterion("resAllocationCode <>", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeGreaterThan(String value) {
            addCriterion("resAllocationCode >", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("resAllocationCode >=", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeLessThan(String value) {
            addCriterion("resAllocationCode <", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeLessThanOrEqualTo(String value) {
            addCriterion("resAllocationCode <=", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeLike(String value) {
            addCriterion("resAllocationCode like", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeNotLike(String value) {
            addCriterion("resAllocationCode not like", value, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeIn(List<String> values) {
            addCriterion("resAllocationCode in", values, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeNotIn(List<String> values) {
            addCriterion("resAllocationCode not in", values, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeBetween(String value1, String value2) {
            addCriterion("resAllocationCode between", value1, value2, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andResAllocationCodeNotBetween(String value1, String value2) {
            addCriterion("resAllocationCode not between", value1, value2, "resAllocationCode");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNull() {
            addCriterion("regionName is null");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNotNull() {
            addCriterion("regionName is not null");
            return (Criteria) this;
        }

        public Criteria andRegionNameEqualTo(String value) {
            addCriterion("regionName =", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotEqualTo(String value) {
            addCriterion("regionName <>", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThan(String value) {
            addCriterion("regionName >", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("regionName >=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThan(String value) {
            addCriterion("regionName <", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThanOrEqualTo(String value) {
            addCriterion("regionName <=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLike(String value) {
            addCriterion("regionName like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotLike(String value) {
            addCriterion("regionName not like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameIn(List<String> values) {
            addCriterion("regionName in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotIn(List<String> values) {
            addCriterion("regionName not in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameBetween(String value1, String value2) {
            addCriterion("regionName between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotBetween(String value1, String value2) {
            addCriterion("regionName not between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andCpuSizeIsNull() {
            addCriterion("cpuSize is null");
            return (Criteria) this;
        }

        public Criteria andCpuSizeIsNotNull() {
            addCriterion("cpuSize is not null");
            return (Criteria) this;
        }

        public Criteria andCpuSizeEqualTo(Integer value) {
            addCriterion("cpuSize =", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeNotEqualTo(Integer value) {
            addCriterion("cpuSize <>", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeGreaterThan(Integer value) {
            addCriterion("cpuSize >", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpuSize >=", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeLessThan(Integer value) {
            addCriterion("cpuSize <", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeLessThanOrEqualTo(Integer value) {
            addCriterion("cpuSize <=", value, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeIn(List<Integer> values) {
            addCriterion("cpuSize in", values, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeNotIn(List<Integer> values) {
            addCriterion("cpuSize not in", values, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeBetween(Integer value1, Integer value2) {
            addCriterion("cpuSize between", value1, value2, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("cpuSize not between", value1, value2, "cpuSize");
            return (Criteria) this;
        }

        public Criteria andCpuCountIsNull() {
            addCriterion("cpuCount is null");
            return (Criteria) this;
        }

        public Criteria andCpuCountIsNotNull() {
            addCriterion("cpuCount is not null");
            return (Criteria) this;
        }

        public Criteria andCpuCountEqualTo(Integer value) {
            addCriterion("cpuCount =", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountNotEqualTo(Integer value) {
            addCriterion("cpuCount <>", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountGreaterThan(Integer value) {
            addCriterion("cpuCount >", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpuCount >=", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountLessThan(Integer value) {
            addCriterion("cpuCount <", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountLessThanOrEqualTo(Integer value) {
            addCriterion("cpuCount <=", value, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountIn(List<Integer> values) {
            addCriterion("cpuCount in", values, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountNotIn(List<Integer> values) {
            addCriterion("cpuCount not in", values, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountBetween(Integer value1, Integer value2) {
            addCriterion("cpuCount between", value1, value2, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuCountNotBetween(Integer value1, Integer value2) {
            addCriterion("cpuCount not between", value1, value2, "cpuCount");
            return (Criteria) this;
        }

        public Criteria andCpuModelIsNull() {
            addCriterion("cpuModel is null");
            return (Criteria) this;
        }

        public Criteria andCpuModelIsNotNull() {
            addCriterion("cpuModel is not null");
            return (Criteria) this;
        }

        public Criteria andCpuModelEqualTo(String value) {
            addCriterion("cpuModel =", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotEqualTo(String value) {
            addCriterion("cpuModel <>", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelGreaterThan(String value) {
            addCriterion("cpuModel >", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelGreaterThanOrEqualTo(String value) {
            addCriterion("cpuModel >=", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLessThan(String value) {
            addCriterion("cpuModel <", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLessThanOrEqualTo(String value) {
            addCriterion("cpuModel <=", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelLike(String value) {
            addCriterion("cpuModel like", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotLike(String value) {
            addCriterion("cpuModel not like", value, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelIn(List<String> values) {
            addCriterion("cpuModel in", values, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotIn(List<String> values) {
            addCriterion("cpuModel not in", values, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelBetween(String value1, String value2) {
            addCriterion("cpuModel between", value1, value2, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andCpuModelNotBetween(String value1, String value2) {
            addCriterion("cpuModel not between", value1, value2, "cpuModel");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkIsNull() {
            addCriterion("warrantyRemark is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkIsNotNull() {
            addCriterion("warrantyRemark is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkEqualTo(String value) {
            addCriterion("warrantyRemark =", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkNotEqualTo(String value) {
            addCriterion("warrantyRemark <>", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkGreaterThan(String value) {
            addCriterion("warrantyRemark >", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("warrantyRemark >=", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkLessThan(String value) {
            addCriterion("warrantyRemark <", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkLessThanOrEqualTo(String value) {
            addCriterion("warrantyRemark <=", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkLike(String value) {
            addCriterion("warrantyRemark like", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkNotLike(String value) {
            addCriterion("warrantyRemark not like", value, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkIn(List<String> values) {
            addCriterion("warrantyRemark in", values, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkNotIn(List<String> values) {
            addCriterion("warrantyRemark not in", values, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkBetween(String value1, String value2) {
            addCriterion("warrantyRemark between", value1, value2, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyRemarkNotBetween(String value1, String value2) {
            addCriterion("warrantyRemark not between", value1, value2, "warrantyRemark");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerIsNull() {
            addCriterion("warrantyManufacturer is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerIsNotNull() {
            addCriterion("warrantyManufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerEqualTo(String value) {
            addCriterion("warrantyManufacturer =", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerNotEqualTo(String value) {
            addCriterion("warrantyManufacturer <>", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerGreaterThan(String value) {
            addCriterion("warrantyManufacturer >", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("warrantyManufacturer >=", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerLessThan(String value) {
            addCriterion("warrantyManufacturer <", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerLessThanOrEqualTo(String value) {
            addCriterion("warrantyManufacturer <=", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerLike(String value) {
            addCriterion("warrantyManufacturer like", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerNotLike(String value) {
            addCriterion("warrantyManufacturer not like", value, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerIn(List<String> values) {
            addCriterion("warrantyManufacturer in", values, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerNotIn(List<String> values) {
            addCriterion("warrantyManufacturer not in", values, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerBetween(String value1, String value2) {
            addCriterion("warrantyManufacturer between", value1, value2, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyManufacturerNotBetween(String value1, String value2) {
            addCriterion("warrantyManufacturer not between", value1, value2, "warrantyManufacturer");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeIsNull() {
            addCriterion("warrantyEndTime is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeIsNotNull() {
            addCriterion("warrantyEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyEndTime =", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyEndTime <>", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("warrantyEndTime >", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyEndTime >=", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("warrantyEndTime <", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyEndTime <=", value, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("warrantyEndTime in", values, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("warrantyEndTime not in", values, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("warrantyEndTime between", value1, value2, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("warrantyEndTime not between", value1, value2, "warrantyEndTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeIsNull() {
            addCriterion("warrantyStartTime is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeIsNotNull() {
            addCriterion("warrantyStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyStartTime =", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyStartTime <>", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("warrantyStartTime >", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyStartTime >=", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("warrantyStartTime <", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("warrantyStartTime <=", value, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("warrantyStartTime in", values, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("warrantyStartTime not in", values, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("warrantyStartTime between", value1, value2, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("warrantyStartTime not between", value1, value2, "warrantyStartTime");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractIsNull() {
            addCriterion("warrantyContract is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractIsNotNull() {
            addCriterion("warrantyContract is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractEqualTo(String value) {
            addCriterion("warrantyContract =", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractNotEqualTo(String value) {
            addCriterion("warrantyContract <>", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractGreaterThan(String value) {
            addCriterion("warrantyContract >", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractGreaterThanOrEqualTo(String value) {
            addCriterion("warrantyContract >=", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractLessThan(String value) {
            addCriterion("warrantyContract <", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractLessThanOrEqualTo(String value) {
            addCriterion("warrantyContract <=", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractLike(String value) {
            addCriterion("warrantyContract like", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractNotLike(String value) {
            addCriterion("warrantyContract not like", value, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractIn(List<String> values) {
            addCriterion("warrantyContract in", values, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractNotIn(List<String> values) {
            addCriterion("warrantyContract not in", values, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractBetween(String value1, String value2) {
            addCriterion("warrantyContract between", value1, value2, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andWarrantyContractNotBetween(String value1, String value2) {
            addCriterion("warrantyContract not between", value1, value2, "warrantyContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractIsNull() {
            addCriterion("purchaseContract is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractIsNotNull() {
            addCriterion("purchaseContract is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractEqualTo(String value) {
            addCriterion("purchaseContract =", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractNotEqualTo(String value) {
            addCriterion("purchaseContract <>", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractGreaterThan(String value) {
            addCriterion("purchaseContract >", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractGreaterThanOrEqualTo(String value) {
            addCriterion("purchaseContract >=", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractLessThan(String value) {
            addCriterion("purchaseContract <", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractLessThanOrEqualTo(String value) {
            addCriterion("purchaseContract <=", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractLike(String value) {
            addCriterion("purchaseContract like", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractNotLike(String value) {
            addCriterion("purchaseContract not like", value, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractIn(List<String> values) {
            addCriterion("purchaseContract in", values, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractNotIn(List<String> values) {
            addCriterion("purchaseContract not in", values, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractBetween(String value1, String value2) {
            addCriterion("purchaseContract between", value1, value2, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andPurchaseContractNotBetween(String value1, String value2) {
            addCriterion("purchaseContract not between", value1, value2, "purchaseContract");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumIsNull() {
            addCriterion("newSAPNum is null");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumIsNotNull() {
            addCriterion("newSAPNum is not null");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumEqualTo(String value) {
            addCriterion("newSAPNum =", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumNotEqualTo(String value) {
            addCriterion("newSAPNum <>", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumGreaterThan(String value) {
            addCriterion("newSAPNum >", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumGreaterThanOrEqualTo(String value) {
            addCriterion("newSAPNum >=", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumLessThan(String value) {
            addCriterion("newSAPNum <", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumLessThanOrEqualTo(String value) {
            addCriterion("newSAPNum <=", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumLike(String value) {
            addCriterion("newSAPNum like", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumNotLike(String value) {
            addCriterion("newSAPNum not like", value, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumIn(List<String> values) {
            addCriterion("newSAPNum in", values, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumNotIn(List<String> values) {
            addCriterion("newSAPNum not in", values, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumBetween(String value1, String value2) {
            addCriterion("newSAPNum between", value1, value2, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andNewSAPNumNotBetween(String value1, String value2) {
            addCriterion("newSAPNum not between", value1, value2, "newSAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumIsNull() {
            addCriterion("SAPNum is null");
            return (Criteria) this;
        }

        public Criteria andSAPNumIsNotNull() {
            addCriterion("SAPNum is not null");
            return (Criteria) this;
        }

        public Criteria andSAPNumEqualTo(String value) {
            addCriterion("SAPNum =", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumNotEqualTo(String value) {
            addCriterion("SAPNum <>", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumGreaterThan(String value) {
            addCriterion("SAPNum >", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumGreaterThanOrEqualTo(String value) {
            addCriterion("SAPNum >=", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumLessThan(String value) {
            addCriterion("SAPNum <", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumLessThanOrEqualTo(String value) {
            addCriterion("SAPNum <=", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumLike(String value) {
            addCriterion("SAPNum like", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumNotLike(String value) {
            addCriterion("SAPNum not like", value, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumIn(List<String> values) {
            addCriterion("SAPNum in", values, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumNotIn(List<String> values) {
            addCriterion("SAPNum not in", values, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumBetween(String value1, String value2) {
            addCriterion("SAPNum between", value1, value2, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andSAPNumNotBetween(String value1, String value2) {
            addCriterion("SAPNum not between", value1, value2, "SAPNum");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNull() {
            addCriterion("deviceModel is null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIsNotNull() {
            addCriterion("deviceModel is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceModelEqualTo(String value) {
            addCriterion("deviceModel =", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotEqualTo(String value) {
            addCriterion("deviceModel <>", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThan(String value) {
            addCriterion("deviceModel >", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelGreaterThanOrEqualTo(String value) {
            addCriterion("deviceModel >=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThan(String value) {
            addCriterion("deviceModel <", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLessThanOrEqualTo(String value) {
            addCriterion("deviceModel <=", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelLike(String value) {
            addCriterion("deviceModel like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotLike(String value) {
            addCriterion("deviceModel not like", value, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelIn(List<String> values) {
            addCriterion("deviceModel in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotIn(List<String> values) {
            addCriterion("deviceModel not in", values, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelBetween(String value1, String value2) {
            addCriterion("deviceModel between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andDeviceModelNotBetween(String value1, String value2) {
            addCriterion("deviceModel not between", value1, value2, "deviceModel");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andStandTeamIsNull() {
            addCriterion("standTeam is null");
            return (Criteria) this;
        }

        public Criteria andStandTeamIsNotNull() {
            addCriterion("standTeam is not null");
            return (Criteria) this;
        }

        public Criteria andStandTeamEqualTo(String value) {
            addCriterion("standTeam =", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamNotEqualTo(String value) {
            addCriterion("standTeam <>", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamGreaterThan(String value) {
            addCriterion("standTeam >", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamGreaterThanOrEqualTo(String value) {
            addCriterion("standTeam >=", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamLessThan(String value) {
            addCriterion("standTeam <", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamLessThanOrEqualTo(String value) {
            addCriterion("standTeam <=", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamLike(String value) {
            addCriterion("standTeam like", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamNotLike(String value) {
            addCriterion("standTeam not like", value, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamIn(List<String> values) {
            addCriterion("standTeam in", values, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamNotIn(List<String> values) {
            addCriterion("standTeam not in", values, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamBetween(String value1, String value2) {
            addCriterion("standTeam between", value1, value2, "standTeam");
            return (Criteria) this;
        }

        public Criteria andStandTeamNotBetween(String value1, String value2) {
            addCriterion("standTeam not between", value1, value2, "standTeam");
            return (Criteria) this;
        }

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andHBAModelIsNull() {
            addCriterion("HBAModel is null");
            return (Criteria) this;
        }

        public Criteria andHBAModelIsNotNull() {
            addCriterion("HBAModel is not null");
            return (Criteria) this;
        }

        public Criteria andHBAModelEqualTo(String value) {
            addCriterion("HBAModel =", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelNotEqualTo(String value) {
            addCriterion("HBAModel <>", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelGreaterThan(String value) {
            addCriterion("HBAModel >", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelGreaterThanOrEqualTo(String value) {
            addCriterion("HBAModel >=", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelLessThan(String value) {
            addCriterion("HBAModel <", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelLessThanOrEqualTo(String value) {
            addCriterion("HBAModel <=", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelLike(String value) {
            addCriterion("HBAModel like", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelNotLike(String value) {
            addCriterion("HBAModel not like", value, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelIn(List<String> values) {
            addCriterion("HBAModel in", values, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelNotIn(List<String> values) {
            addCriterion("HBAModel not in", values, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelBetween(String value1, String value2) {
            addCriterion("HBAModel between", value1, value2, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBAModelNotBetween(String value1, String value2) {
            addCriterion("HBAModel not between", value1, value2, "HBAModel");
            return (Criteria) this;
        }

        public Criteria andHBACountIsNull() {
            addCriterion("HBACount is null");
            return (Criteria) this;
        }

        public Criteria andHBACountIsNotNull() {
            addCriterion("HBACount is not null");
            return (Criteria) this;
        }

        public Criteria andHBACountEqualTo(Integer value) {
            addCriterion("HBACount =", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountNotEqualTo(Integer value) {
            addCriterion("HBACount <>", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountGreaterThan(Integer value) {
            addCriterion("HBACount >", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountGreaterThanOrEqualTo(Integer value) {
            addCriterion("HBACount >=", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountLessThan(Integer value) {
            addCriterion("HBACount <", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountLessThanOrEqualTo(Integer value) {
            addCriterion("HBACount <=", value, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountIn(List<Integer> values) {
            addCriterion("HBACount in", values, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountNotIn(List<Integer> values) {
            addCriterion("HBACount not in", values, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountBetween(Integer value1, Integer value2) {
            addCriterion("HBACount between", value1, value2, "HBACount");
            return (Criteria) this;
        }

        public Criteria andHBACountNotBetween(Integer value1, Integer value2) {
            addCriterion("HBACount not between", value1, value2, "HBACount");
            return (Criteria) this;
        }

        public Criteria andNetCardModelIsNull() {
            addCriterion("netCardModel is null");
            return (Criteria) this;
        }

        public Criteria andNetCardModelIsNotNull() {
            addCriterion("netCardModel is not null");
            return (Criteria) this;
        }

        public Criteria andNetCardModelEqualTo(String value) {
            addCriterion("netCardModel =", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelNotEqualTo(String value) {
            addCriterion("netCardModel <>", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelGreaterThan(String value) {
            addCriterion("netCardModel >", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelGreaterThanOrEqualTo(String value) {
            addCriterion("netCardModel >=", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelLessThan(String value) {
            addCriterion("netCardModel <", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelLessThanOrEqualTo(String value) {
            addCriterion("netCardModel <=", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelLike(String value) {
            addCriterion("netCardModel like", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelNotLike(String value) {
            addCriterion("netCardModel not like", value, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelIn(List<String> values) {
            addCriterion("netCardModel in", values, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelNotIn(List<String> values) {
            addCriterion("netCardModel not in", values, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelBetween(String value1, String value2) {
            addCriterion("netCardModel between", value1, value2, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andNetCardModelNotBetween(String value1, String value2) {
            addCriterion("netCardModel not between", value1, value2, "netCardModel");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotIsNull() {
            addCriterion("remainingDiskSlot is null");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotIsNotNull() {
            addCriterion("remainingDiskSlot is not null");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotEqualTo(Integer value) {
            addCriterion("remainingDiskSlot =", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotNotEqualTo(Integer value) {
            addCriterion("remainingDiskSlot <>", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotGreaterThan(Integer value) {
            addCriterion("remainingDiskSlot >", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotGreaterThanOrEqualTo(Integer value) {
            addCriterion("remainingDiskSlot >=", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotLessThan(Integer value) {
            addCriterion("remainingDiskSlot <", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotLessThanOrEqualTo(Integer value) {
            addCriterion("remainingDiskSlot <=", value, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotIn(List<Integer> values) {
            addCriterion("remainingDiskSlot in", values, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotNotIn(List<Integer> values) {
            addCriterion("remainingDiskSlot not in", values, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotBetween(Integer value1, Integer value2) {
            addCriterion("remainingDiskSlot between", value1, value2, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andRemainingDiskSlotNotBetween(Integer value1, Integer value2) {
            addCriterion("remainingDiskSlot not between", value1, value2, "remainingDiskSlot");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionIsNull() {
            addCriterion("physicalPosition is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionIsNotNull() {
            addCriterion("physicalPosition is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionEqualTo(String value) {
            addCriterion("physicalPosition =", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionNotEqualTo(String value) {
            addCriterion("physicalPosition <>", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionGreaterThan(String value) {
            addCriterion("physicalPosition >", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionGreaterThanOrEqualTo(String value) {
            addCriterion("physicalPosition >=", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionLessThan(String value) {
            addCriterion("physicalPosition <", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionLessThanOrEqualTo(String value) {
            addCriterion("physicalPosition <=", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionLike(String value) {
            addCriterion("physicalPosition like", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionNotLike(String value) {
            addCriterion("physicalPosition not like", value, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionIn(List<String> values) {
            addCriterion("physicalPosition in", values, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionNotIn(List<String> values) {
            addCriterion("physicalPosition not in", values, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionBetween(String value1, String value2) {
            addCriterion("physicalPosition between", value1, value2, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andPhysicalPositionNotBetween(String value1, String value2) {
            addCriterion("physicalPosition not between", value1, value2, "physicalPosition");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerIsNull() {
            addCriterion("cpuManufacturer is null");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerIsNotNull() {
            addCriterion("cpuManufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerEqualTo(String value) {
            addCriterion("cpuManufacturer =", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerNotEqualTo(String value) {
            addCriterion("cpuManufacturer <>", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerGreaterThan(String value) {
            addCriterion("cpuManufacturer >", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("cpuManufacturer >=", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerLessThan(String value) {
            addCriterion("cpuManufacturer <", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerLessThanOrEqualTo(String value) {
            addCriterion("cpuManufacturer <=", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerLike(String value) {
            addCriterion("cpuManufacturer like", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerNotLike(String value) {
            addCriterion("cpuManufacturer not like", value, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerIn(List<String> values) {
            addCriterion("cpuManufacturer in", values, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerNotIn(List<String> values) {
            addCriterion("cpuManufacturer not in", values, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerBetween(String value1, String value2) {
            addCriterion("cpuManufacturer between", value1, value2, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuManufacturerNotBetween(String value1, String value2) {
            addCriterion("cpuManufacturer not between", value1, value2, "cpuManufacturer");
            return (Criteria) this;
        }

        public Criteria andCpuArchIsNull() {
            addCriterion("cpuArch is null");
            return (Criteria) this;
        }

        public Criteria andCpuArchIsNotNull() {
            addCriterion("cpuArch is not null");
            return (Criteria) this;
        }

        public Criteria andCpuArchEqualTo(String value) {
            addCriterion("cpuArch =", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchNotEqualTo(String value) {
            addCriterion("cpuArch <>", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchGreaterThan(String value) {
            addCriterion("cpuArch >", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchGreaterThanOrEqualTo(String value) {
            addCriterion("cpuArch >=", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchLessThan(String value) {
            addCriterion("cpuArch <", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchLessThanOrEqualTo(String value) {
            addCriterion("cpuArch <=", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchLike(String value) {
            addCriterion("cpuArch like", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchNotLike(String value) {
            addCriterion("cpuArch not like", value, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchIn(List<String> values) {
            addCriterion("cpuArch in", values, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchNotIn(List<String> values) {
            addCriterion("cpuArch not in", values, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchBetween(String value1, String value2) {
            addCriterion("cpuArch between", value1, value2, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andCpuArchNotBetween(String value1, String value2) {
            addCriterion("cpuArch not between", value1, value2, "cpuArch");
            return (Criteria) this;
        }

        public Criteria andAssetBelongIsNull() {
            addCriterion("assetBelong is null");
            return (Criteria) this;
        }

        public Criteria andAssetBelongIsNotNull() {
            addCriterion("assetBelong is not null");
            return (Criteria) this;
        }

        public Criteria andAssetBelongEqualTo(String value) {
            addCriterion("assetBelong =", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongNotEqualTo(String value) {
            addCriterion("assetBelong <>", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongGreaterThan(String value) {
            addCriterion("assetBelong >", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongGreaterThanOrEqualTo(String value) {
            addCriterion("assetBelong >=", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongLessThan(String value) {
            addCriterion("assetBelong <", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongLessThanOrEqualTo(String value) {
            addCriterion("assetBelong <=", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongLike(String value) {
            addCriterion("assetBelong like", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongNotLike(String value) {
            addCriterion("assetBelong not like", value, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongIn(List<String> values) {
            addCriterion("assetBelong in", values, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongNotIn(List<String> values) {
            addCriterion("assetBelong not in", values, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongBetween(String value1, String value2) {
            addCriterion("assetBelong between", value1, value2, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAssetBelongNotBetween(String value1, String value2) {
            addCriterion("assetBelong not between", value1, value2, "assetBelong");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusIsNull() {
            addCriterion("allocateStatus is null");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusIsNotNull() {
            addCriterion("allocateStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusEqualTo(String value) {
            addCriterion("allocateStatus =", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusNotEqualTo(String value) {
            addCriterion("allocateStatus <>", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusGreaterThan(String value) {
            addCriterion("allocateStatus >", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusGreaterThanOrEqualTo(String value) {
            addCriterion("allocateStatus >=", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusLessThan(String value) {
            addCriterion("allocateStatus <", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusLessThanOrEqualTo(String value) {
            addCriterion("allocateStatus <=", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusLike(String value) {
            addCriterion("allocateStatus like", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusNotLike(String value) {
            addCriterion("allocateStatus not like", value, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusIn(List<String> values) {
            addCriterion("allocateStatus in", values, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusNotIn(List<String> values) {
            addCriterion("allocateStatus not in", values, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusBetween(String value1, String value2) {
            addCriterion("allocateStatus between", value1, value2, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andAllocateStatusNotBetween(String value1, String value2) {
            addCriterion("allocateStatus not between", value1, value2, "allocateStatus");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortIsNull() {
            addCriterion("deviceAssort is null");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortIsNotNull() {
            addCriterion("deviceAssort is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortEqualTo(String value) {
            addCriterion("deviceAssort =", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortNotEqualTo(String value) {
            addCriterion("deviceAssort <>", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortGreaterThan(String value) {
            addCriterion("deviceAssort >", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortGreaterThanOrEqualTo(String value) {
            addCriterion("deviceAssort >=", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortLessThan(String value) {
            addCriterion("deviceAssort <", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortLessThanOrEqualTo(String value) {
            addCriterion("deviceAssort <=", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortLike(String value) {
            addCriterion("deviceAssort like", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortNotLike(String value) {
            addCriterion("deviceAssort not like", value, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortIn(List<String> values) {
            addCriterion("deviceAssort in", values, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortNotIn(List<String> values) {
            addCriterion("deviceAssort not in", values, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortBetween(String value1, String value2) {
            addCriterion("deviceAssort between", value1, value2, "deviceAssort");
            return (Criteria) this;
        }

        public Criteria andDeviceAssortNotBetween(String value1, String value2) {
            addCriterion("deviceAssort not between", value1, value2, "deviceAssort");
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

        public Criteria andMemSizeIsNull() {
            addCriterion("memSize is null");
            return (Criteria) this;
        }

        public Criteria andMemSizeIsNotNull() {
            addCriterion("memSize is not null");
            return (Criteria) this;
        }

        public Criteria andMemSizeEqualTo(Integer value) {
            addCriterion("memSize =", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeNotEqualTo(Integer value) {
            addCriterion("memSize <>", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeGreaterThan(Integer value) {
            addCriterion("memSize >", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("memSize >=", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeLessThan(Integer value) {
            addCriterion("memSize <", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeLessThanOrEqualTo(Integer value) {
            addCriterion("memSize <=", value, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeIn(List<Integer> values) {
            addCriterion("memSize in", values, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeNotIn(List<Integer> values) {
            addCriterion("memSize not in", values, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeBetween(Integer value1, Integer value2) {
            addCriterion("memSize between", value1, value2, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("memSize not between", value1, value2, "memSize");
            return (Criteria) this;
        }

        public Criteria andMemCountIsNull() {
            addCriterion("memCount is null");
            return (Criteria) this;
        }

        public Criteria andMemCountIsNotNull() {
            addCriterion("memCount is not null");
            return (Criteria) this;
        }

        public Criteria andMemCountEqualTo(Integer value) {
            addCriterion("memCount =", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountNotEqualTo(Integer value) {
            addCriterion("memCount <>", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountGreaterThan(Integer value) {
            addCriterion("memCount >", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("memCount >=", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountLessThan(Integer value) {
            addCriterion("memCount <", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountLessThanOrEqualTo(Integer value) {
            addCriterion("memCount <=", value, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountIn(List<Integer> values) {
            addCriterion("memCount in", values, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountNotIn(List<Integer> values) {
            addCriterion("memCount not in", values, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountBetween(Integer value1, Integer value2) {
            addCriterion("memCount between", value1, value2, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemCountNotBetween(Integer value1, Integer value2) {
            addCriterion("memCount not between", value1, value2, "memCount");
            return (Criteria) this;
        }

        public Criteria andMemModelIsNull() {
            addCriterion("memModel is null");
            return (Criteria) this;
        }

        public Criteria andMemModelIsNotNull() {
            addCriterion("memModel is not null");
            return (Criteria) this;
        }

        public Criteria andMemModelEqualTo(String value) {
            addCriterion("memModel =", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelNotEqualTo(String value) {
            addCriterion("memModel <>", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelGreaterThan(String value) {
            addCriterion("memModel >", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelGreaterThanOrEqualTo(String value) {
            addCriterion("memModel >=", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelLessThan(String value) {
            addCriterion("memModel <", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelLessThanOrEqualTo(String value) {
            addCriterion("memModel <=", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelLike(String value) {
            addCriterion("memModel like", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelNotLike(String value) {
            addCriterion("memModel not like", value, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelIn(List<String> values) {
            addCriterion("memModel in", values, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelNotIn(List<String> values) {
            addCriterion("memModel not in", values, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelBetween(String value1, String value2) {
            addCriterion("memModel between", value1, value2, "memModel");
            return (Criteria) this;
        }

        public Criteria andMemModelNotBetween(String value1, String value2) {
            addCriterion("memModel not between", value1, value2, "memModel");
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

        public ReserveServerExample.Criteria andAdminsIsNull() {
            addCriterion("admins is null");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsIsNotNull() {
            addCriterion("admins is not null");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsEqualTo(String value) {
            addCriterion("admins =", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsNotEqualTo(String value) {
            addCriterion("admins <>", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsGreaterThan(String value) {
            addCriterion("admins >", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsGreaterThanOrEqualTo(String value) {
            addCriterion("admins >=", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsLessThan(String value) {
            addCriterion("admins <", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsLessThanOrEqualTo(String value) {
            addCriterion("admins <=", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsLike(String value) {
            addCriterion("admins like", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsNotLike(String value) {
            addCriterion("admins not like", value, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsIn(List<String> values) {
            addCriterion("admins in", values, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsNotIn(List<String> values) {
            addCriterion("admins not in", values, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsBetween(String value1, String value2) {
            addCriterion("admins between", value1, value2, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAdminsNotBetween(String value1, String value2) {
            addCriterion("admins not between", value1, value2, "admins");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAliasIsNull() {
            addCriterion("`alias` is null");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAliasIsNotNull() {
            addCriterion("`alias` is not null");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAliasEqualTo(String value) {
            addCriterion("`alias` =", value, "alias");
            return (ReserveServerExample.Criteria) this;
        }

        public ReserveServerExample.Criteria andAliasNotEqualTo(String value) {
            addCriterion("`alias` <>", value, "alias");
            return (ReserveServerExample.Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("`alias` >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("`alias` >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("`alias` <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("`alias` <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("`alias` like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("`alias` not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("`alias` in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("`alias` not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("`alias` between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("`alias` not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyIsNull() {
            addCriterion("memberCompany is null");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyIsNotNull() {
            addCriterion("memberCompany is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyEqualTo(String value) {
            addCriterion("memberCompany =", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyNotEqualTo(String value) {
            addCriterion("memberCompany <>", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyGreaterThan(String value) {
            addCriterion("memberCompany >", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("memberCompany >=", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyLessThan(String value) {
            addCriterion("memberCompany <", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyLessThanOrEqualTo(String value) {
            addCriterion("memberCompany <=", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyLike(String value) {
            addCriterion("memberCompany like", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyNotLike(String value) {
            addCriterion("memberCompany not like", value, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyIn(List<String> values) {
            addCriterion("memberCompany in", values, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyNotIn(List<String> values) {
            addCriterion("memberCompany not in", values, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyBetween(String value1, String value2) {
            addCriterion("memberCompany between", value1, value2, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andMemberCompanyNotBetween(String value1, String value2) {
            addCriterion("memberCompany not between", value1, value2, "memberCompany");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagIsNull() {
            addCriterion("warrantyTag is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagIsNotNull() {
            addCriterion("warrantyTag is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagEqualTo(String value) {
            addCriterion("warrantyTag =", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagNotEqualTo(String value) {
            addCriterion("warrantyTag <>", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagGreaterThan(String value) {
            addCriterion("warrantyTag >", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagGreaterThanOrEqualTo(String value) {
            addCriterion("warrantyTag >=", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagLessThan(String value) {
            addCriterion("warrantyTag <", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagLessThanOrEqualTo(String value) {
            addCriterion("warrantyTag <=", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagLike(String value) {
            addCriterion("warrantyTag like", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagNotLike(String value) {
            addCriterion("warrantyTag not like", value, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagIn(List<String> values) {
            addCriterion("warrantyTag in", values, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagNotIn(List<String> values) {
            addCriterion("warrantyTag not in", values, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagBetween(String value1, String value2) {
            addCriterion("warrantyTag between", value1, value2, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andWarrantyTagNotBetween(String value1, String value2) {
            addCriterion("warrantyTag not between", value1, value2, "warrantyTag");
            return (Criteria) this;
        }

        public Criteria andManageUsernameIsNull() {
            addCriterion("manageUsername is null");
            return (Criteria) this;
        }

        public Criteria andManageUsernameIsNotNull() {
            addCriterion("manageUsername is not null");
            return (Criteria) this;
        }

        public Criteria andManageUsernameEqualTo(String value) {
            addCriterion("manageUsername =", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameNotEqualTo(String value) {
            addCriterion("manageUsername <>", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameGreaterThan(String value) {
            addCriterion("manageUsername >", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("manageUsername >=", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameLessThan(String value) {
            addCriterion("manageUsername <", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameLessThanOrEqualTo(String value) {
            addCriterion("manageUsername <=", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameLike(String value) {
            addCriterion("manageUsername like", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameNotLike(String value) {
            addCriterion("manageUsername not like", value, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameIn(List<String> values) {
            addCriterion("manageUsername in", values, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameNotIn(List<String> values) {
            addCriterion("manageUsername not in", values, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameBetween(String value1, String value2) {
            addCriterion("manageUsername between", value1, value2, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManageUsernameNotBetween(String value1, String value2) {
            addCriterion("manageUsername not between", value1, value2, "manageUsername");
            return (Criteria) this;
        }

        public Criteria andManagePasswordIsNull() {
            addCriterion("managePassword is null");
            return (Criteria) this;
        }

        public Criteria andManagePasswordIsNotNull() {
            addCriterion("managePassword is not null");
            return (Criteria) this;
        }

        public Criteria andManagePasswordEqualTo(String value) {
            addCriterion("managePassword =", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordNotEqualTo(String value) {
            addCriterion("managePassword <>", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordGreaterThan(String value) {
            addCriterion("managePassword >", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordGreaterThanOrEqualTo(String value) {
            addCriterion("managePassword >=", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordLessThan(String value) {
            addCriterion("managePassword <", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordLessThanOrEqualTo(String value) {
            addCriterion("managePassword <=", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordLike(String value) {
            addCriterion("managePassword like", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordNotLike(String value) {
            addCriterion("managePassword not like", value, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordIn(List<String> values) {
            addCriterion("managePassword in", values, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordNotIn(List<String> values) {
            addCriterion("managePassword not in", values, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordBetween(String value1, String value2) {
            addCriterion("managePassword between", value1, value2, "managePassword");
            return (Criteria) this;
        }

        public Criteria andManagePasswordNotBetween(String value1, String value2) {
            addCriterion("managePassword not between", value1, value2, "managePassword");
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