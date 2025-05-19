package com.truewis.elladas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Timer
import kotlin.math.min

class MusicManager {

    private val musicMap = mutableMapOf<String, Music>()
    private var currentMusic: Music? = null
    private var targetMusic: Music? = null

    private var fadeOutDuration = 1.5f
    private var fadeInDuration = 1.5f
    private var fadeStep = 0.05f

    private var isFadingOut = false
    private var isFadingIn = false

    private var fadeTimer: Timer.Task? = null

    fun loadMusic(name: String, filePath: String) {
        musicMap[name] = Gdx.audio.newMusic(Gdx.files.internal(filePath)).apply {
            isLooping = true
            volume = 0f
        }
    }

    fun playMusic(name: String) {
        val newMusic = musicMap[name] ?: return

        if (currentMusic == newMusic) return  // Already playing

        targetMusic = newMusic
        fadeOutCurrent()
    }

    private fun fadeOutCurrent() {
        currentMusic?.let { music ->
            isFadingOut = true
            fadeTimer?.cancel()
            fadeTimer = Timer.schedule(object : Timer.Task() {
                override fun run() {
                    val volume = music.volume - (fadeStep / fadeOutDuration)
                    if (volume <= 0f) {
                        music.stop()
                        music.volume = 0f
                        isFadingOut = false
                        currentMusic = null
                        this.cancel()
                        fadeInNext()
                    } else {
                        music.volume = volume
                    }
                }
            }, 0f, fadeStep)
        } ?: fadeInNext()
    }

    private fun fadeInNext() {
        targetMusic?.let { music ->
            music.play()
            music.volume = 0f
            currentMusic = music
            isFadingIn = true
            fadeTimer?.cancel()
            fadeTimer = Timer.schedule(object : Timer.Task() {
                override fun run() {
                    val volume = music.volume + (fadeStep / fadeInDuration)
                    if (volume >= 1f) {
                        music.volume = 1f
                        isFadingIn = false
                        this.cancel()
                    } else {
                        music.volume = min(1f, volume)
                    }
                }
            }, 0f, fadeStep)
        }
    }

    fun dispose() {
        musicMap.values.forEach { it.dispose() }
        fadeTimer?.cancel()
    }
}
