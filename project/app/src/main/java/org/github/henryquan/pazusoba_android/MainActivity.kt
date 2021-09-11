package org.github.henryquan.pazusoba_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.github.henryquan.pazusoba_android.core.Pazusoba
import org.github.henryquan.pazusoba_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = Pazusoba.solve("GDHRGRGRBDHLHRBDGGHLLBBDHGGLBB")
    }
}