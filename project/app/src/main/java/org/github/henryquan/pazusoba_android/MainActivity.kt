package org.github.henryquan.pazusoba_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.coroutineScope
import org.github.henryquan.pazusoba_android.core.Pazusoba
import org.github.henryquan.pazusoba_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        solveBoard()
    }

    fun solve(view: android.view.View) {
        solveBoard()
    }

    /**
     * Run in a thread so UI is not blocked while solving the board
     */
    private fun solveBoard() {
        Thread {
            val stepList = Pazusoba.solve("GDHRGRGRBDHLHRBDGGHLLBBDHGGLBB")
            for (step in stepList) {
                Log.d("pazusoba", "${step} =>")
            }
            Log.d("pazusoba", "END")
        }.start()
    }
}