package com.example.mysamsungapp.sdkhealthconnect

import androidx.health.connect.client.records.*

interface HealthConnectCallback {
    fun onReceiveHRData(heartRateRecord: List<HeartRateRecord>)
    fun onReceiveCaloriesBurned(caloriesBurnedRecord: List<ActiveCaloriesBurnedRecord>)
    fun onReceivedBasalBodyTempt(basalBodyTempRecord: List<BasalBodyTemperatureRecord>)
    fun onReceivedBasalMetabolicrate(basalMetabolicRateRecord: List<BasalMetabolicRateRecord>)
    fun onReceiveBloodGlucose(bloodGlucoseRecord: List<BloodGlucoseRecord>)
}