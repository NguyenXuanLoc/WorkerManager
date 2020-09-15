package com.example.workermanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(ctx: Context, param: WorkerParameters) : Worker(ctx, param) {
    override fun doWork(): Result {
        return try {
            for (i in 0..3000) {
                Log.e("TAG", "MUTIL   DOWNLOADING: $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

}