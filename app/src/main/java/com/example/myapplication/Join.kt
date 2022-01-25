package com.example.myapplication


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//import org.jistsi.meet.sdk.JitsiMeetActivity

import java.net.URL

//extends JitsiMeetActivity
class Join : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join)

        // calling the action bar (back button on the action bar)
        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val join_Meeting=findViewById(R.id.join_room_name) as Button
        join_Meeting.setOnClickListener(){
         /*   val options: JitsiMeetConferenceOptions = Builder()
                .setServerURL(URL("https://meet.jit.si"))
                .setRoom("test123")
                .setAudioMuted(false)
                .setVideoMuted(false)
                .setAudioOnly(false)
                .setWelcomePageEnabled(false)
                .setConfigOverride("requireDisplayName", true)
                .build()*/
        }



    }
    // this event will enable the back
    // function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}