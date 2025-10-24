// 1. CHANGE: REPLACE 'com.example.codemath' with your project's exact package name
package com.example.codemath

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// 2. CHANGE: REPLACE 'com.example.codemath' with your project's exact package name
import com.example.codemath.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Declare the binding variable for ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Conversion factors (Constants)
    private val GRAMS_TO_OUNCES = 0.035274
    private val OUNCES_TO_GRAMS = 28.3495

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding and set the content view (connects XML to Kotlin)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Attach the OnClickListener to the 'Convert' Button (Interactive View)
        binding.convertButton.setOnClickListener {
            performConversion()
        }
    }

    /**
     * Performs the required calculation based on user input and selected unit.
     */
    @SuppressLint("DefaultLocale")
    private fun performConversion() {
        // --- 1. Get input value and safely handle errors ---
        val inputStr = binding.inputValue.text.toString()
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value to convert", Toast.LENGTH_SHORT).show()
            binding.resultTextView.text = ""
            return
        }

        val inputValue: Double = try {
            inputStr.toDouble()
        } catch (_: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
            binding.resultTextView.text = ""
            return
        }

        // --- 2. Determine conversion direction from Spinner ---
        val selectedUnit = binding.unitSpinner.selectedItem.toString()
        var result: Double
        var resultUnit: String
        var inputUnit: String

        if (selectedUnit == "Grams") {
            // Grams to Ounces
            result = inputValue * GRAMS_TO_OUNCES
            inputUnit = "Grams"
            resultUnit = "Ounces"
        } else {
            // Ounces to Grams
            result = inputValue * OUNCES_TO_GRAMS
            inputUnit = "Ounces"
            resultUnit = "Grams"
        }

        // --- 3. Format and display the output (TextView) ---
        val formattedInput = String.format("%.2f", inputValue)
        val formattedResult = String.format("%.2f", result)

        val outputText = "$formattedInput $inputUnit = $formattedResult $resultUnit"
        binding.resultTextView.text = outputText // Update the output TextView
    }
}