package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponents.databinding.FragmentHomeBinding

class HomeFrag : Fragment() {
 private var binding: FragmentHomeBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navController=findNavController()
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding?.SendMoney?.setOnClickListener(){
            val action=HomeFragDirections.actionHomeFragToChooseRecieverFrag()
            navController.navigate(action)
        }
        val view= binding?.root
        return view
    }


}