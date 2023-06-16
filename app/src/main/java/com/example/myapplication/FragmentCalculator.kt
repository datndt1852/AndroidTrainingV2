package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.CalculaytorLayoutBinding
import com.google.android.material.textview.MaterialTextView

class FragmentCalculator : Fragment() {
    private var _binding: CalculaytorLayoutBinding? = null
    private val binding get() = _binding

    private lateinit var display: MaterialTextView
    private var operator: String? = null
    private var result: Int = 0
    private var inputNumber: String = "0"
    private var isInput: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalculaytorLayoutBinding.inflate(inflater, container, false)
        val view = binding?.root

        display = binding!!.resultNumber

        setupNumberButtons()
        setupOperatorButtons()
        setupEqualsButton()
        setupClearButton()
        return view
    }

    private fun setupClearButton() {
        binding?.clear?.setOnClickListener {
            result = 0
            inputNumber = "0"
            operator = null
            display.text = result.toString()
            binding?.clear?.text = "AC"
        }
    }

    private fun setupEqualsButton() {
        binding?.equal?.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        when (operator) {
            "+" -> {
                result += inputNumber.toInt()
            }
            "-" -> {
                result -= inputNumber.toInt()
            }
            "*" -> {
                result *= inputNumber.toInt()
            }
            "/" -> {
                result -= inputNumber.toInt()
            }
            else -> {
                /* no-op */
            }
        }
        display.text = result.toString()
    }

    private fun setupOperatorButtons() {
        binding?.plus?.setOnClickListener {
            result += inputNumber.toInt()
            if (operator != null) {
                display.text = result.toString()
            }
            operator = "+"
            inputNumber = ""
        }
        binding?.minus?.setOnClickListener {
            if (operator == null) {
                result += inputNumber.toInt()
            } else {
                result -= inputNumber.toInt()
                display.text = result.toString()
            }
            operator = "-"
            inputNumber = ""
        }
        binding?.multiply?.setOnClickListener {
            if (operator == null) {
                result += inputNumber.toInt()
            } else {
                result *= inputNumber.toInt()
                display.text = result.toString()
            }
            operator = "*"
            inputNumber = ""
        }
        binding?.divide?.setOnClickListener {
            try {
                if (operator == null) {
                    result += inputNumber.toInt()
                } else {
                    result -= inputNumber.toInt()
                    display.text = result.toString()
                }
            } catch (exception: Exception) {
                // handle divide zero
            }
            operator = "/"
            inputNumber = ""
        }
    }

    private fun setupNumberButtons() {
        binding?.one?.setOnClickListener {
            updateInputNumber(1)
        }
        binding?.two?.setOnClickListener {
            updateInputNumber(2)
        }
        binding?.three?.setOnClickListener {
            updateInputNumber(3)
        }
        binding?.four?.setOnClickListener {
            updateInputNumber(4)
        }
        binding?.five?.setOnClickListener {
            updateInputNumber(5)
        }
        binding?.six?.setOnClickListener {
            updateInputNumber(6)
        }
        binding?.seven?.setOnClickListener {
            updateInputNumber(7)
        }
        binding?.eight?.setOnClickListener {
            updateInputNumber(8)
        }
        binding?.nine?.setOnClickListener {
            updateInputNumber(9)
        }
        binding?.zero?.setOnClickListener {
            updateInputNumber(0)
        }
    }

    private fun updateInputNumber(inputNumber: Int) {
        this.inputNumber = if (this.inputNumber == "0")
            "$inputNumber"
        else
            "${this.inputNumber}$inputNumber"
        display.text = this.inputNumber
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}