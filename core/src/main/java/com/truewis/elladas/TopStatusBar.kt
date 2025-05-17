import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import ktx.scene2d.image
import ktx.scene2d.scene2d
import kotlin.math.min

class TopStatusBar(skin: Skin) : Table() {


    data class StatusUI(
        val label: Label,
        val progressBar: ProgressBar,
        val upArrow: Image,
        val downArrow: Image
    )

    private val statusNames = listOf("Religion", "Antiquity", "Economy", "Liberalism")
    private val statusUIs = mutableListOf<StatusUI>()
    private val arrowSize: Float = 24f
    init {
        top().left()
        setFillParent(true)
        pad(20f)

        val container = Table()

        statusNames.forEach { statusName ->
            val label = Label(statusName, skin)

            val bar = ProgressBar(0f, 100f, 1f, true, skin).apply {
                value = 50f
                setAnimateDuration(0.3f)
                width = 30f
                height = 100f
            }

            val upArrow = scene2d.image("button-normal").apply {
                isVisible = false
                color = Color(0f, 1f, 0f, 1f)
                setSize(arrowSize, arrowSize)
            }

            val downArrow = scene2d.image("button-normal").apply {
                isVisible = false
                color = Color(1f, 0f, 0f, 1f)
                setSize(arrowSize, arrowSize)
            }
            animateArrow(upArrow)
            animateArrow(downArrow)

            val barColumn = Table()
            barColumn.add(label).padBottom(5f).row()
            barColumn.add(upArrow).size(arrowSize).padBottom(2f).row()
            barColumn.add(bar).width(30f).height(100f).padBottom(2f).row()
            barColumn.add(downArrow).size(arrowSize)

            container.add(barColumn).pad(20f)
            statusUIs.add(StatusUI(label, bar, upArrow, downArrow))
        }

        add(container).expand().top()
    }
    fun updateValues(values: HashMap<String, Int>) {
        val keys = listOf("religion","antiquity", "economy", "liberalism")
        for (i in 0 until 4) {
            val status = statusUIs[i]
            val newVal = values[keys[i]]
            status.progressBar.value = newVal!!.toFloat()
        }
    }

    fun previewValues(values: List<Int>) {
        for (i in 0 until 4) {
            val status = statusUIs[i]
            val newVal = values[i]


            // Animate arrows based on change
            if (newVal > 0) {
                status.upArrow.isVisible = true
                status.downArrow.isVisible = false
            } else if (newVal < 0) {
                status.upArrow.isVisible = false
                status.downArrow.isVisible = true
            } else {
                status.upArrow.isVisible = false
                status.downArrow.isVisible = false
            }
        }
    }

    private fun animateArrow(arrow: Actor) {
        arrow.clearActions()

        val moveAmount = 5f
        val duration = 0.3f

        arrow.addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.moveBy(0f, moveAmount, duration),
                    Actions.moveBy(0f, -moveAmount, duration)
                )
            )
        )
    }
}
