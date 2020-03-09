package org.jeecg.modules.bee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.bee.entity.SysConfig;
import org.jeecg.modules.bee.service.SysConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/conf")
@Slf4j
public class UserSysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("/glb")
    public Result<SysConfig> getBoxConf(@RequestParam String uid){
        Result<SysConfig> result = new Result<>();
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        result.setSuccess(true);
        result.setResult(sysConfig);
        return result;
    }

    @PutMapping("/glb")
    public Result<SysConfig> putBoxConf(@RequestBody JSONObject jsonObject){
        Result<SysConfig> result = new Result<>();
        SysConfig sysConfig = JSON.parseObject(jsonObject.toJSONString(), SysConfig.class);
        try {
            sysConfigService.updateConfig(sysConfig);
            result.success("修改成功");
        } catch (Exception e) {
            result.error500("修改失败");
            e.printStackTrace();
        }
        return result;
    }


}
