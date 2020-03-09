package org.jeecg.modules.bee.service;


import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.model.OperModel;

import java.util.List;

public interface UserBeeboxService {

    /**
     * 获取用户所有蜂箱号
     * @param uid
     * @return
     */
    List<String> selectBbidsByUid(String uid);

    /**
     * 所有蜂箱状态信息
     * @return
     */
    List<UserBeebox> selectAllBbids();

    /**
     * 获取蜂箱状态信息
      * @param uid
     * @return
     */
    List<UserBeebox> selectUserBeeboxByUid(String uid);

    /**
     * 获取蜂箱状态信息
     * @param bbid
     * @return
     */
    UserBeebox selectUserBeeboxByBbid(String bbid);

    /**
     * 更新蜂箱状态信息
     * @param userBeebox
     */
    OperModel updateUserBeebox(UserBeebox  userBeebox);

    /**
     * 批量更新蜂箱状态信息
     * @param userBeeboxes
     * @return
     */
    List<OperModel> updateUserBeeboxMult(List<UserBeebox> userBeeboxes);

    /**
     * 新增蜂箱
     * @param userBeebox
     */
    void insertUserBeebox(UserBeebox userBeebox);

    /**
     * 删除蜂箱
     * @param bbid
     */
    void deleteUserBeebox(String bbid);


}
