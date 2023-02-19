package com.example.mysamsungapp.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamsungapp.sdkhealthconnect.HealthConnectCallback
import com.example.mysamsungapp.sdkhealthconnect.HealthConnectImpl
import java.time.ZonedDateTime

class HomeViewModel : ViewModel() {
    private var _heartRateRecord = MutableLiveData<List<HeartRateRecord>>()
    val heartRateRecord: LiveData<List<HeartRateRecord>> = _heartRateRecord

    private var _activeCaloriesBurnedRecord = MutableLiveData<List<ActiveCaloriesBurnedRecord>>()
    val activeCaloriesBurnedRecord: LiveData<List<ActiveCaloriesBurnedRecord>> =
        _activeCaloriesBurnedRecord

    private var _basalBodyTemptRecord = MutableLiveData<List<BasalBodyTemperatureRecord>>()
    val bodyBasalBodyTemperatureRecord: LiveData<List<BasalBodyTemperatureRecord>> =
        _basalBodyTemptRecord

    fun initContext(context: Context) {
        healthSDK.initContext(context = context)
    }

    private val healthSDK =
        HealthConnectImpl(object : HealthConnectCallback {
            override fun onReceiveHRData(heartRateRecord: List<HeartRateRecord>) {
                _heartRateRecord.value = heartRateRecord
            }

            override fun onReceiveCaloriesBurned(caloriesBurnedRecord: List<ActiveCaloriesBurnedRecord>) {
                _activeCaloriesBurnedRecord.value = caloriesBurnedRecord
            }

            override fun onReceivedBasalBodyTempt(basalBodyTempRecord: List<BasalBodyTemperatureRecord>) {
                _basalBodyTemptRecord.value = basalBodyTempRecord
            }
        })


    suspend fun readHeartRecord(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readHeartRecord(start.toInstant(), end.toInstant())
    }

    suspend fun writeHeartRecord(
        start: ZonedDateTime,
        end: ZonedDateTime,
        sample: List<HeartRateRecord.Sample>
    ) {
        healthSDK.writeHeartRate(start, end, sample)
    }

    suspend fun readActiveCaloriesBurnedRecord(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readActiveCaloriesBurned(start.toInstant(), end.toInstant())
    }

    suspend fun writeActiveCaloriesBurnedRecord(
        data: List<ActiveCaloriesBurnedRecord>
    ) {
        healthSDK.writeActiveCaloriesBurned(data)
    }

    suspend fun readBasalBodyTempt(start: ZonedDateTime, end: ZonedDateTime) {
        Log.d("HomeViewModel", "readBasalBodyTempt: errorr")
        healthSDK.readBasalBodyTempt(start.toInstant(), end.toInstant())
    }

    suspend fun writeBasalBodyTemptRecord(
        data: List<BasalBodyTemperatureRecord>
    ) {
        healthSDK.writeBasalBodyTempt(data)
    }


}