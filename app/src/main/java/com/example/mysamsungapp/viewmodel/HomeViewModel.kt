package com.example.mysamsungapp.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.health.connect.client.records.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamsungapp.sdkhealthconnect.HealthConnectCallback
import com.example.mysamsungapp.sdkhealthconnect.HealthConnectImpl
import java.time.ZonedDateTime

class HomeViewModel : ViewModel() {
    private val _isHCInstalled = MutableLiveData<Boolean>()
    val isHCInstalled: LiveData<Boolean> = _isHCInstalled

    private var _heartRateRecord = MutableLiveData<List<HeartRateRecord>>()
    val heartRateRecord: LiveData<List<HeartRateRecord>> = _heartRateRecord

    private var _activeCaloriesBurnedRecord = MutableLiveData<List<ActiveCaloriesBurnedRecord>>()
    val activeCaloriesBurnedRecord: LiveData<List<ActiveCaloriesBurnedRecord>> =
        _activeCaloriesBurnedRecord

    private var _basalBodyTemptRecord = MutableLiveData<List<BasalBodyTemperatureRecord>>()
    val bodyBasalBodyTemperatureRecord: LiveData<List<BasalBodyTemperatureRecord>> =
        _basalBodyTemptRecord

    private var _basalMetabolicRate = MutableLiveData<List<BasalMetabolicRateRecord>>()
    val basalMetabolicRateRecord: LiveData<List<BasalMetabolicRateRecord>> = _basalMetabolicRate

    private var _bloodGlucose = MutableLiveData<List<BloodGlucoseRecord>>()
    val bloodGlucose: LiveData<List<BloodGlucoseRecord>> = _bloodGlucose

    private var _bloodPressure = MutableLiveData<List<BloodPressureRecord>>()
    val bloodPressureRecord: LiveData<List<BloodPressureRecord>> = _bloodPressure

    private var _bodyFatRecord = MutableLiveData<List<BodyFatRecord>>()
    val bodyFatRecord: LiveData<List<BodyFatRecord>> = _bodyFatRecord

    private var _bodyTemper = MutableLiveData<List<BodyTemperatureRecord>>()
    val bodyTemper: LiveData<List<BodyTemperatureRecord>> = _bodyTemper

    private var _boneMass = MutableLiveData<List<BoneMassRecord>>()
    val boneMass: LiveData<List<BoneMassRecord>> = _boneMass

    private val _cervicalMucus = MutableLiveData<List<CervicalMucusRecord>>()
    val cervicalMucus: LiveData<List<CervicalMucusRecord>> = _cervicalMucus

    private val _cyclingPedaling = MutableLiveData<List<CyclingPedalingCadenceRecord>>()
    val cyclingPedaling: LiveData<List<CyclingPedalingCadenceRecord>> = _cyclingPedaling

    private val _distance = MutableLiveData<List<DistanceRecord>>()
    val distance: LiveData<List<DistanceRecord>> = _distance

    private val _elevationGained = MutableLiveData<List<ElevationGainedRecord>>()
    val elevationGainedRecord: LiveData<List<ElevationGainedRecord>> = _elevationGained

    private val _exerciseSession = MutableLiveData<List<ExerciseSessionRecord>>()
    val exerciseSession: LiveData<List<ExerciseSessionRecord>> = _exerciseSession

    private val _floorsClimbed = MutableLiveData<List<FloorsClimbedRecord>>()
    val floorsClimbedRecord: LiveData<List<FloorsClimbedRecord>> = _floorsClimbed

    private val _height = MutableLiveData<List<HeightRecord>>()
    val height: LiveData<List<HeightRecord>> = _height

    private val _hydration = MutableLiveData<List<HydrationRecord>>()
    val hydration: LiveData<List<HydrationRecord>> = _hydration

    private val _leanBodyMass = MutableLiveData<List<LeanBodyMassRecord>>()
    val leanBodyMass: LiveData<List<LeanBodyMassRecord>> = _leanBodyMass

    private val _menstruationFlow = MutableLiveData<List<MenstruationFlowRecord>>()
    val menstruationFlow: LiveData<List<MenstruationFlowRecord>> = _menstruationFlow

    private val _nutrition = MutableLiveData<List<NutritionRecord>>()
    val nutrition: LiveData<List<NutritionRecord>> = _nutrition

