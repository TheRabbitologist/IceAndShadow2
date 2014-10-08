package iceandshadow2.nyx.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iceandshadow2.ias.items.IaSBaseItemMulti;
import iceandshadow2.util.EnumIaSModule;

public class NyxItemExousium extends IaSBaseItemMulti {

	@SideOnly(Side.CLIENT)
	protected IIcon rockIcon, crystalIcon;
	
	public NyxItemExousium(String texName) {
		super(EnumIaSModule.NYX, texName, 3);
		GameRegistry.addShapelessRecipe(new ItemStack(this,1,1), 
				new ItemStack(this,1,0), new ItemStack(this,1,0),
				new ItemStack(this,1,0), new ItemStack(this,1,0));
		GameRegistry.addShapelessRecipe(new ItemStack(this,1,2), 
				new ItemStack(this,1,1), new ItemStack(this,1,1), 
				new ItemStack(this,1,1), new ItemStack(this,1,1));
		GameRegistry.addShapelessRecipe(new ItemStack(this,4,1), new ItemStack(this,1,2));
		GameRegistry.addShapelessRecipe(new ItemStack(this,4,0), new ItemStack(this,1,1));
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