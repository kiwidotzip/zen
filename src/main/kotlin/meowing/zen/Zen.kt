package meowing.zen

import meowing.zen.config.ConfigAccessor
import meowing.zen.config.ZenConfig
import meowing.zen.config.ui.ConfigUI
import meowing.zen.events.AreaEvent
import meowing.zen.events.EntityEvent
import meowing.zen.events.EventBus
import meowing.zen.events.GuiEvent
import meowing.zen.feats.Feature
import meowing.zen.feats.FeatureLoader
import meowing.zen.utils.ChatUtils
import meowing.zen.utils.DataUtils
import meowing.zen.utils.TickUtils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiInventory
import net.minecraft.event.ClickEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

data class firstInstall(val isFirstInstall: Boolean = true)

@Mod(modid = "zen", name = "Zen", version = "1.8.9", useMetadata = true, clientSideOnly = true)
class Zen {
    private var eventCall: EventBus.EventCall? = null
    private lateinit var dataUtils: DataUtils<firstInstall>

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        configUI = ZenConfig()
        config = ConfigAccessor(configUI)
        FeatureLoader.init()
        executePendingCallbacks()

        dataUtils = DataUtils("zen-data", firstInstall())

        eventCall = EventBus.register<EntityEvent.Join> ({ event ->
            if (event.entity == Minecraft.getMinecraft().thePlayer) {
                ChatUtils.addMessage(
                    "§c[Zen] §fMod loaded - §c${FeatureLoader.getModuleCount() + 1} §ffeatures",
                    "§c${FeatureLoader.getLoadtime()}ms §8- §c${FeatureLoader.getCommandCount()} commands §7| §c10 utils"
                )
                val data = dataUtils.getData()
                if (data.isFirstInstall) {
                    ChatUtils.addMessage("§c[Zen] §fThanks for installing Zen!")
                    ChatUtils.addMessage("§7> §fUse §c/zen §fto open the config or §c/zenhud §fto edit HUD elements")
                    ChatUtils.addMessage("§7> §cDiscord:§b [Discord]", "Discord server", ClickEvent.Action.OPEN_URL, "https://discord.gg/KPmHQUC97G")
                    dataUtils.setData(data.copy(isFirstInstall = false))
                    dataUtils.save()
                }
                UpdateChecker.checkForUpdates()
                eventCall?.unregister()
                eventCall = null
            }
        })

        EventBus.register<GuiEvent.Open> ({ event ->
            if (event.screen is GuiInventory) isInInventory = true
        })

        EventBus.register<GuiEvent.Close> ({
            isInInventory = false
        })

        EventBus.register<AreaEvent.Main> ({
            TickUtils.scheduleServer(1) {
                updateFeatures()
            }
        })

        EventBus.register<AreaEvent.Sub> ({
            TickUtils.scheduleServer(1) {
                updateFeatures()
            }
        })
    }

    companion object {
        val features = mutableListOf<Feature>()
        val mc = Minecraft.getMinecraft()
        var isInInventory = false
        private lateinit var configUI: ConfigUI
        lateinit var config: ConfigAccessor
        private val pendingCallbacks = mutableListOf<Pair<String, (Any) -> Unit>>()

        private fun updateFeatures() {
            features.forEach { it.update() }
        }

        private fun executePendingCallbacks() {
            pendingCallbacks.forEach { (configKey, callback) ->
                configUI.registerListener(configKey, callback)
            }
            pendingCallbacks.clear()
        }

        fun registerListener(configKey: String, instance: Any) {
            if (::configUI.isInitialized) {
                configUI.registerListener(configKey) { newValue ->
                    val isEnabled = newValue as? Boolean ?: false
                    if (instance is Feature) {
                        instance.onToggle(isEnabled)
                    } else {
                        if (isEnabled) MinecraftForge.EVENT_BUS.register(instance)
                        else MinecraftForge.EVENT_BUS.unregister(instance)
                    }
                }
            } else {
                pendingCallbacks.add(configKey to { newValue ->
                    val isEnabled = newValue as? Boolean ?: false
                    if (instance is Feature) {
                        instance.onToggle(isEnabled)
                    } else {
                        if (isEnabled) MinecraftForge.EVENT_BUS.register(instance)
                        else MinecraftForge.EVENT_BUS.unregister(instance)
                    }
                })
            }
        }

        fun registerCallback(configKey: String, callback: (Any) -> Unit) {
            if (::configUI.isInitialized) configUI.registerListener(configKey, callback)
            else pendingCallbacks.add(configKey to callback)
        }

        fun addFeature(feature: Feature) {
            features.add(feature)
            feature.addConfig(configUI)
        }

        fun openConfig() {
            mc.displayGuiScreen(configUI)
        }
    }
}