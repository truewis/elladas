import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.truewis.elladas.Main
import com.truewis.elladas.Main.Companion.exhaustedKeys
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.stack
import ktx.scene2d.table
import kotlin.math.abs

class CardActor(
    private val stage: Stage,
    private val skin: Skin,
    private val key: String,
    val onStateChange: ArrayList<(CardActorState, String) -> Unit> = arrayListOf(),
    val gState:HashMap<String, Int>
) : Table() {


    var state: CardActorState = CardActorState.NEUTRAL
        set(value) {
            onStateChange.forEach { it(value, key) }
            field = value
        }
    private var startX = 0f
    private var startY = 0f
    private val threshold = 200f

    val img = scene2d.image {  }

    val stack = scene2d.stack{
        image(drawableName = "white"){
            drawable = TextureRegionDrawable(
                Main.instance.assetManager.get(
                    "image/paper.jpg",
                    Texture::class.java
                )!!
            )
        }
        var desc = ""
        if(key !in Main.endingKeys)
            desc = Main.storyJson[key]!!.jsonObject["question"]!!.jsonPrimitive.content
        else
            desc = Main.endingJson[key]!!.jsonObject["question"]!!.jsonPrimitive.content

        table {
            add(img).fill().pad(10f)
            row()
            label(desc) {
                it.grow()
                it.minHeight(350f)
                wrap = true
                setAlignment(Align.center)
                color = Color.BLACK
            }
        }
    }

    init {
        Main.exhaustedKeys.add(key)
        onStateChange.forEach { it(CardActorState.NEUTRAL, key) }
        width = 400f
        height = 600f


        var imgName = ""
        if(key !in Main.endingKeys)
            imgName = Main.storyJson[key]!!.jsonObject["image"]!!.jsonPrimitive.content
        else
            imgName = Main.endingJson[key]!!.jsonObject["image"]!!.jsonPrimitive.content
        Main.instance.assetManager.finishLoadingAsset<Texture>(imgName)
        img.drawable = TextureRegionDrawable(
            Main.instance.assetManager.get(
                imgName,
                Texture::class.java
            )!!
        )

        setOrigin(Align.center)
        add(stack).fill()
        setPosition(
            (Gdx.graphics.width - width) / 2f,
            (Gdx.graphics.height - height) / 2f - 60f
        )

        addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                startX = this@CardActor.x
                startY = this@CardActor.y
                return true
            }

            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                this@CardActor.moveBy(Gdx.input.deltaX.toFloat()*2, 0f)
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
        Main.flipSound.play()
        addAction(
            Actions.sequence(
                Actions.moveTo(offX, startY, 0.3f),
                Actions.run {
                    remove()
                    if(key !in Main.endingKeys)
                    generateNewCard()
                }
            )
        )
    }

    private fun generateNewCard() {
        isEnding().also {
            when (it){
                "religion"->Main.instance.musicManager.playMusic("religion")
                "lowReligion"->Main.instance.musicManager.playMusic("religion")
                "lowEconomy"->Main.instance.musicManager.playMusic("lowEconomy")
                "lowAntiquity"->Main.instance.musicManager.playMusic("lowAncient")
                "lowLiberalism"->Main.instance.musicManager.playMusic("lowLiberalism")
                "mundane"->Main.instance.musicManager.playMusic("lowLiberalism")
            }
            if (it != null) {
                val newCard =
                    CardActor(stage, skin, it, onStateChange, gState)
                stage.addActor(newCard)
            }
            else if (key == "title"){
                val newCard =
                    CardActor(stage, skin, "tutorial", onStateChange, gState)
                stage.addActor(newCard)

            }
            else {
                val newCard =
                    CardActor(stage, skin, (Main.storyJson.keys - exhaustedKeys).random(), onStateChange, gState)
                stage.addActor(newCard)
                Main.instance.gState["time"] = Main.instance.gState["time"]!!+1
            }
        }
    }

    private fun isEnding():String?{
        if(gState["religion"]!!>=100) return "religion"
        if(gState["religion"]!!<=0) return "lowReligion"
        if(gState["economy"]!!<=0) return "lowEconomy"
        if(gState["antiquity"]!!<=0) return "lowAntiquity"
        if(gState["liberalism"]!!<=0) return "lowLiberalism"
        if(gState["time"]!!>=20) return "mundane"
        return null
    }

}

enum class CardActorState{
    YES, YES_TILT,NEUTRAL, NO_TILT, NO
}

