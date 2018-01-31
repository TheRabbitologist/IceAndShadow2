package iceandshadow2.nyx.forge;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import iceandshadow2.IaSFlags;
import iceandshadow2.api.IIaSDescriptive;
import iceandshadow2.ias.util.IaSItemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class IaSTooltipHandler {

	public IaSTooltipHandler() {
	}
	
	public void addTooltip(List l, String localized, boolean isWarning) {
		if(localized != null && !localized.isEmpty()) {
			final String format = isWarning?
				(
					EnumChatFormatting.DARK_RED.toString()+
					EnumChatFormatting.UNDERLINE.toString()
				):
				(	
					EnumChatFormatting.GRAY.toString()
				);
			l.add(
			format +
			EnumChatFormatting.ITALIC.toString() +
			localized +
			EnumChatFormatting.RESET);
		}
	}

	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent e) {
		Object it = IaSItemHelper.extractItem(e.itemStack);
		boolean isWarning = false;
		if(it instanceof IIaSDescriptive) {
			String localized = null;
			String unlocalized = 
					((IIaSDescriptive)it).getUnlocalizedHint(e.entityPlayer, e.itemStack);
				if(unlocalized != null && !unlocalized.isEmpty()) {
					isWarning = 
						((IIaSDescriptive)it).isHintWarning(e.entityPlayer, e.itemStack);
					if(isWarning || true) { //TODO: Inventory search for a player guide.
						final String prefix = isWarning?"ias2.warning.":"ias2.hint.";
						localized = LanguageRegistry.instance().getStringLocalization(prefix+unlocalized);
					}
			}
			if(isWarning)
				addTooltip(e.toolTip, localized, true);
			if(GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak)) {
				if(!isWarning)
					addTooltip(e.toolTip, localized, false);
				unlocalized = 
					((IIaSDescriptive)it).getUnlocalizedDescription(e.entityPlayer, e.itemStack);
				if(unlocalized != null && !unlocalized.isEmpty()) {
					localized = LanguageRegistry.instance().getStringLocalization("ias2.description."+unlocalized);
				}
				addTooltip(e.toolTip, localized, false);
			}
		}
	}
}