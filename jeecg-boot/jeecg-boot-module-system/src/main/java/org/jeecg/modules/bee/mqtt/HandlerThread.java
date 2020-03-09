package org.jeecg.modules.bee.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.mapper.HistoryOperMapper;
import org.jeecg.modules.bee.service.BeeboxInfoService;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.jeecg.modules.bee.service.impl.HistoryOperTransService;
import org.jeecg.modules.bee.socket.QueueUtil;
import org.jeecg.modules.message.websocket.WebSocket;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class HandlerThread implements Runnable {

	private BeeboxInfoService beeboxInfoService = SpringContextUtils.getBean(BeeboxInfoService.class);
	private UserBeeboxService userBeeboxService = SpringContextUtils.getBean(UserBeeboxService.class);
	private HistoryOperTransService historyOperTransService = SpringContextUtils.getBean(HistoryOperTransService.class);
	private ISysUserService sysUserService = SpringContextUtils.getBean(ISysUserService.class);
	private WebSocket webSocket = SpringContextUtils.getBean(WebSocket.class);


	private String topic;
	private String content;

	public HandlerThread(String topic, String content) {
		this.topic = topic;
		this.content = content;
	}

	@Override
	public void run() {

		if (this.content.length() > 0) {
			//最好是用工厂模式创建每个接口对应的处理对象，或调用每个接口对应的处理方法.
			try {
				String cmd = null;
				try {
					JSONObject json = JSON.parseObject(content);
					cmd = json.getString("cmd");
				} catch (Exception e) {
//					e.printStackTrace();
				}
				if (cmd == null){
					return;
				}
				if(topic.startsWith(MqttClientUtil.TOPIC_PREFIX_DEVICE)){
					//从top 获取app 用户的 id
					String username = topic.substring( topic.indexOf("device/") + 7 , topic.lastIndexOf("/client") );
					//来自device的消息
					if ("T".equals(cmd.substring(0,1))){
						String[] data = cmd.split(":");
						String bbid = data[0].substring(1, 5);
						String[] data1 = data[1].split(" ");
						Double temper = null;
						Double weight = null;
						String relay;// 继电器
						Integer doorStatus = null;
						Integer heating = null;
						Integer cleaning = null;
						Integer feeding = null;
						if (data1.length >= 2){
							temper = Double.parseDouble(data1[0]);
							weight = Double.parseDouble(data1[1])*1000;
							relay = data1[2];
							doorStatus = Integer.parseInt(relay.substring(0,1));
							heating = Integer.parseInt(relay.substring(1,2));
							cleaning = Integer.parseInt(relay.substring(2,3));
							feeding = Integer.parseInt(relay.substring(3,4));
						}
						BeeboxInfo beeboxInfo = new BeeboxInfo();
						beeboxInfo.setBbid(bbid);
						beeboxInfo.setRecvTime(new Date());
						beeboxInfo.setTemper(temper);
						beeboxInfo.setWeight(weight);
						// 插入蜂箱信息表
						beeboxInfoService.insertBeeboxInfo(beeboxInfo);
						// 更新蜂箱状态
						UserBeebox ub = UserBeebox.builder().bbid(bbid).doorStatus(doorStatus).heating(heating).cleaning(cleaning).feeding(feeding).build();
						userBeeboxService.updateUserBeebox(ub);
					}else if("OK".equals(cmd.substring(0,2))){
						String[] split = cmd.split(":");
						String bbid = split[0].substring(2, 6);
						String oper = split[1];

						if (QueueUtil.CLEAN_OPEN.equals(oper)){
							System.out.println(bbid+"开始清洗");
							//ws
							String uid = sysUserService.getUserByName(username).getId();
							if (uid == null){
								throw new RuntimeException("用户名不存在");
							}
							// 更新蜂箱清洗状态
							UserBeebox ub = UserBeebox.builder().bbid(bbid).cleaning(1).build();
							userBeeboxService.updateUserBeebox(ub);

							JSONObject obj = new JSONObject();
							obj.put("cmd", "clean");
							obj.put("userId", uid);
							obj.put("msgId", bbid);
							obj.put("msgCode", 200);
							obj.put("msgTxt", "["+bbid+"]号蜂箱:开始清洗");
							webSocket.sendOneMessage(uid,obj.toJSONString());
						} else if (QueueUtil.FEED_REWD_OPEN.equals(oper) || QueueUtil.FEED_SUPP_OPEN.equals(oper)){
							System.out.println(bbid+"开始喂食");
							// ws
							String uid = sysUserService.getUserByName(username).getId();
							if (uid == null){
								throw new RuntimeException("用户名不存在");
							}
							// 更新蜂箱清洗状态
							UserBeebox ub = UserBeebox.builder().bbid(bbid).feeding(1).build();
							userBeeboxService.updateUserBeebox(ub);

							JSONObject obj = new JSONObject();
							obj.put("cmd", "feed");
							obj.put("userId", uid);
							obj.put("msgId", bbid);
							obj.put("msgCode", 200);
							obj.put("msgTxt", "["+bbid+"]号蜂箱:开始喂食");

							webSocket.sendOneMessage(uid,obj.toJSONString());
						}
						if (QueueUtil.CLEAN_CLOSE.equals(oper)){
							System.out.println("清洗完成");
							historyOperTransService.feedOrCleanComplete(bbid,"clean");
							// ws
							String uid = sysUserService.getUserByName(username).getId();
							if (uid == null){
								throw new RuntimeException("用户名不存在");
							}

							JSONObject obj = new JSONObject();
							obj.put("cmd", "cleanOk");
							obj.put("userId", uid);
							obj.put("msgId", bbid);
							obj.put("msgCode", 200);
							obj.put("msgTxt", "["+bbid+"]号蜂箱:清洗结束");
							webSocket.sendOneMessage(uid,obj.toJSONString());
						}else if (oper.equals(QueueUtil.FEED_REWD_CLOSE) || oper.equals(QueueUtil.FEED_SUPP_CLOSE)){
							System.out.println("喂食完成");
							historyOperTransService.feedOrCleanComplete(bbid,"feed");
							//ws
							String uid = sysUserService.getUserByName(username).getId();
							if (uid == null){
								throw new RuntimeException("用户名不存在");
							}

							JSONObject obj = new JSONObject();
							obj.put("cmd", "feedOk");
							obj.put("userId", uid);
							obj.put("msgId", bbid);
							obj.put("msgCode", 200);
							obj.put("msgTxt", "["+bbid+"]号蜂箱:喂食结束");
							webSocket.sendOneMessage(uid,obj.toJSONString());
						}
						QueueUtil.setOper.remove(bbid + ":" + oper);
					}
					else if("aaaaaaaaaaaaaa".equals(cmd)){ // TODO: 心跳[99]
						//从topic中 获取device 用户的 id
						String id = topic.substring( topic.indexOf("device/") + 7 , topic.lastIndexOf("/client") );
						//获取 发送给指定id的app用户的topic
						String topic = MqttClientUtil.getDevicePublishTopic(id);
						//要发送消息的内容
						JSONObject content = new JSONObject();
						content.put("cmd",cmd+"/response");
						content.put("ret","0");
						content.put("msg","测试消息已经处成功处理");
						//发布消息
						MqttClientUtil.publish_common(topic,content.toString());
					}
				}else if(topic.startsWith(MqttClientUtil.TOPIC_PREFIX_APP)){
					//来自app的消息
					if("/test".equals(cmd)){
						//从top 获取app 用户的 id
						String id = topic.substring( topic.indexOf("app/") + 4 , topic.lastIndexOf("/client") );

						//获取 发送给 指定id 的app用户的topic
						String topic = MqttClientUtil.getAppPublishTopic(id);

						//要发送消息的内容
						JSONObject content = new JSONObject();
						content.put("cmd",cmd+"/response");
						content.put("ret","0");
						content.put("msg","测试消息已经处成功处理");

						//发布消息
						MqttClientUtil.publish_common(topic,content.toString());
					}
				}
			} catch (Throwable e) {
				System.out.println("mqtt HandlerThread run");
				e.printStackTrace();
			}
		}
	}

}