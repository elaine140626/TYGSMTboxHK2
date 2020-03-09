package org.jeecg.modules.bee.entity;

import java.util.ArrayList;
import java.util.List;

public class SysConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public SysConfigExample() {
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

        public Criteria andSavetime1IsNull() {
            addCriterion("savetime1 is null");
            return (Criteria) this;
        }

        public Criteria andSavetime1IsNotNull() {
            addCriterion("savetime1 is not null");
            return (Criteria) this;
        }

        public Criteria andSavetime1EqualTo(String value) {
            addCriterion("savetime1 =", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1NotEqualTo(String value) {
            addCriterion("savetime1 <>", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1GreaterThan(String value) {
            addCriterion("savetime1 >", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1GreaterThanOrEqualTo(String value) {
            addCriterion("savetime1 >=", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1LessThan(String value) {
            addCriterion("savetime1 <", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1LessThanOrEqualTo(String value) {
            addCriterion("savetime1 <=", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1Like(String value) {
            addCriterion("savetime1 like", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1NotLike(String value) {
            addCriterion("savetime1 not like", value, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1In(List<String> values) {
            addCriterion("savetime1 in", values, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1NotIn(List<String> values) {
            addCriterion("savetime1 not in", values, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1Between(String value1, String value2) {
            addCriterion("savetime1 between", value1, value2, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime1NotBetween(String value1, String value2) {
            addCriterion("savetime1 not between", value1, value2, "savetime1");
            return (Criteria) this;
        }

        public Criteria andSavetime2IsNull() {
            addCriterion("savetime2 is null");
            return (Criteria) this;
        }

        public Criteria andSavetime2IsNotNull() {
            addCriterion("savetime2 is not null");
            return (Criteria) this;
        }

        public Criteria andSavetime2EqualTo(String value) {
            addCriterion("savetime2 =", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2NotEqualTo(String value) {
            addCriterion("savetime2 <>", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2GreaterThan(String value) {
            addCriterion("savetime2 >", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2GreaterThanOrEqualTo(String value) {
            addCriterion("savetime2 >=", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2LessThan(String value) {
            addCriterion("savetime2 <", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2LessThanOrEqualTo(String value) {
            addCriterion("savetime2 <=", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2Like(String value) {
            addCriterion("savetime2 like", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2NotLike(String value) {
            addCriterion("savetime2 not like", value, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2In(List<String> values) {
            addCriterion("savetime2 in", values, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2NotIn(List<String> values) {
            addCriterion("savetime2 not in", values, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2Between(String value1, String value2) {
            addCriterion("savetime2 between", value1, value2, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime2NotBetween(String value1, String value2) {
            addCriterion("savetime2 not between", value1, value2, "savetime2");
            return (Criteria) this;
        }

        public Criteria andSavetime3IsNull() {
            addCriterion("savetime3 is null");
            return (Criteria) this;
        }

        public Criteria andSavetime3IsNotNull() {
            addCriterion("savetime3 is not null");
            return (Criteria) this;
        }

        public Criteria andSavetime3EqualTo(String value) {
            addCriterion("savetime3 =", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3NotEqualTo(String value) {
            addCriterion("savetime3 <>", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3GreaterThan(String value) {
            addCriterion("savetime3 >", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3GreaterThanOrEqualTo(String value) {
            addCriterion("savetime3 >=", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3LessThan(String value) {
            addCriterion("savetime3 <", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3LessThanOrEqualTo(String value) {
            addCriterion("savetime3 <=", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3Like(String value) {
            addCriterion("savetime3 like", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3NotLike(String value) {
            addCriterion("savetime3 not like", value, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3In(List<String> values) {
            addCriterion("savetime3 in", values, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3NotIn(List<String> values) {
            addCriterion("savetime3 not in", values, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3Between(String value1, String value2) {
            addCriterion("savetime3 between", value1, value2, "savetime3");
            return (Criteria) this;
        }

        public Criteria andSavetime3NotBetween(String value1, String value2) {
            addCriterion("savetime3 not between", value1, value2, "savetime3");
            return (Criteria) this;
        }

        public Criteria andFeedWeightIsNull() {
            addCriterion("feed_weight is null");
            return (Criteria) this;
        }

        public Criteria andFeedWeightIsNotNull() {
            addCriterion("feed_weight is not null");
            return (Criteria) this;
        }

        public Criteria andFeedWeightEqualTo(Integer value) {
            addCriterion("feed_weight =", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightNotEqualTo(Integer value) {
            addCriterion("feed_weight <>", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightGreaterThan(Integer value) {
            addCriterion("feed_weight >", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_weight >=", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightLessThan(Integer value) {
            addCriterion("feed_weight <", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightLessThanOrEqualTo(Integer value) {
            addCriterion("feed_weight <=", value, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightIn(List<Integer> values) {
            addCriterion("feed_weight in", values, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightNotIn(List<Integer> values) {
            addCriterion("feed_weight not in", values, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightBetween(Integer value1, Integer value2) {
            addCriterion("feed_weight between", value1, value2, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andFeedWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_weight not between", value1, value2, "feedWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightIsNull() {
            addCriterion("escape_weight is null");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightIsNotNull() {
            addCriterion("escape_weight is not null");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightEqualTo(Integer value) {
            addCriterion("escape_weight =", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightNotEqualTo(Integer value) {
            addCriterion("escape_weight <>", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightGreaterThan(Integer value) {
            addCriterion("escape_weight >", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("escape_weight >=", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightLessThan(Integer value) {
            addCriterion("escape_weight <", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightLessThanOrEqualTo(Integer value) {
            addCriterion("escape_weight <=", value, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightIn(List<Integer> values) {
            addCriterion("escape_weight in", values, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightNotIn(List<Integer> values) {
            addCriterion("escape_weight not in", values, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightBetween(Integer value1, Integer value2) {
            addCriterion("escape_weight between", value1, value2, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andEscapeWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("escape_weight not between", value1, value2, "escapeWeight");
            return (Criteria) this;
        }

        public Criteria andHeatLowerIsNull() {
            addCriterion("heat_lower is null");
            return (Criteria) this;
        }

        public Criteria andHeatLowerIsNotNull() {
            addCriterion("heat_lower is not null");
            return (Criteria) this;
        }

        public Criteria andHeatLowerEqualTo(Double value) {
            addCriterion("heat_lower =", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerNotEqualTo(Double value) {
            addCriterion("heat_lower <>", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerGreaterThan(Double value) {
            addCriterion("heat_lower >", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerGreaterThanOrEqualTo(Double value) {
            addCriterion("heat_lower >=", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerLessThan(Double value) {
            addCriterion("heat_lower <", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerLessThanOrEqualTo(Double value) {
            addCriterion("heat_lower <=", value, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerIn(List<Double> values) {
            addCriterion("heat_lower in", values, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerNotIn(List<Double> values) {
            addCriterion("heat_lower not in", values, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerBetween(Double value1, Double value2) {
            addCriterion("heat_lower between", value1, value2, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatLowerNotBetween(Double value1, Double value2) {
            addCriterion("heat_lower not between", value1, value2, "heatLower");
            return (Criteria) this;
        }

        public Criteria andHeatUpperIsNull() {
            addCriterion("heat_upper is null");
            return (Criteria) this;
        }

        public Criteria andHeatUpperIsNotNull() {
            addCriterion("heat_upper is not null");
            return (Criteria) this;
        }

        public Criteria andHeatUpperEqualTo(Double value) {
            addCriterion("heat_upper =", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperNotEqualTo(Double value) {
            addCriterion("heat_upper <>", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperGreaterThan(Double value) {
            addCriterion("heat_upper >", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperGreaterThanOrEqualTo(Double value) {
            addCriterion("heat_upper >=", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperLessThan(Double value) {
            addCriterion("heat_upper <", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperLessThanOrEqualTo(Double value) {
            addCriterion("heat_upper <=", value, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperIn(List<Double> values) {
            addCriterion("heat_upper in", values, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperNotIn(List<Double> values) {
            addCriterion("heat_upper not in", values, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperBetween(Double value1, Double value2) {
            addCriterion("heat_upper between", value1, value2, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andHeatUpperNotBetween(Double value1, Double value2) {
            addCriterion("heat_upper not between", value1, value2, "heatUpper");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateIsNull() {
            addCriterion("king_ctrl_rate is null");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateIsNotNull() {
            addCriterion("king_ctrl_rate is not null");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateEqualTo(Double value) {
            addCriterion("king_ctrl_rate =", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateNotEqualTo(Double value) {
            addCriterion("king_ctrl_rate <>", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateGreaterThan(Double value) {
            addCriterion("king_ctrl_rate >", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateGreaterThanOrEqualTo(Double value) {
            addCriterion("king_ctrl_rate >=", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateLessThan(Double value) {
            addCriterion("king_ctrl_rate <", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateLessThanOrEqualTo(Double value) {
            addCriterion("king_ctrl_rate <=", value, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateIn(List<Double> values) {
            addCriterion("king_ctrl_rate in", values, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateNotIn(List<Double> values) {
            addCriterion("king_ctrl_rate not in", values, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateBetween(Double value1, Double value2) {
            addCriterion("king_ctrl_rate between", value1, value2, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andKingCtrlRateNotBetween(Double value1, Double value2) {
            addCriterion("king_ctrl_rate not between", value1, value2, "kingCtrlRate");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalIsNull() {
            addCriterion("draft_interval is null");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalIsNotNull() {
            addCriterion("draft_interval is not null");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalEqualTo(Integer value) {
            addCriterion("draft_interval =", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalNotEqualTo(Integer value) {
            addCriterion("draft_interval <>", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalGreaterThan(Integer value) {
            addCriterion("draft_interval >", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("draft_interval >=", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalLessThan(Integer value) {
            addCriterion("draft_interval <", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("draft_interval <=", value, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalIn(List<Integer> values) {
            addCriterion("draft_interval in", values, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalNotIn(List<Integer> values) {
            addCriterion("draft_interval not in", values, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalBetween(Integer value1, Integer value2) {
            addCriterion("draft_interval between", value1, value2, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDraftIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("draft_interval not between", value1, value2, "draftInterval");
            return (Criteria) this;
        }

        public Criteria andDecreseRateIsNull() {
            addCriterion("decrese_rate is null");
            return (Criteria) this;
        }

        public Criteria andDecreseRateIsNotNull() {
            addCriterion("decrese_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDecreseRateEqualTo(Double value) {
            addCriterion("decrese_rate =", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateNotEqualTo(Double value) {
            addCriterion("decrese_rate <>", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateGreaterThan(Double value) {
            addCriterion("decrese_rate >", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateGreaterThanOrEqualTo(Double value) {
            addCriterion("decrese_rate >=", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateLessThan(Double value) {
            addCriterion("decrese_rate <", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateLessThanOrEqualTo(Double value) {
            addCriterion("decrese_rate <=", value, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateIn(List<Double> values) {
            addCriterion("decrese_rate in", values, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateNotIn(List<Double> values) {
            addCriterion("decrese_rate not in", values, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateBetween(Double value1, Double value2) {
            addCriterion("decrese_rate between", value1, value2, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andDecreseRateNotBetween(Double value1, Double value2) {
            addCriterion("decrese_rate not between", value1, value2, "decreseRate");
            return (Criteria) this;
        }

        public Criteria andStdWeightIsNull() {
            addCriterion("std_weight is null");
            return (Criteria) this;
        }

        public Criteria andStdWeightIsNotNull() {
            addCriterion("std_weight is not null");
            return (Criteria) this;
        }

        public Criteria andStdWeightEqualTo(Integer value) {
            addCriterion("std_weight =", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightNotEqualTo(Integer value) {
            addCriterion("std_weight <>", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightGreaterThan(Integer value) {
            addCriterion("std_weight >", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("std_weight >=", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightLessThan(Integer value) {
            addCriterion("std_weight <", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightLessThanOrEqualTo(Integer value) {
            addCriterion("std_weight <=", value, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightIn(List<Integer> values) {
            addCriterion("std_weight in", values, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightNotIn(List<Integer> values) {
            addCriterion("std_weight not in", values, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightBetween(Integer value1, Integer value2) {
            addCriterion("std_weight between", value1, value2, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andStdWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("std_weight not between", value1, value2, "stdWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightIsNull() {
            addCriterion("box_low_weight is null");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightIsNotNull() {
            addCriterion("box_low_weight is not null");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightEqualTo(Integer value) {
            addCriterion("box_low_weight =", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightNotEqualTo(Integer value) {
            addCriterion("box_low_weight <>", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightGreaterThan(Integer value) {
            addCriterion("box_low_weight >", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_low_weight >=", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightLessThan(Integer value) {
            addCriterion("box_low_weight <", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightLessThanOrEqualTo(Integer value) {
            addCriterion("box_low_weight <=", value, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightIn(List<Integer> values) {
            addCriterion("box_low_weight in", values, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightNotIn(List<Integer> values) {
            addCriterion("box_low_weight not in", values, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightBetween(Integer value1, Integer value2) {
            addCriterion("box_low_weight between", value1, value2, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxLowWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("box_low_weight not between", value1, value2, "boxLowWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightIsNull() {
            addCriterion("box_normal_weight is null");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightIsNotNull() {
            addCriterion("box_normal_weight is not null");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightEqualTo(Integer value) {
            addCriterion("box_normal_weight =", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightNotEqualTo(Integer value) {
            addCriterion("box_normal_weight <>", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightGreaterThan(Integer value) {
            addCriterion("box_normal_weight >", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_normal_weight >=", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightLessThan(Integer value) {
            addCriterion("box_normal_weight <", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightLessThanOrEqualTo(Integer value) {
            addCriterion("box_normal_weight <=", value, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightIn(List<Integer> values) {
            addCriterion("box_normal_weight in", values, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightNotIn(List<Integer> values) {
            addCriterion("box_normal_weight not in", values, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightBetween(Integer value1, Integer value2) {
            addCriterion("box_normal_weight between", value1, value2, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andBoxNormalWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("box_normal_weight not between", value1, value2, "boxNormalWeight");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysIsNull() {
            addCriterion("his_show_days is null");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysIsNotNull() {
            addCriterion("his_show_days is not null");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysEqualTo(Integer value) {
            addCriterion("his_show_days =", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysNotEqualTo(Integer value) {
            addCriterion("his_show_days <>", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysGreaterThan(Integer value) {
            addCriterion("his_show_days >", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("his_show_days >=", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysLessThan(Integer value) {
            addCriterion("his_show_days <", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysLessThanOrEqualTo(Integer value) {
            addCriterion("his_show_days <=", value, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysIn(List<Integer> values) {
            addCriterion("his_show_days in", values, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysNotIn(List<Integer> values) {
            addCriterion("his_show_days not in", values, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysBetween(Integer value1, Integer value2) {
            addCriterion("his_show_days between", value1, value2, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andHisShowDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("his_show_days not between", value1, value2, "hisShowDays");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightIsNull() {
            addCriterion("output_ignore_weight is null");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightIsNotNull() {
            addCriterion("output_ignore_weight is not null");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightEqualTo(Integer value) {
            addCriterion("output_ignore_weight =", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightNotEqualTo(Integer value) {
            addCriterion("output_ignore_weight <>", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightGreaterThan(Integer value) {
            addCriterion("output_ignore_weight >", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("output_ignore_weight >=", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightLessThan(Integer value) {
            addCriterion("output_ignore_weight <", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightLessThanOrEqualTo(Integer value) {
            addCriterion("output_ignore_weight <=", value, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightIn(List<Integer> values) {
            addCriterion("output_ignore_weight in", values, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightNotIn(List<Integer> values) {
            addCriterion("output_ignore_weight not in", values, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightBetween(Integer value1, Integer value2) {
            addCriterion("output_ignore_weight between", value1, value2, "outputIgnoreWeight");
            return (Criteria) this;
        }

        public Criteria andOutputIgnoreWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("output_ignore_weight not between", value1, value2, "outputIgnoreWeight");
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

        public Criteria andHintTemperIsNull() {
            addCriterion("hint_temper is null");
            return (Criteria) this;
        }

        public Criteria andHintTemperIsNotNull() {
            addCriterion("hint_temper is not null");
            return (Criteria) this;
        }

        public Criteria andHintTemperEqualTo(Double value) {
            addCriterion("hint_temper =", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperNotEqualTo(Double value) {
            addCriterion("hint_temper <>", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperGreaterThan(Double value) {
            addCriterion("hint_temper >", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperGreaterThanOrEqualTo(Double value) {
            addCriterion("hint_temper >=", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperLessThan(Double value) {
            addCriterion("hint_temper <", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperLessThanOrEqualTo(Double value) {
            addCriterion("hint_temper <=", value, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperIn(List<Double> values) {
            addCriterion("hint_temper in", values, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperNotIn(List<Double> values) {
            addCriterion("hint_temper not in", values, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperBetween(Double value1, Double value2) {
            addCriterion("hint_temper between", value1, value2, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintTemperNotBetween(Double value1, Double value2) {
            addCriterion("hint_temper not between", value1, value2, "hintTemper");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameIsNull() {
            addCriterion("hint_weight_frame is null");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameIsNotNull() {
            addCriterion("hint_weight_frame is not null");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameEqualTo(Double value) {
            addCriterion("hint_weight_frame =", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameNotEqualTo(Double value) {
            addCriterion("hint_weight_frame <>", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameGreaterThan(Double value) {
            addCriterion("hint_weight_frame >", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameGreaterThanOrEqualTo(Double value) {
            addCriterion("hint_weight_frame >=", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameLessThan(Double value) {
            addCriterion("hint_weight_frame <", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameLessThanOrEqualTo(Double value) {
            addCriterion("hint_weight_frame <=", value, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameIn(List<Double> values) {
            addCriterion("hint_weight_frame in", values, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameNotIn(List<Double> values) {
            addCriterion("hint_weight_frame not in", values, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameBetween(Double value1, Double value2) {
            addCriterion("hint_weight_frame between", value1, value2, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightFrameNotBetween(Double value1, Double value2) {
            addCriterion("hint_weight_frame not between", value1, value2, "hintWeightFrame");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeIsNull() {
            addCriterion("hint_weight_change is null");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeIsNotNull() {
            addCriterion("hint_weight_change is not null");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeEqualTo(Double value) {
            addCriterion("hint_weight_change =", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeNotEqualTo(Double value) {
            addCriterion("hint_weight_change <>", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeGreaterThan(Double value) {
            addCriterion("hint_weight_change >", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeGreaterThanOrEqualTo(Double value) {
            addCriterion("hint_weight_change >=", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeLessThan(Double value) {
            addCriterion("hint_weight_change <", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeLessThanOrEqualTo(Double value) {
            addCriterion("hint_weight_change <=", value, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeIn(List<Double> values) {
            addCriterion("hint_weight_change in", values, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeNotIn(List<Double> values) {
            addCriterion("hint_weight_change not in", values, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeBetween(Double value1, Double value2) {
            addCriterion("hint_weight_change between", value1, value2, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andHintWeightChangeNotBetween(Double value1, Double value2) {
            addCriterion("hint_weight_change not between", value1, value2, "hintWeightChange");
            return (Criteria) this;
        }

        public Criteria andOutputingIsNull() {
            addCriterion("outputing is null");
            return (Criteria) this;
        }

        public Criteria andOutputingIsNotNull() {
            addCriterion("outputing is not null");
            return (Criteria) this;
        }

        public Criteria andOutputingEqualTo(Integer value) {
            addCriterion("outputing =", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingNotEqualTo(Integer value) {
            addCriterion("outputing <>", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingGreaterThan(Integer value) {
            addCriterion("outputing >", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingGreaterThanOrEqualTo(Integer value) {
            addCriterion("outputing >=", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingLessThan(Integer value) {
            addCriterion("outputing <", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingLessThanOrEqualTo(Integer value) {
            addCriterion("outputing <=", value, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingIn(List<Integer> values) {
            addCriterion("outputing in", values, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingNotIn(List<Integer> values) {
            addCriterion("outputing not in", values, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingBetween(Integer value1, Integer value2) {
            addCriterion("outputing between", value1, value2, "outputing");
            return (Criteria) this;
        }

        public Criteria andOutputingNotBetween(Integer value1, Integer value2) {
            addCriterion("outputing not between", value1, value2, "outputing");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourIsNull() {
            addCriterion("auto_feed_start_hour is null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourIsNotNull() {
            addCriterion("auto_feed_start_hour is not null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourEqualTo(Integer value) {
            addCriterion("auto_feed_start_hour =", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourNotEqualTo(Integer value) {
            addCriterion("auto_feed_start_hour <>", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourGreaterThan(Integer value) {
            addCriterion("auto_feed_start_hour >", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_feed_start_hour >=", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourLessThan(Integer value) {
            addCriterion("auto_feed_start_hour <", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourLessThanOrEqualTo(Integer value) {
            addCriterion("auto_feed_start_hour <=", value, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourIn(List<Integer> values) {
            addCriterion("auto_feed_start_hour in", values, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourNotIn(List<Integer> values) {
            addCriterion("auto_feed_start_hour not in", values, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed_start_hour between", value1, value2, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedStartHourNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed_start_hour not between", value1, value2, "autoFeedStartHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourIsNull() {
            addCriterion("auto_feed_end_hour is null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourIsNotNull() {
            addCriterion("auto_feed_end_hour is not null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourEqualTo(Integer value) {
            addCriterion("auto_feed_end_hour =", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourNotEqualTo(Integer value) {
            addCriterion("auto_feed_end_hour <>", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourGreaterThan(Integer value) {
            addCriterion("auto_feed_end_hour >", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_feed_end_hour >=", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourLessThan(Integer value) {
            addCriterion("auto_feed_end_hour <", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourLessThanOrEqualTo(Integer value) {
            addCriterion("auto_feed_end_hour <=", value, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourIn(List<Integer> values) {
            addCriterion("auto_feed_end_hour in", values, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourNotIn(List<Integer> values) {
            addCriterion("auto_feed_end_hour not in", values, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed_end_hour between", value1, value2, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEndHourNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed_end_hour not between", value1, value2, "autoFeedEndHour");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongIsNull() {
            addCriterion("feed_reword_long is null");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongIsNotNull() {
            addCriterion("feed_reword_long is not null");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongEqualTo(Integer value) {
            addCriterion("feed_reword_long =", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongNotEqualTo(Integer value) {
            addCriterion("feed_reword_long <>", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongGreaterThan(Integer value) {
            addCriterion("feed_reword_long >", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_reword_long >=", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongLessThan(Integer value) {
            addCriterion("feed_reword_long <", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongLessThanOrEqualTo(Integer value) {
            addCriterion("feed_reword_long <=", value, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongIn(List<Integer> values) {
            addCriterion("feed_reword_long in", values, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongNotIn(List<Integer> values) {
            addCriterion("feed_reword_long not in", values, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongBetween(Integer value1, Integer value2) {
            addCriterion("feed_reword_long between", value1, value2, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedRewordLongNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_reword_long not between", value1, value2, "feedRewordLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongIsNull() {
            addCriterion("feed_supp_long is null");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongIsNotNull() {
            addCriterion("feed_supp_long is not null");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongEqualTo(Integer value) {
            addCriterion("feed_supp_long =", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongNotEqualTo(Integer value) {
            addCriterion("feed_supp_long <>", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongGreaterThan(Integer value) {
            addCriterion("feed_supp_long >", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongGreaterThanOrEqualTo(Integer value) {
            addCriterion("feed_supp_long >=", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongLessThan(Integer value) {
            addCriterion("feed_supp_long <", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongLessThanOrEqualTo(Integer value) {
            addCriterion("feed_supp_long <=", value, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongIn(List<Integer> values) {
            addCriterion("feed_supp_long in", values, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongNotIn(List<Integer> values) {
            addCriterion("feed_supp_long not in", values, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongBetween(Integer value1, Integer value2) {
            addCriterion("feed_supp_long between", value1, value2, "feedSuppLong");
            return (Criteria) this;
        }

        public Criteria andFeedSuppLongNotBetween(Integer value1, Integer value2) {
            addCriterion("feed_supp_long not between", value1, value2, "feedSuppLong");
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