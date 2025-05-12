package com.truewis.elladas

import CardActor
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
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Main : ApplicationAdapter() {
    private var stage: Stage? = null
    private var skin: Skin? = null

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
            MathUtils.roundPositive(stage!!.getWidth() / 2f - window.getWidth() / 2f).toFloat(),
            MathUtils.roundPositive(stage!!.getHeight() / 2f - window.getHeight() / 2f).toFloat()
        )
        window.addAction(Actions.sequence(Actions.alpha(0f), Actions.fadeIn(1f)))
        stage!!.addActor(window)

        val drawable = skin!!.getDrawable("button-normal") // or use a custom NinePatchDrawable
        val card = CardActor(stage!!, skin!!, drawable, "Do you accept the quest?")
        stage!!.addActor(card)


        Gdx.input.setInputProcessor(stage)
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        stage!!.act(Gdx.graphics.getDeltaTime())
        stage!!.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage!!.getViewport().update(width, height)
    }

    override fun dispose() {
        stage!!.dispose()
        skin!!.dispose()
    }
}
