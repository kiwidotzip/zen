package meowing.zen.config

import meowing.zen.Zen
import meowing.zen.utils.TickUtils
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer

class GUICommand : CommandBase() {
    override fun getCommandName(): String? {
        return "zen"
    }

    override fun getCommandUsage(sender: ICommandSender?): String? {
        return "/zen - Opens the Zen Config GUI"
    }

    override fun getRequiredPermissionLevel(): Int {
        return 0
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String?>?) {
        if (sender is EntityPlayer)
            TickUtils.schedule(1) {
                Zen.openConfig()
            }
    }
}