package com.truewis.elladas

import CardActor
import CardActorState
import TopStatusBar
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.ObjectMap
import kotlinx.serialization.json.*
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.scene2d.Scene2DSkin
import java.io.File
import com.rafaskoberg.gdx.typinglabel.*

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Main : ApplicationAdapter() {
    private lateinit var stage: Stage
    private lateinit var skin: Skin
    private lateinit var statusBar:TopStatusBar
    private lateinit var separator: Separator
    private lateinit var window:Window
    private lateinit var endingDescription:TypingLabel
    private lateinit var endingImage:Image
    lateinit var musicManager: MusicManager
    val gState = hashMapOf("religion" to 30, "antiquity" to 30, "economy" to 30, "liberalism" to 30, "time" to 0)
    val assetManager = AssetManager()
    fun startAgain(){
        gState["religion"] = 30
        gState["antiquity"] = 30
        gState["economy"] = 30
        gState["liberalism"] = 30
        gState["time"] = 0
        exhaustedKeys.clear()
        musicManager.playMusic("main")
        val card = CardActor(stage, skin, "tutorial", arrayListOf(this::func), gState)
        stage.addActor(card)
        statusBar.updateValues(gState)
        separator.setVis(left = false, right = false)
        window.isVisible = false
    }
    override fun create() {
        instance = this
        stage = Stage(FitViewport(1280f, 960f))
        musicManager = MusicManager()
        flipSound = Gdx.audio.newSound(Gdx.files.internal("music/flip.wav"))

        // Load music at app start
        musicManager.loadMusic("lowAncient", "music/lowAncient.mp3")
        musicManager.loadMusic("lowEconomy", "music/lowEconomy.mp3")
        musicManager.loadMusic("lowLiberalism", "music/lowLiberalism.mp3")
        musicManager.loadMusic("main", "music/main.mp3")
        musicManager.loadMusic("religion", "music/religion.mp3")

        // Start playing "intro" music
        musicManager.playMusic("main")

        val resolver = InternalFileHandleResolver()
        assetManager.setLoader(
            Texture::class.java, TextureLoader(resolver)
        )
        assetManager.load("image/desk.jpg", Texture::class.java)
        assetManager.load("image/paper.jpg", Texture::class.java)

//        println("Explicit asset imports successful.")
//        assetManager.finishLoading()


        val gen = FreeTypeFontGenerator(Gdx.files.internal("byzantine.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 30
        //Include the below line for Unicode support
        //parameter.characters = Gdx.files.internal("korean2350.txt").readString("UTF-8")
        val nanum = gen.generateFont(parameter)
        val fontMap = ObjectMap<String, Any>()
        fontMap.put("byzantine", nanum)
        gen.dispose()
        val param = SkinLoader.SkinParameter(fontMap)
        assetManager.load("ui2.json", Skin::class.java, param)
        assetManager.finishLoading()


        storyJson.forEach {
            assetManager.load(it.value.jsonObject["image"]!!.jsonPrimitive.content, Texture::class.java)
        }

        endingJson.forEach {
            assetManager.load(it.value.jsonObject["image"]!!.jsonPrimitive.content, Texture::class.java)
        }


        skin = assetManager.get("ui2.json")
        stage.addActor(
            Image(TextureRegionDrawable(assetManager.get(
                "image/desk.jpg",
                Texture::class.java
            ))).apply{
                setFillParent(true)
            }
        )
        window = Window("Game Over", skin, "default")
        endingDescription= TypingLabel("", skin)
        endingDescription.wrap = true
        endingImage = Image()

        window.defaults().pad(4f)
        window.add(endingImage).grow().row()
        window.add(endingDescription).grow().row()
        val button = TextButton("New Game", skin)
        button.pad(8f)
        button.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                startAgain()
            }
        })
        window.add(button)
        window.pack()
        window.setFillParent(true)
        // We round the window position to avoid awkward half-pixel artifacts.
        // Casting using (int) would also work.
        window.setPosition(
            MathUtils.roundPositive(stage.width / 2f - window.getWidth() / 2f).toFloat(),
            MathUtils.roundPositive(stage.height / 2f - window.getHeight() / 2f).toFloat()
        )
        window.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(1f)))
        window.isVisible =false
        Scene2DSkin.defaultSkin = skin
        statusBar = TopStatusBar(skin)
        stage.addActor(statusBar)




        separator = Separator()
        stage.addActor(separator)
        separator.setVis(left = false, right = false)




        val card = CardActor(stage, skin, "tutorial", arrayListOf(this::func), gState)
        stage.addActor(card)



        stage.addActor(window)
        Gdx.input.inputProcessor = stage
    }

    fun func(s:CardActorState, key:String){
        if(key !in endingKeys) {
            val yesNumbers = listOf(
                storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["religion"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["antiquity"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["liberalism"]?.jsonPrimitive?.int ?: 0
            )
            val noNumbers = listOf(
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["religion"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["antiquity"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["liberalism"]?.jsonPrimitive?.int ?: 0
            )

            val rightText = storyJson[key]!!.jsonObject["yes"]?.jsonPrimitive?.content ?: "YES"
            val leftText = storyJson[key]!!.jsonObject["no"]?.jsonPrimitive?.content ?: "NO"
            when (s) {
                CardActorState.YES -> {
                    gState["religion"] = gState["religion"]!! + yesNumbers[0]
                    gState["antiquity"] = gState["antiquity"]!! + yesNumbers[1]
                    gState["economy"] = gState["economy"]!! + yesNumbers[2]
                    gState["liberalism"] = gState["liberalism"]!! + yesNumbers[3]
                    statusBar.updateValues(gState)
                }

                CardActorState.YES_TILT -> {
                    statusBar.previewValues(yesNumbers)
                    separator.setVis(left = true, right = false)
                }

                CardActorState.NEUTRAL -> {
                    statusBar.previewValues(listOf(0, 0, 0,0))
                    separator.setTexts(rightText, leftText)
                    separator.setVis(left = false, right = false)
                }

                CardActorState.NO_TILT -> {
                    statusBar.previewValues(noNumbers)
                    separator.setVis(left = false, right = true)
                }

                CardActorState.NO -> {
                    gState["religion"] = gState["religion"]!! + noNumbers[0]
                    gState["antiquity"] = gState["antiquity"]!! + noNumbers[1]
                    gState["economy"] = gState["economy"]!! + noNumbers[2]
                    gState["liberalism"] = gState["liberalism"]!! + noNumbers[3]
                    statusBar.updateValues(gState)
                }
            }
        }
        else{
            val rightText = endingJson[key]!!.jsonObject["answer"]?.jsonPrimitive?.content ?: "..."
            val leftText = endingJson[key]!!.jsonObject["answer"]?.jsonPrimitive?.content ?: "..."
            when (s) {
                CardActorState.YES -> {
                    ending(key)
                }

                CardActorState.YES_TILT -> {
                    separator.setVis(left = true, right = false)
                }

                CardActorState.NEUTRAL -> {
                    statusBar.previewValues(listOf(0, 0, 0,0))
                    separator.setTexts(rightText, leftText)
                    separator.setVis(left = false, right = false)
                }

                CardActorState.NO_TILT -> {
                    separator.setVis(left = false, right = true)
                }

                CardActorState.NO -> {
                    ending(key)
                }
            }
        }

    }
    fun ending(key:String){
        endingImage.drawable = TextureRegionDrawable(assetManager.get(
            endingJson[key]!!.jsonObject["image"]!!.jsonPrimitive.content,
                Texture::class.java
            )!!)
        endingDescription.restart(endingJson[key]!!.jsonObject["ending"]!!.jsonPrimitive.content)
        window.layout()
        window.isVisible = true
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }

    companion object {
        val exhaustedKeys = hashSetOf<String>()
        val storyJson = Json.parseToJsonElement(
            Gdx.files?.internal("story.json")?.readString() ?: File("../assets/story.json").readText()
        ).jsonObject

        val endingJson = Json.parseToJsonElement(
            Gdx.files?.internal("endings.json")?.readString() ?: File("../assets/endings.json").readText()
        ).jsonObject
        val endingKeys = listOf("religion", "lowReligion", "lowEconomy", "lowAntiquity", "mundane", "lowLiberalism")
        lateinit var flipSound: Sound

        lateinit var instance: Main

    }
}
