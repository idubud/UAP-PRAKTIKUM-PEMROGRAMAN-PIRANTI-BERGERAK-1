package com.example.ukin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val resultGender = findViewById<TextView>(R.id.resultGender)
        val resultName = findViewById<TextView>(R.id.resultName)
        val nameInput = findViewById<TextView>(R.id.nameInput)

        calculateButton.setOnClickListener {
            val selectedGenderId =genderGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId)
            val heightData = heightInput.text.toString()
            val nameData = nameInput.text.toString()

            if (heightData.isEmpty()) {
                Toast.makeText(this, "Silahkan isi tinggi badan anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nameData.isEmpty()) {
                Toast.makeText(this, "Silahkan isi identitas anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val height = heightData.toDouble()
            val idealWeight = if (selectedGender.text == "Pria") {
                resultGender.text = "Jenis kelamin: Pria"
                (height-100) * 0.9
            } else {
                resultGender.text = "Jenis kelamin: Wanita"
                (height-100) * 0.85
            }
            resultName.text= "nama $nameData"
            resultText.text= "Berat Badan Ideal anda adalah ${"%.2f".format(idealWeight)} kg"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}