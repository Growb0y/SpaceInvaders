package com.example.kotlininvaders

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

class SoundPlayer(context: Context) {

    // For sound FX
    private val soundPool: SoundPool = SoundPool(10,
        AudioManager.STREAM_MUSIC,
        0)

    companion object {
        var playerExplodeID = -1
        var invaderExplodeID = -1
        var shootID = -1
        var uhID = -1
        var ohID = -1
        var coinCollectionID = -1
    }

    init {
        try {
            // Create objects of the 2 required classes
            val assetManager = context.assets

            // Load our fx in memory ready for use
            var descriptor: AssetFileDescriptor = assetManager.openFd("shoot.mp3")
            shootID = soundPool.load(descriptor, 0)

            descriptor = assetManager.openFd("invaderExplosion.mp3")
            invaderExplodeID = soundPool.load(descriptor, 0)

            descriptor = assetManager.openFd("playerExplosion.mp3")
            playerExplodeID = soundPool.load(descriptor, 0)

            descriptor = assetManager.openFd("uh.mp3")
            uhID = soundPool.load(descriptor, 0)

            descriptor = assetManager.openFd("oh.mp3")
            ohID = soundPool.load(descriptor, 0)

            descriptor = assetManager.openFd("coinCollection.mp3")
            coinCollectionID = soundPool.load(descriptor, 0)


        } catch (e: IOException) {
            // Print an error message to the console
            Log.e("error", "failed to load sound files")
        }
    }

    private var volume = 0.5f

    fun playSound(id: Int){
        volume = if (id == coinCollectionID || id == playerExplodeID) 1f else 0.1f
        soundPool.play(id, 1*volume, 1*volume, 0, 0, 1f)
    }
}