    private val _ovulationTest = MutableLiveData<List<OvulationTestRecord>>()
    val ovulationTest: LiveData<List<OvulationTestRecord>> = _ovulationTest

    private val _oxygenSaturation = MutableLiveData<List<OxygenSaturationRecord>>()
    val oxygenSaturationRecord: LiveData<List<OxygenSaturationRecord>> = _oxygenSaturation

    private val _power = MutableLiveData<List<PowerRecord>>()
    val power: LiveData<List<PowerRecord>> = _power

    private val _respirationRate = MutableLiveData<List<RespiratoryRateRecord>>()
    val respirationRate: LiveData<List<RespiratoryRateRecord>> = _respirationRate

    private val _restingHeartRate = MutableLiveData<List<RestingHeartRateRecord>>()
    val restingHeartRate: LiveData<List<RestingHeartRateRecord>> = _restingHeartRate

    private val _sexualActivity = MutableLiveData<List<SexualActivityRecord>>()
    val sexualActivityRecord: LiveData<List<SexualActivityRecord>> = _sexualActivity

    private val _sleepSession = MutableLiveData<List<SleepSessionRecord>>()
    val sleepSession: LiveData<List<SleepSessionRecord>> = _sleepSession

    private val _sleepStage = MutableLiveData<List<SleepStageRecord>>()
    val sleepStage: LiveData<List<SleepStageRecord>> = _sleepStage

    private val _speed = MutableLiveData<List<SpeedRecord>>()
    val speed: LiveData<List<SpeedRecord>> = _speed

    private val _stepCadence = MutableLiveData<List<StepsCadenceRecord>>()
    val stepCadence: LiveData<List<StepsCadenceRecord>> = _stepCadence

    private val _step = MutableLiveData<List<StepsRecord>>()
    val step: LiveData<List<StepsRecord>> = _step

    private val _totalCaloriesBurnded = MutableLiveData<List<TotalCaloriesBurnedRecord>>()
    val totalCaloriesBurnedRecord: LiveData<List<TotalCaloriesBurnedRecord>> = _totalCaloriesBurnded

    private val _vo2Max = MutableLiveData<List<Vo2MaxRecord>>()
    val vo2Max: LiveData<List<Vo2MaxRecord>> = _vo2Max

    private val _weight = MutableLiveData<List<WeightRecord>>()
    val weight: LiveData<List<WeightRecord>> = _weight

    private val _wheelchairPushes = MutableLiveData<List<WheelchairPushesRecord>>()
    val wheelchairPushes: LiveData<List<WheelchairPushesRecord>> = _wheelchairPushes

    //wajib dipanggil setelah inisialisasi vM
    fun initContext(context: Context) {
        healthSDK.initContext(context = context)
    }

