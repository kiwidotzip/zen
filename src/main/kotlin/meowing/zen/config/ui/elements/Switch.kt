package meowing.zen.config.ui.elements

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.AspectConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.animate
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.percent
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.dsl.toConstraint
import meowing.zen.utils.Utils.createBlock
import java.awt.Color

class Switch(
    private var isOn: Boolean = false,
    private val onChange: ((Boolean) -> Unit)? = null
) : UIContainer() {
    private val onColor = Color(100, 245, 255, 255)
    private val offColor = Color(35, 40, 45, 255)
    private val bgColor = Color(18, 22, 26, 255)

    private val handle: UIComponent

    init {
        setColor(bgColor)

        val bg = createBlock(6f).constrain {
            x = 0.pixels()
            y = 0.pixels()
            width = 100.percent()
            height = 100.percent()
        }.setColor(bgColor) childOf this

        handle = createBlock(6f).constrain {
            x = if (isOn) 70.percent() else 3.percent()
            y = CenterConstraint()
            width = AspectConstraint(1f)
            height = 80.percent()
        }.setColor(if (isOn) onColor else offColor) childOf bg

        onMouseClick {
            toggle()
        }
    }

    fun setValue(value: Boolean, skipAnimation: Boolean = false) {
        isOn = value

        if (skipAnimation) {
            handle.constrain {
                x = if (isOn) 70.percent() else 3.percent()
            }
            handle.setColor(if (isOn) onColor else offColor)
        } else {
            handle.animate {
                setXAnimation(Animations.OUT_EXP, 0.5f, if (isOn) 70.percent() else 3.percent())
                setColorAnimation(Animations.OUT_EXP, 0.5f, (if (isOn) onColor else offColor).toConstraint())
            }
        }
    }

    fun toggle() {
        setValue(!isOn)
        onChange?.invoke(isOn)
    }

    fun getValue(): Boolean = isOn
}