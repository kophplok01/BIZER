package tr.xip.unimarnf.Helpers;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;

import tr.xip.unimarnf.R;


public class AlertHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private MediaSessionCompat mediaSession;

    private NotificationManager mManager;

    public AlertHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.logoof);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                       .setSmallIcon(R.drawable.ic_carreraas_24dp)
                        .setColor(getResources().getColor(R.color.tab_indicator_gravy))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setLargeIcon(artwork);

    }
}
