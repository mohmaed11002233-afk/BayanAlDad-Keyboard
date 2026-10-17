package com.bayan.aldad

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class BayankeyboardService : InputMethodService(), View.OnClickListener {

    private lateinit var btnCorrect: Button

    override fun onCreateInputView(): View {
        // إنشاء واجهة الكيبورد برمجياً في نفس الملف بدون الحاجة لملفات XML
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(android.graphics.Color.parseColor("#ECEFF1"))
            setPadding(16, 16, 16, 16)
        }

        btnCorrect = Button(this).apply {
            text = "✨ التصحيح السريع - بيان الضاد"
            textSize = 16f
            setTextColor(android.graphics.Color.WHITE)
            setBackgroundColor(android.graphics.Color.parseColor("#00C853"))
            setOnClickListener(this@BayankeyboardService)
        }

        layout.addView(btnCorrect)
        return layout
    }

    override fun onClick(v: View?) {
        val inputConnection: InputConnection = currentInputConnection ?: return

        // جلب النص وتصحيحه تلقائياً
        val currentText = inputConnection.getTextBeforeCursor(100, 0) ?: ""

        if (currentText.isNotEmpty()) {
            val correctedText = currentText.toString().trim()
            inputConnection.deleteSurroundingText(currentText.length, 0)
            inputConnection.commitText(correctedText, 1)
        } else {
            Toast.makeText(this, "لا يوجد نص لتصحيحه", Toast.LENGTH_SHORT).show()
        }
    }
}
