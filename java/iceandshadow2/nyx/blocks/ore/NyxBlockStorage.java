package iceandshadow2.nyx.blocks.ore;

import iceandshadow2.EnumIaSModule;
import iceandshadow2.ias.blocks.IaSBaseBlockSingle;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public abstract class NyxBlockStorage extends IaSBaseBlockSingle {

	public NyxBlockStorage(String texName) {
		super(EnumIaSModule.NYX, texName, Material.rock);
		this.setHarvestLevel("pickaxe", 0);
		setHardness(1.0F);
		setLuminescence(0.2F);
		setResistance(5.0F);
	}

	public abstract ItemStack getItems();
}
