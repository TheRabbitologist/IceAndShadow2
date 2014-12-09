package iceandshadow2.nyx.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import iceandshadow2.EnumIaSModule;
import iceandshadow2.ias.items.IaSBaseItemSingle;
import iceandshadow2.nyx.NyxBlocks;
import iceandshadow2.nyx.world.gen.GenPoisonTrees;

public class NyxItemPlumPoisonFertile extends IaSBaseItemSingle {

	public NyxItemPlumPoisonFertile(String texName) {
		super(EnumIaSModule.NYX, texName);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z,
			int meta, float watA, float watB,
			float watC) {
		GenPoisonTrees nyxPoisonTree = new GenPoisonTrees();
		Block bl = world.getBlock(x, y, z);
		if (bl == Blocks.snow_layer || bl == NyxBlocks.permafrost) {
			if (nyxPoisonTree.generate(world, world.rand, x, y+1,
					z)) {
				stack.stackSize -= 1;
				world.playSoundAtEntity(player,
						"mob.zombie.infect", 0.5F,
						world.rand.nextFloat() * 0.1F + 0.9F);
				return true;
			}
		}
		return false;
	}
}