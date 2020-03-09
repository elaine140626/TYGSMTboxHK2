package org.jeecg.modules.bee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryOutputExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public HistoryOutputExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andBbidIsNull() {
            addCriterion("bbid is null");
            return (Criteria) this;
        }

        public Criteria andBbidIsNotNull() {
            addCriterion("bbid is not null");
            return (Criteria) this;
        }

        public Criteria andBbidEqualTo(String value) {
            addCriterion("bbid =", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidNotEqualTo(String value) {
            addCriterion("bbid <>", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidGreaterThan(String value) {
            addCriterion("bbid >", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidGreaterThanOrEqualTo(String value) {
            addCriterion("bbid >=", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidLessThan(String value) {
            addCriterion("bbid <", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidLessThanOrEqualTo(String value) {
            addCriterion("bbid <=", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidLike(String value) {
            addCriterion("bbid like", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidNotLike(String value) {
            addCriterion("bbid not like", value, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidIn(List<String> values) {
            addCriterion("bbid in", values, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidNotIn(List<String> values) {
            addCriterion("bbid not in", values, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidBetween(String value1, String value2) {
            addCriterion("bbid between", value1, value2, "bbid");
            return (Criteria) this;
        }

        public Criteria andBbidNotBetween(String value1, String value2) {
            addCriterion("bbid not between", value1, value2, "bbid");
            return (Criteria) this;
        }

        public Criteria andFrameIsNull() {
            addCriterion("frame is null");
            return (Criteria) this;
        }

        public Criteria andFrameIsNotNull() {
            addCriterion("frame is not null");
            return (Criteria) this;
        }

        public Criteria andFrameEqualTo(Integer value) {
            addCriterion("frame =", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameNotEqualTo(Integer value) {
            addCriterion("frame <>", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameGreaterThan(Integer value) {
            addCriterion("frame >", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameGreaterThanOrEqualTo(Integer value) {
            addCriterion("frame >=", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameLessThan(Integer value) {
            addCriterion("frame <", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameLessThanOrEqualTo(Integer value) {
            addCriterion("frame <=", value, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameIn(List<Integer> values) {
            addCriterion("frame in", values, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameNotIn(List<Integer> values) {
            addCriterion("frame not in", values, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameBetween(Integer value1, Integer value2) {
            addCriterion("frame between", value1, value2, "frame");
            return (Criteria) this;
        }

        public Criteria andFrameNotBetween(Integer value1, Integer value2) {
            addCriterion("frame not between", value1, value2, "frame");
            return (Criteria) this;
        }

        public Criteria andHoneyIsNull() {
            addCriterion("honey is null");
            return (Criteria) this;
        }

        public Criteria andHoneyIsNotNull() {
            addCriterion("honey is not null");
            return (Criteria) this;
        }

        public Criteria andHoneyEqualTo(Double value) {
            addCriterion("honey =", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyNotEqualTo(Double value) {
            addCriterion("honey <>", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyGreaterThan(Double value) {
            addCriterion("honey >", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("honey >=", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyLessThan(Double value) {
            addCriterion("honey <", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyLessThanOrEqualTo(Double value) {
            addCriterion("honey <=", value, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyIn(List<Double> values) {
            addCriterion("honey in", values, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyNotIn(List<Double> values) {
            addCriterion("honey not in", values, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyBetween(Double value1, Double value2) {
            addCriterion("honey between", value1, value2, "honey");
            return (Criteria) this;
        }

        public Criteria andHoneyNotBetween(Double value1, Double value2) {
            addCriterion("honey not between", value1, value2, "honey");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("`time` is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("`time` is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("`time` =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("`time` <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("`time` >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`time` >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("`time` <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("`time` <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("`time` in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("`time` not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("`time` between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("`time` not between", value1, value2, "time");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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