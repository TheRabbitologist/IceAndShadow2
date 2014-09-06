package iceandshadow2.ias.items;

import iceandshadow2.IceAndShadow2;
import iceandshadow2.ias.IIaSModName;
import iceandshadow2.util.EnumIaSModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemShears;

public class IaSItemShears extends ItemShears implements IIaSModName {

	public IaSItemShears(EnumIaSModule mod, String texName) {
		super();
		this.setUnlocalizedName(mod.prefix+"Item"+texName);
		this.setTextureName(IceAndShadow2.MODID+':'+mod.prefix+texName);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon("IceAndShadow:" + this.getUnlocalizedName().split("\\.")[1]);
	}

	@Override
	public String getModName() {
		return this.getUnlocalizedName().substring(5);
	}

}