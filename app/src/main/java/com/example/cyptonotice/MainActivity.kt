package com.example.cyptonotice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.cyptonotice.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "com.example.sample_notification_channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "sample_notification_channel"
        const val NOTIFICATION_CHANNEL_DESCRIPTION = "This is the sample notification!"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        pushBtn.setOnClickListener {
            notificationAlerm()
        }
    }

    fun notificationAlerm() {
        val channelId = NOTIFICATION_CHANNEL_ID
        val channelName = NOTIFICATION_CHANNEL_NAME
        val channelDescription = NOTIFICATION_CHANNEL_DESCRIPTION

        //Android 8.0 以上ではアプリの通知チャンネルを登録することが必要。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT //---*1
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        //通知をシステムに登録しています。
        val builder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.tanukigolf) //---*2
            setContentTitle("通知のタイトル")
            setContentText("通知するメッセージ")
            priority = NotificationCompat.PRIORITY_DEFAULT //---*3
        }
        val id = 0 //---*4
        NotificationManagerCompat.from(this).notify(id, builder.build())
    }
}