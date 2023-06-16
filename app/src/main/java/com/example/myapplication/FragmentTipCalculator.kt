package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.TipCalculatorLayoutBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class FragmentTipCalculator : Fragment() {
    private var _binding: TipCalculatorLayoutBinding? = null
    private val binding get() = _binding
    private lateinit var inputBillAmount: TextInputEditText
    private lateinit var viewPercent: TextView
    private lateinit var viewTip: TextView
    private lateinit var viewTotal: TextView
    private lateinit var increaseBtn: MaterialButton
    private lateinit var decreaseBtn: MaterialButton
    private var percent: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TipCalculatorLayoutBinding.inflate(inflater, container, false)
        val view = binding?.root

        inputBillAmount = binding?.inputBillAmount!!
        viewPercent = binding?.viewPercent!!
        viewTip = binding?.viewTip!!
        viewTotal = binding?.viewTotal!!
        increaseBtn = binding?.increaseBtn!!
        decreaseBtn = binding?.decreaseBtn!!

        percent = viewPercent.text.toString().replace("%", "").toInt()
        updateButtonState(percent)
        increaseBtn.setOnClickListener {
            if (canClickIncrease()) {
                percent += 5
                viewPercent.text = getString(R.string.percent, percent.toString())
                calculateTip()
            } else {
                increaseBtn.isEnabled = false
            }
            updateButtonState(percent)
        }
        decreaseBtn.setOnClickListener {
            if (canClickDecrease()) {
                percent -= 5
                viewPercent.text = getString(R.string.percent, percent.toString())
                calculateTip()
            } else {
                decreaseBtn.isEnabled = false
            }
            updateButtonState(percent)
        }


        inputBillAmount.doAfterTextChanged {
            calculateTip()
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.inputBillAmount?.doAfterTextChanged {
            calculateTip()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun calculateTip() {
        val billAmount = inputBillAmount.text.toString().toFloatOrNull() ?: 0.0f
        val percent = viewPercent.text.toString().replace("%", "").toFloatOrNull() ?: 0.0f

        val tipAmount = billAmount * percent / 100
        val totalAmount = billAmount + tipAmount

        viewTip.text = "$${"%.2f".format(tipAmount)}"
        viewTotal.text = "$${"%.2f".format(totalAmount)}"
    }

    private fun canClickIncrease(): Boolean {
        return percent < 100
    }

    private fun canClickDecrease(): Boolean {
        return percent > 0
    }

    private fun updateButtonState(percent: Int) {
        increaseBtn.isEnabled = percent != 100
        decreaseBtn.isEnabled = percent != 0
    }
}