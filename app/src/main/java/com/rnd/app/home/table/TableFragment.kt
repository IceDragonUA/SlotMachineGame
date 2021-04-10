package com.rnd.app.home.table

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import com.rnd.app.R
import com.rnd.app.common.getDisplayHeight
import com.rnd.app.common.getDisplayWidth
import com.rnd.app.databinding.FragmentTableBinding

/**
 * @author Vladyslav Havrylenko
 * @since 27.01.2021
 */
class TableFragment : DialogFragment(){

    companion object {
        fun getInstance(): TableFragment = TableFragment()
    }

    private val dialogWidth: Int = R.dimen.dialog_width
    private val dialogHeight: Int  = R.dimen.dialog_height

    private var bind: FragmentTableBinding? = null
    private val binding get() = bind!!

    override fun onResume() {
        super.onResume()
        context?.let {
            val width = (it.getDisplayWidth() * ResourcesCompat.getFloat(resources, dialogWidth)).toInt()
            val height = (it.getDisplayHeight() * ResourcesCompat.getFloat(resources, dialogHeight)).toInt()
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }

}