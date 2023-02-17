package com.example.mysamsungapp.sdkhealthconnect

import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.HeartRateRecord

interface HealthConnectCallback {
    fun onReceiveHRData(heartRateRecord: List<HeartRateRecord>)
    fun onReceiveCaloriesBurned(caloriesBurnedRecord: List<ActiveCaloriesBurnedRecord>)
}