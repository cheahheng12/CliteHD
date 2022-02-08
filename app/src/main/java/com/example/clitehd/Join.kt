package com.example.clitehd


import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView

import java.net.URL

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




            if (TextUtils.isEmpty(room_name.text.toString())) {
                room_name.setError("Please input")

            } else {
                    val view = JitsiMeetView(this@Join)
                    val options: JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder()
                         .setServerURL(URL("https://v2.clitehd.com"))
                        .setRoom("https://meet.jit.si/AbstractShirtsRejectFast")

                        .setAudioMuted(false)
                        .setVideoMuted(false)
                        //.setToken()
                        .setAudioOnly(false)
                        //  .setWelcomePageEnabled(false)
                        .setConfigOverride("requireDisplayName", true)
                        .build()

                    view.join(options)
                    setContentView(view)
                supportActionBar?.hide()


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