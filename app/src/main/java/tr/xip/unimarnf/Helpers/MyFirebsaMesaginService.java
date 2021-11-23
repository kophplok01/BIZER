package tr.xip.unimarnf.Helpers;

import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import tr.xip.unimarnf.R;

public class MyFirebsaMesaginService extends FirebaseMessagingService {



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        RemoteViews ssCollapsed = new RemoteViews(getPackageName(),R.layout.notification_collapsed);
        RemoteViews ssExtended = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        if(remoteMessage.getNotification()!=null){
            //String tittle = remoteMessage.getNotification().getTitle();
            ssCollapsed.setTextViewText(R.id.textView_Collapsed_1, remoteMessage.getNotification().getTitle());
            ssCollapsed.setTextViewText(R.id.textView_Collapsed_2, remoteMessage.getNotification().getBody());
            ssExtended.setTextViewText(R.id.textView_Collapsed_1, remoteMessage.getNotification().getTitle());
            ssExtended.setTextViewText(R.id.textView_Collapsed_2, remoteMessage.getNotification().getBody());


            NotificationHelper.displayNotification(getApplicationContext(), ssExtended, ssCollapsed);

        }
    }
}
