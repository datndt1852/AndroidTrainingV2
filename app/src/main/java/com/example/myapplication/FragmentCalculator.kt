package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.CalculaytorLayoutBinding
import com.google.android.material.textview.MaterialTextView

class FragmentCalculator(private val resultCalListener: ResultCalListener) : Fragment() {
    private var _binding: CalculaytorLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var display: MaterialTextView
    private var result: Double = 0.0
    private var currentNumber: Double = 0.0
    private var currentOperator: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CalculaytorLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        display = binding.resultNumber
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNumberButtons()
        setupOperatorButtons()
        setupEqualsButton()
        setupClearButton()

        binding.equal.setOnClickListener{
            resultCalListener.saveResultCal(result.toString())
        }
    }

    private fun setupClearButton() {
        binding.clear.setOnClickListener {
            clearDisplay()
        }
    }

    private fun setupEqualsButton() {
        binding.equal.setOnClickListener {
            if (currentOperator != null) {
                calculate()
                currentOperator = null
            }
        }
    }

    private fun calculate(
    ) {
        val operator = currentOperator ?: return
        val secondOperand = currentNumber

        when (operator) {
            "+" -> result += secondOperand
            "-" -> result -= secondOperand
            "*" -> result *= secondOperand
            "/" -> {
                if (secondOperand != 0.0) {
                    result /= secondOperand
                } else {
                    display.text = "Error"
                    return
                }
            }
        }
        display.text = result.toString()
        currentNumber = result

    }

    private fun setupOperatorButtons() {
        binding.plus.setOnClickListener {
            applyOperator("+")
        }
        binding.minus.setOnClickListener {
            applyOperator("-")
        }
        binding.multiply.setOnClickListener {
            applyOperator("*")
        }
        binding.divide.setOnClickListener {
            applyOperator("/")
        }
    }

    private fun applyOperator(operator: String) {
        if (currentOperator != null) {
            calculate()
        }
        currentOperator = operator
        currentNumber = display.text.toString().toDouble()
    }

    private fun setupNumberButtons() {
        binding.one.setOnClickListener {
            appendNumber(1)
        }
        binding.two.setOnClickListener {
            appendNumber(2)
        }
        binding.three.setOnClickListener {
            appendNumber(3)
        }
        binding.four.setOnClickListener {
            appendNumber(4)
        }
        binding.five.setOnClickListener {
            appendNumber(5)
        }
        binding.six.setOnClickListener {
            appendNumber(6)
        }
        binding.seven.setOnClickListener {
            appendNumber(7)
        }
        binding.eight.setOnClickListener {
            appendNumber(8)
        }
        binding.nine.setOnClickListener {
            appendNumber(9)
        }
        binding.zero.setOnClickListener {
            appendNumber(0)
        }
        binding.dot.setOnClickListener {
            appendDecimal()
        }
    }

    private fun appendNumber(number: Int) {
        val currentText = display.text.toString()
        val newText = if (currentText == "0") {
            number.toString()
        } else {
            currentText + number
        }
        display.text = newText
        currentNumber = newText.toDouble()
    }

    private fun appendDecimal() {
        val currentText = display.text.toString()
        if (!currentText.contains(".")) {
            val newText = currentText + "."
            display.text = newText
            currentNumber = newText.toDouble()
        }
    }

    private fun clearDisplay() {
        display.text = "0"
        result = 0.0
        currentNumber = 0.0
        currentOperator = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ResultCalListener {
        fun saveResultCal(result: String)
    }
}
