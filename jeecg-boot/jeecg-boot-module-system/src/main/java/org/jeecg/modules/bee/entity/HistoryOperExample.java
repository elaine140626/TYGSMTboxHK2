package org.jeecg.modules.bee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryOperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public HistoryOperExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("`uid` like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("`uid` not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andCleanTimeIsNull() {
            addCriterion("clean_time is null");
            return (Criteria) this;
        }

        public Criteria andCleanTimeIsNotNull() {
            addCriterion("clean_time is not null");
            return (Criteria) this;
        }

        public Criteria andCleanTimeEqualTo(Date value) {
            addCriterion("clean_time =", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeNotEqualTo(Date value) {
            addCriterion("clean_time <>", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeGreaterThan(Date value) {
            addCriterion("clean_time >", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("clean_time >=", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeLessThan(Date value) {
            addCriterion("clean_time <", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeLessThanOrEqualTo(Date value) {
            addCriterion("clean_time <=", value, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeIn(List<Date> values) {
            addCriterion("clean_time in", values, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeNotIn(List<Date> values) {
            addCriterion("clean_time not in", values, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeBetween(Date value1, Date value2) {
            addCriterion("clean_time between", value1, value2, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanTimeNotBetween(Date value1, Date value2) {
            addCriterion("clean_time not between", value1, value2, "cleanTime");
            return (Criteria) this;
        }

        public Criteria andCleanLongIsNull() {
            addCriterion("clean_long is null");
            return (Criteria) this;
        }

        public Criteria andCleanLongIsNotNull() {
            addCriterion("clean_long is not null");
            return (Criteria) this;
        }

        public Criteria andCleanLongEqualTo(Integer value) {
            addCriterion("clean_long =", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongNotEqualTo(Integer value) {
            addCriterion("clean_long <>", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongGreaterThan(Integer value) {
            addCriterion("clean_long >", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongGreaterThanOrEqualTo(Integer value) {
            addCriterion("clean_long >=", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongLessThan(Integer value) {
            addCriterion("clean_long <", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongLessThanOrEqualTo(Integer value) {
            addCriterion("clean_long <=", value, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongIn(List<Integer> values) {
            addCriterion("clean_long in", values, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongNotIn(List<Integer> values) {
            addCriterion("clean_long not in", values, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongBetween(Integer value1, Integer value2) {
            addCriterion("clean_long between", value1, value2, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanLongNotBetween(Integer value1, Integer value2) {
            addCriterion("clean_long not between", value1, value2, "cleanLong");
            return (Criteria) this;
        }

        public Criteria andCleanStatusIsNull() {
            addCriterion("clean_status is null");
            return (Criteria) this;
        }

        public Criteria andCleanStatusIsNotNull() {
            addCriterion("clean_status is not null");
            return (Criteria) this;
        }

        public Criteria andCleanStatusEqualTo(Integer value) {
            addCriterion("clean_status =", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusNotEqualTo(Integer value) {
            addCriterion("clean_status <>", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusGreaterThan(Integer value) {
            addCriterion("clean_status >", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("clean_status >=", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusLessThan(Integer value) {
            addCriterion("clean_status <", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusLessThanOrEqualTo(Integer value) {
            addCriterion("clean_status <=", value, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusIn(List<Integer> values) {
            addCriterion("clean_status in", values, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusNotIn(List<Integer> values) {
            addCriterion("clean_status not in", values, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusBetween(Integer value1, Integer value2) {
            addCriterion("clean_status between", value1, value2, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andCleanStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("clean_status not between", value1, value2, "cleanStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeIsNull() {
            addCriterion("heat_starttime is null");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeIsNotNull() {
            addCriterion("heat_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeEqualTo(Date value) {
            addCriterion("heat_starttime =", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeNotEqualTo(Date value) {
            addCriterion("heat_starttime <>", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeGreaterThan(Date value) {
            addCriterion("heat_starttime >", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("heat_starttime >=", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeLessThan(Date value) {
            addCriterion("heat_starttime <", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("heat_starttime <=", value, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeIn(List<Date> values) {
            addCriterion("heat_starttime in", values, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeNotIn(List<Date> values) {
            addCriterion("heat_starttime not in", values, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeBetween(Date value1, Date value2) {
            addCriterion("heat_starttime between", value1, value2, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("heat_starttime not between", value1, value2, "heatStarttime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeIsNull() {
            addCriterion("heat_endtime is null");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeIsNotNull() {
            addCriterion("heat_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeEqualTo(Date value) {
            addCriterion("heat_endtime =", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeNotEqualTo(Date value) {
            addCriterion("heat_endtime <>", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeGreaterThan(Date value) {
            addCriterion("heat_endtime >", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("heat_endtime >=", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeLessThan(Date value) {
            addCriterion("heat_endtime <", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("heat_endtime <=", value, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeIn(List<Date> values) {
            addCriterion("heat_endtime in", values, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeNotIn(List<Date> values) {
            addCriterion("heat_endtime not in", values, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeBetween(Date value1, Date value2) {
            addCriterion("heat_endtime between", value1, value2, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("heat_endtime not between", value1, value2, "heatEndtime");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperIsNull() {
            addCriterion("heat_start_temper is null");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperIsNotNull() {
            addCriterion("heat_start_temper is not null");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperEqualTo(Double value) {
            addCriterion("heat_start_temper =", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperNotEqualTo(Double value) {
            addCriterion("heat_start_temper <>", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperGreaterThan(Double value) {
            addCriterion("heat_start_temper >", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperGreaterThanOrEqualTo(Double value) {
            addCriterion("heat_start_temper >=", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperLessThan(Double value) {
            addCriterion("heat_start_temper <", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperLessThanOrEqualTo(Double value) {
            addCriterion("heat_start_temper <=", value, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperIn(List<Double> values) {
            addCriterion("heat_start_temper in", values, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperNotIn(List<Double> values) {
            addCriterion("heat_start_temper not in", values, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperBetween(Double value1, Double value2) {
            addCriterion("heat_start_temper between", value1, value2, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStartTemperNotBetween(Double value1, Double value2) {
            addCriterion("heat_start_temper not between", value1, value2, "heatStartTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperIsNull() {
            addCriterion("heat_end_temper is null");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperIsNotNull() {
            addCriterion("heat_end_temper is not null");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperEqualTo(Double value) {
            addCriterion("heat_end_temper =", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperNotEqualTo(Double value) {
            addCriterion("heat_end_temper <>", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperGreaterThan(Double value) {
            addCriterion("heat_end_temper >", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperGreaterThanOrEqualTo(Double value) {
            addCriterion("heat_end_temper >=", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperLessThan(Double value) {
            addCriterion("heat_end_temper <", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperLessThanOrEqualTo(Double value) {
            addCriterion("heat_end_temper <=", value, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperIn(List<Double> values) {
            addCriterion("heat_end_temper in", values, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperNotIn(List<Double> values) {
            addCriterion("heat_end_temper not in", values, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperBetween(Double value1, Double value2) {
            addCriterion("heat_end_temper between", value1, value2, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatEndTemperNotBetween(Double value1, Double value2) {
            addCriterion("heat_end_temper not between", value1, value2, "heatEndTemper");
            return (Criteria) this;
        }

        public Criteria andHeatStatusIsNull() {
            addCriterion("heat_status is null");
            return (Criteria) this;
        }

        public Criteria andHeatStatusIsNotNull() {
            addCriterion("heat_status is not null");
            return (Criteria) this;
        }

        public Criteria andHeatStatusEqualTo(Integer value) {
            addCriterion("heat_status =", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusNotEqualTo(Integer value) {
            addCriterion("heat_status <>", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusGreaterThan(Integer value) {
            addCriterion("heat_status >", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("heat_status >=", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusLessThan(Integer value) {
            addCriterion("heat_status <", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusLessThanOrEqualTo(Integer value) {
            addCriterion("heat_status <=", value, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusIn(List<Integer> values) {
            addCriterion("heat_status in", values, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusNotIn(List<Integer> values) {
            addCriterion("heat_status not in", values, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusBetween(Integer value1, Integer value2) {
            addCriterion("heat_status between", value1, value2, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andHeatStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("heat_status not between", value1, value2, "heatStatus");
            return (Criteria) this;
        }

        public Criteria andFeedTimeIsNull() {
            addCriterion("feed_time is null");
            return (Criteria) this;
        }

        public Criteria andFeedTimeIsNotNull() {
            addCriterion("feed_time is not null");
            return (Criteria) this;
        }

        public Criteria andFeedTimeEqualTo(Date value) {
            addCriterion("feed_time =", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeNotEqualTo(Date value) {
            addCriterion("feed_time <>", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeGreaterThan(Date value) {
            addCriterion("feed_time >", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feed_time >=", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeLessThan(Date value) {
            addCriterion("feed_time <", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeLessThanOrEqualTo(Date value) {
            addCriterion("feed_time <=", value, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeIn(List<Date> values) {
            addCriterion("feed_time in", values, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeNotIn(List<Date> values) {
            addCriterion("feed_time not in", values, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeBetween(Date value1, Date value2) {
            addCriterion("feed_time between", value1, value2, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedTimeNotBetween(Date value1, Date value2) {
            addCriterion("feed_time not between", value1, value2, "feedTime");
            return (Criteria) this;
        }

        public Criteria andFeedStatusIsNull() {
            addCriterion("feed_status is null");
            return (Criteria) this;
        }

        public Criteria andFeedStatusIsNotNull() {
            addCriterion("feed_status is not null");
            return (Criteria) this;
        }

        public Criteria andFeedStatusEqualTo(Integer value) {
            addCriterion("feed_status =", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusNotEqualTo(Integer value) {
            addCriterion("feed_status <>", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusGreaterThan(Integer value) {
            addCriterion("feed_status >", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_status >=", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusLessThan(Integer value) {
            addCriterion("feed_status <", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusLessThanOrEqualTo(Integer value) {
            addCriterion("feed_status <=", value, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusIn(List<Integer> values) {
            addCriterion("feed_status in", values, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusNotIn(List<Integer> values) {
            addCriterion("feed_status not in", values, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusBetween(Integer value1, Integer value2) {
            addCriterion("feed_status between", value1, value2, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_status not between", value1, value2, "feedStatus");
            return (Criteria) this;
        }

        public Criteria andFeedTypeIsNull() {
            addCriterion("feed_type is null");
            return (Criteria) this;
        }

        public Criteria andFeedTypeIsNotNull() {
            addCriterion("feed_type is not null");
            return (Criteria) this;
        }

        public Criteria andFeedTypeEqualTo(Integer value) {
            addCriterion("feed_type =", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotEqualTo(Integer value) {
            addCriterion("feed_type <>", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeGreaterThan(Integer value) {
            addCriterion("feed_type >", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_type >=", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeLessThan(Integer value) {
            addCriterion("feed_type <", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeLessThanOrEqualTo(Integer value) {
            addCriterion("feed_type <=", value, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeIn(List<Integer> values) {
            addCriterion("feed_type in", values, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotIn(List<Integer> values) {
            addCriterion("feed_type not in", values, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeBetween(Integer value1, Integer value2) {
            addCriterion("feed_type between", value1, value2, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_type not between", value1, value2, "feedType");
            return (Criteria) this;
        }

        public Criteria andFeedOrderIsNull() {
            addCriterion("feed_order is null");
            return (Criteria) this;
        }

        public Criteria andFeedOrderIsNotNull() {
            addCriterion("feed_order is not null");
            return (Criteria) this;
        }

        public Criteria andFeedOrderEqualTo(Integer value) {
            addCriterion("feed_order =", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderNotEqualTo(Integer value) {
            addCriterion("feed_order <>", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderGreaterThan(Integer value) {
            addCriterion("feed_order >", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_order >=", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderLessThan(Integer value) {
            addCriterion("feed_order <", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderLessThanOrEqualTo(Integer value) {
            addCriterion("feed_order <=", value, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderIn(List<Integer> values) {
            addCriterion("feed_order in", values, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderNotIn(List<Integer> values) {
            addCriterion("feed_order not in", values, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderBetween(Integer value1, Integer value2) {
            addCriterion("feed_order between", value1, value2, "feedOrder");
            return (Criteria) this;
        }

        public Criteria andFeedOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_order not between", value1, value2, "feedOrder");
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