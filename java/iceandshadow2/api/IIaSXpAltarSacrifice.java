package iceandshadow2.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IIaSXpAltarSacrifice {
	public int getXpValue(World world, ItemStack is);
	public boolean rejectWhenZero();
}
