package uz.itech.onlineshoping.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import uz.itech.onlineshoping.BuildConfig
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.screen.MainActivity
import uz.itech.onlineshoping.utils.PrefUtils

class AppFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("tag-debug: ", token)
        PrefUtils.setFCMToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        try {
            Log.d("tag-debug: body " , message.notification?.body.toString() )
            Log.d("tag-debug: title " , message.notification?.title.toString() )
            val body= message.notification?.body
            val title= message.notification?.title
            showMessage(
                title?:"",
                body?:""
            )
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun showMessage(title: String, body: String, id: Long=System.currentTimeMillis()) {
        val defaultSoundUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var intent= Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = BuildConfig.APPLICATION_ID
        val builder=
            NotificationCompat.Builder(this, channelId)
                .setDefaults(DEFAULT_CONCURRENCY)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_launcher_foreground))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setColor(Color.parseColor("FFFFFFFF"))
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setContentIntent(pendingIntent)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                " ${BuildConfig.APPLICATION_ID} channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify(id.toInt(), builder.build())


    }
}