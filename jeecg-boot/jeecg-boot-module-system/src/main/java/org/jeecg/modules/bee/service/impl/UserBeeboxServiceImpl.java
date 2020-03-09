package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.entity.UserBeeboxExample;
import org.jeecg.modules.bee.mapper.UserBeeboxMapper;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserBeeboxServiceImpl implements UserBeeboxService {

    @Resource
    private UserBeeboxMapper userBeeboxMapper;

    @Override
    public List<String> selectBbidsByUid(String uid) {
        List<UserBeebox> userBeeboxes = selectUserBeeboxByUid(uid);
        List<String> bbids = new LinkedList<>();
        for (UserBeebox ub : userBeeboxes) {
            bbids.add(ub.getBbid());
        }
        return bbids;
    }

    @Override
    public List<UserBeebox> selectAllBbids() {
        List<UserBeebox> userBeeboxes = userBeeboxMapper.selectByExample(null);
        return userBeeboxes;
    }

    /**
     * 获取蜂箱状态信息
     * @param uid 用户id
     * @return
     */
    @Override
    public List<UserBeebox> selectUserBeeboxByUid(String uid) {
        UserBeeboxExample userBeeboxExample = new UserBeeboxExample();
        userBeeboxExample.createCriteria().andUidEqualTo(uid);
        return userBeeboxMapper.selectByExample(userBeeboxExample);
    }

    /**
     * 获取蜂箱状态信息
     * @param bbid 蜂箱号
     * @return
     */
    @Override
    public UserBeebox selectUserBeeboxByBbid(String bbid) {
        UserBeeboxExample userBeeboxExample = new UserBeeboxExample();
        userBeeboxExample.createCriteria().andBbidEqualTo(bbid);
        return userBeeboxMapper.selectByPrimaryKey(bbid);
    }

    /**
     * 更新蜂箱状态信息
     * @param userBeebox
     */
    @Override
    @Transactional(propagation = Propagation.NESTED,rollbackFor = {RuntimeException.class, Error.class})
    public OperModel updateUserBeebox(UserBeebox userBeebox) {
        OperModel operModel = new OperModel();
        operModel.setBbid(userBeebox.getBbid());
        try {
            int i = userBeeboxMapper.updateByPrimaryKeySelective(userBeebox);
            if (i == 0){
                operModel.setErrorMessage("操作失败:蜂箱不存在");
            }
        } catch (Exception e) {
            operModel.setErrorMessage("操作失败:请重试");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return operModel;
    }

    /**
     * 批量更新蜂箱状态
     * @param userBeeboxes
     * @return
     */
    public List<OperModel> updateUserBeeboxMult(List<UserBeebox> userBeeboxes){
        List<OperModel> operModels = new LinkedList<>();
        OperModel operModel = null;
        for (UserBeebox ub : userBeeboxes){
            // 更新
            operModel = updateUserBeebox(ub);
            operModels.add(operModel);
        }
        return operModels;
    }

    /**
     * 新增蜂箱
     * @param userBeebox
     */
    @Override
    public void insertUserBeebox(UserBeebox userBeebox) {
        userBeeboxMapper.insertSelective(userBeebox);
    }

    /**
     * 删除蜂箱
     * @param bbid
     */
    @Override
    public void deleteUserBeebox(String bbid) {
        userBeeboxMapper.deleteByPrimaryKey(bbid);
    }
}
