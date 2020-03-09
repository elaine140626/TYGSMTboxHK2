package org.jeecg.modules.bee.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.HistoryOutput;
import org.jeecg.modules.bee.entity.SysConfig;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.service.BeeOutputService;
import org.jeecg.modules.bee.service.HistoryOperService;
import org.jeecg.modules.bee.service.SysConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/oper")
@Slf4j
public class BeeOperController {

    @Resource
    private HistoryOperService historyOperService;

    @Resource
    private BeeOutputService beeOutputService;

    @Resource
    private SysConfigService sysConfigService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取n天之内用户蜂箱的操作记录
     * @param uid
     * @return
     */
    @GetMapping("/history/uid")
    public Result<List<HistoryOper>> getHistoryOperByUid(@RequestParam String uid){
        Result<List<HistoryOper>> result = new Result<>();
        List<HistoryOper> historyOpers = historyOperService.selectHistoryOperByUid(uid);
        result.setSuccess(true);
        result.setResult(historyOpers);
        return result;
    }

    /**
     * 获取n天内蜂箱的操作记录
     * @param bbid
     * @return
     */
    @GetMapping("/history/bbid")
    public Result<List<HistoryOper>> getHistoryOperByBbid(@RequestParam String uid , @RequestParam String bbid){
        Result<List<HistoryOper>> result = new Result<>();
        List<HistoryOper> historyOpers = historyOperService.selectHistoryOperByBbid(uid,bbid);
        result.setSuccess(true);
        result.setResult(historyOpers);
        return result;
    }

    /**
     * 批量清洗蜂箱
     * @param jsonObject
     * @return
     */
    @PostMapping("/clean/mult")
    public Result<List<OperModel>> postCleanMult(@RequestBody JSONObject jsonObject){
        Result<List<OperModel>> result = new Result<>();

        String uid = jsonObject.getString("uid");
        JSONArray jsonbbids = jsonObject.getJSONArray("bbids");
        List<String> bbids = new LinkedList<>();

        // 产出统计中 关闭清洗功能
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        if (sysConfig.getOutputing() != 0){
            result.error500("产出统计中,清洗暂时关闭");
            return result;
        }

        for (int i=0;i<jsonbbids.size();i++){
            String bbid = (String) jsonbbids.get(i);
            bbids.add(bbid);
        }
        List<OperModel> operModels = historyOperService.cleanMultBox(uid, bbids);
        result.setSuccess(true);
        result.setResult(operModels);
        return result;
    }

    /**
     * 批量喂食蜂箱
     * @param jsonObject
     * @return
     */
    @PostMapping("/feed/mult")
    public Result<List<OperModel>> postFeedMult(@RequestBody JSONObject jsonObject){
        Result<List<OperModel>> result = new Result<>();

        String uid = jsonObject.getString("uid");
        JSONArray jsonbbids = jsonObject.getJSONArray("bbids");
        Integer feedOrder = jsonObject.getInteger("feedOrder");

        // 产出统计中 关闭喂食功能
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        if (sysConfig.getOutputing() != 0){
            result.error500("产出统计中,喂食暂时关闭");
            return result;
        }
        // 获取奖励喂食时长
        Integer feedRewordLong = sysConfig.getFeedRewordLong();
        // 获取补充喂食时长
        Integer feedSuppLong = sysConfig.getFeedSuppLong();
        List<String> bbids = new LinkedList<>();
        for (int i=0;i<jsonbbids.size();i++){
            String bbid = (String) jsonbbids.get(i);
            bbids.add(bbid);
        }
        List<OperModel> operModels = null;
        if (feedOrder == 0){
            // 补充喂食
            operModels = historyOperService.feedMultBox(uid, bbids,feedOrder,1,feedSuppLong);
        } else if (feedOrder == 1){
            // 奖励喂食
            operModels = historyOperService.feedMultBox(uid, bbids,feedOrder,1,feedRewordLong);
        } else{
            result.error500("喂食类型出错");
        }
        result.setSuccess(true);
        result.setResult(operModels);
        return result;

    }

    /**
     * 批量巢门控制
     * @param jsonObject
     * @return
     */
    @PostMapping("/door/mult")
    public Result<List<OperModel>> postDoorMult(@RequestBody JSONObject jsonObject){
        Result<List<OperModel>> result = new Result<>();

        String uid = jsonObject.getString("uid");
        JSONArray jsonbbids = jsonObject.getJSONArray("bbids");
        Integer doorStatus = jsonObject.getInteger("doorStatus");

        List<String> bbids = new LinkedList<>();
        for (int i=0;i<jsonbbids.size();i++){
            String bbid = (String) jsonbbids.get(i);
            bbids.add(bbid);
        }
        List<OperModel> operModels = historyOperService.doorMultBox(uid, bbids,doorStatus);
        result.setSuccess(true);
        result.setResult(operModels);
        return result;
    }

    /*-------------------产出统计-------------------*/

    @PostMapping("output/frame/bbid")
    public Result<Integer> outputFrameByBbid(@RequestBody JSONObject jsonObject){
        Result<Integer> result = new Result<>();
        String bbid = jsonObject.getString("bbid");
        historyOperService.outputFrame(bbid);
        result.setSuccess(true);
        return result;
    }

