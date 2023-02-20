package com.example.mysamsungapp.sdkhealthconnect

import androidx.health.connect.client.records.*
import java.time.Instant
import java.time.ZonedDateTime

interface HealthConnectData {
     fun isProviderAvailable()
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
    suspend fun writeBloodGlucose(bloodGlucoseRecord: List<BloodGlucoseRecord>)
    suspend fun readBloodPressure(start: Instant, end: Instant)
    suspend fun writeBloodPressure(bloodPressureRecord: List<BloodPressureRecord>)
    suspend fun readBodyFat(start: Instant, end: Instant)
    suspend fun writeBodyFat(bodyFatRecord: List<BodyFatRecord>)
    suspend fun readBodyTemper(start: Instant, end: Instant)
    suspend fun writeBodyTemper(bodyTemper: List<BodyTemperatureRecord>)
    suspend fun readBoneMass(start: Instant, end: Instant)
    suspend fun writeBoneMass(boneMassRecord: List<BoneMassRecord>)
    suspend fun readCervicalMucus(start: Instant, end: Instant)
    suspend fun writeCervicalMucus(cervicalMucusRecord: List<CervicalMucusRecord>)
    suspend fun readCyclingPedaling(start: Instant, end: Instant)
    suspend fun writeCyclingPedaling(cyclingPedalingCadenceRecord: List<CyclingPedalingCadenceRecord>)
    suspend fun readDistance(start: Instant, end: Instant)
    suspend fun writeDistance(distanceRecord: List<DistanceRecord>)
    suspend fun readElevationGained(start: Instant, end: Instant)
    suspend fun writeElevationGained(elevationGainedRecord: List<ElevationGainedRecord>)
    suspend fun readExerciseSession(start: Instant, end: Instant)
    suspend fun writeExerciseSession(exerciseSessionRecord: List<ExerciseSessionRecord>)
    suspend fun readFloorsClimbed(start: Instant, end: Instant)
    suspend fun writeFloorsClimbed(floorsClimbedRecord: List<FloorsClimbedRecord>)
    suspend fun writeHeight(heightRecord: List<HeightRecord>)
    suspend fun readHeight(start: Instant, end: Instant)
    suspend fun readHydration(start: Instant, end: Instant)
    suspend fun writeHydration(hydrationRecord: List<HydrationRecord>)
    suspend fun readLeanBodyMass(start: Instant, end: Instant)
    suspend fun writeLeanBodyMass(leanBodyMassRecord: List<LeanBodyMassRecord>)
    suspend fun readMenstruationFlow(start: Instant, end: Instant)
    suspend fun writeMenstruationFlow(menstruationFlowRecord: List<MenstruationFlowRecord>)
    suspend fun readNutrition(start: Instant, end: Instant)
    suspend fun writeNutrition(nutritionRecord: List<NutritionRecord>)
    suspend fun readOvulationTest(start: Instant, end: Instant)
    suspend fun writeOvulationTest(ovulationTestRecord: List<OvulationTestRecord>)
    suspend fun readOxygenSaturation(start: Instant, end: Instant)
    suspend fun writeOxygenSaturation(oxygenSaturationRecord: List<OxygenSaturationRecord>)
    suspend fun readPower(start: Instant, end: Instant)
    suspend fun writePower(powerRecord: List<PowerRecord>)
    suspend fun readRespirationRate(start: Instant, end: Instant)
    suspend fun writeRespirationRate(respiratoryRateRecord: List<RespiratoryRateRecord>)
    suspend fun readRestingHeartRate(start: Instant, end: Instant)
    suspend fun writeRestingHeartRate(restingHeartRateRecord: List<RestingHeartRateRecord>)
    suspend fun readSexualActivity(start: Instant, end: Instant)
    suspend fun writeSexualActivity(sexualActivityRecord: List<SexualActivityRecord>)
    suspend fun readSleepSession(start: Instant, end: Instant)
    suspend fun writeSleepSession(sleepSessionRecord: List<SleepSessionRecord>)
    suspend fun readSleepStage(start: Instant, end: Instant)
    suspend fun writeSleepStage(sleepStageRecord: List<SleepStageRecord>)
    suspend fun writeSpeed(speedRecord: List<SpeedRecord>)
    suspend fun readSpeed(start: Instant, end: Instant)
    suspend fun readStepCadence(start: Instant, end: Instant)
    suspend fun writeStepCadence(stepsCadenceRecord: List<StepsCadenceRecord>)
    suspend fun readSteps(start: Instant, end: Instant)
    suspend fun writeSteps(stepsRecord: List<StepsRecord>)

    suspend fun readTotalCaloriesBurned(start: Instant, end: Instant)
    suspend fun writeTotalCaloriesBurned(totalCaloriesBurnedRecord: List<TotalCaloriesBurnedRecord>)
    suspend fun readVo2Max(start: Instant, end: Instant)
    suspend fun writeVo2Max(vo2MaxRecord: List<Vo2MaxRecord>)
    suspend fun readWeight(start: Instant, end: Instant)
    suspend fun writeWeight(weightRecord: List<WeightRecord>)
    suspend fun readWheelChairPushes(start: Instant, end: Instant)
    suspend fun writeWheelChairPushes(wheelchairPushesRecord: List<WheelchairPushesRecord>)
}