package com.example.rxjavasamples.dependencyinjection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavasamples.R

class PhonesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phones)

        val battery = Battery()
        val memoryCard = MemoryCard()

        val serviceProvider = ServiceProvider()
        val simCard = SIMCard(serviceProvider)

        val smartPhone = SmartPhone(battery, memoryCard, simCard)

        smartPhone.makeACall()


    }
}
