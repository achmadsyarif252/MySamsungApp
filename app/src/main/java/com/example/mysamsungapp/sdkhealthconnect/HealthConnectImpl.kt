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

    //    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }
    private lateinit var healthConnectClient: HealthConnectClient
    fun initContext(context: Context) {
        this.context = context
    }

    override fun isProviderAvailable() {
        if (HealthConnectClient.isAvailable(context)) {
            // Health Connect is available and installed.
            healthConnectClient = HealthConnectClient.getOrCreate(context)
            callback.onAvailableHC(true)
        } else {
            callback.onAvailableHC(false)
        }

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

    override suspend fun readCervicalMucus(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = CervicalMucusRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveCervicalMucus(response.records)
    }

    override suspend fun writeCervicalMucus(cervicalMucusRecord: List<CervicalMucusRecord>) {
        healthConnectClient.insertRecords(cervicalMucusRecord)
    }

    override suspend fun readCyclingPedaling(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = CyclingPedalingCadenceRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveCyclingPedalingCadence(response.records)
    }

    override suspend fun writeCyclingPedaling(cyclingPedalingCadenceRecord: List<CyclingPedalingCadenceRecord>) {
        healthConnectClient.insertRecords(cyclingPedalingCadenceRecord)
    }

    override suspend fun readDistance(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = DistanceRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveDistance(response.records)
    }

    override suspend fun writeDistance(distanceRecord: List<DistanceRecord>) {
        healthConnectClient.insertRecords(distanceRecord)
    }

    override suspend fun readElevationGained(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = ElevationGainedRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveElevationGained(response.records)
    }

    override suspend fun writeElevationGained(elevationGainedRecord: List<ElevationGainedRecord>) {
        healthConnectClient.insertRecords(elevationGainedRecord)
    }

    override suspend fun readExerciseSession(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = ExerciseSessionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveExerciseSession(response.records)
    }

    override suspend fun writeExerciseSession(exerciseSessionRecord: List<ExerciseSessionRecord>) {
        healthConnectClient.insertRecords(exerciseSessionRecord)
    }

    override suspend fun readFloorsClimbed(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = FloorsClimbedRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveFloorsClimbed(response.records)
    }

    override suspend fun writeFloorsClimbed(floorsClimbedRecord: List<FloorsClimbedRecord>) {
        healthConnectClient.insertRecords(floorsClimbedRecord)
    }

    override suspend fun writeHeight(heightRecord: List<HeightRecord>) {
        healthConnectClient.insertRecords(heightRecord)
    }

    override suspend fun readHeight(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = HeightRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveHeight(response.records)
    }

    override suspend fun readHydration(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = HydrationRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveHydration(response.records)
    }

    override suspend fun writeHydration(hydrationRecord: List<HydrationRecord>) {
        healthConnectClient.insertRecords(hydrationRecord)
    }

    override suspend fun readLeanBodyMass(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = LeanBodyMassRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveLeanBodyMass(response.records)
    }

    override suspend fun writeLeanBodyMass(leanBodyMassRecord: List<LeanBodyMassRecord>) {
        healthConnectClient.insertRecords(leanBodyMassRecord)
    }

    override suspend fun readMenstruationFlow(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = MenstruationFlowRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveMenstruationFLow(response.records)
    }

    override suspend fun writeMenstruationFlow(menstruationFlowRecord: List<MenstruationFlowRecord>) {
        healthConnectClient.insertRecords(menstruationFlowRecord)
    }

    override suspend fun readNutrition(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = NutritionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveNutrition(response.records)
    }

    override suspend fun writeNutrition(nutritionRecord: List<NutritionRecord>) {
        healthConnectClient.insertRecords(nutritionRecord)
    }

    override suspend fun readOvulationTest(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = OvulationTestRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveOvulationTest(response.records)
    }

    override suspend fun writeOvulationTest(ovulationTestRecord: List<OvulationTestRecord>) {
        healthConnectClient.insertRecords(ovulationTestRecord)
    }

    override suspend fun readOxygenSaturation(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = OxygenSaturationRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveOxygenSaturation(response.records)
    }

    override suspend fun writeOxygenSaturation(oxygenSaturationRecord: List<OxygenSaturationRecord>) {
        healthConnectClient.insertRecords(oxygenSaturationRecord)
    }

    override suspend fun readPower(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = PowerRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceivePower(response.records)
    }

    override suspend fun writePower(powerRecord: List<PowerRecord>) {
        healthConnectClient.insertRecords(powerRecord)
    }

    override suspend fun readRespirationRate(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = RespiratoryRateRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveRespirationRate(response.records)
    }

    override suspend fun writeRespirationRate(respiratoryRateRecord: List<RespiratoryRateRecord>) {
        healthConnectClient.insertRecords(respiratoryRateRecord)
    }

    override suspend fun readRestingHeartRate(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = RestingHeartRateRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveRestingHearRate(response.records)
    }

    override suspend fun writeRestingHeartRate(restingHeartRateRecord: List<RestingHeartRateRecord>) {
        healthConnectClient.insertRecords(restingHeartRateRecord)
    }

    override suspend fun readSexualActivity(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = SexualActivityRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveSexualActivity(response.records)
    }

    override suspend fun writeSexualActivity(sexualActivityRecord: List<SexualActivityRecord>) {
        healthConnectClient.insertRecords(sexualActivityRecord)
    }

    override suspend fun readSleepSession(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = SleepSessionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveSleepSession(response.records)
    }

    override suspend fun writeSleepSession(sleepSessionRecord: List<SleepSessionRecord>) {
        healthConnectClient.insertRecords(sleepSessionRecord)
    }

    override suspend fun readSleepStage(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = SleepStageRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveSleepStage(response.records)
    }

    override suspend fun writeSleepStage(sleepStageRecord: List<SleepStageRecord>) {
        healthConnectClient.insertRecords(sleepStageRecord)
    }

    override suspend fun writeSpeed(speedRecord: List<SpeedRecord>) {
        healthConnectClient.insertRecords(speedRecord)
    }

    override suspend fun readSpeed(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = SpeedRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveSpeed(response.records)
    }

    override suspend fun readStepCadence(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = StepsCadenceRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveStepsCadence(response.records)
    }

    override suspend fun writeStepCadence(stepsCadenceRecord: List<StepsCadenceRecord>) {
        healthConnectClient.insertRecords(stepsCadenceRecord)
    }

    override suspend fun readSteps(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = StepsRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveStep(response.records)
    }

    override suspend fun writeSteps(stepsRecord: List<StepsRecord>) {
        healthConnectClient.insertRecords(stepsRecord)
    }

    override suspend fun readTotalCaloriesBurned(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = TotalCaloriesBurnedRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveTotalCaloriesBurned(response.records)
    }

    override suspend fun writeTotalCaloriesBurned(totalCaloriesBurnedRecord: List<TotalCaloriesBurnedRecord>) {
        healthConnectClient.insertRecords(totalCaloriesBurnedRecord)
    }

    override suspend fun readVo2Max(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = Vo2MaxRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveVo2Max(response.records)
    }

    override suspend fun writeVo2Max(vo2MaxRecord: List<Vo2MaxRecord>) {
        healthConnectClient.insertRecords(vo2MaxRecord)
    }

    override suspend fun readWeight(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = WeightRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveWeight(response.records)
    }

    override suspend fun writeWeight(weightRecord: List<WeightRecord>) {
        healthConnectClient.insertRecords(weightRecord)
    }

    override suspend fun readWheelChairPushes(start: Instant, end: Instant) {
        val request = ReadRecordsRequest(
            recordType = WheelchairPushesRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        callback.onReceiveWheelChairPushes(response.records)
    }

    override suspend fun writeWheelChairPushes(wheelchairPushesRecord: List<WheelchairPushesRecord>) {
        healthConnectClient.insertRecords(wheelchairPushesRecord)
    }

}