package meowing.zen.config

import meowing.zen.config.ui.ConfigUI
import java.awt.Color

class ConfigAccessor(val configUI: ConfigUI) {
    val blockoverlayfill: Boolean get() = configUI.getConfigValue("blockoverlayfill") as? Boolean ?: false
    val blockoverlaycolor: Color get() = configUI.getConfigValue("blockoverlaycolor") as? Color ?: Color(0, 255, 255, 127)
    val blockoverlaywidth: Double get() = configUI.getConfigValue("blockoverlaywidth") as? Double ?: 2.0

    val entityhighlightplayercolor: Color get() = configUI.getConfigValue("entityhighlightplayercolor") as? Color ?: Color(0, 255, 255, 255)
    val entityhighlightmobcolor: Color get() = configUI.getConfigValue("entityhighlightmobcolor") as? Color ?: Color(255, 0, 0, 255)
    val entityhighlightanimalcolor: Color get() = configUI.getConfigValue("entityhighlightanimalcolor") as? Color ?: Color(0, 255, 0, 255)
    val entityhighlightothercolor: Color get() = configUI.getConfigValue("entityhighlightothercolor") as? Color ?: Color(255, 255, 255, 255)
    val entityhighlightwidth: Double get() = configUI.getConfigValue("entityhighlightwidth") as? Double ?: 2.0

    val arrowpoison: Boolean get() = configUI.getConfigValue("arrowpoison") as? Boolean ?: false

    val slayertimer: Boolean get() = configUI.getConfigValue("slayertimer") as? Boolean ?: false
    val slayerhighlightcolor: Color get() = configUI.getConfigValue("slayerhighlightcolor") as? Color ?: Color(0, 255, 255, 127)
    val slayerhighlightwidth: Double get() = configUI.getConfigValue("slayerhighlightwidth") as? Double ?: 2.0
    val slayerstats: Boolean get() = configUI.getConfigValue("slayerstats") as? Boolean ?: false
    val lasertimer: Boolean get() = configUI.getConfigValue("lasertimer") as? Boolean ?: false
    val vengdmg: Boolean get() = configUI.getConfigValue("vengdmg") as? Boolean ?: false

    val carrycounter: Boolean get() = configUI.getConfigValue("carrycounter") as? Boolean ?: false
    val carryvalue: String get() = configUI.getConfigValue("carryvalue") as? String ?: "1.3"
    val carrybosshighlight: Boolean get() = configUI.getConfigValue("carrybosshighlight") as? Boolean ?: false
    val carrybosscolor: Color get() = configUI.getConfigValue("carrybosscolor") as? Color ?: Color(0, 255, 255, 127)
    val carrybosswidth: Double get() = configUI.getConfigValue("carrybosswidth") as? Double ?: 2.0
    val carryclienthighlight: Boolean get() = configUI.getConfigValue("carryclienthighlight") as? Boolean ?: false
    val carryclientcolor: Color get() = configUI.getConfigValue("carryclientcolor") as? Color ?: Color(0, 255, 255, 127)
    val carryclientwidth: Double get() = configUI.getConfigValue("carryclientwidth") as? Double ?: 2.0
    val carrycountsend: Boolean get() = configUI.getConfigValue("carrycountsend") as? Boolean ?: false
    val keyhighlightcolor: Color get() = configUI.getConfigValue("keyhighlightcolor") as? Color ?: Color(0, 255, 255, 127)
    val keyhighlightwidth: Double get() = configUI.getConfigValue("keyhighlightwidth") as? Double ?: 2.0

    val cryptreminderdelay: Double get() = configUI.getConfigValue("cryptreminderdelay") as? Double ?: 2.0
    val nothunder: Boolean get() = configUI.getConfigValue("nothunder") as? Boolean ?: false
    val draftself: Boolean get() = configUI.getConfigValue("draftself") as? Boolean ?: false
    val autogetdraft: Boolean get() = configUI.getConfigValue("autogetdraft") as? Boolean ?: false
    val hidestatuseffects: Boolean get() = configUI.getConfigValue("hidestatuseffects") as? Boolean ?: false
    val leapmessage: String get() = configUI.getConfigValue("leapmessage") as? String ?: "Leaping to"

    val boxstarmobscolor: Color get() = configUI.getConfigValue("boxstarmobscolor") as? Color ?: Color(0, 255, 255, 127)
    val boxstarmobswidth: Double get() = configUI.getConfigValue("boxstarmobswidth") as? Double ?: 2.0

    fun getValue(key: String): Any? = configUI.getConfigValue(key)

    inline fun <reified T> getValue(key: String, default: T): T {
        return configUI.getConfigValue(key) as? T ?: default
    }
}