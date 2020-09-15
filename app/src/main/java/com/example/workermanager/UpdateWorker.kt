package com.example.workermanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UpdateWorker(ctx: Context, param: WorkerParameters) : Worker(ctx, param) {
    companion object {
        const val KEY_WORKER = "232"
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            var count = inputData.getInt(MainActivity.KEY, 0)
            for (i in 0..count) {
                Log.e("TAG", "Uploading $i")
            }
            var time = SimpleDateFormat("hh:mm:ss")
            var currentTime = time.format(Date())
            var outPutData = Data.Builder().put(KEY_WORKER, currentTime).build()
            Result.success(outPutData)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}