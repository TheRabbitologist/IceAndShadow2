package iceandshadow2.nyx.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceandshadow2.ias.items.IaSBaseItemMulti;
import iceandshadow2.ias.items.IaSBaseItemSingle;
import iceandshadow2.util.EnumIaSModule;

public class NyxExousium extends IaSBaseItemMulti {

	@SideOnly(Side.CLIENT)
	protected IIcon rockIcon, crystalIcon;
	
	public NyxExousium(String texName) {
		super(EnumIaSModule.NYX, texName, 3);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int dmg) {
		if(dmg == 2)
			return crystalIcon;
		if(dmg == 1)
			return rockIcon;
		return this.itemIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(this.getTexName()+"Dust");
		this.rockIcon = reg.registerIcon(this.getTexName()+"Rock");
		this.crystalIcon = reg.registerIcon(this.getTexName()+"Crystal");
	}

}