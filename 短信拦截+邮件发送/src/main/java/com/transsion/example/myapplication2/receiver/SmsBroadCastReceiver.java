package com.transsion.example.myapplication2.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.transsion.example.myapplication2.service.IntentService_SendEmil;
import com.transsion.example.myapplication2.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dongrp on 2016/6/21.
 * 自定义广播接收器：监听SMS_RECEIVED广播，然后开启IntentService_SendEmil服务
 */
public class SmsBroadCastReceiver extends BroadcastReceiver {
    static String ACTION_SMS = "android.provider.Telephony.SMS_RECEIVED";
    String stringMsg; //msg中的各种信息,最终拼接成的字符串

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SMS)) {
            ToastUtil.showToast(context, "监听到了");
            Bundle bundle = intent.getExtras();
            SmsMessage msg; //bundle中的携带的 msg对象
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object p : pdusObj) {
                    msg = SmsMessage.createFromPdu((byte[]) p);
                    //msg的内容
                    String msgTxt = msg.getMessageBody();
                    //msg发送时间
                    Date date = new Date(msg.getTimestampMillis());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String receiveTime = format.format(date);
                    //msg发送号码
                    String senderNumber = msg.getOriginatingAddress();
                    //拼接msg中的各种信息
                    stringMsg = "发送人：" + senderNumber + "  短信内容：" + msgTxt + "  接收时间：" + receiveTime;
                    ToastUtil.showToast(context, stringMsg);
                }
            }

            //携带stringMsg跳转到SMSActivity
            Intent mIntent = new Intent(context, IntentService_SendEmil.class);
            mIntent.putExtra("stringMsg",stringMsg);
            context.startService(mIntent);
        }
    }
}

