package attendance.com.pe.attendance;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String LOGTAG = "android-fcm";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            String titulo = remoteMessage.getNotification().getTitle();
            String texto = remoteMessage.getNotification().getBody();

            Log.d(LOGTAG, "PNS::: NOTIFICACION RECIBIDA");
            Log.d(LOGTAG, "PNS::: Título: " + titulo);
            Log.d(LOGTAG, "PNS::: Texto: " + texto);

            //Opcional: mostramos la notificación en la barra de estado
            showNotification(titulo, texto);
        }

        if(remoteMessage.getData() != null) {
            Log.d(LOGTAG, "PNS::: DATOS RECIBIDOS");
            Log.d(LOGTAG, "PNS::: Usuario: " + remoteMessage.getData().get("usuario"));
            Log.d(LOGTAG, "PNS:::  Estado: " + remoteMessage.getData().get("estado"));
        }
    }

    private void showNotification(String title, String text) {

        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        //int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.drawable.myicon: R.mipmap.myicon;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(rawBitmap)  //set it in the notification
                        .setSmallIcon(android.R.drawable.stat_notify_more)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
