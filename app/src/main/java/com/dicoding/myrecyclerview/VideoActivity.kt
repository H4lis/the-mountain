package com.dicoding.myrecyclerview

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class VideoActivity : AppCompatActivity(){
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoId=intent.getIntExtra("videoId",-1)
        videoView=findViewById(R.id.vv_video)
        val videoPath="android.resource://${packageName}/${videoId}"
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.setMediaController(MediaController(this))
        videoView.start()

    }
}