import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
import com.truewis.elladas.Main
import com.truewis.elladas.Main.Companion.exhaustedKeys
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.stack
import kotlin.math.abs

class CardActor(
    private val stage: Stage,
    private val skin: Skin,
    private val key: String,
    val onStateChange: ArrayList<(CardActorState, String) -> Unit> = arrayListOf()
) : Table() {


    var state: CardActorState = CardActorState.NEUTRAL
        set(value) {
            onStateChange.forEach { it(value, key) }
            field = value
        }

    private var startX = 0f
    private var startY = 0f
    private val threshold = 200f

    val stack = scene2d.stack{
        image(drawableName = "button-normal")
        label(key){
            wrap = true
            setAlignment(Align.center)
        }
    }

    init {
        Main.exhaustedKeys.add(key)
        width = 200f
        height = 350f
        setOrigin(Align.center)

        setPosition(
            (Gdx.graphics.width - width) / 2f,
            (Gdx.graphics.height - height) / 2f
        )
        add(stack).grow()

        addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                startX = this@CardActor.x
                startY = this@CardActor.y
                return true
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                this@CardActor.moveBy(Gdx.input.deltaX.toFloat(), 0f)
                state =  if (this@CardActor.x>startX) CardActorState.NO_TILT else CardActorState.YES_TILT
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (abs(this@CardActor.x - startX) > threshold) {
                    val direction = if (this@CardActor.x > startX) 1 else -1
                    swipeOffScreen(direction)
                } else {
                    addAction(Actions.moveTo(startX, startY, 0.2f))
                    state = CardActorState.NEUTRAL
                }
            }
        })
    }

    private fun swipeOffScreen(direction: Int) {
        state = if (direction>0) CardActorState.NO else CardActorState.YES
        val offX = if (direction > 0) Gdx.graphics.width.toFloat() else -width * 2
        addAction(
            Actions.sequence(
                Actions.moveTo(offX, startY, 0.3f),
                Actions.run {
                    remove()
                    generateNewCard()
                }
            )
        )
    }

    private fun generateNewCard() {
        val newCard = CardActor(stage, skin, (Main.storyJson.keys - exhaustedKeys).random(), onStateChange)
        stage.addActor(newCard)
    }

}

enum class CardActorState{
    YES, YES_TILT,NEUTRAL, NO_TILT, NO
}

