package org.github.henryquan.pazusoba_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.coroutineScope
import org.github.henryquan.pazusoba_android.core.Pazusoba
import org.github.henryquan.pazusoba_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var solver: Pazusoba = Pazusoba()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        solveBoard()
    }

    fun solve(view: android.view.View) {
        val stepValue = binding.stepSlider.values.first()
        val beamValue = binding.beamSizeSlider.values.first()

        val maxStep = (50 * stepValue).toInt()
        val beamSize = (beamValue * 10000).toInt()
        solver = Pazusoba(3, maxStep, beamSize)
        solveBoard()
    }

    /**
     * Run in a thread so UI is not blocked while solving the board
     */
    private fun solveBoard() {
        binding.sampleText.text = "Solving..."
        Thread {
            val routeString = solver.solve("GDHRGRGRBDHLHRBDGGHLLBBDHGGLBB")
            runOnUiThread {
                binding.sampleText.text = routeString
            }
        }.start()
    }
}