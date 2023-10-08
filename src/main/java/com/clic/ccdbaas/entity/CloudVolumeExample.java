package com.clic.ccdbaas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CloudVolumeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CloudVolumeExample() {
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

        public Criteria andNativeidIsNull() {
            addCriterion("nativeId is null");
            return (Criteria) this;
        }

        public Criteria andNativeidIsNotNull() {
            addCriterion("nativeId is not null");
            return (Criteria) this;
        }

        public Criteria andNativeidEqualTo(String value) {
            addCriterion("nativeId =", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidNotEqualTo(String value) {
            addCriterion("nativeId <>", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidGreaterThan(String value) {
            addCriterion("nativeId >", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidGreaterThanOrEqualTo(String value) {
            addCriterion("nativeId >=", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidLessThan(String value) {
            addCriterion("nativeId <", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidLessThanOrEqualTo(String value) {
            addCriterion("nativeId <=", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidLike(String value) {
            addCriterion("nativeId like", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidNotLike(String value) {
            addCriterion("nativeId not like", value, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidIn(List<String> values) {
            addCriterion("nativeId in", values, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidNotIn(List<String> values) {
            addCriterion("nativeId not in", values, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidBetween(String value1, String value2) {
            addCriterion("nativeId between", value1, value2, "nativeid");
            return (Criteria) this;
        }

        public Criteria andNativeidNotBetween(String value1, String value2) {
            addCriterion("nativeId not between", value1, value2, "nativeid");
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

        public Criteria andImageidIsNull() {
            addCriterion("imageId is null");
            return (Criteria) this;
        }

        public Criteria andImageidIsNotNull() {
            addCriterion("imageId is not null");
            return (Criteria) this;
        }

        public Criteria andImageidEqualTo(String value) {
            addCriterion("imageId =", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotEqualTo(String value) {
            addCriterion("imageId <>", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidGreaterThan(String value) {
            addCriterion("imageId >", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidGreaterThanOrEqualTo(String value) {
            addCriterion("imageId >=", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidLessThan(String value) {
            addCriterion("imageId <", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidLessThanOrEqualTo(String value) {
            addCriterion("imageId <=", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidLike(String value) {
            addCriterion("imageId like", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotLike(String value) {
            addCriterion("imageId not like", value, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidIn(List<String> values) {
            addCriterion("imageId in", values, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotIn(List<String> values) {
            addCriterion("imageId not in", values, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidBetween(String value1, String value2) {
            addCriterion("imageId between", value1, value2, "imageid");
            return (Criteria) this;
        }

        public Criteria andImageidNotBetween(String value1, String value2) {
            addCriterion("imageId not between", value1, value2, "imageid");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNull() {
            addCriterion("projectId is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectId is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(String value) {
            addCriterion("projectId =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(String value) {
            addCriterion("projectId <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(String value) {
            addCriterion("projectId >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(String value) {
            addCriterion("projectId >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(String value) {
            addCriterion("projectId <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(String value) {
            addCriterion("projectId <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLike(String value) {
            addCriterion("projectId like", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotLike(String value) {
            addCriterion("projectId not like", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<String> values) {
            addCriterion("projectId in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<String> values) {
            addCriterion("projectId not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(String value1, String value2) {
            addCriterion("projectId between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(String value1, String value2) {
            addCriterion("projectId not between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("`size` is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("`size` is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("`size` =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("`size` <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("`size` >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`size` >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("`size` <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("`size` <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("`size` in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("`size` not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("`size` between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("`size` not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andEncryptedIsNull() {
            addCriterion("`encrypted` is null");
            return (Criteria) this;
        }

        public Criteria andEncryptedIsNotNull() {
            addCriterion("`encrypted` is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptedEqualTo(Boolean value) {
            addCriterion("`encrypted` =", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotEqualTo(Boolean value) {
            addCriterion("`encrypted` <>", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedGreaterThan(Boolean value) {
            addCriterion("`encrypted` >", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`encrypted` >=", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedLessThan(Boolean value) {
            addCriterion("`encrypted` <", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedLessThanOrEqualTo(Boolean value) {
            addCriterion("`encrypted` <=", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedIn(List<Boolean> values) {
            addCriterion("`encrypted` in", values, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotIn(List<Boolean> values) {
            addCriterion("`encrypted` not in", values, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedBetween(Boolean value1, Boolean value2) {
            addCriterion("`encrypted` between", value1, value2, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`encrypted` not between", value1, value2, "encrypted");
            return (Criteria) this;
        }

        public Criteria andBootableIsNull() {
            addCriterion("bootable is null");
            return (Criteria) this;
        }

        public Criteria andBootableIsNotNull() {
            addCriterion("bootable is not null");
            return (Criteria) this;
        }

        public Criteria andBootableEqualTo(Boolean value) {
            addCriterion("bootable =", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableNotEqualTo(Boolean value) {
            addCriterion("bootable <>", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableGreaterThan(Boolean value) {
            addCriterion("bootable >", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bootable >=", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableLessThan(Boolean value) {
            addCriterion("bootable <", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableLessThanOrEqualTo(Boolean value) {
            addCriterion("bootable <=", value, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableIn(List<Boolean> values) {
            addCriterion("bootable in", values, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableNotIn(List<Boolean> values) {
            addCriterion("bootable not in", values, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableBetween(Boolean value1, Boolean value2) {
            addCriterion("bootable between", value1, value2, "bootable");
            return (Criteria) this;
        }

        public Criteria andBootableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bootable not between", value1, value2, "bootable");
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

        public Criteria andCreatedatIsNull() {
            addCriterion("createdAt is null");
            return (Criteria) this;
        }

        public Criteria andCreatedatIsNotNull() {
            addCriterion("createdAt is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedatEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt =", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt <>", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThan(Date value) {
            addCriterionForJDBCDate("createdAt >", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt >=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThan(Date value) {
            addCriterionForJDBCDate("createdAt <", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createdAt <=", value, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatIn(List<Date> values) {
            addCriterionForJDBCDate("createdAt in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotIn(List<Date> values) {
            addCriterionForJDBCDate("createdAt not in", values, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdAt between", value1, value2, "createdat");
            return (Criteria) this;
        }

        public Criteria andCreatedatNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createdAt not between", value1, value2, "createdat");
            return (Criteria) this;
        }

        public Criteria andAzoneidIsNull() {
            addCriterion("azoneId is null");
            return (Criteria) this;
        }

        public Criteria andAzoneidIsNotNull() {
            addCriterion("azoneId is not null");
            return (Criteria) this;
        }

        public Criteria andAzoneidEqualTo(String value) {
            addCriterion("azoneId =", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidNotEqualTo(String value) {
            addCriterion("azoneId <>", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidGreaterThan(String value) {
            addCriterion("azoneId >", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidGreaterThanOrEqualTo(String value) {
            addCriterion("azoneId >=", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidLessThan(String value) {
            addCriterion("azoneId <", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidLessThanOrEqualTo(String value) {
            addCriterion("azoneId <=", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidLike(String value) {
            addCriterion("azoneId like", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidNotLike(String value) {
            addCriterion("azoneId not like", value, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidIn(List<String> values) {
            addCriterion("azoneId in", values, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidNotIn(List<String> values) {
            addCriterion("azoneId not in", values, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidBetween(String value1, String value2) {
            addCriterion("azoneId between", value1, value2, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzoneidNotBetween(String value1, String value2) {
            addCriterion("azoneId not between", value1, value2, "azoneid");
            return (Criteria) this;
        }

        public Criteria andAzonenameIsNull() {
            addCriterion("azoneName is null");
            return (Criteria) this;
        }

        public Criteria andAzonenameIsNotNull() {
            addCriterion("azoneName is not null");
            return (Criteria) this;
        }

        public Criteria andAzonenameEqualTo(String value) {
            addCriterion("azoneName =", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameNotEqualTo(String value) {
            addCriterion("azoneName <>", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameGreaterThan(String value) {
            addCriterion("azoneName >", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameGreaterThanOrEqualTo(String value) {
            addCriterion("azoneName >=", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameLessThan(String value) {
            addCriterion("azoneName <", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameLessThanOrEqualTo(String value) {
            addCriterion("azoneName <=", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameLike(String value) {
            addCriterion("azoneName like", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameNotLike(String value) {
            addCriterion("azoneName not like", value, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameIn(List<String> values) {
            addCriterion("azoneName in", values, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameNotIn(List<String> values) {
            addCriterion("azoneName not in", values, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameBetween(String value1, String value2) {
            addCriterion("azoneName between", value1, value2, "azonename");
            return (Criteria) this;
        }

        public Criteria andAzonenameNotBetween(String value1, String value2) {
            addCriterion("azoneName not between", value1, value2, "azonename");
            return (Criteria) this;
        }

        public Criteria andSharetypeIsNull() {
            addCriterion("shareType is null");
            return (Criteria) this;
        }

        public Criteria andSharetypeIsNotNull() {
            addCriterion("shareType is not null");
            return (Criteria) this;
        }

        public Criteria andSharetypeEqualTo(String value) {
            addCriterion("shareType =", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotEqualTo(String value) {
            addCriterion("shareType <>", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeGreaterThan(String value) {
            addCriterion("shareType >", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeGreaterThanOrEqualTo(String value) {
            addCriterion("shareType >=", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeLessThan(String value) {
            addCriterion("shareType <", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeLessThanOrEqualTo(String value) {
            addCriterion("shareType <=", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeLike(String value) {
            addCriterion("shareType like", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotLike(String value) {
            addCriterion("shareType not like", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeIn(List<String> values) {
            addCriterion("shareType in", values, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotIn(List<String> values) {
            addCriterion("shareType not in", values, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeBetween(String value1, String value2) {
            addCriterion("shareType between", value1, value2, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotBetween(String value1, String value2) {
            addCriterion("shareType not between", value1, value2, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSanpshotidIsNull() {
            addCriterion("sanpshotId is null");
            return (Criteria) this;
        }

        public Criteria andSanpshotidIsNotNull() {
            addCriterion("sanpshotId is not null");
            return (Criteria) this;
        }

        public Criteria andSanpshotidEqualTo(String value) {
            addCriterion("sanpshotId =", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidNotEqualTo(String value) {
            addCriterion("sanpshotId <>", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidGreaterThan(String value) {
            addCriterion("sanpshotId >", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidGreaterThanOrEqualTo(String value) {
            addCriterion("sanpshotId >=", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidLessThan(String value) {
            addCriterion("sanpshotId <", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidLessThanOrEqualTo(String value) {
            addCriterion("sanpshotId <=", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidLike(String value) {
            addCriterion("sanpshotId like", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidNotLike(String value) {
            addCriterion("sanpshotId not like", value, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidIn(List<String> values) {
            addCriterion("sanpshotId in", values, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidNotIn(List<String> values) {
            addCriterion("sanpshotId not in", values, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidBetween(String value1, String value2) {
            addCriterion("sanpshotId between", value1, value2, "sanpshotid");
            return (Criteria) this;
        }

        public Criteria andSanpshotidNotBetween(String value1, String value2) {
            addCriterion("sanpshotId not between", value1, value2, "sanpshotid");
            return (Criteria) this;
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

        public Criteria andRegionidIsNull() {
            addCriterion("regionId is null");
            return (Criteria) this;
        }

        public Criteria andRegionidIsNotNull() {
            addCriterion("regionId is not null");
            return (Criteria) this;
        }

        public Criteria andRegionidEqualTo(String value) {
            addCriterion("regionId =", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidNotEqualTo(String value) {
            addCriterion("regionId <>", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidGreaterThan(String value) {
            addCriterion("regionId >", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidGreaterThanOrEqualTo(String value) {
            addCriterion("regionId >=", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidLessThan(String value) {
            addCriterion("regionId <", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidLessThanOrEqualTo(String value) {
            addCriterion("regionId <=", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidLike(String value) {
            addCriterion("regionId like", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidNotLike(String value) {
            addCriterion("regionId not like", value, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidIn(List<String> values) {
            addCriterion("regionId in", values, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidNotIn(List<String> values) {
            addCriterion("regionId not in", values, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidBetween(String value1, String value2) {
            addCriterion("regionId between", value1, value2, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionidNotBetween(String value1, String value2) {
            addCriterion("regionId not between", value1, value2, "regionid");
            return (Criteria) this;
        }

        public Criteria andRegionnameIsNull() {
            addCriterion("regionName is null");
            return (Criteria) this;
        }

        public Criteria andRegionnameIsNotNull() {
            addCriterion("regionName is not null");
            return (Criteria) this;
        }

        public Criteria andRegionnameEqualTo(String value) {
            addCriterion("regionName =", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameNotEqualTo(String value) {
            addCriterion("regionName <>", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameGreaterThan(String value) {
            addCriterion("regionName >", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameGreaterThanOrEqualTo(String value) {
            addCriterion("regionName >=", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameLessThan(String value) {
            addCriterion("regionName <", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameLessThanOrEqualTo(String value) {
            addCriterion("regionName <=", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameLike(String value) {
            addCriterion("regionName like", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameNotLike(String value) {
            addCriterion("regionName not like", value, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameIn(List<String> values) {
            addCriterion("regionName in", values, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameNotIn(List<String> values) {
            addCriterion("regionName not in", values, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameBetween(String value1, String value2) {
            addCriterion("regionName between", value1, value2, "regionname");
            return (Criteria) this;
        }

        public Criteria andRegionnameNotBetween(String value1, String value2) {
            addCriterion("regionName not between", value1, value2, "regionname");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIsNull() {
            addCriterion("lastModified is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIsNotNull() {
            addCriterion("lastModified is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedEqualTo(Date value) {
            addCriterion("lastModified =", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotEqualTo(Date value) {
            addCriterion("lastModified <>", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThan(Date value) {
            addCriterion("lastModified >", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("lastModified >=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThan(Date value) {
            addCriterion("lastModified <", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedLessThanOrEqualTo(Date value) {
            addCriterion("lastModified <=", value, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedIn(List<Date> values) {
            addCriterion("lastModified in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotIn(List<Date> values) {
            addCriterion("lastModified not in", values, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedBetween(Date value1, Date value2) {
            addCriterion("lastModified between", value1, value2, "lastmodified");
            return (Criteria) this;
        }

        public Criteria andLastmodifiedNotBetween(Date value1, Date value2) {
            addCriterion("lastModified not between", value1, value2, "lastmodified");
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