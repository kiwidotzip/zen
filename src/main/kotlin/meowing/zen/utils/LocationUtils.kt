package meowing.zen.utils

import meowing.zen.events.*
import net.minecraft.network.play.server.S38PacketPlayerListItem
import net.minecraft.network.play.server.S3EPacketTeams
import net.minecraftforge.common.MinecraftForge

object Location {
    private val areaRegex = "^(?:Area|Dungeon): ([\\w ]+)$".toRegex()
    private val subAreaRegex = "^ ([⏣ф]) .*".toRegex()
    private val emoteRegex = "[^\\u0000-\\u007F]".toRegex()

    var area: String? = null
    var subarea: String? = null

    fun initialize() {
        MinecraftForge.EVENT_BUS.register(this)
        EventBus.register<PacketEvent.Received>({ event ->
            when (val packet = event.packet) {
                is S38PacketPlayerListItem -> {
                    if (packet.action == S38PacketPlayerListItem.Action.UPDATE_DISPLAY_NAME || packet.action == S38PacketPlayerListItem.Action.ADD_PLAYER) {
                        packet.entries?.forEach { entry ->
                            val displayName = entry.displayName?.unformattedText ?: return@forEach
                            val line = displayName.replace(emoteRegex, "")
                            if (areaRegex.matches(line)) {
                                val newArea = areaRegex.find(line)?.groupValues?.get(1) ?: return@forEach
                                if (newArea != area) {
                                    EventBus.post(AreaEvent(newArea))
                                    area = newArea.lowercase()
                                }
                            }
                        }
                    }
                }
                is S3EPacketTeams -> {
                    val teamPrefix = packet.prefix
                    val teamSuffix = packet.suffix
                    if (teamPrefix.isEmpty() || teamSuffix.isEmpty()) return@register
                    val line = "$teamPrefix$teamSuffix"
                    if (subAreaRegex.matches(line))
                        if (line != subarea) {
                            EventBus.post(SubAreaEvent(line))
                            subarea = line.lowercase()
                        }
                }
            }
        })
    }
}