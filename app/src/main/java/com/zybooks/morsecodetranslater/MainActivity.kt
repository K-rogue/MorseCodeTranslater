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
                editTextMorse.setText("...")
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

}
