package com.example.rxjavasamples.rxbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.rxjavasamples.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_rx_binding.*
import java.util.function.Consumer

class RxBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_binding)
/*        etInputField.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tvInput.text = s
            }

        } )
        btnClear.setOnClickListener {
            etInputField.setText("")
            tvInput.text = ""
        }*/

        rxBinding()
    }

    private fun rxBinding() {
        val disposable = RxTextView.textChanges(etInputField)
            .subscribe { charSequence -> tvInput.text = charSequence }
        val disposable1 = RxView.clicks(btnClear)
            .subscribe {
                etInputField.setText("")
                tvInput.text = ""
            }
    }


}
