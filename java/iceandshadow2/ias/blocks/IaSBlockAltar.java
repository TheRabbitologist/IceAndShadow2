package iceandshadow2.ias.blocks;

import iceandshadow2.util.EnumIaSModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/*
 * A block to provide the formfactor for the altars used in Ice and Shadow.
 */

public class IaSBlockAltar extends IaSBaseBlockSingle {

	@SideOnly(Side.CLIENT)
	protected IIcon iconTop, iconSide, iconBottom;
	
	protected IaSBlockAltar(EnumIaSModule mod, String id) {
		super(mod, id, Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.setLightOpacity(7);
	}
	
	@Override
	public int getMobilityFlag() {
		return 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.iconTop = 
				reg.registerIcon("IceAndShadow:"+this.getModName()+"Top");
		this.iconSide = 
				reg.registerIcon("IceAndShadow:"+this.getModName()+"Side");
		this.iconBottom = 
				reg.registerIcon("IceAndShadow:"+this.getModName()+"Bottom");
		this.blockIcon = iconSide;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.iconBottom : (par1 == 1 ? this.iconTop : this.blockIcon);
    }
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
}
