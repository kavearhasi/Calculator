package com.example.calculator

import android.os.Bundle
import android.view.textservice.TextInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)
//        val addButton = findViewById<Button>(R.id.add)
//        addButton.setOnClickListener{
//            val input1 = findViewById<EditText>(R.id.input1)
//            val input2 = findViewById<EditText>(R.id.input2)
//            val answer = (input1.text.toString().toInt())+ (input2.text.toString().toInt())
//            val answerField = findViewById<TextView>(R.id.answer)
//            answerField.setText( answer.toString())
//
//        }
//
//        val subtractButton = findViewById<TextView>(R.id.minus)
//        subtractButton.setOnClickListener{
//            val input1 = findViewById<EditText>(R.id.input1)
//            val input2 = findViewById<EditText>(R.id.input2)
//            val answer = (input1.text.toString().toInt())- (input2.text.toString().toInt())
//            val answerField = findViewById<TextView>(R.id.answer)
//            answerField.setText( answer.toString())
//        }
//        val MultiplyButton = findViewById<TextView>(R.id.multiply)
//        MultiplyButton.setOnClickListener{
//            val input1 = findViewById<EditText>(R.id.input1)
//            val input2 = findViewById<EditText>(R.id.input2)
//            val answer = (input1.text.toString().toInt())* (input2.text.toString().toInt())
//            val answerField = findViewById<TextView>(R.id.answer)
//            answerField.setText( answer.toString())
//        }
//        val divideButton = findViewById<TextView>(R.id.divide)
//        divideButton.setOnClickListener{
//            val input1 = findViewById<EditText>(R.id.input1)
//            val input2 = findViewById<EditText>(R.id.input2)
//            var answer = ""
//            if(input2.text.toString().toInt() != 0) {
//                 answer = ((input1.text.toString().toInt()) / (input2.text.toString().toInt())).toString()
//            }
//            else{
//                answer = "Invalid Divident"
//            }
//            val answerField = findViewById<TextView>(R.id.answer)
//            answerField.setText( answer.toString())
//        }

    }
}