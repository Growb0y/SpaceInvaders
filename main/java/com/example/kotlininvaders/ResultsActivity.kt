package com.example.kotlininvaders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultsActivity : Activity() {

    private lateinit var textView : TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_results)

        val score = intent.getIntExtra("score", 0)
        val wavesPassed = intent.getIntExtra("wavesPassed", 0)
        val highScore = intent.getIntExtra("highScore", 0)

        textView = findViewById(R.id.textViewScore)
        textView.text = score.toString()

        textView = findViewById(R.id.textViewWaves)
        textView.text = wavesPassed.toString()

        textView = findViewById(R.id.textViewHighScore)
        textView.text = highScore.toString()

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@ResultsActivity, KotlinInvadersActivity::class.java)
            startActivity(intent)
        }
    }

}
