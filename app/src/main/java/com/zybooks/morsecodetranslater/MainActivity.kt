package com.zybooks.morsecodetranslater

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {

    private lateinit var vibrator: Vibrator // Var with vibrator property
    private val timescale = 300L // 1 time unit is set to 300 ms

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Variable for widgets objects
        val outputButton: Button = findViewById(R.id.outputButton)
        val editTextEnglish: EditText = findViewById(R.id.editTextEnglish)
        val editTextMorse: EditText = findViewById(R.id.editTextMorse)
        val buttonEnterMorse: Button = findViewById(R.id.buttonEnterMorse)
        // Adding edit text filters to limit input

        editTextEnglish.filters = arrayOf<InputFilter>(InputFilter.AllCaps(), InputFilter.LengthFilter(50), InputFilter { source, _, _, _, _, _ ->
            if (source.matches(Regex("[A-Z0-9.,?'!/()&:;=+-_\"$@ ]*"))) null else ""
        })
        editTextMorse.filters = arrayOf<InputFilter>(InputFilter.AllCaps(), InputFilter.LengthFilter(50), InputFilter { source, _, _, _, _, _ ->
            if (source.matches(Regex("[.\\-\\ ]*"))) null else ""
        })

        // Listener for regular clicks (short presses)
        buttonEnterMorse.setOnClickListener {
            // Code to execute for a short press

        }

        // Listener for long presses
        buttonEnterMorse.setOnLongClickListener {
            // Code to execute for a long press


            true // Return true to indicate that the long press was handled
        }

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        // Listener for output click
        outputButton.setOnClickListener {
            println("Button Clicked")
            val morseString = editTextMorse.text.toString() // Get string of morse text
            for (i in 0 until morseString.length) {
                morseToVibrate(morseString[i]) // Vibrate based on character
            }
        }

        editTextEnglish.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Code to execute when the English text changes
                editTextMorse.setText(translate(editTextEnglish.getText().toString()))
            }
        })

        editTextMorse.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Code to execute when the Morse text changes

            }
        })


    }
    /**
    Function for inputting a string of english and outputting a string of morse code. One pause is used for new letter, two for new word
    '.'' = short
    '-' = long
    ' ' = pause
    accepted charecters are A-Z, a-z, 0-9, and space
     */
    fun translate(upperMorse : String): String {
        var morse = upperMorse.lowercase()//input string tolowercase
        var morseString : String = "" // creates string for output
        //for size of input string
        for(i in 0..morse.length-1){
            //when input string at i equals
            when(morse[i]){
                'a'-> morseString += ".- "
                'b'-> morseString += "-... "
                'c'-> morseString += "-.-. "
                'd'-> morseString += "-.. "
                'e'-> morseString += ". "
                'f'-> morseString += "..-. "
                'g'-> morseString += "--. "
                'h'-> morseString += ".... "
                'i'-> morseString += ".. "
                'j'-> morseString += ".--- "
                'k'-> morseString += "-.- "
                'l'-> morseString += ".-.. "
                'm'-> morseString += "-- "
                'n'-> morseString += "-. "
                'o'-> morseString += "--- "
                'p'-> morseString += ".--. "
                'q'-> morseString += "--.- "
                'r'-> morseString += ".-. "
                's'-> morseString += "... "
                't'-> morseString += "- "
                'u'-> morseString += "..- "
                'v'-> morseString += "...- "
                'w'-> morseString += ".-- "
                'x'-> morseString += "-..- "
                'y'-> morseString += "-.-- "
                'z'-> morseString += "--.. "
                '0'-> morseString += "----- "
                '1'-> morseString += ".---- "
                '2'-> morseString += "..--- "
                '3'-> morseString += "...-- "
                '4'-> morseString += "....- "
                '5'-> morseString += "..... "
                '6'-> morseString += "-... "
                '7'-> morseString += "--... "
                '8'-> morseString += "---.. "
                '9'-> morseString += "----. "
                ' '-> morseString += "  "
            }
        }
        return morseString
    }

    private fun vibrateDot() {
        vibrator.vibrate(timescale) //Dot is 1 time unit
        Thread.sleep(timescale * 2) // Give time to vibrate + delay
        println("Vibrate Dot")
    }

    private fun vibrateDash() {
        vibrator.vibrate(timescale * 3) // Dash is 3 time
        Thread.sleep(timescale * 4) // Give time to vibrate + delay
        println("Vibrate Dash")
    }

    private fun stopVibration() {
        vibrator.cancel() // Stop vibrating
        Thread.sleep(timescale) // Delay time
        println("Stop vibrating")
    }

    /**
    Function for reading morse and outputting vibration
    '.' = short (vibrate 1 timescale)
    '-' = long (vibrate 3 timescales)
    ' ' = pause (stop vibration for 1 timescale)
    no other characters should be in the morse text box
     */
    private fun morseToVibrate(morseChar : Char){
        when (morseChar) {
            '.' -> vibrateDot()
            '-' -> vibrateDash()
            ' ' -> stopVibration()
            else -> println("Non-morse character in morse box")
        }
    }
}
