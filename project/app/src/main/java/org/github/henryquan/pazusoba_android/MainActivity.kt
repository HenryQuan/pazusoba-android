package org.github.henryquan.pazusoba_android

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.github.henryquan.pazusoba_android.core.Pazusoba
import org.github.henryquan.pazusoba_android.databinding.ActivityMainBinding
import org.github.henryquan.pazusoba_android.extensions.hideKeyboard

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var solver: Pazusoba = Pazusoba()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            boardInputField.setText(
                "GDHRGRGRBDHLHRBDGGHLLBBDHGGLBB",
                TextView.BufferType.EDITABLE
            )

            // 30 steps
            stepSlider.values = mutableListOf(0.6.toFloat())
            // 1000 beam
            beamSizeSlider.values = mutableListOf(0.1.toFloat())
        }

        // Click anywhere to dismiss the keyboard
        binding.root.setOnClickListener {
            it.hideKeyboard()
        }
    }

    fun solve(view: android.view.View) {
        val stepValue = binding.stepSlider.values.first()
        val beamValue = binding.beamSizeSlider.values.first()

        solver.beamSize = (beamValue * 10000).toInt()
        solver.maxStep = (50 * stepValue).toInt()
        solveBoard()
    }

    /**
     * Run in a thread so UI is not blocked while solving the board
     */
    private fun solveBoard() {
        binding.sampleText.text = "Solving..."
        val board = binding.boardInputField.text.toString()
        solver.solve(board) { route ->
            runOnUiThread {
                binding.sampleText.text = route
            }
        }
    }
}