package org.jeecg.modules.bee.socket;

import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueUtil {
    // 巢门状态
    public final static String DOOR_CLEAN = "11";   // 冲洗
    public final static String DOOR_KING = "12";    // 控王
    public final static String DOOR_NORMAL = "13";  // 正常
    public final static String DOOR_DRAFT = "14";   // 通风档


    public final static String CLEAN_OPEN = "03";    // 开启清洗
    public final static String CLEAN_CLOSE = "04";   // 关闭清洗
    public final static String HEAT_OPEN = "05";     // 开启加热
    public final static String HEAT_CLOSE = "06";    // 关闭加热
    public final static String FEED_REWD_OPEN = "07";     // 开启喂食[奖励喂食]
    public final static String FEED_REWD_CLOSE = "08";     // 关闭喂食[奖励喂食]
    public final static String FEED_SUPP_OPEN = "09";    // 开启喂食[补偿喂食]
    public final static String FEED_SUPP_CLOSE = "10";    // 关闭喂食[补偿喂食]


    public final static String BOX_HEART_BEAT = "99";   //心跳


//    public final static String BEEP_OPEN = "09";     // 开启蜂鸣器
//    public final static String BEEP_CLOSE = "10";    // 关闭蜂鸣器

    // 操作队列
    public static Queue<String> queueOper;

    // 用于判断是否下位机操作成功
    public static Set<String> setOper;

    static{
        queueOper = new LinkedBlockingQueue<>();
        setOper = new LinkedHashSet<>();
    }

    public static boolean addQueueOper(String bbid,String oper){
        String message = "*-R"+bbid+":"+oper;// 通知所有下位机
        boolean add = setOper.add(bbid + ":" + oper);
        boolean add1 = queueOper.add(message);

        return true;

//        if (add && add1){
//            return true;
//        }else{
////            setOper.remove(bbid+":"+oper);
//            queueOper.remove(errorMessage);
//            return false;
//        }
    }

    /**
     * 判断操作是否存在表中  若存在则操作失败. 不存在说明操作成功
     * @param bbid
     * @param oper
     * @return
     */
    public static boolean isOperExists(String bbid,String oper){
        String message = bbid + ":" + oper;
        return setOper.contains(message);
    }
}
