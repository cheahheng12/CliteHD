package com.example.myapplication


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView

import java.net.URL
import java.util.jar.Manifest

//extends JitsiMeetActivity
class Join : AppCompatActivity(), JitsiMeetActivityInterface {
    lateinit var room_name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.join)

        // calling the action bar (back button on the action bar)
        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val join_Meeting = findViewById<Button>(R.id.join_room_name)
        room_name = findViewById(R.id.RoomName)

        join_Meeting.setOnClickListener() {
            val temp = "test123"

            if (TextUtils.isEmpty(room_name.text.toString())) {
                room_name.setError("Please input")

            } else {
                if ((room_name.text.toString()).equals(temp) == true) {
                    val view = JitsiMeetView(this@Join)
                    val options: JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder()
                        // .setServerURL(URL("https://meet.jit.si"))
                        .setRoom("https://meet.jit.si/AbstractShirtsRejectFast")
                        // .setRoom("room1")
                        .setAudioMuted(false)
                        .setVideoMuted(false)
                        //.setToken()
                        .setAudioOnly(false)
                        //  .setWelcomePageEnabled(false)
                        .setConfigOverride("requireDisplayName", true)
                        .build()

                    view.join(options)
                    setContentView(view)
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        "cannot find the room",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            }


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

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }


}