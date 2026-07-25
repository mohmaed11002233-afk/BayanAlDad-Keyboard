package com.bayandalad.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class BayanKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(R.layout.bayan_keyboard_layout, null) as LinearLayout
        
        val magicBtn = keyboardView.findViewById<Button>(R.id.magicCorrectionBtn)
        magicBtn.setOnClickListener {
            val ic = currentInputConnection
            if (ic != null) {
                correctTextInField(ic)
            }
        }
        
        return keyboardView
    }

    private fun correctTextInField(ic: InputConnection) {
        val currentText = ic.getTextBeforeCursor(100, 0) ?: ""
        val corrected = applyBayanRules(currentText.toString())
        
        ic.deleteSurroundingText(currentText.length, 0)
        ic.commitText(corrected, 1)
    }

    private fun applyBayanRules(text: String): String {
        return text.trim() 
    }
}
