package org.github.henryquan.pazusoba_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.github.henryquan.pazusoba_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI() + "\n" + helloworldFromJNI()
    }

    /**
     * Native methods from the shared library
     */
    private external fun stringFromJNI(): String
    private external fun helloworldFromJNI(): String

    companion object {
        // Load the 'pazusoba_android' library on application startup
        init {
            System.loadLibrary("pazusoba_android")
        }
    }
}