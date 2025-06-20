package meowing.zen.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Color
import cc.polyfrost.oneconfig.config.annotations.HUD
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.annotations.Text
import cc.polyfrost.oneconfig.config.core.OneColor
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.config.data.OptionSize
import meowing.zen.feats.carrying.CarryHud
import meowing.zen.feats.dungeons.FireFreezeHud
import meowing.zen.feats.slayers.VengTimer

class zenconfig : Config(
    Mod("Zen", ModType.UTIL_QOL, "/assets/modicon.svg"),
    "ZenConfig.json",
) {
    @JvmField
    @Switch(
        name = "Clean guild messages",
        description = "Reformats the Guild messages that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var guildmsg = false

    @JvmField
    @Switch(
        name = "Clean party messages",
        description = "Reformats the Guild messages that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var partymsg = false

    @JvmField
    @Switch(
        name = "Clean guild join messages",
        description = "Reformats the Guild join/leave message that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var guildjoinleave = false

    @JvmField
    @Switch(
        name = "Clean friend join messages",
        description = "Reformats the Friend join/leave message that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var friendjoinleave = false

    @JvmField
    @Switch(
        name = "Better AH messages",
        description = "Reformats the auction house messages that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var betterah = false

    @JvmField
    @Switch(
        name = "Better BZ messages",
        description = "Reformats the bazaar messages that you receive.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "Clean chat"
    )
    var betterbz = false

    @JvmField
    @Switch(
        name = "Custom player size",
        description = "Custom model size for your player.",
        size = OptionSize.DUAL,
        category = "General",
        subcategory = "Custom models"
    )
    var customsize = false

    @JvmField
    @Text(
        name = "Custom X size",
        description = "Custom model X value for your player.",
        size = OptionSize.DUAL,
        category = "General",
        subcategory = "Custom models",
        placeholder = "1.0"
    )
    var customX = "1"

    @JvmField
    @Text(
        name = "Custom Y size",
        description = "Custom model Y value for your player.",
        size = OptionSize.DUAL,
        category = "General",
        subcategory = "Custom models",
        placeholder = "1.0"
    )
    var customY = "1"

    @JvmField
    @Text(
        name = "Custom Z size",
        description = "Custom model Z value for your player.",
        size = OptionSize.DUAL,
        category = "General",
        subcategory = "Custom models",
        placeholder = "1.0"
    )
    var customZ = "1"

    @JvmField
    @Switch(
        name = "Disable hurt camera",
        description = "Disables the camera bob when you get hit",
        size = OptionSize.DUAL,
        category = "General",
        subcategory = "Misc"
    )
    var nohurtcam = false

    @JvmField
    @Switch(
        name = "World age message",
        description = "Sends the world age in your chat.",
        size = OptionSize.SINGLE,
        category = "General",
        subcategory = "World age"
    )
    var worldagechat = false

    @JvmField
    @Switch(
        name = "Slayer timer",
        description = "Slayer kill and spawn timer",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "General"
    )
    var slayertimer = false

    @JvmField
    @Switch(
        name = "Slayer highlight",
        description = "Highlights your slayer boss",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "General"
    )
    var slayerhighlight = false

    @JvmField
    @Switch(
        name = "Vengeance damage",
        description = "Sends your vengeance damage to the chat.",
        size = OptionSize.DUAL,
        category = "Slayers",
        subcategory = "Blaze"
    )
    var vengdmg = false

    @JvmField
    @Switch(
        name = "Vengeance timer",
        description = "Timer until vengeance procs.",
        size = OptionSize.DUAL,
        category = "Slayers",
        subcategory = "Blaze"
    )
    var vengtimer = false

    @HUD(
        name = "Vengeance hud",
        category = "Slayers",
        subcategory = "Blaze"
    )
    var vengtimerhud = VengTimer()

    @JvmField
    @Color(
        name = "Slayer highlight color",
        description = "Slayer highlight color",
        size = OptionSize.DUAL,
        category = "Slayers",
        subcategory = "General"
    )
    var slayerhighlightcolor = OneColor(0, 255, 255, 127)

    @JvmField
    @Slider(
        name = "Slayer highlight width",
        min = 1f, max = 10f,
        step = 1,
        category = "Slayers",
        subcategory = "General"
    )
    var slayerhighlightwidth = 2f

    @JvmField
    @Switch(
        name = "Carry counter",
        description = "Counts the carries automatically",
        size = OptionSize.DUAL,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carrycounter = false

    @JvmField
    @Text(
        name = "Carry value",
        description = "The values for the auto-add from trade in carry counter",
        size = OptionSize.DUAL,
        category = "Slayers",
        subcategory = "Carrying",
        placeholder = "1.3"
    )
    var carryvalue = "1.3"

    @JvmField
    @Switch(
        name = "Carry boss highlight",
        description = "Highlights your client's slayer boss",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carrybosshighlight = false

    @JvmField
    @Color(
        name = "Carry boss highlight color",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carrybosscolor = OneColor(0, 255, 255, 127)

    @JvmField
    @Slider(
        name = "Carry boss highlight width",
        min = 1f, max = 10f,
        step = 1,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carrybosswidth = 2f

    @JvmField
    @Switch(
        name = "Carry client highlight",
        description = "Highlights your client",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carryclienthighlight = false

    @JvmField
    @Color(
        name = "Carry client highlight color",
        size = OptionSize.SINGLE,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carryclientcolor = OneColor(0, 255, 255, 127)

    @JvmField
    @Slider(
        name = "Carry client highlight width",
        min = 1f, max = 10f,
        step = 1,
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carryclientwidth = 2f

    @HUD(
        name = "Carry Counter",
        category = "Slayers",
        subcategory = "Carrying"
    )
    var carryhud = CarryHud()

    @JvmField
    @Switch(
        name = "Auto meow",
        description = "Automatically responds with a random meow message when someone says \"meow\".",
        size = OptionSize.SINGLE,
        category = "Meowing",
        subcategory = "Auto meow"
    )
    var automeow = false

    @JvmField
    @Switch(
        name = "Meow death sounds",
        description = "Plays cat sounds when a mob dies and spawns some particles.",
        size = OptionSize.SINGLE,
        category = "Meowing",
        subcategory = "Meow Sounds"
    )
    var meowdeathsounds = false

    @JvmField
    @Switch(
        name = "Meow sounds",
        description = "Plays cat sounds when someone's message includes \"meow\".",
        size = OptionSize.SINGLE,
        category = "Meowing",
        subcategory = "Meow Sounds"
    )
    var meowsounds = false

    @JvmField
    @Switch(
        name = "Blood camp helper",
        description = "Sends information related to blood camping.",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Blood helper"
    )
    var bloodtimer = false

    @JvmField
    @Switch(
        name = "Terminal tracker",
        description = "Tracks the terminals/levers/devices that your party does.",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Terminals"
    )
    var termtracker = false

    @JvmField
    @Switch(
        name = "Key spawn alert",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Keys"
    )
    var keyalert = false

    @JvmField
    @Switch(
        name = "Key highlight",
        description = "Highlights the wither/blood key",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Keys"
    )
    var keyhighlight = false

    @JvmField
    @Color(
        name = "Key highlight color",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Keys"
    )
    var keyhighlightcolor = OneColor(0, 255, 255, 127)

    @JvmField
    @Slider(
        name = "Key highlight width",
        min = 1f, max = 10f,
        step = 1,
        category = "Dungeons",
        subcategory = "Keys"
    )
    var keyhighlightwidth = 2f

    @JvmField
    @Switch(
        name = "Party finder messages",
        description = "Custom party finder messages.",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Party finder"
    )
    var partyfindermsgs = false

    @JvmField
    @Switch(
        name = "Server lag timer",
        description = "Amount of difference between the client ticks and the serevr ticks",
        size = OptionSize.SINGLE,
        category = "Dungeons",
        subcategory = "Misc."
    )
    var serverlagtimer = false

    @JvmField
    @Switch(
        name = "Fire freeze timer",
        description = "Time until you should activate fire freeze",
        size = OptionSize.DUAL,
        category = "Dungeons",
        subcategory = "Fire freeze"
    )
    var firefreeze = false

    @HUD(
        name = "Fire freeze hud",
        category = "Dungeons",
        subcategory = "Fire freeze"
    )
    var firefreezehud = FireFreezeHud()

    init {
        initialize()
    }

    fun registerListener(option: String, callback: Runnable) = addListener(option, callback)
}