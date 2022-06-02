package com.info.sozlukuygulamadb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.card_tasarim.textViewIngilizces

class DetayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        textViewIngilizces.text = kelime.ingilizce
        textViewTurkces.text = kelime.turkce
    }
}