package meowing.zen.features.general

import meowing.zen.Zen
import meowing.zen.Zen.Companion.prefix
import meowing.zen.utils.ChatUtils
import meowing.zen.utils.CommandUtils
import net.minecraft.command.ICommandSender
import javax.script.ScriptEngineManager

@Zen.Command
object Calculator : CommandUtils(
    "zencalc",
    "/zencalc <equation>",
    listOf("calc")
) {
    override fun processCommand(sender: ICommandSender?, args: Array<out String?>?) {
        if (args?.isEmpty() == true) return ChatUtils.addMessage("$prefix §fCommand: §c/zencalc <equation>")

        try {
            val equation = args!!.filterNotNull().joinToString(" ")
            val engine = ScriptEngineManager(null).getEngineByName("JavaScript") ?: return
            val sanitized = equation.replace(Regex("[^0-9+\\-*/().\\s]"), "")
            val result = engine.eval(sanitized) ?: return
            ChatUtils.addMessage("$prefix §b$equation §f= §b$result")
        } catch (e: Exception) {
            ChatUtils.addMessage("$prefix §fInvalid equation.")
            println("[Zen] Invalid equation: $e")
        }
    }
}