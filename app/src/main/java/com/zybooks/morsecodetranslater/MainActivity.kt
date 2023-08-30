package com.zybooks.morsecodetranslater

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {




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
        // Listner for output click
        outputButton.setOnClickListener {
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
}
