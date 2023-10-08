package com.clic.ccdbaas.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RegistrationProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegistrationProductExample() {
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

        public Criteria andAbbrIsNull() {
            addCriterion("abbr is null");
            return (Criteria) this;
        }

        public Criteria andAbbrIsNotNull() {
            addCriterion("abbr is not null");
            return (Criteria) this;
        }

        public Criteria andAbbrEqualTo(String value) {
            addCriterion("abbr =", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrNotEqualTo(String value) {
            addCriterion("abbr <>", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrGreaterThan(String value) {
            addCriterion("abbr >", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrGreaterThanOrEqualTo(String value) {
            addCriterion("abbr >=", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrLessThan(String value) {
            addCriterion("abbr <", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrLessThanOrEqualTo(String value) {
            addCriterion("abbr <=", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrLike(String value) {
            addCriterion("abbr like", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrNotLike(String value) {
            addCriterion("abbr not like", value, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrIn(List<String> values) {
            addCriterion("abbr in", values, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrNotIn(List<String> values) {
            addCriterion("abbr not in", values, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrBetween(String value1, String value2) {
            addCriterion("abbr between", value1, value2, "abbr");
            return (Criteria) this;
        }

        public Criteria andAbbrNotBetween(String value1, String value2) {
            addCriterion("abbr not between", value1, value2, "abbr");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andchangingInfoIsNull() {
            addCriterion("changingInfo is null");
            return (Criteria) this;
        }

        public Criteria andchangingInfoIsNotNull() {
            addCriterion("changingInfo is not null");
            return (Criteria) this;
        }

        public Criteria andchangingInfoEqualTo(String value) {
            addCriterion("changingInfo =", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoNotEqualTo(String value) {
            addCriterion("changingInfo <>", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoGreaterThan(String value) {
            addCriterion("changingInfo >", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoGreaterThanOrEqualTo(String value) {
            addCriterion("changingInfo >=", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoLessThan(String value) {
            addCriterion("changingInfo <", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoLessThanOrEqualTo(String value) {
            addCriterion("changingInfo <=", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoLike(String value) {
            addCriterion("changingInfo like", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoNotLike(String value) {
            addCriterion("changingInfo not like", value, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoIn(List<String> values) {
            addCriterion("changingInfo in", values, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoNotIn(List<String> values) {
            addCriterion("changingInfo not in", values, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoBetween(String value1, String value2) {
            addCriterion("changingInfo between", value1, value2, "changingInfo");
            return (Criteria) this;
        }

        public Criteria andchangingInfoNotBetween(String value1, String value2) {
            addCriterion("changingInfo not between", value1, value2, "changingInfo");
            return (Criteria) this;
        }

        public Criteria anddataLevelIsNull() {
            addCriterion("dataLevel is null");
            return (Criteria) this;
        }

        public Criteria anddataLevelIsNotNull() {
            addCriterion("dataLevel is not null");
            return (Criteria) this;
        }

        public Criteria anddataLevelEqualTo(Integer value) {
            addCriterion("dataLevel =", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelNotEqualTo(Integer value) {
            addCriterion("dataLevel <>", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelGreaterThan(Integer value) {
            addCriterion("dataLevel >", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("dataLevel >=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelLessThan(Integer value) {
            addCriterion("dataLevel <", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelLessThanOrEqualTo(Integer value) {
            addCriterion("dataLevel <=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelIn(List<Integer> values) {
            addCriterion("dataLevel in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelNotIn(List<Integer> values) {
            addCriterion("dataLevel not in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelBetween(Integer value1, Integer value2) {
            addCriterion("dataLevel between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria anddataLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("dataLevel not between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("`desc` is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("`desc` is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("`desc` =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("`desc` <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("`desc` >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("`desc` >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("`desc` <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("`desc` <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("`desc` like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("`desc` not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("`desc` in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("`desc` not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("`desc` between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("`desc` not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria anddetailDescIsNull() {
            addCriterion("detailDesc is null");
            return (Criteria) this;
        }

        public Criteria anddetailDescIsNotNull() {
            addCriterion("detailDesc is not null");
            return (Criteria) this;
        }

        public Criteria anddetailDescEqualTo(String value) {
            addCriterion("detailDesc =", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescNotEqualTo(String value) {
            addCriterion("detailDesc <>", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescGreaterThan(String value) {
            addCriterion("detailDesc >", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescGreaterThanOrEqualTo(String value) {
            addCriterion("detailDesc >=", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescLessThan(String value) {
            addCriterion("detailDesc <", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescLessThanOrEqualTo(String value) {
            addCriterion("detailDesc <=", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescLike(String value) {
            addCriterion("detailDesc like", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescNotLike(String value) {
            addCriterion("detailDesc not like", value, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescIn(List<String> values) {
            addCriterion("detailDesc in", values, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescNotIn(List<String> values) {
            addCriterion("detailDesc not in", values, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescBetween(String value1, String value2) {
            addCriterion("detailDesc between", value1, value2, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddetailDescNotBetween(String value1, String value2) {
            addCriterion("detailDesc not between", value1, value2, "detailDesc");
            return (Criteria) this;
        }

        public Criteria anddmUidsIsNull() {
            addCriterion("dmUids is null");
            return (Criteria) this;
        }

        public Criteria anddmUidsIsNotNull() {
            addCriterion("dmUids is not null");
            return (Criteria) this;
        }

        public Criteria anddmUidsEqualTo(String value) {
            addCriterion("dmUids =", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsNotEqualTo(String value) {
            addCriterion("dmUids <>", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsGreaterThan(String value) {
            addCriterion("dmUids >", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsGreaterThanOrEqualTo(String value) {
            addCriterion("dmUids >=", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsLessThan(String value) {
            addCriterion("dmUids <", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsLessThanOrEqualTo(String value) {
            addCriterion("dmUids <=", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsLike(String value) {
            addCriterion("dmUids like", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsNotLike(String value) {
            addCriterion("dmUids not like", value, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsIn(List<String> values) {
            addCriterion("dmUids in", values, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsNotIn(List<String> values) {
            addCriterion("dmUids not in", values, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsBetween(String value1, String value2) {
            addCriterion("dmUids between", value1, value2, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddmUidsNotBetween(String value1, String value2) {
            addCriterion("dmUids not between", value1, value2, "dmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsIsNull() {
            addCriterion("dtmUids is null");
            return (Criteria) this;
        }

        public Criteria anddtmUidsIsNotNull() {
            addCriterion("dtmUids is not null");
            return (Criteria) this;
        }

        public Criteria anddtmUidsEqualTo(String value) {
            addCriterion("dtmUids =", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsNotEqualTo(String value) {
            addCriterion("dtmUids <>", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsGreaterThan(String value) {
            addCriterion("dtmUids >", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsGreaterThanOrEqualTo(String value) {
            addCriterion("dtmUids >=", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsLessThan(String value) {
            addCriterion("dtmUids <", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsLessThanOrEqualTo(String value) {
            addCriterion("dtmUids <=", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsLike(String value) {
            addCriterion("dtmUids like", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsNotLike(String value) {
            addCriterion("dtmUids not like", value, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsIn(List<String> values) {
            addCriterion("dtmUids in", values, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsNotIn(List<String> values) {
            addCriterion("dtmUids not in", values, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsBetween(String value1, String value2) {
            addCriterion("dtmUids between", value1, value2, "dtmUids");
            return (Criteria) this;
        }

        public Criteria anddtmUidsNotBetween(String value1, String value2) {
            addCriterion("dtmUids not between", value1, value2, "dtmUids");
            return (Criteria) this;
        }

        public Criteria andEntranceIsNull() {
            addCriterion("entrance is null");
            return (Criteria) this;
        }

        public Criteria andEntranceIsNotNull() {
            addCriterion("entrance is not null");
            return (Criteria) this;
        }

        public Criteria andEntranceEqualTo(String value) {
            addCriterion("entrance =", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotEqualTo(String value) {
            addCriterion("entrance <>", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceGreaterThan(String value) {
            addCriterion("entrance >", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceGreaterThanOrEqualTo(String value) {
            addCriterion("entrance >=", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLessThan(String value) {
            addCriterion("entrance <", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLessThanOrEqualTo(String value) {
            addCriterion("entrance <=", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceLike(String value) {
            addCriterion("entrance like", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotLike(String value) {
            addCriterion("entrance not like", value, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceIn(List<String> values) {
            addCriterion("entrance in", values, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotIn(List<String> values) {
            addCriterion("entrance not in", values, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceBetween(String value1, String value2) {
            addCriterion("entrance between", value1, value2, "entrance");
            return (Criteria) this;
        }

        public Criteria andEntranceNotBetween(String value1, String value2) {
            addCriterion("entrance not between", value1, value2, "entrance");
            return (Criteria) this;
        }

        public Criteria andgpLevelIsNull() {
            addCriterion("gpLevel is null");
            return (Criteria) this;
        }

        public Criteria andgpLevelIsNotNull() {
            addCriterion("gpLevel is not null");
            return (Criteria) this;
        }

        public Criteria andgpLevelEqualTo(Integer value) {
            addCriterion("gpLevel =", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelNotEqualTo(Integer value) {
            addCriterion("gpLevel <>", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelGreaterThan(Integer value) {
            addCriterion("gpLevel >", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("gpLevel >=", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelLessThan(Integer value) {
            addCriterion("gpLevel <", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelLessThanOrEqualTo(Integer value) {
            addCriterion("gpLevel <=", value, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelIn(List<Integer> values) {
            addCriterion("gpLevel in", values, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelNotIn(List<Integer> values) {
            addCriterion("gpLevel not in", values, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelBetween(Integer value1, Integer value2) {
            addCriterion("gpLevel between", value1, value2, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgpLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("gpLevel not between", value1, value2, "gpLevel");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdIsNull() {
            addCriterion("groupRobotId is null");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdIsNotNull() {
            addCriterion("groupRobotId is not null");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdEqualTo(String value) {
            addCriterion("groupRobotId =", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdNotEqualTo(String value) {
            addCriterion("groupRobotId <>", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdGreaterThan(String value) {
            addCriterion("groupRobotId >", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdGreaterThanOrEqualTo(String value) {
            addCriterion("groupRobotId >=", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdLessThan(String value) {
            addCriterion("groupRobotId <", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdLessThanOrEqualTo(String value) {
            addCriterion("groupRobotId <=", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdLike(String value) {
            addCriterion("groupRobotId like", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdNotLike(String value) {
            addCriterion("groupRobotId not like", value, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdIn(List<String> values) {
            addCriterion("groupRobotId in", values, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdNotIn(List<String> values) {
            addCriterion("groupRobotId not in", values, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdBetween(String value1, String value2) {
            addCriterion("groupRobotId between", value1, value2, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotIdNotBetween(String value1, String value2) {
            addCriterion("groupRobotId not between", value1, value2, "groupRobotId");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameIsNull() {
            addCriterion("groupRobotName is null");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameIsNotNull() {
            addCriterion("groupRobotName is not null");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameEqualTo(String value) {
            addCriterion("groupRobotName =", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameNotEqualTo(String value) {
            addCriterion("groupRobotName <>", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameGreaterThan(String value) {
            addCriterion("groupRobotName >", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameGreaterThanOrEqualTo(String value) {
            addCriterion("groupRobotName >=", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameLessThan(String value) {
            addCriterion("groupRobotName <", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameLessThanOrEqualTo(String value) {
            addCriterion("groupRobotName <=", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameLike(String value) {
            addCriterion("groupRobotName like", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameNotLike(String value) {
            addCriterion("groupRobotName not like", value, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameIn(List<String> values) {
            addCriterion("groupRobotName in", values, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameNotIn(List<String> values) {
            addCriterion("groupRobotName not in", values, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameBetween(String value1, String value2) {
            addCriterion("groupRobotName between", value1, value2, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andgroupRobotNameNotBetween(String value1, String value2) {
            addCriterion("groupRobotName not between", value1, value2, "groupRobotName");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathIsNull() {
            addCriterion("mainUgIdpath is null");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathIsNotNull() {
            addCriterion("mainUgIdpath is not null");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathEqualTo(String value) {
            addCriterion("mainUgIdpath =", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathNotEqualTo(String value) {
            addCriterion("mainUgIdpath <>", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathGreaterThan(String value) {
            addCriterion("mainUgIdpath >", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathGreaterThanOrEqualTo(String value) {
            addCriterion("mainUgIdpath >=", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathLessThan(String value) {
            addCriterion("mainUgIdpath <", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathLessThanOrEqualTo(String value) {
            addCriterion("mainUgIdpath <=", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathLike(String value) {
            addCriterion("mainUgIdpath like", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathNotLike(String value) {
            addCriterion("mainUgIdpath not like", value, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathIn(List<String> values) {
            addCriterion("mainUgIdpath in", values, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathNotIn(List<String> values) {
            addCriterion("mainUgIdpath not in", values, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathBetween(String value1, String value2) {
            addCriterion("mainUgIdpath between", value1, value2, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathNotBetween(String value1, String value2) {
            addCriterion("mainUgIdpath not between", value1, value2, "mainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoIsNull() {
            addCriterion("mainUgIdpathInfo is null");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoIsNotNull() {
            addCriterion("mainUgIdpathInfo is not null");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoEqualTo(String value) {
            addCriterion("mainUgIdpathInfo =", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoNotEqualTo(String value) {
            addCriterion("mainUgIdpathInfo <>", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoGreaterThan(String value) {
            addCriterion("mainUgIdpathInfo >", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoGreaterThanOrEqualTo(String value) {
            addCriterion("mainUgIdpathInfo >=", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoLessThan(String value) {
            addCriterion("mainUgIdpathInfo <", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoLessThanOrEqualTo(String value) {
            addCriterion("mainUgIdpathInfo <=", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoLike(String value) {
            addCriterion("mainUgIdpathInfo like", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoNotLike(String value) {
            addCriterion("mainUgIdpathInfo not like", value, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoIn(List<String> values) {
            addCriterion("mainUgIdpathInfo in", values, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoNotIn(List<String> values) {
            addCriterion("mainUgIdpathInfo not in", values, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoBetween(String value1, String value2) {
            addCriterion("mainUgIdpathInfo between", value1, value2, "mainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andmainUgIdpathInfoNotBetween(String value1, String value2) {
            addCriterion("mainUgIdpathInfo not between", value1, value2, "mainUgIdpathInfo");
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

        public Criteria andObjectIsNull() {
            addCriterion("`object` is null");
            return (Criteria) this;
        }

        public Criteria andObjectIsNotNull() {
            addCriterion("`object` is not null");
            return (Criteria) this;
        }

        public Criteria andObjectEqualTo(String value) {
            addCriterion("`object` =", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotEqualTo(String value) {
            addCriterion("`object` <>", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThan(String value) {
            addCriterion("`object` >", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThanOrEqualTo(String value) {
            addCriterion("`object` >=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThan(String value) {
            addCriterion("`object` <", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThanOrEqualTo(String value) {
            addCriterion("`object` <=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLike(String value) {
            addCriterion("`object` like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotLike(String value) {
            addCriterion("`object` not like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectIn(List<String> values) {
            addCriterion("`object` in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotIn(List<String> values) {
            addCriterion("`object` not in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectBetween(String value1, String value2) {
            addCriterion("`object` between", value1, value2, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotBetween(String value1, String value2) {
            addCriterion("`object` not between", value1, value2, "object");
            return (Criteria) this;
        }

        public Criteria andomUidsIsNull() {
            addCriterion("omUids is null");
            return (Criteria) this;
        }

        public Criteria andomUidsIsNotNull() {
            addCriterion("omUids is not null");
            return (Criteria) this;
        }

        public Criteria andomUidsEqualTo(String value) {
            addCriterion("omUids =", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsNotEqualTo(String value) {
            addCriterion("omUids <>", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsGreaterThan(String value) {
            addCriterion("omUids >", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsGreaterThanOrEqualTo(String value) {
            addCriterion("omUids >=", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsLessThan(String value) {
            addCriterion("omUids <", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsLessThanOrEqualTo(String value) {
            addCriterion("omUids <=", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsLike(String value) {
            addCriterion("omUids like", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsNotLike(String value) {
            addCriterion("omUids not like", value, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsIn(List<String> values) {
            addCriterion("omUids in", values, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsNotIn(List<String> values) {
            addCriterion("omUids not in", values, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsBetween(String value1, String value2) {
            addCriterion("omUids between", value1, value2, "omUids");
            return (Criteria) this;
        }

        public Criteria andomUidsNotBetween(String value1, String value2) {
            addCriterion("omUids not between", value1, value2, "omUids");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("`owner` is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("`owner` is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("`owner` =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("`owner` <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("`owner` >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("`owner` >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("`owner` <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("`owner` <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("`owner` like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("`owner` not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("`owner` in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("`owner` not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("`owner` between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("`owner` not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andparentAbbrIsNull() {
            addCriterion("parentAbbr is null");
            return (Criteria) this;
        }

        public Criteria andparentAbbrIsNotNull() {
            addCriterion("parentAbbr is not null");
            return (Criteria) this;
        }

        public Criteria andparentAbbrEqualTo(String value) {
            addCriterion("parentAbbr =", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrNotEqualTo(String value) {
            addCriterion("parentAbbr <>", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrGreaterThan(String value) {
            addCriterion("parentAbbr >", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrGreaterThanOrEqualTo(String value) {
            addCriterion("parentAbbr >=", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrLessThan(String value) {
            addCriterion("parentAbbr <", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrLessThanOrEqualTo(String value) {
            addCriterion("parentAbbr <=", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrLike(String value) {
            addCriterion("parentAbbr like", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrNotLike(String value) {
            addCriterion("parentAbbr not like", value, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrIn(List<String> values) {
            addCriterion("parentAbbr in", values, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrNotIn(List<String> values) {
            addCriterion("parentAbbr not in", values, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrBetween(String value1, String value2) {
            addCriterion("parentAbbr between", value1, value2, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentAbbrNotBetween(String value1, String value2) {
            addCriterion("parentAbbr not between", value1, value2, "parentAbbr");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathIsNull() {
            addCriterion("parentMainUgIdpath is null");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathIsNotNull() {
            addCriterion("parentMainUgIdpath is not null");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathEqualTo(String value) {
            addCriterion("parentMainUgIdpath =", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathNotEqualTo(String value) {
            addCriterion("parentMainUgIdpath <>", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathGreaterThan(String value) {
            addCriterion("parentMainUgIdpath >", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathGreaterThanOrEqualTo(String value) {
            addCriterion("parentMainUgIdpath >=", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathLessThan(String value) {
            addCriterion("parentMainUgIdpath <", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathLessThanOrEqualTo(String value) {
            addCriterion("parentMainUgIdpath <=", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathLike(String value) {
            addCriterion("parentMainUgIdpath like", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathNotLike(String value) {
            addCriterion("parentMainUgIdpath not like", value, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathIn(List<String> values) {
            addCriterion("parentMainUgIdpath in", values, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathNotIn(List<String> values) {
            addCriterion("parentMainUgIdpath not in", values, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathBetween(String value1, String value2) {
            addCriterion("parentMainUgIdpath between", value1, value2, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathNotBetween(String value1, String value2) {
            addCriterion("parentMainUgIdpath not between", value1, value2, "parentMainUgIdpath");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoIsNull() {
            addCriterion("parentMainUgIdpathInfo is null");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoIsNotNull() {
            addCriterion("parentMainUgIdpathInfo is not null");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoEqualTo(String value) {
            addCriterion("parentMainUgIdpathInfo =", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoNotEqualTo(String value) {
            addCriterion("parentMainUgIdpathInfo <>", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoGreaterThan(String value) {
            addCriterion("parentMainUgIdpathInfo >", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoGreaterThanOrEqualTo(String value) {
            addCriterion("parentMainUgIdpathInfo >=", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoLessThan(String value) {
            addCriterion("parentMainUgIdpathInfo <", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoLessThanOrEqualTo(String value) {
            addCriterion("parentMainUgIdpathInfo <=", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoLike(String value) {
            addCriterion("parentMainUgIdpathInfo like", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoNotLike(String value) {
            addCriterion("parentMainUgIdpathInfo not like", value, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoIn(List<String> values) {
            addCriterion("parentMainUgIdpathInfo in", values, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoNotIn(List<String> values) {
            addCriterion("parentMainUgIdpathInfo not in", values, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoBetween(String value1, String value2) {
            addCriterion("parentMainUgIdpathInfo between", value1, value2, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentMainUgIdpathInfoNotBetween(String value1, String value2) {
            addCriterion("parentMainUgIdpathInfo not between", value1, value2, "parentMainUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andparentNameIsNull() {
            addCriterion("parentName is null");
            return (Criteria) this;
        }

        public Criteria andparentNameIsNotNull() {
            addCriterion("parentName is not null");
            return (Criteria) this;
        }

        public Criteria andparentNameEqualTo(String value) {
            addCriterion("parentName =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameNotEqualTo(String value) {
            addCriterion("parentName <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameGreaterThan(String value) {
            addCriterion("parentName >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parentName >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameLessThan(String value) {
            addCriterion("parentName <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameLessThanOrEqualTo(String value) {
            addCriterion("parentName <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameLike(String value) {
            addCriterion("parentName like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameNotLike(String value) {
            addCriterion("parentName not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameIn(List<String> values) {
            addCriterion("parentName in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameNotIn(List<String> values) {
            addCriterion("parentName not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameBetween(String value1, String value2) {
            addCriterion("parentName between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentNameNotBetween(String value1, String value2) {
            addCriterion("parentName not between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andparentTokenIsNull() {
            addCriterion("parentToken is null");
            return (Criteria) this;
        }

        public Criteria andparentTokenIsNotNull() {
            addCriterion("parentToken is not null");
            return (Criteria) this;
        }

        public Criteria andparentTokenEqualTo(String value) {
            addCriterion("parentToken =", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenNotEqualTo(String value) {
            addCriterion("parentToken <>", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenGreaterThan(String value) {
            addCriterion("parentToken >", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenGreaterThanOrEqualTo(String value) {
            addCriterion("parentToken >=", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenLessThan(String value) {
            addCriterion("parentToken <", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenLessThanOrEqualTo(String value) {
            addCriterion("parentToken <=", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenLike(String value) {
            addCriterion("parentToken like", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenNotLike(String value) {
            addCriterion("parentToken not like", value, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenIn(List<String> values) {
            addCriterion("parentToken in", values, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenNotIn(List<String> values) {
            addCriterion("parentToken not in", values, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenBetween(String value1, String value2) {
            addCriterion("parentToken between", value1, value2, "parentToken");
            return (Criteria) this;
        }

        public Criteria andparentTokenNotBetween(String value1, String value2) {
            addCriterion("parentToken not between", value1, value2, "parentToken");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdIsNull() {
            addCriterion("productMonitorWarnDisposalGroupId is null");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdIsNotNull() {
            addCriterion("productMonitorWarnDisposalGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdEqualTo(String value) {
            addCriterion("productMonitorWarnDisposalGroupId =", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdNotEqualTo(String value) {
            addCriterion("productMonitorWarnDisposalGroupId <>", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdGreaterThan(String value) {
            addCriterion("productMonitorWarnDisposalGroupId >", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("productMonitorWarnDisposalGroupId >=", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdLessThan(String value) {
            addCriterion("productMonitorWarnDisposalGroupId <", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdLessThanOrEqualTo(String value) {
            addCriterion("productMonitorWarnDisposalGroupId <=", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdLike(String value) {
            addCriterion("productMonitorWarnDisposalGroupId like", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdNotLike(String value) {
            addCriterion("productMonitorWarnDisposalGroupId not like", value, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdIn(List<String> values) {
            addCriterion("productMonitorWarnDisposalGroupId in", values, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdNotIn(List<String> values) {
            addCriterion("productMonitorWarnDisposalGroupId not in", values, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdBetween(String value1, String value2) {
            addCriterion("productMonitorWarnDisposalGroupId between", value1, value2, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductMonitorWarnDisposalGroupIdNotBetween(String value1, String value2) {
            addCriterion("productMonitorWarnDisposalGroupId not between", value1, value2, "productMonitorWarnDisposalGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdIsNull() {
            addCriterion("productSafeAnswerGroupId is null");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdIsNotNull() {
            addCriterion("productSafeAnswerGroupId is not null");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdEqualTo(String value) {
            addCriterion("productSafeAnswerGroupId =", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdNotEqualTo(String value) {
            addCriterion("productSafeAnswerGroupId <>", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdGreaterThan(String value) {
            addCriterion("productSafeAnswerGroupId >", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("productSafeAnswerGroupId >=", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdLessThan(String value) {
            addCriterion("productSafeAnswerGroupId <", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdLessThanOrEqualTo(String value) {
            addCriterion("productSafeAnswerGroupId <=", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdLike(String value) {
            addCriterion("productSafeAnswerGroupId like", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdNotLike(String value) {
            addCriterion("productSafeAnswerGroupId not like", value, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdIn(List<String> values) {
            addCriterion("productSafeAnswerGroupId in", values, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdNotIn(List<String> values) {
            addCriterion("productSafeAnswerGroupId not in", values, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdBetween(String value1, String value2) {
            addCriterion("productSafeAnswerGroupId between", value1, value2, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andproductSafeAnswerGroupIdNotBetween(String value1, String value2) {
            addCriterion("productSafeAnswerGroupId not between", value1, value2, "productSafeAnswerGroupId");
            return (Criteria) this;
        }

        public Criteria andPublishIsNull() {
            addCriterion("publish is null");
            return (Criteria) this;
        }

        public Criteria andPublishIsNotNull() {
            addCriterion("publish is not null");
            return (Criteria) this;
        }

        public Criteria andPublishEqualTo(Integer value) {
            addCriterion("publish =", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotEqualTo(Integer value) {
            addCriterion("publish <>", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThan(Integer value) {
            addCriterion("publish >", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish >=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThan(Integer value) {
            addCriterion("publish <", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThanOrEqualTo(Integer value) {
            addCriterion("publish <=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishIn(List<Integer> values) {
            addCriterion("publish in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotIn(List<Integer> values) {
            addCriterion("publish not in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishBetween(Integer value1, Integer value2) {
            addCriterion("publish between", value1, value2, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotBetween(Integer value1, Integer value2) {
            addCriterion("publish not between", value1, value2, "publish");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathIsNull() {
            addCriterion("secondaryUgIdpath is null");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathIsNotNull() {
            addCriterion("secondaryUgIdpath is not null");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathEqualTo(String value) {
            addCriterion("secondaryUgIdpath =", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathNotEqualTo(String value) {
            addCriterion("secondaryUgIdpath <>", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathGreaterThan(String value) {
            addCriterion("secondaryUgIdpath >", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathGreaterThanOrEqualTo(String value) {
            addCriterion("secondaryUgIdpath >=", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathLessThan(String value) {
            addCriterion("secondaryUgIdpath <", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathLessThanOrEqualTo(String value) {
            addCriterion("secondaryUgIdpath <=", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathLike(String value) {
            addCriterion("secondaryUgIdpath like", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathNotLike(String value) {
            addCriterion("secondaryUgIdpath not like", value, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathIn(List<String> values) {
            addCriterion("secondaryUgIdpath in", values, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathNotIn(List<String> values) {
            addCriterion("secondaryUgIdpath not in", values, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathBetween(String value1, String value2) {
            addCriterion("secondaryUgIdpath between", value1, value2, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathNotBetween(String value1, String value2) {
            addCriterion("secondaryUgIdpath not between", value1, value2, "secondaryUgIdpath");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoIsNull() {
            addCriterion("secondaryUgIdpathInfo is null");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoIsNotNull() {
            addCriterion("secondaryUgIdpathInfo is not null");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoEqualTo(String value) {
            addCriterion("secondaryUgIdpathInfo =", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoNotEqualTo(String value) {
            addCriterion("secondaryUgIdpathInfo <>", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoGreaterThan(String value) {
            addCriterion("secondaryUgIdpathInfo >", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoGreaterThanOrEqualTo(String value) {
            addCriterion("secondaryUgIdpathInfo >=", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoLessThan(String value) {
            addCriterion("secondaryUgIdpathInfo <", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoLessThanOrEqualTo(String value) {
            addCriterion("secondaryUgIdpathInfo <=", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoLike(String value) {
            addCriterion("secondaryUgIdpathInfo like", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoNotLike(String value) {
            addCriterion("secondaryUgIdpathInfo not like", value, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoIn(List<String> values) {
            addCriterion("secondaryUgIdpathInfo in", values, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoNotIn(List<String> values) {
            addCriterion("secondaryUgIdpathInfo not in", values, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoBetween(String value1, String value2) {
            addCriterion("secondaryUgIdpathInfo between", value1, value2, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsecondaryUgIdpathInfoNotBetween(String value1, String value2) {
            addCriterion("secondaryUgIdpathInfo not between", value1, value2, "secondaryUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdIsNull() {
            addCriterion("singleRobotId is null");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdIsNotNull() {
            addCriterion("singleRobotId is not null");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdEqualTo(String value) {
            addCriterion("singleRobotId =", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdNotEqualTo(String value) {
            addCriterion("singleRobotId <>", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdGreaterThan(String value) {
            addCriterion("singleRobotId >", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdGreaterThanOrEqualTo(String value) {
            addCriterion("singleRobotId >=", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdLessThan(String value) {
            addCriterion("singleRobotId <", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdLessThanOrEqualTo(String value) {
            addCriterion("singleRobotId <=", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdLike(String value) {
            addCriterion("singleRobotId like", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdNotLike(String value) {
            addCriterion("singleRobotId not like", value, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdIn(List<String> values) {
            addCriterion("singleRobotId in", values, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdNotIn(List<String> values) {
            addCriterion("singleRobotId not in", values, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdBetween(String value1, String value2) {
            addCriterion("singleRobotId between", value1, value2, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotIdNotBetween(String value1, String value2) {
            addCriterion("singleRobotId not between", value1, value2, "singleRobotId");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameIsNull() {
            addCriterion("singleRobotName is null");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameIsNotNull() {
            addCriterion("singleRobotName is not null");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameEqualTo(String value) {
            addCriterion("singleRobotName =", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameNotEqualTo(String value) {
            addCriterion("singleRobotName <>", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameGreaterThan(String value) {
            addCriterion("singleRobotName >", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameGreaterThanOrEqualTo(String value) {
            addCriterion("singleRobotName >=", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameLessThan(String value) {
            addCriterion("singleRobotName <", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameLessThanOrEqualTo(String value) {
            addCriterion("singleRobotName <=", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameLike(String value) {
            addCriterion("singleRobotName like", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameNotLike(String value) {
            addCriterion("singleRobotName not like", value, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameIn(List<String> values) {
            addCriterion("singleRobotName in", values, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameNotIn(List<String> values) {
            addCriterion("singleRobotName not in", values, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameBetween(String value1, String value2) {
            addCriterion("singleRobotName between", value1, value2, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsingleRobotNameNotBetween(String value1, String value2) {
            addCriterion("singleRobotName not between", value1, value2, "singleRobotName");
            return (Criteria) this;
        }

        public Criteria andsmUidsIsNull() {
            addCriterion("smUids is null");
            return (Criteria) this;
        }

        public Criteria andsmUidsIsNotNull() {
            addCriterion("smUids is not null");
            return (Criteria) this;
        }

        public Criteria andsmUidsEqualTo(String value) {
            addCriterion("smUids =", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsNotEqualTo(String value) {
            addCriterion("smUids <>", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsGreaterThan(String value) {
            addCriterion("smUids >", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsGreaterThanOrEqualTo(String value) {
            addCriterion("smUids >=", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsLessThan(String value) {
            addCriterion("smUids <", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsLessThanOrEqualTo(String value) {
            addCriterion("smUids <=", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsLike(String value) {
            addCriterion("smUids like", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsNotLike(String value) {
            addCriterion("smUids not like", value, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsIn(List<String> values) {
            addCriterion("smUids in", values, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsNotIn(List<String> values) {
            addCriterion("smUids not in", values, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsBetween(String value1, String value2) {
            addCriterion("smUids between", value1, value2, "smUids");
            return (Criteria) this;
        }

        public Criteria andsmUidsNotBetween(String value1, String value2) {
            addCriterion("smUids not between", value1, value2, "smUids");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathIsNull() {
            addCriterion("thirdUgIdpath is null");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathIsNotNull() {
            addCriterion("thirdUgIdpath is not null");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathEqualTo(String value) {
            addCriterion("thirdUgIdpath =", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathNotEqualTo(String value) {
            addCriterion("thirdUgIdpath <>", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathGreaterThan(String value) {
            addCriterion("thirdUgIdpath >", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathGreaterThanOrEqualTo(String value) {
            addCriterion("thirdUgIdpath >=", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathLessThan(String value) {
            addCriterion("thirdUgIdpath <", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathLessThanOrEqualTo(String value) {
            addCriterion("thirdUgIdpath <=", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathLike(String value) {
            addCriterion("thirdUgIdpath like", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathNotLike(String value) {
            addCriterion("thirdUgIdpath not like", value, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathIn(List<String> values) {
            addCriterion("thirdUgIdpath in", values, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathNotIn(List<String> values) {
            addCriterion("thirdUgIdpath not in", values, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathBetween(String value1, String value2) {
            addCriterion("thirdUgIdpath between", value1, value2, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathNotBetween(String value1, String value2) {
            addCriterion("thirdUgIdpath not between", value1, value2, "thirdUgIdpath");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoIsNull() {
            addCriterion("thirdUgIdpathInfo is null");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoIsNotNull() {
            addCriterion("thirdUgIdpathInfo is not null");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoEqualTo(String value) {
            addCriterion("thirdUgIdpathInfo =", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoNotEqualTo(String value) {
            addCriterion("thirdUgIdpathInfo <>", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoGreaterThan(String value) {
            addCriterion("thirdUgIdpathInfo >", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoGreaterThanOrEqualTo(String value) {
            addCriterion("thirdUgIdpathInfo >=", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoLessThan(String value) {
            addCriterion("thirdUgIdpathInfo <", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoLessThanOrEqualTo(String value) {
            addCriterion("thirdUgIdpathInfo <=", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoLike(String value) {
            addCriterion("thirdUgIdpathInfo like", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoNotLike(String value) {
            addCriterion("thirdUgIdpathInfo not like", value, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoIn(List<String> values) {
            addCriterion("thirdUgIdpathInfo in", values, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoNotIn(List<String> values) {
            addCriterion("thirdUgIdpathInfo not in", values, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoBetween(String value1, String value2) {
            addCriterion("thirdUgIdpathInfo between", value1, value2, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andthirdUgIdpathInfoNotBetween(String value1, String value2) {
            addCriterion("thirdUgIdpathInfo not between", value1, value2, "thirdUgIdpathInfo");
            return (Criteria) this;
        }

        public Criteria andproductTokenIsNull() {
            addCriterion("productToken is null");
            return (Criteria) this;
        }

        public Criteria andproductTokenIsNotNull() {
            addCriterion("productToken is not null");
            return (Criteria) this;
        }

        public Criteria andproductTokenEqualTo(String value) {
            addCriterion("productToken =", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenNotEqualTo(String value) {
            addCriterion("productToken <>", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenGreaterThan(String value) {
            addCriterion("productToken >", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenGreaterThanOrEqualTo(String value) {
            addCriterion("productToken >=", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenLessThan(String value) {
            addCriterion("productToken <", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenLessThanOrEqualTo(String value) {
            addCriterion("productToken <=", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenLike(String value) {
            addCriterion("productToken like", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenNotLike(String value) {
            addCriterion("productToken not like", value, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenIn(List<String> values) {
            addCriterion("productToken in", values, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenNotIn(List<String> values) {
            addCriterion("productToken not in", values, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenBetween(String value1, String value2) {
            addCriterion("productToken between", value1, value2, "productToken");
            return (Criteria) this;
        }

        public Criteria andproductTokenNotBetween(String value1, String value2) {
            addCriterion("productToken not between", value1, value2, "productToken");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdIsNull() {
            addCriterion("userGroupId is null");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdIsNotNull() {
            addCriterion("userGroupId is not null");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdEqualTo(String value) {
            addCriterion("userGroupId =", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdNotEqualTo(String value) {
            addCriterion("userGroupId <>", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdGreaterThan(String value) {
            addCriterion("userGroupId >", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("userGroupId >=", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdLessThan(String value) {
            addCriterion("userGroupId <", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdLessThanOrEqualTo(String value) {
            addCriterion("userGroupId <=", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdLike(String value) {
            addCriterion("userGroupId like", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdNotLike(String value) {
            addCriterion("userGroupId not like", value, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdIn(List<String> values) {
            addCriterion("userGroupId in", values, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdNotIn(List<String> values) {
            addCriterion("userGroupId not in", values, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdBetween(String value1, String value2) {
            addCriterion("userGroupId between", value1, value2, "userGroupId");
            return (Criteria) this;
        }

        public Criteria anduserGroupIdNotBetween(String value1, String value2) {
            addCriterion("userGroupId not between", value1, value2, "userGroupId");
            return (Criteria) this;
        }

        public Criteria andlastModifiedIsNull() {
            addCriterion("lastModified is null");
            return (Criteria) this;
        }

        public Criteria andlastModifiedIsNotNull() {
            addCriterion("lastModified is not null");
            return (Criteria) this;
        }

        public Criteria andlastModifiedEqualTo(Date value) {
            addCriterionForJDBCDate("lastModified =", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastModified <>", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedGreaterThan(Date value) {
            addCriterionForJDBCDate("lastModified >", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastModified >=", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedLessThan(Date value) {
            addCriterionForJDBCDate("lastModified <", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastModified <=", value, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedIn(List<Date> values) {
            addCriterionForJDBCDate("lastModified in", values, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastModified not in", values, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastModified between", value1, value2, "lastModified");
            return (Criteria) this;
        }

        public Criteria andlastModifiedNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastModified not between", value1, value2, "lastModified");
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