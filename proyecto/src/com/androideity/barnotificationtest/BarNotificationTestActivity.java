package com.androideity.barnotificationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Button;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

public class BarNotificationTestActivity extends Activity {
	
	private final int NOTIFICATION_ID = 1010;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn = (Button)findViewById(R.id.btn_notification);
        
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        triggerNotification();
                    }
                };
                timer.schedule(timerTask, 3000);
            }
        });
    }
    
    private void triggerNotification(){
    	
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.androideity, "¡Nuevo mensaje!", System.currentTimeMillis());
        
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_layout);
        contentView.setImageViewResource(R.id.img_notification, R.drawable.androideity);
        contentView.setTextViewText(R.id.txt_notification, "Ey mundo! Esta es mi notificación personalizada.");
        
        notification.contentView = contentView;
        
        Intent notificationIntent = new Intent(this, BarNotificationTestActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;
        
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}