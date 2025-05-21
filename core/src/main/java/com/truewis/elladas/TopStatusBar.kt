import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBarA
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Stack
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.truewis.elladas.MeterUI
import ktx.scene2d.container
import ktx.scene2d.image
import ktx.scene2d.progressBar
import ktx.scene2d.scene2d
import ktx.scene2d.stack
import kotlin.math.abs

class TopStatusBar(skin: Skin) : Table() {


    data class StatusUI(
        val label: Label,
        val progressBar: ProgressBarA,
        val upArrow: Image,
        val downArrow: Image
    )

    private val statusNames = listOf("Religion", "Antiquity", "Economy", "Liberalism")
    private val statusUIs = mutableListOf<StatusUI>()
    private val yearText = Label("", skin)
    private val arrowSize: Float = 30f
    init {
        top().left()
        setFillParent(true)

        val container = Table(skin)
        container.setBackground("white")
        container.color = Color.NAVY


// inside init block, update the forEach block
        statusNames.forEach { statusName ->
            val label = Label(statusName, skin)
            val bar = ProgressBarA(0f, 100f, 1f, true, skin).apply {
                setValue(50f)
                animateDuration = 0.3f
            }



            val upArrow = scene2d.image("spinner-up").apply {
                isVisible = false
                color = Color(0f, 1f, 0f, 1f)
                setSize(arrowSize, arrowSize)
            }

            val downArrow = scene2d.image("spinner-down").apply {
                isVisible = false
                color = Color(1f, 0f, 0f, 1f)
                setSize(arrowSize, arrowSize)
            }

            animateArrow(upArrow)
            animateArrow(downArrow)

            val barColumn = Table()
            barColumn.add(upArrow).size(arrowSize).padBottom(2f).row()
            barColumn.add(scene2d.stack {
                add(bar)

                when(statusName)
                {
                    "Religion"->add(scene2d.image("religion").apply {
                        color = Color.NAVY
                    })
                    "Antiquity"->add(scene2d.image("columnPatch").apply {
                        color = Color.NAVY
                    })
                    "Economy"->add(scene2d.image("economy").apply {
                        color = Color.NAVY
                    })
                    "Liberalism"->add(
                            image("liberalism").apply {
                                color = Color.NAVY
                            }
                        )
                }

            }

            ).padBottom(2f).grow().row()
            barColumn.add(downArrow).size(arrowSize)

            container.add(barColumn).pad(20f).size(110f, 200f).fill()

            statusUIs.add(StatusUI(label, bar, upArrow, downArrow))
        }

        add(container).expand().top().fill(0.3f, 0.10f)
        row()
        add(yearText).growX().center()
    }
    fun updateValues(values: HashMap<String, Int>) {
        val keys = listOf("religion","antiquity", "economy", "liberalism")
        for (i in 0 until 4) {
            val status = statusUIs[i]
            val newVal = values[keys[i]]!!
            status.progressBar.setValue(newVal.toFloat())

            if (status.progressBar.value <= 20f)
                animateBars(status.progressBar) // Animate outline if value too low
            else {
                status.progressBar.clearActions()
                status.progressBar.setScale(1f)
            }
        }
        yearText.setText("Year: "+(2025 + values["time"]!!).toString())
        yearText.setFontScale(1f)
        yearText.setAlignment(Align.center)
    }


    fun previewValues(values: List<Int>) {
        for (i in 0 until 4) {
            val status = statusUIs[i]
            val newVal = values[i]


            // Animate arrows based on change
            if (newVal > 0) {
                status.upArrow.isVisible = true
                status.upArrow.setColor(0f, 1f, 0f, abs(newVal) /20f)
                status.downArrow.isVisible = false
            } else if (newVal < 0) {
                status.upArrow.isVisible = false
                status.downArrow.isVisible = true
                status.downArrow.setColor(1f, 0f, 0f, abs(newVal) /20f)
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

    private fun animateBars(arrow: Actor) {
        arrow.clearActions()
        val duration = 0.3f

        arrow.addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.alpha(0.5f, duration),
                    Actions.alpha(1f, duration)
                )
            )
        )
    }
}
