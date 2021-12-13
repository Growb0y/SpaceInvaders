package com.example.kotlininvaders

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import kotlin.random.Random

class Coin(context: Context,
                 private val screenX: Int,
                 private val screenY: Int) {

    var isActive = true
    private var nextFrame = 1

    private var bitmap: Bitmap = BitmapFactory.decodeResource(
        context.resources, R.drawable.coin)

    private val k = 4.0f
    private val width = screenX / k
    private val height = bitmap.height / (bitmap.width / width)

    // frame width and padding between coins in png file
    private var w = (width/29*4).toInt()
    private var p = (width/28).toInt()

    private var fpsAccumulator = 0.0

    var currentFrame: Bitmap
    private var frame1: Bitmap
    private var frame2: Bitmap
    private var frame3: Bitmap
    private var frame4: Bitmap
    private var frame5: Bitmap
    private var frame6: Bitmap

    val position = RectF()

    private val speed  = 200f

    private val coinFlipTime = 0.12

    init{
        bitmap = Bitmap.createScaledBitmap(bitmap, width.toInt(), height.toInt(), false)
        frame1 = Bitmap.createBitmap(bitmap, 0*w, 0, 1*w, 1*bitmap.height)
        frame2 = Bitmap.createBitmap(bitmap, 1*w+1*p, 0, 1*w, 1*bitmap.height)
        frame3 = Bitmap.createBitmap(bitmap, 2*w+2*p, 0, 1*w, 1*bitmap.height)
        frame4 = Bitmap.createBitmap(bitmap, 3*w+3*p, 0, 1*w, 1*bitmap.height)
        frame5 = Bitmap.createBitmap(bitmap, 4*w+4*p, 0, 1*w, 1*bitmap.height)
        frame6 = Bitmap.createBitmap(bitmap, 5*w+5*p, 0, 1*w, 1*bitmap.height)
        currentFrame = frame1
        appear()
    }

    fun appear() {
        position.left = Random.nextInt(screenX - w).toFloat()
        position.top = -height
        position.right = position.left + w
        position.bottom = position.top + height
        isActive = true
    }

    fun update(fps: Long) {
        fpsAccumulator += 1/fps.toFloat()
        if (fpsAccumulator/coinFlipTime >= 1) {
            fpsAccumulator = 0.0
            when (nextFrame) {
                1 -> {
                    currentFrame = frame1
                }
                2 -> {
                    currentFrame = frame2
                }
                3 -> {
                    currentFrame = frame3
                }
                4 -> {
                    currentFrame = frame4
                }
                5 -> {
                    currentFrame = frame5
                }
                6 -> {
                    currentFrame = frame6
                    nextFrame = 0
                }
            }
            nextFrame++
        }

        position.top += speed / fps
        position.bottom += speed / fps
    }

}