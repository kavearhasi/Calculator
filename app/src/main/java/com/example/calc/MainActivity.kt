package com.example.calc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import data.History
import data.HistoryViewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private var userInput = ""
    private var currentInput = ""
    private var result: Double = 0.0
    private val operator: MutableList<String> = mutableListOf("+")
    private val operands: MutableList<Double> = mutableListOf()
    private val validSymbols: List<String> = listOf("+", "-", "%", "/", "x")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_main)

        val historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
      //reference for the all view button
        val btnAc = findViewById<Button>(R.id.alc)
        val btnDel = findViewById<Button>(R.id.del)
        val btn1 = findViewById<Button>(R.id.num1)
        val btn2 = findViewById<Button>(R.id.num2)
        val btn3 = findViewById<Button>(R.id.num3)
        val btn4 = findViewById<Button>(R.id.num4)
        val btn5 = findViewById<Button>(R.id.num5)
        val btn6 = findViewById<Button>(R.id.num6)
        val btn7 = findViewById<Button>(R.id.num7)
        val btn8 = findViewById<Button>(R.id.num8)
        val btn9 = findViewById<Button>(R.id.num9)
        val btn0 = findViewById<Button>(R.id.num0)
        val btn00 = findViewById<Button>(R.id.num00)
        val btnDot = findViewById<Button>(R.id.dot)
        val btnEqual = findViewById<Button>(R.id.equal)
        val btnAdd = findViewById<Button>(R.id.add)
        val btnSub = findViewById<Button>(R.id.sub)
        val btnMul = findViewById<Button>(R.id.mul)
        val btnDiv = findViewById<Button>(R.id.div)
        val btnPer = findViewById<Button>(R.id.per)
        val btnHistory = findViewById<Button>(R.id.history)
        val inputBox: TextView = findViewById<EditText>(R.id.input)
        val resultBox: TextView = findViewById<EditText>(R.id.result)

        //Checks the intent whether it is returned from the history activity
        if (!intent.getStringExtra("result").isNullOrEmpty()) {
            this.userInput = intent.getStringExtra("result").toString()
            inputBox.text = this.userInput
        }

  // on click listeners for the buttons
        btnAc.setOnClickListener {
            this.userInput = ""
            this.currentInput = ""
            resultBox.text = ""
            this.result = 0.0
            operands.clear()
            operator.clear()
            operator.add("+")
            updateUserInput(inputBox)
        }
        btnPer.setOnClickListener {
            takeInput(btnPer, inputBox, resultBox)
        }
        btnDel.setOnClickListener {
            if (this.userInput != "") {
                val suffix = this.userInput.last()
                this.userInput = this.userInput.removeSuffix(suffix.toString())
                this.currentInput = this.userInput.removeSuffix(suffix.toString())
                finalCalculation(this.userInput, resultBox)
                updateUserInput(inputBox)
            }
        }
        btn1.setOnClickListener {
            takeInput(btn1, inputBox, resultBox)
        }
        btn2.setOnClickListener {
            takeInput(btn2, inputBox, resultBox)
        }
        btn3.setOnClickListener {
            takeInput(btn3, inputBox, resultBox)
        }
        btn4.setOnClickListener {
            takeInput(btn4, inputBox, resultBox)
        }
        btn5.setOnClickListener {
            takeInput(btn5, inputBox, resultBox)
        }
        btn6.setOnClickListener {
            takeInput(btn6, inputBox, resultBox)
        }
        btn7.setOnClickListener {
            takeInput(btn7, inputBox, resultBox)
        }
        btn8.setOnClickListener {
            takeInput(btn8, inputBox, resultBox)
        }
        btn9.setOnClickListener {
            takeInput(btn9, inputBox, resultBox)
        }
        btn0.setOnClickListener {
            takeInput(btn0, inputBox, resultBox)
        }
        btn00.setOnClickListener {
            takeInput(btn00, inputBox, resultBox)
        }
        btnDot.setOnClickListener {
            takeInput(btnDot, inputBox, resultBox)
        }
        btnEqual.setOnClickListener {


                val expression: String = this.userInput
                if (this.result % 1.0 == 0.0 && this.result < Int.MAX_VALUE && this.result > Int.MIN_VALUE) {
                    this.userInput = this.result.toInt().toString()
                } else {
                    this.userInput = this.result.toString()
                }

                // Change the style of the  Input for better representation
                inputBox.setTextColor(getColorStateList(R.color.grey))
                inputBox.textSize = 20.0f
                inputBox.text = this.userInput

                finalCalculation(this.userInput, resultBox)

                // create a history
                val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
                val history = History(
                    0, expression, this.result, LocalDate.now().format(formatter).toString()
                )
                historyViewModel.addHistory(history)

        }
        btnAdd.setOnClickListener {
            takeInput(btnAdd, inputBox, resultBox)
        }
        btnSub.setOnClickListener {
            takeInput(btnSub, inputBox, resultBox)
        }
        btnMul.setOnClickListener {
            takeInput(btnMul, inputBox, resultBox)
        }
        btnDiv.setOnClickListener {
            takeInput(btnDiv, inputBox, resultBox)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun finalCalculation(s: String, resultView: TextView) {
        if (s == "" || s == "Infinity") {
            resultView.text = ""
            this.userInput = ""
            this.result = 0.0
            return
        }
        operands.clear()
        operator.clear()
        operator.add("+")

        filterString(s)

        this.result = 0.0
        if (operator.size != operands.size && operator.isNotEmpty() ) {
            operator.removeAt(operator.size - 1)
        }
        for (i in operator.indices) {
            calculate(operator[i], operands[i], resultView)
        }
        if (this.result % 1.0 == 0.0 && this.result < Int.MAX_VALUE && this.result > Int.MIN_VALUE) {
            resultView.text = String.format(this.result.toInt().toString())
            return
        }
        if (this.result.isInfinite() || this.result.isNaN()) {
            resultView.text = getString(R.string.clac_arthematic_exception)
            return
        }
        resultView.text = String.format(this.result.toString())
    }

    private fun calculate(s: String, d: Double, resultView: TextView) {
        try {
            when (s) {
                "+" -> {
                    this.result += d
                }

                "-" -> {
                    this.result -= d
                }

                "x" -> {
                    this.result *= d
                }

                "/" -> {
                    this.result /= d
                }

                "%" -> {
                    this.result = (this.result / 100) * d
                }
            }
        } catch (_: Exception) {
            resultView.text = getString(R.string.calc_error)
        }
    }

    private fun filterString(s: String) {
        var tempInput = s.trim()
        if (tempInput.first() == '-') {
            operator[0] = "-"
            tempInput = tempInput.removeRange(0, 1)
        }
        val pattern = Regex("[^+\\-x/%]")
        tempInput.split(pattern).forEach {
            if (it.isNotBlank()) {
                operator.add(it)
            }
        }
        tempInput.split("+", "-", "x", "/", "%").forEach {
            if (it.isNotEmpty()) {
                if (it == ".") {
                    operands.add(0.0)
                } else {
                    operands.add(it.toDouble())
                }
            }
        }

    }

    private fun takeInput(v: View, i: TextView, r: TextView) {
        val input = v.tag
        mergeInput(input.toString(), r)
        updateUserInput(i)
    }

    private fun mergeInput(userInput: String, r: TextView) {
        if (this.userInput.isEmpty()) {
            if (userInput == "-" || isValidInput(userInput)) {
                this.currentInput += userInput
                this.userInput += userInput
                r.text = this.userInput
            }
            return
        }
        if (isValidInput(userInput)) {
            this.currentInput += userInput
            this.userInput += userInput
            finalCalculation(this.userInput, r)
        }
        if (validSymbols.contains(userInput) && !validSymbols.contains(
                this.userInput.last().toString()
            )
        ) {
            this.currentInput = ""
            this.userInput += userInput
            finalCalculation(this.userInput, r)
        }
    }

    private fun isValidInput(userInput: String): Boolean {
        if (userInput == ".") {
            return !this.currentInput.contains(".")
        }
        return userInput.isDigitsOnly()
    }

    private fun updateUserInput(i: TextView) {
        i.text = this.userInput
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input", this.userInput)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        userInput = savedInstanceState.getString("input").toString()
    }
}
