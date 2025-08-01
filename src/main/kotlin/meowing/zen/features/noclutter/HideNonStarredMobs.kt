package meowing.zen.features.noclutter

import meowing.zen.Zen
import meowing.zen.config.ui.ConfigUI
import meowing.zen.config.ui.types.ConfigElement
import meowing.zen.config.ui.types.ElementType
import meowing.zen.events.EntityEvent
import meowing.zen.features.Feature
import meowing.zen.utils.TickUtils
import meowing.zen.utils.Utils.removeFormatting
import net.minecraft.entity.item.EntityArmorStand

@Zen.Module
object HideNonStarredMobs : Feature("hidenonstarmobs", area = "catacombs") {
    private val regex = "^(?:\\[Lv\\d+] )?[\\w ]+ [\\d,.]+\\w(?:/[\\d,.]+\\w)?❤$".toRegex()
    private val blaze = "^\\[Lv15] Blaze [\\d,]+/([\\d,]+)❤$".toRegex()

    override fun addConfig(configUI: ConfigUI): ConfigUI {
        return configUI
            .addElement("No clutter", "Hide non-star mob names", ConfigElement(
                "hidenonstarmobs",
                "Hide non-starred mob nametags",
                ElementType.Switch(false)
            ), isSectionToggle = true)
    }

    override fun initialize() {
        register<EntityEvent.Join> { event ->
            TickUtils.scheduleServer(1) {
                val name = event.entity.name.removeFormatting()
                if (event.entity is EntityArmorStand && regex.matches(name) && !blaze.matches(name)) event.entity.setDead()
            }
        }
    }
}