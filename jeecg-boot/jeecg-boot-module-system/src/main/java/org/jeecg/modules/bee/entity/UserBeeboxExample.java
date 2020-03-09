package org.jeecg.modules.bee.entity;

import java.util.ArrayList;
import java.util.List;

public class UserBeeboxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public UserBeeboxExample() {
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

        public Criteria andBoxStatusIsNull() {
            addCriterion("box_status is null");
            return (Criteria) this;
        }

        public Criteria andBoxStatusIsNotNull() {
            addCriterion("box_status is not null");
            return (Criteria) this;
        }

        public Criteria andBoxStatusEqualTo(Integer value) {
            addCriterion("box_status =", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusNotEqualTo(Integer value) {
            addCriterion("box_status <>", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusGreaterThan(Integer value) {
            addCriterion("box_status >", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_status >=", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusLessThan(Integer value) {
            addCriterion("box_status <", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusLessThanOrEqualTo(Integer value) {
            addCriterion("box_status <=", value, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusIn(List<Integer> values) {
            addCriterion("box_status in", values, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusNotIn(List<Integer> values) {
            addCriterion("box_status not in", values, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusBetween(Integer value1, Integer value2) {
            addCriterion("box_status between", value1, value2, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andBoxStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("box_status not between", value1, value2, "boxStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusIsNull() {
            addCriterion("king_status is null");
            return (Criteria) this;
        }

        public Criteria andKingStatusIsNotNull() {
            addCriterion("king_status is not null");
            return (Criteria) this;
        }

        public Criteria andKingStatusEqualTo(String value) {
            addCriterion("king_status =", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusNotEqualTo(String value) {
            addCriterion("king_status <>", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusGreaterThan(String value) {
            addCriterion("king_status >", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusGreaterThanOrEqualTo(String value) {
            addCriterion("king_status >=", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusLessThan(String value) {
            addCriterion("king_status <", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusLessThanOrEqualTo(String value) {
            addCriterion("king_status <=", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusLike(String value) {
            addCriterion("king_status like", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusNotLike(String value) {
            addCriterion("king_status not like", value, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusIn(List<String> values) {
            addCriterion("king_status in", values, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusNotIn(List<String> values) {
            addCriterion("king_status not in", values, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusBetween(String value1, String value2) {
            addCriterion("king_status between", value1, value2, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andKingStatusNotBetween(String value1, String value2) {
            addCriterion("king_status not between", value1, value2, "kingStatus");
            return (Criteria) this;
        }

        public Criteria andFrameNumIsNull() {
            addCriterion("frame_num is null");
            return (Criteria) this;
        }

        public Criteria andFrameNumIsNotNull() {
            addCriterion("frame_num is not null");
            return (Criteria) this;
        }

        public Criteria andFrameNumEqualTo(Integer value) {
            addCriterion("frame_num =", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumNotEqualTo(Integer value) {
            addCriterion("frame_num <>", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumGreaterThan(Integer value) {
            addCriterion("frame_num >", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("frame_num >=", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumLessThan(Integer value) {
            addCriterion("frame_num <", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumLessThanOrEqualTo(Integer value) {
            addCriterion("frame_num <=", value, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumIn(List<Integer> values) {
            addCriterion("frame_num in", values, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumNotIn(List<Integer> values) {
            addCriterion("frame_num not in", values, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumBetween(Integer value1, Integer value2) {
            addCriterion("frame_num between", value1, value2, "frameNum");
            return (Criteria) this;
        }

        public Criteria andFrameNumNotBetween(Integer value1, Integer value2) {
            addCriterion("frame_num not between", value1, value2, "frameNum");
            return (Criteria) this;
        }

        public Criteria andNetStatusIsNull() {
            addCriterion("net_status is null");
            return (Criteria) this;
        }

        public Criteria andNetStatusIsNotNull() {
            addCriterion("net_status is not null");
            return (Criteria) this;
        }

        public Criteria andNetStatusEqualTo(Integer value) {
            addCriterion("net_status =", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotEqualTo(Integer value) {
            addCriterion("net_status <>", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusGreaterThan(Integer value) {
            addCriterion("net_status >", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("net_status >=", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusLessThan(Integer value) {
            addCriterion("net_status <", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusLessThanOrEqualTo(Integer value) {
            addCriterion("net_status <=", value, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusIn(List<Integer> values) {
            addCriterion("net_status in", values, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotIn(List<Integer> values) {
            addCriterion("net_status not in", values, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusBetween(Integer value1, Integer value2) {
            addCriterion("net_status between", value1, value2, "netStatus");
            return (Criteria) this;
        }

        public Criteria andNetStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("net_status not between", value1, value2, "netStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusIsNull() {
            addCriterion("door_status is null");
            return (Criteria) this;
        }

        public Criteria andDoorStatusIsNotNull() {
            addCriterion("door_status is not null");
            return (Criteria) this;
        }

        public Criteria andDoorStatusEqualTo(Integer value) {
            addCriterion("door_status =", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusNotEqualTo(Integer value) {
            addCriterion("door_status <>", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusGreaterThan(Integer value) {
            addCriterion("door_status >", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("door_status >=", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusLessThan(Integer value) {
            addCriterion("door_status <", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusLessThanOrEqualTo(Integer value) {
            addCriterion("door_status <=", value, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusIn(List<Integer> values) {
            addCriterion("door_status in", values, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusNotIn(List<Integer> values) {
            addCriterion("door_status not in", values, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusBetween(Integer value1, Integer value2) {
            addCriterion("door_status between", value1, value2, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andDoorStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("door_status not between", value1, value2, "doorStatus");
            return (Criteria) this;
        }

        public Criteria andAutoFeedIsNull() {
            addCriterion("auto_feed is null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedIsNotNull() {
            addCriterion("auto_feed is not null");
            return (Criteria) this;
        }

        public Criteria andAutoFeedEqualTo(Integer value) {
            addCriterion("auto_feed =", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedNotEqualTo(Integer value) {
            addCriterion("auto_feed <>", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedGreaterThan(Integer value) {
            addCriterion("auto_feed >", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_feed >=", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedLessThan(Integer value) {
            addCriterion("auto_feed <", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedLessThanOrEqualTo(Integer value) {
            addCriterion("auto_feed <=", value, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedIn(List<Integer> values) {
            addCriterion("auto_feed in", values, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedNotIn(List<Integer> values) {
            addCriterion("auto_feed not in", values, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed between", value1, value2, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoFeedNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_feed not between", value1, value2, "autoFeed");
            return (Criteria) this;
        }

        public Criteria andAutoHeatIsNull() {
            addCriterion("auto_heat is null");
            return (Criteria) this;
        }

        public Criteria andAutoHeatIsNotNull() {
            addCriterion("auto_heat is not null");
            return (Criteria) this;
        }

        public Criteria andAutoHeatEqualTo(Integer value) {
            addCriterion("auto_heat =", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatNotEqualTo(Integer value) {
            addCriterion("auto_heat <>", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatGreaterThan(Integer value) {
            addCriterion("auto_heat >", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_heat >=", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatLessThan(Integer value) {
            addCriterion("auto_heat <", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatLessThanOrEqualTo(Integer value) {
            addCriterion("auto_heat <=", value, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatIn(List<Integer> values) {
            addCriterion("auto_heat in", values, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatNotIn(List<Integer> values) {
            addCriterion("auto_heat not in", values, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatBetween(Integer value1, Integer value2) {
            addCriterion("auto_heat between", value1, value2, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andAutoHeatNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_heat not between", value1, value2, "autoHeat");
            return (Criteria) this;
        }

        public Criteria andFeedingIsNull() {
            addCriterion("feeding is null");
            return (Criteria) this;
        }

        public Criteria andFeedingIsNotNull() {
            addCriterion("feeding is not null");
            return (Criteria) this;
        }

        public Criteria andFeedingEqualTo(Integer value) {
            addCriterion("feeding =", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingNotEqualTo(Integer value) {
            addCriterion("feeding <>", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingGreaterThan(Integer value) {
            addCriterion("feeding >", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingGreaterThanOrEqualTo(Integer value) {
            addCriterion("feeding >=", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingLessThan(Integer value) {
            addCriterion("feeding <", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingLessThanOrEqualTo(Integer value) {
            addCriterion("feeding <=", value, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingIn(List<Integer> values) {
            addCriterion("feeding in", values, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingNotIn(List<Integer> values) {
            addCriterion("feeding not in", values, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingBetween(Integer value1, Integer value2) {
            addCriterion("feeding between", value1, value2, "feeding");
            return (Criteria) this;
        }

        public Criteria andFeedingNotBetween(Integer value1, Integer value2) {
            addCriterion("feeding not between", value1, value2, "feeding");
            return (Criteria) this;
        }

        public Criteria andCleaningIsNull() {
            addCriterion("cleaning is null");
            return (Criteria) this;
        }

        public Criteria andCleaningIsNotNull() {
            addCriterion("cleaning is not null");
            return (Criteria) this;
        }

        public Criteria andCleaningEqualTo(Integer value) {
            addCriterion("cleaning =", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningNotEqualTo(Integer value) {
            addCriterion("cleaning <>", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningGreaterThan(Integer value) {
            addCriterion("cleaning >", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningGreaterThanOrEqualTo(Integer value) {
            addCriterion("cleaning >=", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningLessThan(Integer value) {
            addCriterion("cleaning <", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningLessThanOrEqualTo(Integer value) {
            addCriterion("cleaning <=", value, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningIn(List<Integer> values) {
            addCriterion("cleaning in", values, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningNotIn(List<Integer> values) {
            addCriterion("cleaning not in", values, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningBetween(Integer value1, Integer value2) {
            addCriterion("cleaning between", value1, value2, "cleaning");
            return (Criteria) this;
        }

        public Criteria andCleaningNotBetween(Integer value1, Integer value2) {
            addCriterion("cleaning not between", value1, value2, "cleaning");
            return (Criteria) this;
        }

        public Criteria andHeatingIsNull() {
            addCriterion("heating is null");
            return (Criteria) this;
        }

        public Criteria andHeatingIsNotNull() {
            addCriterion("heating is not null");
            return (Criteria) this;
        }

        public Criteria andHeatingEqualTo(Integer value) {
            addCriterion("heating =", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingNotEqualTo(Integer value) {
            addCriterion("heating <>", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingGreaterThan(Integer value) {
            addCriterion("heating >", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingGreaterThanOrEqualTo(Integer value) {
            addCriterion("heating >=", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingLessThan(Integer value) {
            addCriterion("heating <", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingLessThanOrEqualTo(Integer value) {
            addCriterion("heating <=", value, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingIn(List<Integer> values) {
            addCriterion("heating in", values, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingNotIn(List<Integer> values) {
            addCriterion("heating not in", values, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingBetween(Integer value1, Integer value2) {
            addCriterion("heating between", value1, value2, "heating");
            return (Criteria) this;
        }

        public Criteria andHeatingNotBetween(Integer value1, Integer value2) {
            addCriterion("heating not between", value1, value2, "heating");
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