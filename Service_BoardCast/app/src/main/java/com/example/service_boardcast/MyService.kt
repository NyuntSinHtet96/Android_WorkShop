package com.example.service_boardcast

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import kotlin.random.Random

class MyService : Service() {

    private val mp3 = arrayListOf(R.raw.buddy,R.raw.sunny,R.raw.inspire,R.raw.ukulele,R.raw.memories,R.raw.acoustic_breeze,R.raw.creative_minds,R.raw.once_again,R.raw.tenderness)

    private var player: MediaPlayer ?= null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action=="rand_play")
        {
            val pos = Random.nextInt(0,mp3.size)

            stop()
            play(pos)
            broadCast(pos)
        }
        return super.onStartCommand(intent, flags, startId)
    }
    private fun play(pos: Int){
        player = MediaPlayer.create(this,mp3[pos])
        player?.start()
    }
    private fun stop(){
        player?.stop()
        player?.release()
        player = null
    }

    override fun onDestroy() {
        stop()
        super.onDestroy()
    }
    private fun broadCast(pos:Int){
        val intent = Intent("on_rand_play")
        intent.putExtra("music_name",resources.getResourceEntryName(mp3[pos]))
        sendBroadcast(intent)
    }
    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication channel to the service.")
    }
}