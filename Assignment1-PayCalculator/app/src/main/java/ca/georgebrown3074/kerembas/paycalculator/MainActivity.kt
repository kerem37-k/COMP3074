package ca.georgebrown3074.kerembas.paycalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hours = findViewById<EditText>(R.id.editHours)
        val rate = findViewById<EditText>(R.id.editRate)
        val taxRate = findViewById<EditText>(R.id.editTax)

        val payText = findViewById<TextView>(R.id.textPay)
        val overtimeText = findViewById<TextView>(R.id.textOvertime)
        val totalText = findViewById<TextView>(R.id.textTotal)
        val taxText = findViewById<TextView>(R.id.textTax)

        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val aboutButton = findViewById<Button>(R.id.btnAbout)

        calculateButton.setOnClickListener {
            val h = hours.text.toString().toDoubleOrNull()
            val r = rate.text.toString().toDoubleOrNull()
            val t = taxRate.text.toString().toDoubleOrNull()

            if (h == null || r == null || t == null ||
                h < 0 || r < 0 || t < 0 || t > 1) {
                Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show()
            } else {
                val pay: Double
                val overtimePay: Double

                if (h <= 40) {
                    pay = h * r
                    overtimePay = 0.0
                } else {
                    pay = 40 * r
                    overtimePay = (h - 40) * r * 1.5
                }

                val totalPay = pay + overtimePay
                val tax = pay * t

                payText.text = "Pay: $%.2f".format(pay)
                overtimeText.text = "Overtime Pay: $%.2f".format(overtimePay)
                totalText.text = "Total Pay: $%.2f".format(totalPay)
                taxText.text = "Tax: $%.2f".format(tax)
            }
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}
