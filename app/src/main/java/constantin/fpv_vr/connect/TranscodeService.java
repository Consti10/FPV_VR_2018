package constantin.fpv_vr.connect;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.concurrent.FutureTask;

import constantin.fpv_vr.R;

public class TranscodeService extends Service {
    public static final String INPUT_EXTRA="INPUT_EXTRA";
    public static final String NOTIFICATION_CHANNEL_ID = "TranscodeServiceChannel";
    public static final int NOTIFICATION_ID=1;
    private long p=0;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Bitmap bmp=Bitmap.createBitmap(128,128,Bitmap.Config.RGB_565);
        bmp.eraseColor(Color.BLUE);
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Transcoder Service")
                .setContentText("Holla")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bmp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra(INPUT_EXTRA);
        //Intent notificationIntent = new Intent(this, AMain.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this,
        //        0, notificationIntent, 0);


        //do heavy work on a background thread
        //p= SimpleEncoder.nativeStartConvertFile(UVCReceiverDecoder.getDirectoryToSaveDataTo());
        //stopSelf();
        return START_NOT_STICKY;
    }




    public void lol(){
        System.out.println("LOL");
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FPV-VR";
            String description = "Transcoder";
            NotificationChannel serviceChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name,
                    NotificationManager.IMPORTANCE_DEFAULT);
            serviceChannel.setDescription(description);
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager!=null;
            manager.createNotificationChannel(serviceChannel);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
