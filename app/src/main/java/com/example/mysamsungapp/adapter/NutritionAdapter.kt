package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.BodyFatRecord
import androidx.health.connect.client.records.NutritionRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class NutritionAdapter(private val listData: List<NutritionRecord>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {
    class ViewHolder(val binding: HeartRateLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HeartRateLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvData.text = """
           Name : ${listData[position].name}
           Biotin : ${listData[position].biotin?.inGrams} grams
           Caffeine : ${listData[position].caffeine?.inGrams} grams
           Calcium : ${listData[position].calcium?.inGrams} grams
           Chloride : ${listData[position].chloride?.inGrams} grams
           Cholestrol : ${listData[position].cholesterol?.inGrams} grams
           Chromium : ${listData[position].chromium?.inGrams} grams
           Copper : ${listData[position].copper?.inGrams} grams
           Dietary Fiber : ${listData[position].dietaryFiber?.inGrams} grams
           Folate : ${listData[position].folate?.inGrams} grams
           Energy : ${listData[position].energy?.inCalories} cal
           EnergyFrom Foat : ${listData[position].energyFromFat?.inCalories} cal
           Folid Acid : ${listData[position].folicAcid?.inGrams} grams
           Iodin : ${listData[position].iodine?.inGrams} grams
           Iron : ${listData[position].iron?.inGrams} grams
           Magnesium : ${listData[position].magnesium?.inGrams} grams
           Magnase : ${listData[position].manganese?.inGrams} grams 
           Molybdenum : ${listData[position].molybdenum?.inGrams} grams 
           Monoun Saturated Fat : ${listData[position].monounsaturatedFat?.inGrams} grams 
           Meal Type : ${listData[position].mealType}
           Niacin : ${listData[position].niacin?.inGrams} grams
           Panthotenic Acid : ${listData[position].pantothenicAcid?.inGrams} grams
           Phosporus : ${listData[position].phosphorus?.inGrams} grams
           PolyunsaturatedFat : ${listData[position].polyunsaturatedFat?.inGrams} grams
           Potasium : ${listData[position].potassium?.inGrams} grams
           Protein : ${listData[position].protein?.inGrams} grams
           Ribo Flavin : ${listData[position].riboflavin?.inGrams} grams
           Sodium : ${listData[position].sodium?.inGrams} grams
           Sugar : ${listData[position].sugar?.inGrams} grams
           Selenium : ${listData[position].selenium?.inGrams} grams
           Thiamin : ${listData[position].thiamin?.inGrams} grams
           Total Carbohydrate : ${listData[position].totalCarbohydrate?.inGrams} grams
           Total Fat : ${listData[position].totalFat?.inGrams} grams
           Trans Fat : ${listData[position].transFat?.inGrams} grams
           Unsaturated Fat : ${listData[position].unsaturatedFat?.inGrams} grams
           Vitamin A: ${listData[position].vitaminA?.inMilligrams} miligram
           Vitamin C: ${listData[position].vitaminC?.inMilligrams} miligram
           Vitamin D: ${listData[position].vitaminD?.inMilligrams} miligram
           Vitamin E: ${listData[position].vitaminE?.inMilligrams} miligram
           Vitamin K: ${listData[position].vitaminK?.inMilligrams} miligram
           Vitamin B6: ${listData[position].vitaminB6?.inMilligrams} miligram
           Vitamin B12: ${listData[position].vitaminB12?.inMilligrams} miligram
           Start Time ${InstantFormatter.formatTime(listData[position].startTime)}
           End Time ${InstantFormatter.formatTime(listData[position].endTime)}
       """.trimIndent()
    }
}