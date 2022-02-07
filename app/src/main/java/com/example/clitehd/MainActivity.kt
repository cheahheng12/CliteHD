package com.example.clitehd

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mail = "support@clitehd.com"
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_privacy -> {
                    val i =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.clitehd.com/privacy/"))
                    startActivity(i)
                }
                R.id.nav_terms -> {
                    val i =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.clitehd.com/tnc.html"))
                    startActivity(i)
                }
                R.id.nav_setting -> {
                    val intent = Intent(this, Setting::class.java)
                    startActivity(intent)
                }

                R.id.nav_getHelp -> {
                    sendEmail(mail)
                }
            }
            true
        }

        val join_page = findViewById(R.id.join_btn) as Button
        join_page.setOnClickListener() {
            val intent = Intent(this, Join::class.java)
            startActivity(intent)
        }

        val create_page = findViewById(R.id.create_btn) as Button
        create_page.setOnClickListener() {
            val intent = Intent(this, Create::class.java)
            startActivity(intent)
        }

    }


    //menu side bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    //email
    private fun sendEmail(mail: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))


        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }


}