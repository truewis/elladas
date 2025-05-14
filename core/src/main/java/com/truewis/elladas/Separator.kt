package com.truewis.elladas

import com.badlogic.gdx.scenes.scene2d.ui.Table
import ktx.scene2d.KTable
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.table

class Separator: Table(), KTable {
    private val leftTxt = scene2d.label("LEFT")
    private val rightTxt = scene2d.label("RIGHT")
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
        debug()
    }
}
