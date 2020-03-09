package org.jeecg.modules.bee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.model.WeightFrameModel;
import org.jeecg.modules.bee.service.BeeboxInfoService;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.jeecg.modules.bee.service.WeightHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bee")
@Slf4j
public class BeeboxController {

    @Resource
    private UserBeeboxService userBeeboxService;

    @Resource
    private BeeboxInfoService beeboxInfoService;

    @Resource
    private WeightHistoryService weightHistoryService;

    /**
     * 添加蜂箱
     * @param jsonObject
     * @return
     */
    @PostMapping("/addbox")
    public Result<UserBeebox> addBeebox(@RequestBody JSONObject jsonObject){
        Result<UserBeebox> result = new Result<>();

        UserBeebox userBeebox = JSON.parseObject(jsonObject.toJSONString(), UserBeebox.class);
        // 密码校验
        // 校验成功
        try {
            userBeeboxService.insertUserBeebox(userBeebox);
            result.success("添加成功");
            log.info("addBeebox:{}","添加成功");
        } catch (Exception e) {
            result.error500("添加失败");
            log.info("addBeebox:{}","添加失败");
        }
        return result;
    }

    /**
     * 删除蜂箱
     * @param bbid
     * @return
     */
    @DeleteMapping("/delbox")
    public Result<UserBeebox> delBeeboxByBbid(@RequestParam String bbid){
        Result<UserBeebox> result = new Result<>();
        try {
            userBeeboxService.deleteUserBeebox(bbid);
            result.success("删除成功");
            log.info("delBeebox:{}","删除成功");
        } catch (Exception e) {
            result.error500("删除失败");
            log.info("delBeebox:{}","删除失败");
        }
        return result;
    }

    /**
     * 获取所有蜂箱号
     * @param uid
     * @return
     */
    @GetMapping("/bbids/uid")
    public Result<List<String>> getBbidsByUid(@RequestParam String uid){
        Result<List<String>> result = new Result<>();
        List<String> bbids = userBeeboxService.selectBbidsByUid(uid);
        result.setSuccess(true);
        result.setResult(bbids);
        return result;
    }

    /**
     * 获取用户所有蜂箱状态信息
     * @param uid
     * @return
     */
    @GetMapping("/boxStatus/uid")
    public Result<List<UserBeebox>> getBoxStatusByUid(@RequestParam String uid){
        Result<List<UserBeebox>> result = new Result<>();
        List<UserBeebox> userBeeboxes = userBeeboxService.selectUserBeeboxByUid(uid);
        result.setSuccess(true);
        result.setResult(userBeeboxes);
        return result;
    }

    /**
     * 获取蜂箱状态信息
     * @param bbid
     * @return
     */
    @GetMapping("/boxStatus/bbid")
    public Result<UserBeebox> getBoxStatusByBbid(@RequestParam String bbid){
        Result<UserBeebox> result = new Result<>();
        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
        result.setSuccess(true);
        result.setResult(userBeebox);
        return result;
    }

    /**
     * 修改蜂箱状态信息 单个
     * @param jsonObject
     * @return
     */
    @PutMapping("/boxStatus/bbid")
    public Result<OperModel> updateBoxStatusByBbid(@RequestBody JSONObject jsonObject){
        Result<OperModel> result = new Result<>();
        UserBeebox userBeebox = JSON.parseObject(jsonObject.toJSONString(), UserBeebox.class);
        try {
            userBeeboxService.updateUserBeebox(userBeebox);
            result.success("修改成功");
        } catch (Exception e) {
            result.error500("修改失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改蜂箱状态信息 批量
     * [自动加热][自动喂食][巢门控制]
     * @param jsonObject
     * @return
     */
    @PutMapping("/boxStatus/mult")
    public Result<List<OperModel>> updateBoxStatusMult(@RequestBody JSONObject jsonObject){
        Result<List<OperModel>> result = new Result<>();

        JSONArray jsonArray = jsonObject.getJSONArray("userBeeboxes");
        List<UserBeebox> userBeeboxes = jsonArray.toJavaList(UserBeebox.class);

        List<OperModel> operModels = userBeeboxService.updateUserBeeboxMult(userBeeboxes);
        result.setSuccess(true);
        result.setResult(operModels);
        return result;
    }

    /**
     * 查询最新一次的蜂箱信息
     * @param uid
     * @return
     */
    @GetMapping("/boxInfo/uid")
    public Result<List<BeeboxInfo>> getNowBeeboxInfo(@RequestParam String uid,@RequestParam String orderBy){
        Result<List<BeeboxInfo>> result = new Result<>();
        List<BeeboxInfo> beeboxInfos = beeboxInfoService.selectNowBeeboxInfoByUid(uid,orderBy);
        result.setSuccess(true);
        result.setResult(beeboxInfos);
        log.info("getNowBeeboxInfo:{}",uid);
        System.out.println(result.getResult());
        return result;
    }

    /**
     * 查询最新一次的蜂箱信息
     * @param bbid
     * @return
     */
    @GetMapping("/boxInfo/bbid")
    public Result<BeeboxInfo> getNowBeeboxInfo(@RequestParam String bbid){
        Result<BeeboxInfo> result = new Result<>();
        BeeboxInfo beeboxInfo = beeboxInfoService.selectNowBeeboxInfoByBbid(bbid);
        result.setSuccess(true);
        result.setResult(beeboxInfo);
        log.info("getNowBeeboxInfo:{}",bbid);
        return result;
    }

    /**
     * 获取当前重量变化率
     * 重量变化率从小-->大
     * @return
     */
    @GetMapping("/wcr/uid")
    public Result<List<WeightChangeModel>> getNowWCRByUid(@RequestParam String uid){
        Result<List<WeightChangeModel>> result = new Result<>();
        List<WeightChangeModel> weightChangeModels = beeboxInfoService.selectWeightChangeByUid(uid);
        result.setSuccess(true);
        result.setResult(weightChangeModels);
        log.info("getNowWCRByUid:{}",uid);
        return result;
    }

    /**
     * 获取长期重量/框数
     */
    @GetMapping("/history/wfr/bbid")
    public Result<List<WeightFrameModel>> getLongWFRByTimeRange(@RequestParam String bbid,@RequestParam String startDate,
                                                                @RequestParam String endDate,@RequestParam String time){
        Result<List<WeightFrameModel>> result = new Result<>();
        List<WeightFrameModel> weightFrameModels = weightHistoryService.selectWeightFrameByTime(bbid, startDate, endDate, time);
        result.setSuccess(true);
        result.setResult(weightFrameModels);
        return result;
    }

    /**
     * 获取长期重量变化率
     */
    @GetMapping("/history/wcr/bbid")
    public Result<List<WeightChangeModel>> getLongWCRByTimeRange(@RequestParam String bbid,@RequestParam String startDate,
                                                                @RequestParam String endDate,@RequestParam String time){
        Result<List<WeightChangeModel>> result = new Result<>();
        List<WeightChangeModel> weightChangeModels = weightHistoryService.selectWeightChangeByTime(bbid, startDate, endDate, time);
        result.setSuccess(true);
        result.setResult(weightChangeModels);
        return result;
    }



}
