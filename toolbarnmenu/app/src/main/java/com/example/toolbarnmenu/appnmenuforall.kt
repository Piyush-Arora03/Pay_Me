package com.example.toolbarnmenu

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

public open class appnmenuforall:AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.alway -> {
                Toast.makeText(this, "always", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.never -> {
                var intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                return true
            }

            R.id.collapseActionView -> {
                Toast.makeText(this, "collapseActionView", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.withText -> {
                Toast.makeText(this, "withText", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}