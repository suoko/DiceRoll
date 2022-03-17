package com.example.diceroll

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button2)
        rollButton.setOnClickListener {
            hideSoftKeyboard()
            rollDice()
        }
    }

    class Dice(val numSides: Editable) {
        fun roll(): Int {
            return (1..numSides.toString().toInt()).random()
        }
    }

    private fun rollDice() {
        val resultEditText: EditText = findViewById(R.id.diceFaces)
        val customNumSides = resultEditText.text
        //val num = customNumSides.toInt()
        val dice = Dice(customNumSides)
        val diceRoll = dice.roll()
        val resultTextView: TextView = findViewById(R.id.DiceRollResult)
        resultTextView.text = diceRoll.toString()
        val toast = Toast.makeText(this, "Dice with ${customNumSides} sides has rolled", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun Activity.hideSoftKeyboard() {
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = this.currentFocus
        val windowToken = this.window?.decorView?.rootView?.windowToken
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        inputMethodManager.hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}