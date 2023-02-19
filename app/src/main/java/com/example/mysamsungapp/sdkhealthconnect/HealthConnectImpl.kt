package com.example.mysamsungapp.sdkhealthconnect

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.*
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import java.time.ZonedDateTime
import java.time.Instant


class HealthConnectImpl(private val callback: HealthConnectCallback) :
    HealthConnectData {
    private lateinit var context: Context

    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    fun initContext(context: Context) {
        this.context = context
    }

    override suspend fun readHeartRecord(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = HeartRateRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveHRData(response.records)
    }


    override suspend fun writeHeartRate(
        start: ZonedDateTime,
        end: ZonedDateTime,
        sample: List<HeartRateRecord.Sample>
    ) {
        healthConnectClient.insertRecords(
            listOf(
                HeartRateRecord(
                    startTime = start.toInstant(),
                    endTime = end.toInstant(),
                    samples = sample,
                    startZoneOffset = start.offset,
                    endZoneOffset = end.offset,
                )
            )
        )

    }

    override suspend fun readActiveCaloriesBurned(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = ActiveCaloriesBurnedRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveCaloriesBurned(response.records)
    }

    override suspend fun writeActiveCaloriesBurned(
        caloriesBurned: List<ActiveCaloriesBurnedRecord>
    ) {
        healthConnectClient.insertRecords(
            caloriesBurned
        )
    }

    override suspend fun readBasalBodyTempt(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BasalBodyTemperatureRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceivedBasalBodyTempt(response.records)
    }

    override suspend fun writeBasalBodyTempt(bodyTemperatureRecord: List<BasalBodyTemperatureRecord>) {
        healthConnectClient.insertRecords(
            bodyTemperatureRecord
        )
    }

    override suspend fun readBasalMetabolicRate(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BasalMetabolicRateRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceivedBasalMetabolicrate(response.records)
    }

    override suspend fun writeBasalMetabolicRate(basalMetabolicRateRecord: List<BasalMetabolicRateRecord>) {
        healthConnectClient.insertRecords(
            basalMetabolicRateRecord
        )
    }

    override suspend fun readBloodGlucose(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BloodGlucoseRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveBloodGlucose(response.records)
    }

    override suspend fun writeBloodGlucose(bloodGlucoseRecord: List<BloodGlucoseRecord>) {
        healthConnectClient.insertRecords(
            bloodGlucoseRecord
        )
    }

    override suspend fun readBloodPressure(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BloodPressureRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveBloodPressure(response.records)
    }

    override suspend fun writeBloodPressure(bloodPressureRecord: List<BloodPressureRecord>) {
        healthConnectClient.insertRecords(bloodPressureRecord)
    }

    override suspend fun readBodyFat(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BodyFatRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveBodyFat(response.records)
    }

    override suspend fun writeBodyFat(bodyFatRecord: List<BodyFatRecord>) {
        healthConnectClient.insertRecords(bodyFatRecord)
    }

    override suspend fun readBodyTemper(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BodyTemperatureRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceivedBodyTemper(response.records)
    }

    override suspend fun writeBodyTemper(bodyTemper: List<BodyTemperatureRecord>) {
        healthConnectClient.insertRecords(bodyTemper)
    }

    override suspend fun readBoneMass(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = BoneMassRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveBoneMass(response.records)
    }

    override suspend fun writeBoneMass(boneMassRecord: List<BoneMassRecord>) {
        healthConnectClient.insertRecords(boneMassRecord)
    }

}