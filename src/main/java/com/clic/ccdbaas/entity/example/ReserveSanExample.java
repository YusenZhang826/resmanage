package com.clic.ccdbaas.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReserveSanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReserveSanExample() {
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

        public Criteria andResourceStatusIsNull() {
            addCriterion("resourceStatus is null");
            return (Criteria) this;
        }

        public Criteria andResourceStatusIsNotNull() {
            addCriterion("resourceStatus is not null");
            return (Criteria) this;
        }

        public Criteria andResourceStatusEqualTo(String value) {
            addCriterion("resourceStatus =", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusNotEqualTo(String value) {
            addCriterion("resourceStatus <>", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusGreaterThan(String value) {
            addCriterion("resourceStatus >", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusGreaterThanOrEqualTo(String value) {
            addCriterion("resourceStatus >=", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusLessThan(String value) {
            addCriterion("resourceStatus <", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusLessThanOrEqualTo(String value) {
            addCriterion("resourceStatus <=", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusLike(String value) {
            addCriterion("resourceStatus like", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusNotLike(String value) {
            addCriterion("resourceStatus not like", value, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusIn(List<String> values) {
            addCriterion("resourceStatus in", values, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusNotIn(List<String> values) {
            addCriterion("resourceStatus not in", values, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusBetween(String value1, String value2) {
            addCriterion("resourceStatus between", value1, value2, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andResourceStatusNotBetween(String value1, String value2) {
            addCriterion("resourceStatus not between", value1, value2, "resourceStatus");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentIsNull() {
            addCriterion("maintenanceDepartment is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentIsNotNull() {
            addCriterion("maintenanceDepartment is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentEqualTo(String value) {
            addCriterion("maintenanceDepartment =", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentNotEqualTo(String value) {
            addCriterion("maintenanceDepartment <>", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentGreaterThan(String value) {
            addCriterion("maintenanceDepartment >", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("maintenanceDepartment >=", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentLessThan(String value) {
            addCriterion("maintenanceDepartment <", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentLessThanOrEqualTo(String value) {
            addCriterion("maintenanceDepartment <=", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentLike(String value) {
            addCriterion("maintenanceDepartment like", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentNotLike(String value) {
            addCriterion("maintenanceDepartment not like", value, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentIn(List<String> values) {
            addCriterion("maintenanceDepartment in", values, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentNotIn(List<String> values) {
            addCriterion("maintenanceDepartment not in", values, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentBetween(String value1, String value2) {
            addCriterion("maintenanceDepartment between", value1, value2, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andMaintenanceDepartmentNotBetween(String value1, String value2) {
            addCriterion("maintenanceDepartment not between", value1, value2, "maintenanceDepartment");
            return (Criteria) this;
        }

        public Criteria andAdminsIsNull() {
            addCriterion("admins is null");
            return (Criteria) this;
        }

        public Criteria andAdminsIsNotNull() {
            addCriterion("admins is not null");
            return (Criteria) this;
        }

        public Criteria andAdminsEqualTo(String value) {
            addCriterion("admins =", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsNotEqualTo(String value) {
            addCriterion("admins <>", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsGreaterThan(String value) {
            addCriterion("admins >", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsGreaterThanOrEqualTo(String value) {
            addCriterion("admins >=", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsLessThan(String value) {
            addCriterion("admins <", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsLessThanOrEqualTo(String value) {
            addCriterion("admins <=", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsLike(String value) {
            addCriterion("admins like", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsNotLike(String value) {
            addCriterion("admins not like", value, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsIn(List<String> values) {
            addCriterion("admins in", values, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsNotIn(List<String> values) {
            addCriterion("admins not in", values, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsBetween(String value1, String value2) {
            addCriterion("admins between", value1, value2, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminsNotBetween(String value1, String value2) {
            addCriterion("admins not between", value1, value2, "admins");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNull() {
            addCriterion("adminUserName is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNotNull() {
            addCriterion("adminUserName is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameEqualTo(String value) {
            addCriterion("adminUserName =", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotEqualTo(String value) {
            addCriterion("adminUserName <>", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThan(String value) {
            addCriterion("adminUserName >", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("adminUserName >=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThan(String value) {
            addCriterion("adminUserName <", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThanOrEqualTo(String value) {
            addCriterion("adminUserName <=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLike(String value) {
            addCriterion("adminUserName like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotLike(String value) {
            addCriterion("adminUserName not like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIn(List<String> values) {
            addCriterion("adminUserName in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotIn(List<String> values) {
            addCriterion("adminUserName not in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameBetween(String value1, String value2) {
            addCriterion("adminUserName between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotBetween(String value1, String value2) {
            addCriterion("adminUserName not between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAliasIsNull() {
            addCriterion("`alias` is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("`alias` is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("`alias` =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("`alias` <>", value, "alias");
            return (Criteria) this;
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

        public Criteria andUseDescriptionIsNull() {
            addCriterion("useDescription is null");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionIsNotNull() {
            addCriterion("useDescription is not null");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionEqualTo(String value) {
            addCriterion("useDescription =", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionNotEqualTo(String value) {
            addCriterion("useDescription <>", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionGreaterThan(String value) {
            addCriterion("useDescription >", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("useDescription >=", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionLessThan(String value) {
            addCriterion("useDescription <", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionLessThanOrEqualTo(String value) {
            addCriterion("useDescription <=", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionLike(String value) {
            addCriterion("useDescription like", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionNotLike(String value) {
            addCriterion("useDescription not like", value, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionIn(List<String> values) {
            addCriterion("useDescription in", values, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionNotIn(List<String> values) {
            addCriterion("useDescription not in", values, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionBetween(String value1, String value2) {
            addCriterion("useDescription between", value1, value2, "useDescription");
            return (Criteria) this;
        }

        public Criteria andUseDescriptionNotBetween(String value1, String value2) {
            addCriterion("useDescription not between", value1, value2, "useDescription");
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

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
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

        public Criteria andAssetAttributionIsNull() {
            addCriterion("assetAttribution is null");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionIsNotNull() {
            addCriterion("assetAttribution is not null");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionEqualTo(String value) {
            addCriterion("assetAttribution =", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionNotEqualTo(String value) {
            addCriterion("assetAttribution <>", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionGreaterThan(String value) {
            addCriterion("assetAttribution >", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionGreaterThanOrEqualTo(String value) {
            addCriterion("assetAttribution >=", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionLessThan(String value) {
            addCriterion("assetAttribution <", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionLessThanOrEqualTo(String value) {
            addCriterion("assetAttribution <=", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionLike(String value) {
            addCriterion("assetAttribution like", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionNotLike(String value) {
            addCriterion("assetAttribution not like", value, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionIn(List<String> values) {
            addCriterion("assetAttribution in", values, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionNotIn(List<String> values) {
            addCriterion("assetAttribution not in", values, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionBetween(String value1, String value2) {
            addCriterion("assetAttribution between", value1, value2, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andAssetAttributionNotBetween(String value1, String value2) {
            addCriterion("assetAttribution not between", value1, value2, "assetAttribution");
            return (Criteria) this;
        }

        public Criteria andSapIsNull() {
            addCriterion("sap is null");
            return (Criteria) this;
        }

        public Criteria andSapIsNotNull() {
            addCriterion("sap is not null");
            return (Criteria) this;
        }

        public Criteria andSapEqualTo(String value) {
            addCriterion("sap =", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapNotEqualTo(String value) {
            addCriterion("sap <>", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapGreaterThan(String value) {
            addCriterion("sap >", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapGreaterThanOrEqualTo(String value) {
            addCriterion("sap >=", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapLessThan(String value) {
            addCriterion("sap <", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapLessThanOrEqualTo(String value) {
            addCriterion("sap <=", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapLike(String value) {
            addCriterion("sap like", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapNotLike(String value) {
            addCriterion("sap not like", value, "sap");
            return (Criteria) this;
        }

        public Criteria andSapIn(List<String> values) {
            addCriterion("sap in", values, "sap");
            return (Criteria) this;
        }

        public Criteria andSapNotIn(List<String> values) {
            addCriterion("sap not in", values, "sap");
            return (Criteria) this;
        }

        public Criteria andSapBetween(String value1, String value2) {
            addCriterion("sap between", value1, value2, "sap");
            return (Criteria) this;
        }

        public Criteria andSapNotBetween(String value1, String value2) {
            addCriterion("sap not between", value1, value2, "sap");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingIsNull() {
            addCriterion("warrantyMarking is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingIsNotNull() {
            addCriterion("warrantyMarking is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingEqualTo(String value) {
            addCriterion("warrantyMarking =", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingNotEqualTo(String value) {
            addCriterion("warrantyMarking <>", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingGreaterThan(String value) {
            addCriterion("warrantyMarking >", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingGreaterThanOrEqualTo(String value) {
            addCriterion("warrantyMarking >=", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingLessThan(String value) {
            addCriterion("warrantyMarking <", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingLessThanOrEqualTo(String value) {
            addCriterion("warrantyMarking <=", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingLike(String value) {
            addCriterion("warrantyMarking like", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingNotLike(String value) {
            addCriterion("warrantyMarking not like", value, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingIn(List<String> values) {
            addCriterion("warrantyMarking in", values, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingNotIn(List<String> values) {
            addCriterion("warrantyMarking not in", values, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingBetween(String value1, String value2) {
            addCriterion("warrantyMarking between", value1, value2, "warrantyMarking");
            return (Criteria) this;
        }

        public Criteria andWarrantyMarkingNotBetween(String value1, String value2) {
            addCriterion("warrantyMarking not between", value1, value2, "warrantyMarking");
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