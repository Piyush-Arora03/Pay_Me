package com.example.recyclerview

import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),actionmodelistener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CusAdapter
    private lateinit var data: ArrayList<model>
    private var actionmode:ActionMode?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter=CusAdapter(conatant.getdata(),this,this)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        binding.recyclerView.layoutManager=GridLayoutManager(this,2)

        binding.recyclerView.animate()

    }
//start action mode when no item is selected
    override fun startActionMode() {
        if(actionmode==null){
            actionmode=startSupportActionMode(actionModeCallback)
        }
    }

    override fun onItemSelected(selectedItemCount: Int) {
        if(selectedItemCount==0){
            actionmode?.finish()
        }
        else{
            actionmode?.title="$selectedItemCount selected"
            actionmode?.invalidate()
        }
    }
    private val actionModeCallback=object :ActionMode.Callback{
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu,menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
             return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when(item?.itemId){
                R.id.delete->{
                    adapter.deleteSelectedItems()
                    mode?.finish()
                    true
                }
                R.id.clear->{
                    adapter.clearSelection()
                    true
                }
                R.id.selectall->{
                    adapter.selectAll()
                    true
                }
                else ->false
        }}

        override fun onDestroyActionMode(mode: ActionMode?) {
           actionmode=null
            adapter.clearSelection()
        }

    }
}