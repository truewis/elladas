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

    override fun create() {
        stage = Stage(FitViewport(640f, 480f))
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        val window = Window("Example screen", skin, "border")
        window.defaults().pad(4f)
        window.add("This is a simple Scene2D view.").row()
        val button = TextButton("Click me!", skin)
        button.pad(8f)
        button.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                button.setText("Clicked.")
            }
        })
        window.add<TextButton?>(button)
        window.pack()
        // We round the window position to avoid awkward half-pixel artifacts.
        // Casting using (int) would also work.
        window.setPosition(
            MathUtils.roundPositive(stage.width / 2f - window.getWidth() / 2f).toFloat(),
            MathUtils.roundPositive(stage.height / 2f - window.getHeight() / 2f).toFloat()
        )
        window.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(1f)))
        //stage!!.addActor(window)
        Scene2DSkin.defaultSkin = skin
        val card = CardActor(stage, skin, storyJson.keys.random(), arrayListOf(this::func))
        stage.addActor(card)
        statusBar = TopStatusBar(skin)
        stage.addActor(statusBar)
        stage.addActor(Separator())


        Gdx.input.inputProcessor = stage
    }

    fun func(s:CardActorState, key:String){
        val yesNumbers = listOf(storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["religion"]?.jsonPrimitive?.int ?:0,
            storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["antiquity"]?.jsonPrimitive?.int ?:0,
            storyJson[key]!!.jsonObject["yesDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?:0)
        val noNumbers = listOf(storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["religion"]?.jsonPrimitive?.int ?:0,
            storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["antiquity"]?.jsonPrimitive?.int ?:0,
            storyJson[key]!!.jsonObject["noDelta"]!!.jsonObject["economy"]?.jsonPrimitive?.int ?:0)
when(s){
    CardActorState.YES -> statusBar.updateValues(yesNumbers)
    CardActorState.YES_TILT -> statusBar.previewValues(yesNumbers)
    CardActorState.NEUTRAL -> statusBar.previewValues(listOf(0,0,0))
    CardActorState.NO_TILT -> statusBar.previewValues(noNumbers)
    CardActorState.NO -> statusBar.updateValues(noNumbers)
}
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
    }
}
