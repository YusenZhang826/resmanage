package com.clic.ccdbaas.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReserveJumperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReserveJumperExample() {
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

        public Criteria andServerIsNull() {
            addCriterion("server is null");
            return (Criteria) this;
        }

        public Criteria andServerIsNotNull() {
            addCriterion("server is not null");
            return (Criteria) this;
        }

        public Criteria andServerEqualTo(String value) {
            addCriterion("server =", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotEqualTo(String value) {
            addCriterion("server <>", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerGreaterThan(String value) {
            addCriterion("server >", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerGreaterThanOrEqualTo(String value) {
            addCriterion("server >=", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLessThan(String value) {
            addCriterion("server <", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLessThanOrEqualTo(String value) {
            addCriterion("server <=", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLike(String value) {
            addCriterion("server like", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotLike(String value) {
            addCriterion("server not like", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerIn(List<String> values) {
            addCriterion("server in", values, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotIn(List<String> values) {
            addCriterion("server not in", values, "server");
            return (Criteria) this;
        }

        public Criteria andServerBetween(String value1, String value2) {
            addCriterion("server between", value1, value2, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotBetween(String value1, String value2) {
            addCriterion("server not between", value1, value2, "server");
            return (Criteria) this;
        }

        public Criteria andPortServerIsNull() {
            addCriterion("portServer is null");
            return (Criteria) this;
        }

        public Criteria andPortServerIsNotNull() {
            addCriterion("portServer is not null");
            return (Criteria) this;
        }

        public Criteria andPortServerEqualTo(String value) {
            addCriterion("portServer =", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerNotEqualTo(String value) {
            addCriterion("portServer <>", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerGreaterThan(String value) {
            addCriterion("portServer >", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerGreaterThanOrEqualTo(String value) {
            addCriterion("portServer >=", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerLessThan(String value) {
            addCriterion("portServer <", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerLessThanOrEqualTo(String value) {
            addCriterion("portServer <=", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerLike(String value) {
            addCriterion("portServer like", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerNotLike(String value) {
            addCriterion("portServer not like", value, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerIn(List<String> values) {
            addCriterion("portServer in", values, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerNotIn(List<String> values) {
            addCriterion("portServer not in", values, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerBetween(String value1, String value2) {
            addCriterion("portServer between", value1, value2, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortServerNotBetween(String value1, String value2) {
            addCriterion("portServer not between", value1, value2, "portServer");
            return (Criteria) this;
        }

        public Criteria andPortPxj1IsNull() {
            addCriterion("portPxj1 is null");
            return (Criteria) this;
        }

        public Criteria andPortPxj1IsNotNull() {
            addCriterion("portPxj1 is not null");
            return (Criteria) this;
        }

        public Criteria andPortPxj1EqualTo(String value) {
            addCriterion("portPxj1 =", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1NotEqualTo(String value) {
            addCriterion("portPxj1 <>", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1GreaterThan(String value) {
            addCriterion("portPxj1 >", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1GreaterThanOrEqualTo(String value) {
            addCriterion("portPxj1 >=", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1LessThan(String value) {
            addCriterion("portPxj1 <", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1LessThanOrEqualTo(String value) {
            addCriterion("portPxj1 <=", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1Like(String value) {
            addCriterion("portPxj1 like", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1NotLike(String value) {
            addCriterion("portPxj1 not like", value, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1In(List<String> values) {
            addCriterion("portPxj1 in", values, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1NotIn(List<String> values) {
            addCriterion("portPxj1 not in", values, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1Between(String value1, String value2) {
            addCriterion("portPxj1 between", value1, value2, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj1NotBetween(String value1, String value2) {
            addCriterion("portPxj1 not between", value1, value2, "portPxj1");
            return (Criteria) this;
        }

        public Criteria andPortPxj2IsNull() {
            addCriterion("portPxj2 is null");
            return (Criteria) this;
        }

        public Criteria andPortPxj2IsNotNull() {
            addCriterion("portPxj2 is not null");
            return (Criteria) this;
        }

        public Criteria andPortPxj2EqualTo(String value) {
            addCriterion("portPxj2 =", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2NotEqualTo(String value) {
            addCriterion("portPxj2 <>", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2GreaterThan(String value) {
            addCriterion("portPxj2 >", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2GreaterThanOrEqualTo(String value) {
            addCriterion("portPxj2 >=", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2LessThan(String value) {
            addCriterion("portPxj2 <", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2LessThanOrEqualTo(String value) {
            addCriterion("portPxj2 <=", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2Like(String value) {
            addCriterion("portPxj2 like", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2NotLike(String value) {
            addCriterion("portPxj2 not like", value, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2In(List<String> values) {
            addCriterion("portPxj2 in", values, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2NotIn(List<String> values) {
            addCriterion("portPxj2 not in", values, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2Between(String value1, String value2) {
            addCriterion("portPxj2 between", value1, value2, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj2NotBetween(String value1, String value2) {
            addCriterion("portPxj2 not between", value1, value2, "portPxj2");
            return (Criteria) this;
        }

        public Criteria andPortPxj3IsNull() {
            addCriterion("portPxj3 is null");
            return (Criteria) this;
        }

        public Criteria andPortPxj3IsNotNull() {
            addCriterion("portPxj3 is not null");
            return (Criteria) this;
        }

        public Criteria andPortPxj3EqualTo(String value) {
            addCriterion("portPxj3 =", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3NotEqualTo(String value) {
            addCriterion("portPxj3 <>", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3GreaterThan(String value) {
            addCriterion("portPxj3 >", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3GreaterThanOrEqualTo(String value) {
            addCriterion("portPxj3 >=", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3LessThan(String value) {
            addCriterion("portPxj3 <", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3LessThanOrEqualTo(String value) {
            addCriterion("portPxj3 <=", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3Like(String value) {
            addCriterion("portPxj3 like", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3NotLike(String value) {
            addCriterion("portPxj3 not like", value, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3In(List<String> values) {
            addCriterion("portPxj3 in", values, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3NotIn(List<String> values) {
            addCriterion("portPxj3 not in", values, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3Between(String value1, String value2) {
            addCriterion("portPxj3 between", value1, value2, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj3NotBetween(String value1, String value2) {
            addCriterion("portPxj3 not between", value1, value2, "portPxj3");
            return (Criteria) this;
        }

        public Criteria andPortPxj4IsNull() {
            addCriterion("portPxj4 is null");
            return (Criteria) this;
        }

        public Criteria andPortPxj4IsNotNull() {
            addCriterion("portPxj4 is not null");
            return (Criteria) this;
        }

        public Criteria andPortPxj4EqualTo(String value) {
            addCriterion("portPxj4 =", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4NotEqualTo(String value) {
            addCriterion("portPxj4 <>", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4GreaterThan(String value) {
            addCriterion("portPxj4 >", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4GreaterThanOrEqualTo(String value) {
            addCriterion("portPxj4 >=", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4LessThan(String value) {
            addCriterion("portPxj4 <", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4LessThanOrEqualTo(String value) {
            addCriterion("portPxj4 <=", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4Like(String value) {
            addCriterion("portPxj4 like", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4NotLike(String value) {
            addCriterion("portPxj4 not like", value, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4In(List<String> values) {
            addCriterion("portPxj4 in", values, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4NotIn(List<String> values) {
            addCriterion("portPxj4 not in", values, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4Between(String value1, String value2) {
            addCriterion("portPxj4 between", value1, value2, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andPortPxj4NotBetween(String value1, String value2) {
            addCriterion("portPxj4 not between", value1, value2, "portPxj4");
            return (Criteria) this;
        }

        public Criteria andSwitcherIsNull() {
            addCriterion("switcher is null");
            return (Criteria) this;
        }

        public Criteria andSwitcherIsNotNull() {
            addCriterion("switcher is not null");
            return (Criteria) this;
        }

        public Criteria andSwitcherEqualTo(String value) {
            addCriterion("switcher =", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherNotEqualTo(String value) {
            addCriterion("switcher <>", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherGreaterThan(String value) {
            addCriterion("switcher >", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherGreaterThanOrEqualTo(String value) {
            addCriterion("switcher >=", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherLessThan(String value) {
            addCriterion("switcher <", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherLessThanOrEqualTo(String value) {
            addCriterion("switcher <=", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherLike(String value) {
            addCriterion("switcher like", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherNotLike(String value) {
            addCriterion("switcher not like", value, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherIn(List<String> values) {
            addCriterion("switcher in", values, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherNotIn(List<String> values) {
            addCriterion("switcher not in", values, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherBetween(String value1, String value2) {
            addCriterion("switcher between", value1, value2, "switcher");
            return (Criteria) this;
        }

        public Criteria andSwitcherNotBetween(String value1, String value2) {
            addCriterion("switcher not between", value1, value2, "switcher");
            return (Criteria) this;
        }

        public Criteria andPortSwitchIsNull() {
            addCriterion("portSwitch is null");
            return (Criteria) this;
        }

        public Criteria andPortSwitchIsNotNull() {
            addCriterion("portSwitch is not null");
            return (Criteria) this;
        }

        public Criteria andPortSwitchEqualTo(String value) {
            addCriterion("portSwitch =", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchNotEqualTo(String value) {
            addCriterion("portSwitch <>", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchGreaterThan(String value) {
            addCriterion("portSwitch >", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchGreaterThanOrEqualTo(String value) {
            addCriterion("portSwitch >=", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchLessThan(String value) {
            addCriterion("portSwitch <", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchLessThanOrEqualTo(String value) {
            addCriterion("portSwitch <=", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchLike(String value) {
            addCriterion("portSwitch like", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchNotLike(String value) {
            addCriterion("portSwitch not like", value, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchIn(List<String> values) {
            addCriterion("portSwitch in", values, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchNotIn(List<String> values) {
            addCriterion("portSwitch not in", values, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchBetween(String value1, String value2) {
            addCriterion("portSwitch between", value1, value2, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andPortSwitchNotBetween(String value1, String value2) {
            addCriterion("portSwitch not between", value1, value2, "portSwitch");
            return (Criteria) this;
        }

        public Criteria andSpeedIsNull() {
            addCriterion("speed is null");
            return (Criteria) this;
        }

        public Criteria andSpeedIsNotNull() {
            addCriterion("speed is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedEqualTo(String value) {
            addCriterion("speed =", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotEqualTo(String value) {
            addCriterion("speed <>", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThan(String value) {
            addCriterion("speed >", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("speed >=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThan(String value) {
            addCriterion("speed <", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThanOrEqualTo(String value) {
            addCriterion("speed <=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLike(String value) {
            addCriterion("speed like", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotLike(String value) {
            addCriterion("speed not like", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedIn(List<String> values) {
            addCriterion("speed in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotIn(List<String> values) {
            addCriterion("speed not in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedBetween(String value1, String value2) {
            addCriterion("speed between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotBetween(String value1, String value2) {
            addCriterion("speed not between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andBondIsNull() {
            addCriterion("bond is null");
            return (Criteria) this;
        }

        public Criteria andBondIsNotNull() {
            addCriterion("bond is not null");
            return (Criteria) this;
        }

        public Criteria andBondEqualTo(String value) {
            addCriterion("bond =", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotEqualTo(String value) {
            addCriterion("bond <>", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondGreaterThan(String value) {
            addCriterion("bond >", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondGreaterThanOrEqualTo(String value) {
            addCriterion("bond >=", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondLessThan(String value) {
            addCriterion("bond <", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondLessThanOrEqualTo(String value) {
            addCriterion("bond <=", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondLike(String value) {
            addCriterion("bond like", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotLike(String value) {
            addCriterion("bond not like", value, "bond");
            return (Criteria) this;
        }

        public Criteria andBondIn(List<String> values) {
            addCriterion("bond in", values, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotIn(List<String> values) {
            addCriterion("bond not in", values, "bond");
            return (Criteria) this;
        }

        public Criteria andBondBetween(String value1, String value2) {
            addCriterion("bond between", value1, value2, "bond");
            return (Criteria) this;
        }

        public Criteria andBondNotBetween(String value1, String value2) {
            addCriterion("bond not between", value1, value2, "bond");
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

        public Criteria andVlanIsNull() {
            addCriterion("vlan is null");
            return (Criteria) this;
        }

        public Criteria andVlanIsNotNull() {
            addCriterion("vlan is not null");
            return (Criteria) this;
        }

        public Criteria andVlanEqualTo(String value) {
            addCriterion("vlan =", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanNotEqualTo(String value) {
            addCriterion("vlan <>", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanGreaterThan(String value) {
            addCriterion("vlan >", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanGreaterThanOrEqualTo(String value) {
            addCriterion("vlan >=", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanLessThan(String value) {
            addCriterion("vlan <", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanLessThanOrEqualTo(String value) {
            addCriterion("vlan <=", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanLike(String value) {
            addCriterion("vlan like", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanNotLike(String value) {
            addCriterion("vlan not like", value, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanIn(List<String> values) {
            addCriterion("vlan in", values, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanNotIn(List<String> values) {
            addCriterion("vlan not in", values, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanBetween(String value1, String value2) {
            addCriterion("vlan between", value1, value2, "vlan");
            return (Criteria) this;
        }

        public Criteria andVlanNotBetween(String value1, String value2) {
            addCriterion("vlan not between", value1, value2, "vlan");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("`comment` is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("`comment` is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("`comment` =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("`comment` <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("`comment` >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("`comment` >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("`comment` <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("`comment` <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("`comment` like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("`comment` not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("`comment` in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("`comment` not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("`comment` between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("`comment` not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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