package com.truewis.elladas


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import ktx.scene2d.KTable
import ktx.scene2d.container
import ktx.scene2d.image
import ktx.scene2d.stack
import java.lang.Float.min

class MeterUI : Table(), KTable
{
    var cont: Container<Actor>
    var maxValue = 100f
    var value = 0f
        set(x) {
            field = min(x, maxValue)
            val oldVisualValue = getVisualValue()


            if (animateDuration > 0) {
                animateFromValue = oldVisualValue
                animateTime = animateDuration
            }

        }
    var vertical = false

    init
    {
        setOrigin(Align.center)
        stack {
            it.grow()
          container {
              fill()
            image("white") {color= Color.BLACK
              }
           }
            this@MeterUI.cont = container {
                image("white") {
                }
                align(Align.bottomLeft)
            }
            image("columnPatch"){
                color = Color.NAVY
            }
        }
    }


    private var animateInterpolation: Interpolation = Interpolation.linear
    var animateDuration = 0f
    var animateTime = 0f
    var animateFromValue = 0f

    fun getVisualValue(): Float {
        if (animateTime > 0) return animateInterpolation.apply(
            animateFromValue,
            value,
            1 - animateTime / animateDuration
        )
        return value
    }

    override fun act(delta:Float){
        super.act(delta)
        if (animateTime > 0) {
            animateTime -= delta
            val stage = getStage()
            if (stage != null && stage.getActionsRequestRendering()) Gdx.graphics.requestRendering()
        }
        if (this.vertical)
            this.cont.fill(1f, getVisualValue()/maxValue)
        else
            this.cont.fill(getVisualValue()/maxValue, 1f)
        this.cont.layout()
    }
}
