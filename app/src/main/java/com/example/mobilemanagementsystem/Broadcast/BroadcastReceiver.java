package com.example.mobilemanagementsystem.Broadcast;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.mobilemanagementsystem.Notification.Channel;
import com.example.mobilemanagementsystem.R;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    private NotificationManagerCompat notificationManagerCompat;
    Context mcontext;

    public BroadcastReceiver(Context mcontext){
        this.mcontext=mcontext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean noConnectivity;
        notificationManagerCompat = NotificationManagerCompat.from(mcontext);

                notification1();

                notification2();



        }




    private void notification1() {
        Notification notification=new NotificationCompat.Builder(mcontext, Channel.channel_1)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Product ordering")
                .setContentText("10% dsicount")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(1,notification);
    }

    private void notification2()
    {
        Notification notification=new NotificationCompat.Builder(mcontext, Channel.channel_2)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Product ordering")
                .setContentText("Grab your favouritefood")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(2,notification);

    }
}
