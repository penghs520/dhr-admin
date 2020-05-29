package com.qinjee.admin.utils;


import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.httpclient.HTTPException;
import com.github.qcloudsms.httpclient.PoolingHTTPClient;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public class SendMessageUtil {

    public static void sendSingleMessage(int appid, String appkey, int templateId, String smsSign, String phone, String[] params)  {

        SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
        new Thread(()->{
            try {
                singleSender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
