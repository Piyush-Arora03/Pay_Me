package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navigationcomponents.databinding.FragmentChooseRecieverBinding

class ChooseReciever_Frag : Fragment() {
 private var binding: FragmentChooseRecieverBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentChooseRecieverBinding.inflate(inflater, container, false)
        binding?.SendMoney?.setOnClickListener(){
            val recivername=binding?.recivername?.text.toString()
            //-> bundle data passing
//            val args=Bundle()
//            //args->data or arguments
//            args.putString("name",recivername)
//
//            val action= navOptions {
//                anim {
//                    enter = android.R.animator.fade_in
//                    exit = android.R.animator.fade_out
//                }
//            }
//            findNavController().navigate(R.id.sendMoney_Frag,args,action)

            //data passing using safe args
            val action=ChooseReciever_FragDirections.actionChooseRecieverFragToSendMoneyFrag(recivername)
            // nav graph pe ja k argument define karo phir wahi argument bhejna hoga har frag ko jo uspe jaegi
            findNavController().navigate(action)

        }



        // Inflate the layout for this fragment
        val view=binding?.root

        return view
    }


}