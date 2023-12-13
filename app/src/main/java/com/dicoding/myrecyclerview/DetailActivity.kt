package com.dicoding.myrecyclerview

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)




        val hero: Hero? = intent.getParcelableExtra("HERO_DATA")

        if (hero != null) {
            val imgphoto: ImageView = findViewById(R.id.img_gambar)
            val tv_judul: TextView = findViewById(R.id.tv_Judul)
            val tv_desc: TextView = findViewById(R.id.tv_description)
            val playButton: ImageView = findViewById(R.id.tombol_play)

            val imgPlay: ImageView = findViewById(R.id.imgPlay)
            val tv_name: TextView = findViewById(R.id.tv_namespo)

            imgphoto.setImageResource(hero.photo)
            tv_judul.text = hero.name
            tv_name.text = hero.name
            tv_desc.text = hero.description
            playButton.setOnClickListener {
                val videoIntent = Intent(this, VideoActivity::class.java)
                videoIntent.putExtra("videoId", hero.videoId)
                startActivity(videoIntent)
            }

            // Inisialisasi MediaPlayer
            mediaPlayer = MediaPlayer.create(this, hero.audioId) // Menggunakan audioId dari objek Hero

            imgPlay.setOnClickListener {
                if (mediaPlayer != null) {
                    if (!mediaPlayer!!.isPlaying) {
                        mediaPlayer!!.start()
                        imgPlay.setImageResource(R.drawable.ic_pause)

                    } else {
                        mediaPlayer!!.pause()

                        imgPlay.setImageResource(R.drawable.ic_play2)
                    }
                }
            }
        } else {
            // Handle kasus ketika intent tidak memiliki data HERO_DATA
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}