    /**
     * 获取产出统计状态
     * @param uid
     * @return 0正常 1蜜产出中  2脾产出中
     */
    @GetMapping("/output")
    public Result<Integer> getOutputStatus(@RequestParam String uid){
        Result<Integer> result = new Result<>();
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer outputing = sysConfig.getOutputing();
//        String redisHoneyKey = "prefix_output_honey_" + uid;
//        String redisFrameKey = "prefix_output_frame_" + uid;
//        boolean honeyOutputing = redisUtil.hasKey(redisHoneyKey);
//        boolean frameOutputing = redisUtil.hasKey(redisFrameKey);
        result.setResult(0);
        if (outputing == 1) {
            // 产出蜂蜜中
            result.setResult(1);
        }else if (outputing == 2){
            // 产出脾数中
            result.setResult(2);
        }else if (outputing == 3){
            // 产出脾数中
            result.setResult(3);
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 开始产出统计 [蜂蜜]
     * @param uid
     * @return
     */
    @GetMapping("/output/honey/start")
    public Result<Integer> getStartOutputHoney(@RequestParam String uid){
        Result<Integer> result = new Result<>();
        try {
            int i = beeOutputService.startOutputHoney(uid);
            if (i > 0){
                result.success("开始统计蜜产出");
            } else {
                result.error500("暂无蜂箱");
            }
        } catch (Exception e) {
            result.error500("开始统计失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 开始产出统计 [脾数]
     * @param uid
     * @return
     */
    @GetMapping("/output/frame/start")
    public Result<Integer> getStartOutputFrame(@RequestParam String uid){
        Result<Integer> result = new Result<>();
        try {
            int i = beeOutputService.startOutputFrame(uid);
            if (i > 0){
                result.success("开始统计脾产出");
            } else {
                result.error500("暂无蜂箱");
            }
        } catch (Exception e) {
            result.error500("开始统计失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 结束产出统计 [蜂蜜]
     * @param uid
     * @return
     */
    @GetMapping("/output/honey/end")
    public Result<List<HistoryOutput>> getEndOutputHoney(@RequestParam String uid) {
        Result<List<HistoryOutput>> result = new Result<>();
        List<HistoryOutput> historyOutputs = beeOutputService.endOutputHoney(uid);
        result.setSuccess(true);
        result.setResult(historyOutputs);
        return result;
    }

    /**
     * 结束产出统计 [脾数]
     * @param uid
     * @return
     */
    @GetMapping("/output/frame/end")
    public Result<List<HistoryOutput>> getEndOutputFrame(@RequestParam String uid){
        Result<List<HistoryOutput>> result = new Result<>();
        List<HistoryOutput> historyOutputs = beeOutputService.endOutputFrame(uid);
        result.setSuccess(true);
        result.setResult(historyOutputs);
        return result;
    }

    /**
     * 确认产出统计
     * @param jsonObject
     * @return
     */
    @PostMapping("/output/confirm")
    public Result<Integer> confirmOutput(@RequestBody JSONObject jsonObject){
        Result<Integer> result = new Result<>();
        String uid = jsonObject.getString("uid");
        Integer type = jsonObject.getInteger("type");// 产出类型 1蜂蜜 2脾数
        JSONArray historyOutput = jsonObject.getJSONArray("historyOutputs");
        List<HistoryOutput> historyOutputs = historyOutput.toJavaList(HistoryOutput.class);
        System.out.println(uid);
        System.out.println(type);
        for (HistoryOutput ho : historyOutputs){
            System.out.println("!!!"+ho);
        }
        if (uid != null && type != null && historyOutput.size() > 0){
            beeOutputService.confirmOutput(historyOutputs,uid,type);
        }
        result.success("产出统计成功");
        return result;
    }

    /**
     * 取消产出统计
     * @param uid
     * @param type 1蜜 2脾
     * @return
     */
    @GetMapping("/output/cancel")
    public Result<Integer> cancelOutput(@RequestParam String uid,@RequestParam Integer type){
        Result<Integer> result = new Result<>();
        beeOutputService.cancelOutput(uid,type);
        result.success("操作成功");
        return result;
    }

    /**
     * 获取时间范围内的产出记录
     * @param uid
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/output/history/uid")
    public Result<List<HistoryOutput>> getOutputByTimeRangeUid(@RequestParam String uid ,@RequestParam String startDate,@RequestParam String endDate){
        Result<List<HistoryOutput>> result = new Result<>();
        List<HistoryOutput> historyOutputs = beeOutputService.selectByTimeRangeUid(uid, startDate, endDate);
        result.setSuccess(true);
        result.setResult(historyOutputs);
        return result;
    }

    /**
     * 获取时间范围内单个蜂箱的产出记录
     * @param bbid
     * @return
     */
    @GetMapping("/output/history/bbid")
    public Result<List<HistoryOutput>> getOutputByTimeRangeBbid(@RequestParam String bbid){
        Result<List<HistoryOutput>> result = new Result<>();
        List<HistoryOutput> historyOutputs = beeOutputService.selectByTimeRangeBbid(bbid);
        result.setSuccess(true);
        result.setResult(historyOutputs);
        return result;
    }
}
