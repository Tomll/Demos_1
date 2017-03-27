package com.transsion.example.myapplication2.service;

import android.app.IntentService;
import android.content.Intent;

import com.transsion.example.myapplication2.activity.SMSActivity;

/**
 * Created by dongrp on 2016/6/21.
 * 自定义IntentService：用于将SmsBroadCastReceiver监听到的短信 发送到指定的邮箱
 */
public class IntentService_SendEmil extends IntentService {

    public IntentService_SendEmil() {
        //必须实现父类的构造方法
        super("IntentService_SendEmil");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从SmsBroadCastReceiver发过来的
        Intent startIntent = new Intent(getApplicationContext(), SMSActivity.class); //接收到广播后，跳转到MainActivity
        startIntent.putExtra("stringMsg",intent.getStringExtra("stringMsg"));
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(startIntent);


//        try {
//            MailSenderInfo mailInfo = new MailSenderInfo();
//            mailInfo.setMailServerHost("smtp.qq.com");
//            mailInfo.setMailServerPort("25");
//            mailInfo.setValidate(true);
//            mailInfo.setUserName("");  //你的邮箱地址
//            mailInfo.setPassword("");//您的邮箱密码
//            mailInfo.setFromAddress("");
//            mailInfo.setToAddress("");
//            mailInfo.setSubject("拦截时间： " + new Date(System.currentTimeMillis()).toString());
//            mailInfo.setContent(intent.getStringExtra("stringMsg"));
//            //这个类主要来发送邮件
//            SimpleMailSender sms = new SimpleMailSender();
//            sms.sendTextMail(mailInfo);//发送文体格式
//            //sms.sendHtmlMail(mailInfo);//发送html格式
//        } catch (Exception e) {
//            Log.e("SendMail", e.getMessage(), e);
//        }

    }

}
