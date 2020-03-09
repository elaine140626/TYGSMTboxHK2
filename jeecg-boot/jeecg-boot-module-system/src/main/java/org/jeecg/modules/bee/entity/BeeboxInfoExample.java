package org.jeecg.modules.bee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeeboxInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public BeeboxInfoExample() {
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

        public Criteria andRecvTimeIsNull() {
            addCriterion("recv_time is null");
            return (Criteria) this;
        }

        public Criteria andRecvTimeIsNotNull() {
            addCriterion("recv_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecvTimeEqualTo(Date value) {
            addCriterion("recv_time =", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotEqualTo(Date value) {
            addCriterion("recv_time <>", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeGreaterThan(Date value) {
            addCriterion("recv_time >", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recv_time >=", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeLessThan(Date value) {
            addCriterion("recv_time <", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeLessThanOrEqualTo(Date value) {
            addCriterion("recv_time <=", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeIn(List<Date> values) {
            addCriterion("recv_time in", values, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotIn(List<Date> values) {
            addCriterion("recv_time not in", values, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeBetween(Date value1, Date value2) {
            addCriterion("recv_time between", value1, value2, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotBetween(Date value1, Date value2) {
            addCriterion("recv_time not between", value1, value2, "recvTime");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateIsNull() {
            addCriterion("weight_frame_rate is null");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateIsNotNull() {
            addCriterion("weight_frame_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateEqualTo(Double value) {
            addCriterion("weight_frame_rate =", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateNotEqualTo(Double value) {
            addCriterion("weight_frame_rate <>", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateGreaterThan(Double value) {
            addCriterion("weight_frame_rate >", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateGreaterThanOrEqualTo(Double value) {
            addCriterion("weight_frame_rate >=", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateLessThan(Double value) {
            addCriterion("weight_frame_rate <", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateLessThanOrEqualTo(Double value) {
            addCriterion("weight_frame_rate <=", value, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateIn(List<Double> values) {
            addCriterion("weight_frame_rate in", values, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateNotIn(List<Double> values) {
            addCriterion("weight_frame_rate not in", values, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateBetween(Double value1, Double value2) {
            addCriterion("weight_frame_rate between", value1, value2, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightFrameRateNotBetween(Double value1, Double value2) {
            addCriterion("weight_frame_rate not between", value1, value2, "weightFrameRate");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Double value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Double value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Double value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Double value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Double value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Double> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Double> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Double value1, Double value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Double value1, Double value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andTemperIsNull() {
            addCriterion("temper is null");
            return (Criteria) this;
        }

        public Criteria andTemperIsNotNull() {
            addCriterion("temper is not null");
            return (Criteria) this;
        }

        public Criteria andTemperEqualTo(Double value) {
            addCriterion("temper =", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperNotEqualTo(Double value) {
            addCriterion("temper <>", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperGreaterThan(Double value) {
            addCriterion("temper >", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperGreaterThanOrEqualTo(Double value) {
            addCriterion("temper >=", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperLessThan(Double value) {
            addCriterion("temper <", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperLessThanOrEqualTo(Double value) {
            addCriterion("temper <=", value, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperIn(List<Double> values) {
            addCriterion("temper in", values, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperNotIn(List<Double> values) {
            addCriterion("temper not in", values, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperBetween(Double value1, Double value2) {
            addCriterion("temper between", value1, value2, "temper");
            return (Criteria) this;
        }

        public Criteria andTemperNotBetween(Double value1, Double value2) {
            addCriterion("temper not between", value1, value2, "temper");
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