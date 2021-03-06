package iceandshadow2.nyx.blocks;

import java.util.ArrayList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import iceandshadow2.EnumIaSModule;
import iceandshadow2.api.EnumIaSAspect;
import iceandshadow2.ias.blocks.IaSBaseBlockMulti;
import iceandshadow2.nyx.NyxItems;

public class NyxBlockSalt extends IaSBaseBlockMulti {

	public NyxBlockSalt(String texName) {
		super(EnumIaSModule.NYX, texName, Material.rock, 2);
		setHardness(1.5F);
		setResistance(10F);
		this.setHarvestLevel("pickaxe", 0);
		setLightOpacity(5);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		final ArrayList<ItemStack> ret = new ArrayList<ItemStack>(1);
		ret.add(new ItemStack(NyxItems.salt, metadata>0?4:4+world.rand.nextInt(2+fortune)));
		return ret;
	}

	@Override
	public EnumIaSAspect getAspect() {
		return EnumIaSAspect.NATIVE;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess w, int x, int y, int z, int s) {
		if (w.getBlock(x, y, z) == this)
			return false;
		return super.shouldSideBeRendered(w, x, y, z, s);
	}
}
