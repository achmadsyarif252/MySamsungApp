package com.example.mysamsungapp.sdkhealthconnect

import androidx.health.connect.client.records.*
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
    suspend fun readBasalMetabolicRate(start: Instant, end: Instant)
    suspend fun writeBasalMetabolicRate(
        basalMetabolicRateRecord: List<BasalMetabolicRateRecord>
    )

    suspend fun readBloodGlucose(start: Instant, end: Instant)
    suspend fun writeBloogGlucose(bloodGlucoseRecord: List<BloodGlucoseRecord>)

}