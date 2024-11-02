package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponents.databinding.FragmentNotificationBinding
import com.example.navigationcomponents.databinding.ItemViewBinding
import cusAdapter

class Notification_Frag : Fragment() {
       private lateinit var binding: FragmentNotificationBinding
       lateinit var adapter:cusAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNotificationBinding.inflate(inflater,container,false)
        adapter=cusAdapter(Constant.getdata(),requireContext())
        binding.recyclerview.adapter=adapter
        binding.recyclerview.layoutManager=LinearLayoutManager(requireContext())


        val view=binding.root
        // Inflate the layout for this fragment
        return view
    }
}