package com.truewis.elladas

import IndicatorLabel
import com.badlogic.gdx.scenes.scene2d.ui.Table
import ktx.scene2d.KTable
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.table

class Separator: Table(), KTable {
    private val leftTxt = IndicatorLabel(false)
    private val rightTxt = IndicatorLabel(true)
    init {
        top()
        setFillParent(true)
        padTop(20f)
        table {
            it.width(200f)
            it.growY()
            add().growY()
            row()
            add(this@Separator.leftTxt).fill().bottom()


        }
        image {
            it.width(240f)
            it.expandY()
        }
        table {
            it.width(200f)
            it.growY()
            add().growY()
            row()
            add(this@Separator.rightTxt).fill().bottom()



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
