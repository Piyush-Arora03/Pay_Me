package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponents.databinding.FragmentSendMoneyBinding

class SendMoney_Frag : Fragment() {
    private lateinit var binding: FragmentSendMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val args: SendMoney_FragArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        binding.ammountentered.setText(Default_data.defaultdata.value.toString())
        Default_data.defaultdata.observe(viewLifecycleOwner) {
            binding.ammountentered.setText(it.toString())
        }

        //bundle k through jo aaya tha vo recieve kiys hai
//        val name=arguments?.getString("name")
//        binding?.name?.text="send money to $name"

//safe args k through
        val name = args?.recievername
        binding?.name?.text = "send money to $name"

        binding?.send?.setOnClickListener() {
            if (binding?.ammountentered?.text.toString().isEmpty()) {
                return@setOnClickListener
            }
            val amount = binding?.ammountentered?.text.toString().toLong()
            val action =
                SendMoney_FragDirections.actionSendMoneyFragToConfirmDialog(name.toString(), amount)
            findNavController().navigate(action)
        }
        binding.done.setOnClickListener() {
            val action = SendMoney_FragDirections.actionSendMoneyFragToHomeFrag()
            findNavController().navigate(action)
        }
        // Inflate the layout for this fragment
        val view = binding?.root
        return view
    }

}