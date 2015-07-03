package com.lifeistech.android.origin9;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by takashi on 2015/07/03.
 */
public class Notifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context content ,Intent intent){
        //通知がクリックされたときｎ発行されるintentの生成
        Intent sendIntent = new Intent(content,MainActivity.class);
        PendingIntent sender = PendingIntent.getActivity(content,0,sendIntent,0);

        //通知オブジェクトの作成
        Notification notification = new NotificationCompat.Builder(content)
                .setTicker("時間ですよ!")
                .setContentTitle("通知")
                .setContentText("設定した時間が来ました")
                .setSmallIcon(R.drawable.ic_launcher)
                .setVibrate(new long[]{0, 200, 100, 200, 100, 200})
                .setAutoCancel(true)
                .setContentIntent(sender)
                .build();

        NotificationManager manager = (NotificationManager)content.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0,notification);



        }


    }

