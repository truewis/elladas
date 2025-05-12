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
import kotlin.math.abs

class CardActor(
    private val stage: Stage,
    private val skin: Skin,
    private val background: Drawable,
    private val text: String
) : Table() {

    private var startX = 0f
    private val threshold = 200f

    init {
        width = 300f
        height = 400f
        setOrigin(Align.center)

        setPosition(
            (Gdx.graphics.width - width) / 2f,
            (Gdx.graphics.height - height) / 2f
        )

        val label = Label(text, skin)
        label.setWrap(true)
        label.setAlignment(Align.center)

        pad(20f)
        add(label).expand().fill().center()

        addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                startX = this@CardActor.x
                return true
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                this@CardActor.moveBy(Gdx.input.deltaX.toFloat(), 0f)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (abs(this@CardActor.x - startX) > threshold) {
                    val direction = if (this@CardActor.x > startX) 1 else -1
                    swipeOffScreen(direction)
                } else {
                    addAction(Actions.moveTo(startX, y, 0.2f))
                }
            }
        })
    }

    private fun swipeOffScreen(direction: Int) {
        val offX = if (direction > 0) Gdx.graphics.width.toFloat() else -width * 2
        addAction(
            Actions.sequence(
                Actions.moveTo(offX, y, 0.3f),
                Actions.run {
                    remove()
                    generateNewCard()
                }
            )
        )
    }

    private fun generateNewCard() {
        val newCard = CardActor(stage, skin, background, getRandomText())
        stage.addActor(newCard)
    }

    private fun getRandomText(): String {
        val texts = listOf(
            "Do you take the risky path?",
            "Trust the stranger?",
            "Accept the gift?",
            "Fight or flee?",
            "Turn back now?"
        )
        return texts.random()
    }
}
