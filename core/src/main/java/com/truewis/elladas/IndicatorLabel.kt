import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import ktx.scene2d.KTable
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import kotlin.math.min

class IndicatorLabel(faceRight: Boolean) : Table(), KTable {
    lateinit var label:Label
    init {
        image("tree-plus") {  }
        label = label(""){

        }



        val moveAmount = 5f
        val duration = 0.3f

        addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.moveBy(moveAmount, 0f, duration),
                    Actions.moveBy(-moveAmount, 0f, duration)
                )
            )
        )
    }
    fun updateValues(value: String) {
        label.setText(value)
    }
}
