package iceandshadow2.nyx.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.BonemealEvent;
import iceandshadow2.EnumIaSModule;
import iceandshadow2.api.EnumIaSAspect;
import iceandshadow2.api.IIaSBlockThawable;
import iceandshadow2.ias.blocks.IaSBaseBlockFalling;
import iceandshadow2.ias.blocks.IaSBaseBlockMulti;
import iceandshadow2.ias.blocks.IaSBaseBlockSingle;
import iceandshadow2.ias.util.IaSBlockHelper;
import iceandshadow2.ias.util.IaSWorldHelper;
import iceandshadow2.nyx.NyxBlocks;
import iceandshadow2.nyx.world.NyxChunkManager;

public class NyxBlockSnow extends IaSBaseBlockFalling {

	public NyxBlockSnow(String texName) {
		super(EnumIaSModule.NYX, texName, Material.craftedSnow);
		setHardness(0.3F);
		this.setHarvestLevel("spade", 0);
		setStepSound(Block.soundTypeSnow);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.snowball, 4), new ItemStack(this));
		this.setLightOpacity(5);
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z) {}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side != ForgeDirection.DOWN;
	}

	@Override
	public EnumIaSAspect getAspect() {
		return EnumIaSAspect.FROZEN;
	}
	
	// Apparently this stops updates during chunk generation, which should speed
	// things up.
	@Override
	public boolean func_149698_L() {
		return false;
	}
	
	@Override
	public void onFallenUpon(World w, int x, int y, int z, Entity e,
			float f) {
		e.fallDistance /= 2f;
		w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
		w.getBlock(x, y-1, z).onFallenUpon(w, x, y-1, z, e, f);
	}

	@Override
	public void onEntityWalking(World w, int x, int y, int z,
			Entity e) {
		if(e instanceof EntityMob) {
		} else {
			w.scheduleBlockUpdate(x, y, z, this, tickRate(w));
			w.getBlock(x, y-1, z).onEntityWalking(w, x, y-1, z, e);
		}
		super.onEntityWalking(w, x, y, z, e);
	}
	
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block bl)
    {
    	if(!IaSBlockHelper.isAir(bl))
    		p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
    }
	
}
