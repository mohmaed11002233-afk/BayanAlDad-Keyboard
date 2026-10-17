package com.bayan.aldad.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.Toast
import com.bayan.aldad.R

class BayankeyboardService : InputMethodService(), View.OnClickListener {

    private lateinit var keyboardView: View
    private lateinit var btnCorrect: Button

    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.keyboard_layout, null)
        
        btnCorrect = keyboardView.findViewById(R.id.btn_quick_correct)
        btnCorrect.setOnClickListener(this)

        return keyboardView
    }

    override fun onClick(v: View?) {
        val inputConnection = currentInputConnection ?: return
        
        when (v?.id) {
            R.id.btn_quick_correct -> {
                performCorrection(inputConnection)
            }
        }
    }

    private fun performCorrection(inputConnection: InputConnection) {
        val currentText = inputConnection.getTextBeforeCursor(100, 0) ?: ""
        
        if (currentText.isNotEmpty()) {
            val correctedText = applyBayanCorrection(currentText.toString())
            inputConnection.deleteSurroundingText(currentText.length, 0)
            inputConnection.commitText(correctedText, 1)
        } else {
            Toast.makeText(this, "لا يوجد نص لتصحيحه", Toast.LENGTH_SHORT).show()
        }
    }

    private fun applyBayanCorrection(text: String): String {
        return text.trim()
    }
}
