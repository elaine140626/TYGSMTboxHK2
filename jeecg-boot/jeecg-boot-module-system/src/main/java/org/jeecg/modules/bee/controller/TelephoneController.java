package org.jeecg.modules.bee.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tele")
@Slf4j
public class TelephoneController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/cid/username")
    public Result<String> boxCidBindUsername(@RequestBody JSONObject jsonObject){
        Result<String> result = new Result<>();
        String username = jsonObject.getString("username");
        String cid = jsonObject.getString("cid");
        if (cid != null && username != null){
            SysUser userByName = sysUserService.getUserByName(username);
            userByName.setCid(cid);
            int i = sysUserService.editUser(userByName);
            if (i == 1){
                result.success("操作成功");
            }else {
                result.error500("操作失败");
            }
        }
        return result;
    }

    @PostMapping("/cid/mobile")
    public Result<String> boxCidBindMobile(@RequestBody JSONObject jsonObject){
        Result<String> result = new Result<>();
        String mobile = jsonObject.getString("mobile");
        String cid = jsonObject.getString("cid");
        if (cid != null && mobile != null){
            SysUser userByPhone = sysUserService.getUserByPhone(mobile);
            userByPhone.setCid(cid);
            int i = sysUserService.editUser(userByPhone);
            if (i == 1){
                result.success("操作成功");
            }else {
                result.error500("操作失败");
            }
        }
        return result;
    }
}