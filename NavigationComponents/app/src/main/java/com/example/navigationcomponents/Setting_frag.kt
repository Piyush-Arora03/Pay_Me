package com.example.navigationcomponents
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponents.databinding.FragmentHomeBinding
import com.example.navigationcomponents.databinding.FragmentSettingFragBinding

class Setting_frag : Fragment() {
   private lateinit var binding: FragmentSettingFragBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSettingFragBinding.inflate(inflater,container,false)
        binding.defaultamount.setText(Default_data.defaultdata.value.toString())
        binding.save.setOnClickListener(){
            val defalutvalue=binding.defaultamount.text.toString()
            Default_data.defaultdata.value=defalutvalue.toLong()
        }

        binding.aboutapp.setOnClickListener(){
            val action=MainNavGraphDirections.actionGlobalAboutAppFrag()
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        val view=binding.root
        return view
    }
}