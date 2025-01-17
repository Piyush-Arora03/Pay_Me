package com.example.architecture_components

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_components.databinding.ActivityMain2Binding
import com.example.architecture_components.databinding.ContentMainBinding
import com.example.architecture_components.model.NoteAdapter
import com.example.architecture_components.Dao.NoteEntity
import com.example.architecture_components.utils.sampledata

class MainActivity2 : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    private lateinit var contentBindng : ContentMainBinding
    private lateinit var mNoteList: List<NoteEntity>
    private lateinit var mAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.fabAddNote.setOnClickListener {
           val intent= Intent(this,EditorActivity::class.java)
            startActivity(intent)
        }
        mNoteList= sampledata().getSampleData()
        contentBindng=ContentMainBinding.bind(findViewById(R.id.include))
        initRecyclerView()
        ShowData()



    }

    private fun ShowData() {
        mAdapter = NoteAdapter(mNoteList, this)
        contentBindng.noteRecyclerview.adapter = mAdapter
    }

    private fun initRecyclerView() {
        mRecyclerView=contentBindng.noteRecyclerview
        mRecyclerView.hasFixedSize()
        val layoutManager =LinearLayoutManager(this)
        mRecyclerView.layoutManager=layoutManager
    }


}