package com.example.mysamsungapp.sdkhealthconnect

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.records.HeartRateRecord
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

}