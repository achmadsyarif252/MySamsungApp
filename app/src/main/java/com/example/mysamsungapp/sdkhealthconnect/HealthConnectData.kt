package com.example.mysamsungapp.sdkhealthconnect

import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.records.HeartRateRecord
import java.time.Instant
import java.time.ZonedDateTime

interface HealthConnectData {
    suspend fun readHeartRecord(start: Instant, end: Instant)
    suspend fun writeHeartRate(
        start: ZonedDateTime,
        end: ZonedDateTime,
        sample: List<HeartRateRecord.Sample>
    )

    suspend fun readActiveCaloriesBurned(start: Instant, end: Instant)
    suspend fun writeActiveCaloriesBurned(
        caloriesBurned: List<ActiveCaloriesBurnedRecord>
    )

    suspend fun readBasalBodyTempt(start: Instant, end: Instant)
    suspend fun writeBasalBodyTempt(basalBodyTemperatureRecord: List<BasalBodyTemperatureRecord>)

}