package com.example.toolbarnmenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.InspectableProperty
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : appnmenuforall() {
    private var itemid:Int=0
    private var ispresent:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var appbar:Toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        itemid=101
        ispresent=false

        val btn:androidx.appcompat.widget.AppCompatButton=findViewById(R.id.btn)
        btn.setOnClickListener {
            if(ispresent){
                ispresent=false
                invalidateOptionsMenu()
            }
            else{
                ispresent=true
                invalidateOptionsMenu()
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            if (ispresent) {
                if (it.findItem(itemid) == null) {
                    val menuitem: MenuItem = it.add(0, itemid, 6, "Item")
                    menuitem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
                    menuitem.setIcon(R.drawable.ic_launcher_foreground)
                    menuitem.setOnMenuItemClickListener {
                        Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                }
            } else {
                it.removeItem(itemid)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }
}