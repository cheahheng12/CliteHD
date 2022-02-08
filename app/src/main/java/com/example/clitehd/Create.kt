package com.example.clitehd

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView
import java.net.URL

class Create : AppCompatActivity(), JitsiMeetActivityInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create)


        val username_create=findViewById<EditText>(R.id.username)
        val pass_create=findViewById<EditText>(R.id.Password)

        val schedule = findViewById<Button>(R.id.schedule_btn)
        schedule.setOnClickListener() {
            val intent = Intent(this, com.example.clitehd.schedule::class.java)
            startActivity(intent)

        }

        val create_Mting = findViewById<Button>(R.id.Create_Meeting)
        create_Mting.setOnClickListener() {

            if(!TextUtils.isEmpty(username_create.text.toString())&& !TextUtils.isEmpty(pass_create.text.toString())){
                val view = JitsiMeetView(this@Create)
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
            else if(TextUtils.isEmpty(username_create.text.toString())){
                username_create.setError("Please input")
            }
            else if(TextUtils.isEmpty(pass_create.text.toString())){
                pass_create.setError("Please input")
            }


        }





    }

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }


}