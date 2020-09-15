package com.example.workermanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressingWorker(ctx: Context, param: WorkerParameters) : Worker(ctx, param) {
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            for (i in 0..300) {
                Log.e("TAG", "Compressing: $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}