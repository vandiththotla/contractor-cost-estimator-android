package com.vandith.contractor

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    private val TAX_RATE = 0.0825 // 8.25% sample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etHours = findViewById<EditText>(R.id.etHours)
        val etRate = findViewById<EditText>(R.id.etRate)
        val etMaterials = findViewById<EditText>(R.id.etMaterials)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val currency = NumberFormat.getCurrencyInstance()

        btnCalc.setOnClickListener {
            val hours = etHours.text.toString().toDoubleOrNull()
            val rate = etRate.text.toString().toDoubleOrNull()
            val materials = etMaterials.text.toString().toDoubleOrNull()

            if (hours == null || rate == null || materials == null) {
                Toast.makeText(this, "Enter valid numbers for all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val labor = hours * rate
            val subtotal = labor + materials
            val tax = subtotal * TAX_RATE
            val total = subtotal + tax

            tvResult.text = "Subtotal: ${currency.format(subtotal)}  •  Tax: ${currency.format(tax)}  •  Total: ${currency.format(total)}"
        }
    }
}
