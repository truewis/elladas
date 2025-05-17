package com.truewis.elladas

import CardActor
import CardActorState
import TopStatusBar
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Window
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import kotlinx.serialization.json.*
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.scene2d.Scene2DSkin
import java.io.File

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Main : ApplicationAdapter() {
    private lateinit var stage: Stage
    private lateinit var skin: Skin
    private lateinit var statusBar:TopStatusBar
    private lateinit var separator: Separator
    private lateinit var window:Window
    private lateinit var endingDescription:Label
    val gState = hashMapOf("religion" to 50, "antiquity" to 50, "economy" to 50, "time" to 0)

    fun startAgain(){
        gState["religion"] = 50
        gState["antiquity"] = 50
        gState["economy"] = 50
        gState["time"] = 0
        exhaustedKeys.clear()
        val card = CardActor(stage, skin, "tutorial", arrayListOf(this::func), gState)
        stage.addActor(card)
        statusBar.updateValues(gState)
        separator.setVis(left = false, right = false)
        window.isVisible = false
    }
    override fun create() {
        stage = Stage(FitViewport(640f, 480f))
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        window = Window("Game Over", skin, "border")
        endingDescription= Label("", skin)
        endingDescription.wrap = true

        window.defaults().pad(4f)
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
                storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?: 0
            )
            val noNumbers = listOf(
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["religion"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["antiquity"]?.jsonPrimitive?.int ?: 0,
                storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?: 0
            )

            val rightText = storyJson[key]!!.jsonObject["yes"]?.jsonPrimitive?.content ?: "YES"
            val leftText = storyJson[key]!!.jsonObject["no"]?.jsonPrimitive?.content ?: "NO"
            when (s) {
                CardActorState.YES -> {
                    gState["religion"] = gState["religion"]!! + yesNumbers[0]
                    gState["antiquity"] = gState["antiquity"]!! + yesNumbers[1]
                    gState["economy"] = gState["economy"]!! + yesNumbers[2]
                    statusBar.updateValues(gState)
                }

                CardActorState.YES_TILT -> {
                    statusBar.previewValues(yesNumbers)
                    separator.setVis(left = true, right = false)
                }

                CardActorState.NEUTRAL -> {
                    statusBar.previewValues(listOf(0, 0, 0))
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
                    statusBar.updateValues(gState)
                }
            }
        }
        else{
            val rightText = endingJson[key]!!.jsonObject["yes"]?.jsonPrimitive?.content ?: "YES"
            val leftText = endingJson[key]!!.jsonObject["no"]?.jsonPrimitive?.content ?: "NO"
            when (s) {
                CardActorState.YES -> {
                    ending(key)
                }

                CardActorState.YES_TILT -> {
                    separator.setVis(left = true, right = false)
                }

                CardActorState.NEUTRAL -> {
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
        endingDescription.setText(endingJson[key]!!.jsonObject["ending"]!!.jsonPrimitive.content)
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
        val endingKeys = listOf("religion", "lowReligion", "lowEconomy", "lowAntiquity", "mundane")

    }
}