    private val healthSDK =
        HealthConnectImpl(object : HealthConnectCallback {
            override fun onAvailableHC(boolean: Boolean) {
                _isHCInstalled.value = boolean
            }

            override fun onReceiveHRData(heartRateRecord: List<HeartRateRecord>) {
                _heartRateRecord.value = heartRateRecord
            }

            override fun onReceiveCaloriesBurned(caloriesBurnedRecord: List<ActiveCaloriesBurnedRecord>) {
                _activeCaloriesBurnedRecord.value = caloriesBurnedRecord
            }

            override fun onReceivedBasalBodyTempt(basalBodyTempRecord: List<BasalBodyTemperatureRecord>) {
                _basalBodyTemptRecord.value = basalBodyTempRecord
            }

            override fun onReceivedBasalMetabolicrate(basalMetabolicRateRecord: List<BasalMetabolicRateRecord>) {
                _basalMetabolicRate.value = basalMetabolicRateRecord
            }

            override fun onReceiveBloodGlucose(bloodGlucoseRecord: List<BloodGlucoseRecord>) {
                _bloodGlucose.value = bloodGlucoseRecord
            }

            override fun onReceiveBloodPressure(bloodPressureRecord: List<BloodPressureRecord>) {
                _bloodPressure.value = bloodPressureRecord
            }

            override fun onReceiveBodyFat(bodyFat: List<BodyFatRecord>) {
                _bodyFatRecord.value = bodyFat
            }

            override fun onReceivedBodyTemper(bodyTemperatureRecord: List<BodyTemperatureRecord>) {
                _bodyTemper.value = bodyTemperatureRecord
            }

            override fun onReceiveBoneMass(boneMassRecord: List<BoneMassRecord>) {
                _boneMass.value = boneMassRecord
            }

            override fun onReceiveCervicalMucus(cervicalMucusRecord: List<CervicalMucusRecord>) {
                _cervicalMucus.value = cervicalMucusRecord
            }

            override fun onReceiveCyclingPedalingCadence(cyclingPedalingCadenceRecord: List<CyclingPedalingCadenceRecord>) {
                _cyclingPedaling.value = cyclingPedalingCadenceRecord
            }

            override fun onReceiveDistance(distanceRecord: List<DistanceRecord>) {
                _distance.value = distanceRecord
            }

            override fun onReceiveElevationGained(elevationGainedRecord: List<ElevationGainedRecord>) {
                _elevationGained.value = elevationGainedRecord
            }

            override fun onReceiveExerciseSession(exerciseSessionRecord: List<ExerciseSessionRecord>) {
                _exerciseSession.value = exerciseSessionRecord
            }

            override fun onReceiveFloorsClimbed(floorsClimbedRecord: List<FloorsClimbedRecord>) {
                _floorsClimbed.value = floorsClimbedRecord
            }

            override fun onReceiveHeight(heightRecord: List<HeightRecord>) {
                _height.value = heightRecord
            }

            override fun onReceiveHydration(hydrationRecord: List<HydrationRecord>) {
                _hydration.value = hydrationRecord
            }

            override fun onReceiveLeanBodyMass(leanBodyMassRecord: List<LeanBodyMassRecord>) {
                _leanBodyMass.value = leanBodyMassRecord
            }

            override fun onReceiveMenstruationFLow(menstruationFlowRecord: List<MenstruationFlowRecord>) {
                _menstruationFlow.value = menstruationFlowRecord
            }

            override fun onReceiveNutrition(nutritionRecord: List<NutritionRecord>) {
                _nutrition.value = nutritionRecord
            }

            override fun onReceiveOvulationTest(ovulationTestRecord: List<OvulationTestRecord>) {
                _ovulationTest.value = ovulationTestRecord
            }

            override fun onReceiveOxygenSaturation(oxygenSaturationRecord: List<OxygenSaturationRecord>) {
                _oxygenSaturation.value = oxygenSaturationRecord
            }

            override fun onReceivePower(powerRecord: List<PowerRecord>) {
                _power.value = powerRecord
            }

            override fun onReceiveRespirationRate(respiratoryRateRecord: List<RespiratoryRateRecord>) {
                _respirationRate.value = respiratoryRateRecord
            }

            override fun onReceiveRestingHearRate(restingHeartRateRecord: List<RestingHeartRateRecord>) {
                _restingHeartRate.value = restingHeartRateRecord
            }

            override fun onReceiveSexualActivity(sexualActivityRecord: List<SexualActivityRecord>) {
                _sexualActivity.value = sexualActivityRecord
            }

            override fun onReceiveSleepSession(sleepSessionRecord: List<SleepSessionRecord>) {
                _sleepSession.value = sleepSessionRecord
            }

            override fun onReceiveSleepStage(sleepStageRecord: List<SleepStageRecord>) {
                _sleepStage.value = sleepStageRecord
            }

            override fun onReceiveSpeed(speedRecord: List<SpeedRecord>) {
                _speed.value = speedRecord
            }

            override fun onReceiveStepsCadence(stepsCadenceRecord: List<StepsCadenceRecord>) {
                _stepCadence.value = stepsCadenceRecord
            }

            override fun onReceiveStep(stepsRecord: List<StepsRecord>) {
                _step.value = stepsRecord
            }

            override fun onReceiveTotalCaloriesBurned(totalCaloriesBurnedRecord: List<TotalCaloriesBurnedRecord>) {
                _totalCaloriesBurnded.value = totalCaloriesBurnedRecord
            }

            override fun onReceiveVo2Max(vo2MaxRecord: List<Vo2MaxRecord>) {
                _vo2Max.value = vo2MaxRecord
            }

            override fun onReceiveWeight(weightRecord: List<WeightRecord>) {
                _weight.value = weightRecord
            }

            override fun onReceiveWheelChairPushes(wheelchairPushesRecord: List<WheelchairPushesRecord>) {
                _wheelchairPushes.value = wheelchairPushesRecord
            }
        })

