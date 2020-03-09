package org.jeecg;

import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.bee.entity.*;
import org.jeecg.modules.bee.mapper.BeeboxInfoMapper;
import org.jeecg.modules.bee.mapper.HistoryOperMapper;
import org.jeecg.modules.bee.mapper.SysConfigMapper;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.model.WeightFrameModel;
import org.jeecg.modules.bee.service.*;
import org.jeecg.modules.bee.service.impl.HistoryOperTransService;
import org.jeecg.modules.quartz.service.IQuartzJobService;
import org.jeecg.modules.system.service.ISysAnnouncementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ScheduleTest {

    @Resource
    private BeeboxInfoMapper beeboxInfoMapper;

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Resource
    private SysConfigService sysConfigService;
    @Resource
    private BeeboxInfoService beeboxInfoService;

    @Resource
    private HistoryOperService historyOperService;

    @Resource
    private HistoryOperMapper historyOperMapper;

    @Resource
    private HistoryOperTransService historyOperTransService;

    @Resource
    private WeightHistoryService weightHistoryService;

    @Resource
    private BeeOutputService beeOutputService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private UmsExceptionService umsExceptionService;

    @Resource
    private IQuartzJobService quartzJobService;

    @Resource
    private RedisUtil redisUtil;
    @Test
    public void testgetBeeboxInfo(){
        BeeboxInfoExample beeboxInfoExample = new BeeboxInfoExample();
        beeboxInfoExample.createCriteria().andBbidEqualTo("0002");
        List<BeeboxInfo> beeboxInfos = beeboxInfoMapper.selectByExample(beeboxInfoExample);
        System.out.println(beeboxInfos);
    }

    @Test
    public void testselectNowBeeboxInfo(){
//        List<BeeboxInfo> beeboxInfos = beeboxInfoService.selectNowBeeboxInfoByUid("e9ca23d68d884d4ebb19d07889727dae");
//        System.out.println(beeboxInfos);
    }


    @Test
    public void testselectHistoryOperByTimeRange(){

        List<HistoryOper> historyOpers = historyOperService.selectHistoryOperByUid("e9ca23d68d884d4ebb19d07889727dae");
        for (HistoryOper historyOper : historyOpers){
            System.out.println(historyOper);
        }
    }

    @Test
    public void testcleanMultBox(){
        ArrayList<String> bbids = new ArrayList<>();
        bbids.add("0001");
        bbids.add("0002");
        List<OperModel> operModels = historyOperService.cleanMultBox("e9ca23d68d884d4ebb19d07889727dae", bbids);
        for (OperModel operModel : operModels){
            System.out.println(operModel);
        }
    }




    @Test
    public void insertBeeboxInfo(){
        BeeboxInfo beeboxInfo = new BeeboxInfo();
        Date date = new Date();
        for (int i = 0;i<= 100;i++){
            beeboxInfo.setBbid("0001");
            beeboxInfo.setWeight(9000+Math.random()*3000);
            beeboxInfo.setTemper(15+Math.random()*5);
            date.setTime(date.getTime() + 1000*10);
            beeboxInfo.setRecvTime(date);
            beeboxInfoService.insertBeeboxInfo(beeboxInfo);
            beeboxInfo.setBbid("0002");
            beeboxInfo.setWeight(9000+Math.random()*3000);
            beeboxInfoService.insertBeeboxInfo(beeboxInfo);
            beeboxInfo.setBbid("0003");
            beeboxInfo.setWeight(9000+Math.random()*3000);
            beeboxInfoService.insertBeeboxInfo(beeboxInfo);
            beeboxInfo.setBbid("0004");
            beeboxInfo.setWeight(9000+Math.random()*3000);
            beeboxInfoService.insertBeeboxInfo(beeboxInfo);
            beeboxInfo.setBbid("0005");
            beeboxInfo.setWeight(9000+Math.random()*3000);
            beeboxInfoService.insertBeeboxInfo(beeboxInfo);
        }
    }


    @Test
    public void testselectLatestTwoBoxInfoByUid(){
        List<WeightChangeModel> weightChangeModels = beeboxInfoService.selectWeightChangeByUid("e9ca23d68d884d4ebb19d07889727dae");
        for (WeightChangeModel wcm : weightChangeModels){
            System.out.println(wcm);
        }
    }

    @Test
    public void testinsertWeightChangeHistory(){
        ArrayList<WeightChangeModel> weightChangeModels = new ArrayList<>();
        WeightChangeModel wc1 = WeightChangeModel.builder().bbid("0001").weightChange(0.053d).recvTime(new Date()).build();
        WeightChangeModel wc2 = WeightChangeModel.builder().bbid("0002").weightChange(0.033d).recvTime(new Date()).build();
        WeightChangeModel wc3 = WeightChangeModel.builder().bbid("0003").weightChange(-0.033d).recvTime(new Date()).build();
        weightChangeModels.add(wc1);
        weightChangeModels.add(wc2);
        weightChangeModels.add(wc3);
        weightHistoryService.insertWeightChangeHistory(weightChangeModels);
    }

    @Test
    public void testinsertWeightFrameHistory(){
        ArrayList<WeightFrameModel> weightFrameModels = new ArrayList<>();
        WeightFrameModel wf1 = WeightFrameModel.builder().bbid("0001").weightFrame(2313d).recvTime(new Date()).build();
        WeightFrameModel wf2 = WeightFrameModel.builder().bbid("0002").weightFrame(2533d).recvTime(new Date()).build();
        WeightFrameModel wf3 = WeightFrameModel.builder().bbid("0003").weightFrame(2219d).recvTime(new Date()).build();
        weightFrameModels.add(wf1);
        weightFrameModels.add(wf2);
        weightFrameModels.add(wf3);
        weightHistoryService.insertWeightFrameHistory(weightFrameModels);
    }

    @Test
    public void testweightFrameSchedule(){
        weightHistoryService.weightChangeSchedule(18,0,0);
    }


    @Test
    public void testselectNowBeeboxInfoByUid(){
        List<BeeboxInfo> beeboxInfos = beeboxInfoService.selectNowBeeboxInfoByUid("e9ca23d68d884d4ebb19d07889727dae", "temper");

    }

    @Test
    public void teststartoutputHoney(){
        beeOutputService.startOutputHoney("e9ca23d68d884d4ebb19d07889727dae");
    }

    @Test
    public void testendoutputHoney(){
        List<HistoryOutput> historyOutputs = beeOutputService.endOutputHoney("e9ca23d68d884d4ebb19d07889727dae");
        for (HistoryOutput ho : historyOutputs){
            System.out.println(ho);
        }
    }

    @Test
    public void testautoHeatSchedule(){
        scheduleService.autoHeatSchedule();
    }

    @Test
    public void testselectLastFeedHistory(){
        HistoryOper historyOper1 = historyOperService.selectLastOperHistory("0001","feed");
        HistoryOper historyOper2 = historyOperService.selectLastOperHistory("0001","heat");
        HistoryOper historyOper3 = historyOperService.selectLastOperHistory("0001","clean");
        System.out.println(historyOper1);
        System.out.println(historyOper2);
        System.out.println(historyOper3);
    }

    @Test
    public void testupdateFeedHistory(){
        historyOperTransService.feedOrCleanComplete("0005","feed");
    }

    @Test
    public void testoperStatusSchedule(){
        scheduleService.operStatusSchedule();
    }

    @Test
    public void testinsertHistoryOutputList(){
        List<HistoryOutput> historyOutputs = new LinkedList<>();
        HistoryOutput historyOutput = new HistoryOutput();
        historyOutput.setBbid("0001");
        historyOutput.setHoney(1323d);
        historyOutput.setTime(new Date());
        HistoryOutput historyOutput1 = new HistoryOutput();
        historyOutput1.setBbid("0002");
        historyOutput1.setHoney(1223d);
        historyOutput1.setTime(new Date());
        historyOutputs.add(historyOutput);
        historyOutputs.add(historyOutput1);
        beeOutputService.insertHistoryOutputList(historyOutputs);
    }


    @Test
    public void testselectHistoryOperByUid(){
        List<HistoryOper> historyOpers = historyOperService.selectHistoryOperByUid("e9ca23d68d884d4ebb19d07889727dae");
        for (HistoryOper historyOper : historyOpers){
            System.out.println(historyOper);
        }
    }


    @Test
    public void tes1111(){
        List<HistoryOper> historyOpers = historyOperService.selectEveryLastOperHistory();
        for (HistoryOper ho : historyOpers){
            System.out.println(ho);
        }
    }

    @Test
    public void testws(){

    }

}
