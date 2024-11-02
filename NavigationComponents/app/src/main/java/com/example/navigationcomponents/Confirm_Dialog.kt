package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponents.databinding.FragmentConfirmDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Confirm_Dialog : BottomSheetDialogFragment() {
    private val args : Confirm_DialogArgs by navArgs()
    private var binding: FragmentConfirmDialogBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentConfirmDialogBinding.inflate(inflater,container,false)
        val view=binding?.root
        val name=args.name
        val ammount=args.ammount
        binding?.textView?.text="Do you want to send $ammount to $name"
        binding?.yes?.setOnClickListener(){
            Toast.makeText(requireContext(),"$ammount sent to $name",Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding?.no?.setOnClickListener(){
            dismiss()
        }
        // Inflate the layout for this fragment
        return view
    }

}