     fun isHcAvailable() {
        healthSDK.isProviderAvailable()
    }

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
        healthSDK.readBasalBodyTempt(start.toInstant(), end.toInstant())
    }

    suspend fun writeBasalBodyTemptRecord(
        data: List<BasalBodyTemperatureRecord>
    ) {
        healthSDK.writeBasalBodyTempt(data)
    }

    suspend fun readBasalMetabolicRate(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBasalMetabolicRate(start.toInstant(), end.toInstant())
    }

    suspend fun writeBasalMetabolicRate(data: List<BasalMetabolicRateRecord>) {
        healthSDK.writeBasalMetabolicRate(data)
    }

    suspend fun readBloodGlucose(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBloodGlucose(start.toInstant(), end.toInstant())
    }

    suspend fun writeBloodGlucose(data: List<BloodGlucoseRecord>) {
        healthSDK.writeBloodGlucose(data)
    }

    suspend fun readBloodPressure(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBloodPressure(start.toInstant(), end.toInstant())
    }

    suspend fun writeBloodPressure(data: List<BloodPressureRecord>) {
        healthSDK.writeBloodPressure(data)
    }

    suspend fun readBodyFatRecord(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBodyFat(start.toInstant(), end.toInstant())
    }

    suspend fun writeBodyFatRecord(data: List<BodyFatRecord>) {
        healthSDK.writeBodyFat(data)
    }

    suspend fun readBodyTemper(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBodyTemper(start.toInstant(), end.toInstant())
    }

    suspend fun writeBodyTemper(data: List<BodyTemperatureRecord>) {
        healthSDK.writeBodyTemper(data)
    }

    suspend fun readBoneMass(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readBoneMass(start.toInstant(), end.toInstant())
    }

    suspend fun writeBoneMass(data: List<BoneMassRecord>) {
        healthSDK.writeBoneMass(data)
    }

    suspend fun readCervicalMucus(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readCervicalMucus(start.toInstant(), end.toInstant())
    }

    suspend fun writeCervicalMucus(data: List<CervicalMucusRecord>) {
        healthSDK.writeCervicalMucus(data)
    }

    suspend fun readCyclingPedaling(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readCyclingPedaling(start.toInstant(), end.toInstant())
    }

    suspend fun writeCyclingPedaling(data: List<CyclingPedalingCadenceRecord>) {
        healthSDK.writeCyclingPedaling(data)
    }

    suspend fun readDistance(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readDistance(start.toInstant(), end.toInstant())
    }

    suspend fun writeDistance(data: List<DistanceRecord>) {
        healthSDK.writeDistance(data)
    }

    suspend fun readElevationGained(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readElevationGained(start.toInstant(), end.toInstant())
    }

    suspend fun writeElevationGained(data: List<ElevationGainedRecord>) {
        healthSDK.writeElevationGained(data)
    }

    suspend fun readExerciseSession(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readExerciseSession(start.toInstant(), end.toInstant())
    }

    suspend fun writeExerciseSession(data: List<ExerciseSessionRecord>) {
        healthSDK.writeExerciseSession(data)
    }

    suspend fun readFloorsClimbed(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readFloorsClimbed(start.toInstant(), end.toInstant())
    }

    suspend fun writeFloorsClimbed(data: List<FloorsClimbedRecord>) {
        healthSDK.writeFloorsClimbed(data)
    }

    suspend fun readHeight(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readHeight(start.toInstant(), end.toInstant())
    }

    suspend fun writeHeight(data: List<HeightRecord>) {
        healthSDK.writeHeight(data)
    }

    suspend fun readHydration(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readHydration(start.toInstant(), end.toInstant())
    }

    suspend fun writeHydration(data: List<HydrationRecord>) {
        healthSDK.writeHydration(data)
    }

    suspend fun readLeanBodyMass(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readLeanBodyMass(start.toInstant(), end.toInstant())
    }

    suspend fun writeLeanBodyMass(data: List<LeanBodyMassRecord>) {
        healthSDK.writeLeanBodyMass(data)
    }

    suspend fun readMenstruationFlow(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readMenstruationFlow(start.toInstant(), end.toInstant())
    }

    suspend fun writeMenstruationFlow(data: List<MenstruationFlowRecord>) {
        healthSDK.writeMenstruationFlow(data)
    }

    suspend fun readNutrition(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readNutrition(start.toInstant(), end.toInstant())
    }

    suspend fun writeNutrition(data: List<NutritionRecord>) {
        healthSDK.writeNutrition(data)
    }

    suspend fun readOvulationTest(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readOvulationTest(start.toInstant(), end.toInstant())
    }

    suspend fun writeOvulationTest(data: List<OvulationTestRecord>) {
        healthSDK.writeOvulationTest(data)
    }

    suspend fun readOxygenSaturation(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readOxygenSaturation(start.toInstant(), end.toInstant())
    }

    suspend fun writeOxygenSaturation(data: List<OxygenSaturationRecord>) {
        healthSDK.writeOxygenSaturation(data)
    }

    suspend fun readPower(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readPower(start.toInstant(), end.toInstant())
    }

    suspend fun writePower(data: List<PowerRecord>) {
        healthSDK.writePower(data)
    }

    suspend fun readRespirationRate(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readRespirationRate(start.toInstant(), end.toInstant())
    }

    suspend fun writeRespirationRate(data: List<RespiratoryRateRecord>) {
        healthSDK.writeRespirationRate(data)
    }

    suspend fun readRestingHeartRate(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readRestingHeartRate(start.toInstant(), end.toInstant())
    }

    suspend fun writeRestingHeartRate(data: List<RestingHeartRateRecord>) {
        healthSDK.writeRestingHeartRate(data)
    }

    suspend fun readSexualActivity(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readSexualActivity(start.toInstant(), end.toInstant())
    }

    suspend fun writeSexualActivity(data: List<SexualActivityRecord>) {
        healthSDK.writeSexualActivity(data)
    }

    suspend fun readSleepSession(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readSleepSession(start.toInstant(), end.toInstant())
    }

    suspend fun writeSleepSession(data: List<SleepSessionRecord>) {
        healthSDK.writeSleepSession(data)
    }

    suspend fun readSleepStage(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readSleepStage(start.toInstant(), end.toInstant())
    }

    suspend fun writeSleepStage(data: List<SleepStageRecord>) {
        healthSDK.writeSleepStage(data)
    }

    suspend fun readSpeed(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readSpeed(start.toInstant(), end.toInstant())
    }

    suspend fun writeSpeed(data: List<SpeedRecord>) {
        healthSDK.writeSpeed(data)
    }

    suspend fun readStepsCadence(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readStepCadence(start.toInstant(), end.toInstant())
    }

    suspend fun writeStepsCadence(data: List<StepsCadenceRecord>) {
        healthSDK.writeStepCadence(data)
    }

    suspend fun readStep(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readSteps(start.toInstant(), end.toInstant())
    }

    suspend fun writeSteps(data: List<StepsRecord>) {
        healthSDK.writeSteps(data)
    }

    suspend fun readTotalCaloriesBurned(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readTotalCaloriesBurned(start.toInstant(), end.toInstant())
    }

    suspend fun writeTotalCaloriesBurned(data: List<TotalCaloriesBurnedRecord>) {
        healthSDK.writeTotalCaloriesBurned(data)
    }

    suspend fun readVo2Max(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readVo2Max(start.toInstant(), end.toInstant())
    }

    suspend fun writeVo2Max(data: List<Vo2MaxRecord>) {
        healthSDK.writeVo2Max(data)
    }

    suspend fun readWeight(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readWeight(start.toInstant(), end.toInstant())
    }

    suspend fun writeWeight(data: List<WeightRecord>) {
        healthSDK.writeWeight(data)
    }

    suspend fun readWheelChairPushes(start: ZonedDateTime, end: ZonedDateTime) {
        healthSDK.readWheelChairPushes(start.toInstant(), end.toInstant())
    }

    suspend fun writeWheelChairPushes(data: List<WheelchairPushesRecord>) {
        healthSDK.writeWheelChairPushes(data)
    }

}