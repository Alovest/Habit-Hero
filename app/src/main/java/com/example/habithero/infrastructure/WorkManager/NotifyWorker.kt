package com.example.habithero.infrastructure.WorkManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextParams
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.habithero.R
import io.reactivex.Scheduler

class NotifyWorker(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {
    private val channelId = "notify_channel"

    override fun doWork(): Result {
        ShowNotification()
        return Result.success()
    }

    private fun ShowNotification(){
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "Уведомления приложения",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            nm.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Не забудьте совершать ежедневную привычку!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        nm.notify(1, notification)
    }
}