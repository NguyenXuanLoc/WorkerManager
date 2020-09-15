package com.example.workermanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY = "232"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTimeWorkRequest()
    }

    @SuppressLint("RestrictedApi")
    private fun setTimeWorkRequest() {
        // set điều kiện để chaỵ
        var data: Data = Data.Builder().put(KEY, 55555).build()
        var constrains = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED).build()
        var workManager = WorkManager.getInstance(this)
        var uploadRequest = OneTimeWorkRequest.Builder(UpdateWorker::class.java)
                .setConstraints(constrains)
                .setInputData(data).build()
        var filterRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()
        var compresingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java).build()
        var downLoadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        /*WorkManager.getInstance(this).enqueue(uploadRequest)*/
        var paralleWork = mutableListOf<OneTimeWorkRequest>()
        paralleWork.add(downLoadRequest)
        paralleWork.add(filterRequest)

        workManager.beginWith(paralleWork).then(compresingRequest).then(uploadRequest).enqueue()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {
            lblResult.text = it.state.name
            if (it.state.isFinished) {
                val data = it.outputData
                lblResult.text = data.getString(UpdateWorker.KEY_WORKER)
            }
        })
    }
}