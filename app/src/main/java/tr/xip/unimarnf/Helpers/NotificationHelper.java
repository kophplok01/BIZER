package tr.xip.unimarnf.Helpers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import tr.xip.unimarnf.Activities.NavActivity;
import tr.xip.unimarnf.R;

import static tr.xip.unimarnf.Activities.NavActivity.CHANEL_ID;

public class NotificationHelper {



    public static void displayNotification (Context context,  RemoteViews sexp, RemoteViews sCollap){

        Intent intent = new Intent(context, NavActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                100, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );



        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANEL_ID).setSmallIcon(R.drawable.ic_calendario_24dp)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setCustomContentView(sCollap)
                .setCustomBigContentView(sexp)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat mNotificationManagerCompat = NotificationManagerCompat.from(context);

        mNotificationManagerCompat.notify(1, mBuilder.build());


    }
}
