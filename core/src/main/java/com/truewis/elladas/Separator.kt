package com.truewis.elladas

import IndicatorLabel
import com.badlogic.gdx.scenes.scene2d.ui.Table
import ktx.scene2d.KTable
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.stack
import ktx.scene2d.table

class Separator: Table(), KTable {
    private val leftTxt = IndicatorLabel(false)
    private val rightTxt = IndicatorLabel(true)
    init {
        setFillParent(true)
        stack {
            it.expand().fill(1f, 0.2f)
            it.bottom()
            add(this@Separator.leftTxt)
            add(this@Separator.rightTxt)
        }
    }
    fun setTexts(left:String, right:String){
        leftTxt.label.setText(left)
        rightTxt.label.setText(right)
    }
    fun setVis(left:Boolean, right:Boolean){
            leftTxt.isVisible = left
            rightTxt.isVisible = right
    }
}
