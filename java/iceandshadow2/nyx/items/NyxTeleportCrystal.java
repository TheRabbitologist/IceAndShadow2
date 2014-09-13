package iceandshadow2.nyx.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import iceandshadow2.IaSFlags;
import iceandshadow2.ias.items.IaSBaseItemSingle;
import iceandshadow2.nyx.NyxBlocks;
import iceandshadow2.nyx.NyxItems;
import iceandshadow2.nyx.world.NyxTeleporter;
import iceandshadow2.util.EnumIaSModule;
import iceandshadow2.util.IaSEntityHelper;
import iceandshadow2.util.IaSPlayerHelper;

public class NyxTeleportCrystal extends IaSBaseItemSingle {

	@SideOnly(Side.CLIENT)
	protected IIcon empty;
	
	public NyxTeleportCrystal(String texName) {
		super(EnumIaSModule.NYX, texName);
		this.setMaxStackSize(1);
		GameRegistry.addShapelessRecipe(new ItemStack(this,1,0), 
				new ItemStack(Items.nether_star), 
				new ItemStack(Items.ender_pearl), 
				new ItemStack(Items.snowball));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int dmg) {
		if((dmg & 4) == 4)
			return empty;
		return this.itemIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister reg) {
		super.registerIcons(reg);
		empty = reg.registerIcon(this.getTexName()+"Empty");
	}

	@Override
	public void onUpdate(ItemStack pile, World earth,
			Entity tree, int time, boolean boule) {
		if(tree.worldObj.isRemote)
			return;
		if((pile.getItemDamage() & 2) == 0 && tree.dimension == IaSFlags.dim_nyx_id) {
			pile.setItemDamage(pile.getItemDamage()+2);
			if(tree instanceof EntityPlayer) {
				EntityPlayer ep = (EntityPlayer)tree;
				if(!IaSPlayerHelper.giveItem(ep, new ItemStack(NyxItems.seedObsidian,1)))
					IaSPlayerHelper.messagePlayer(ep, "Something flew out of the crystal and fell on the ground.");
			}
		}
		boolean active = true;
		if(tree.dimension != IaSFlags.dim_nyx_id) {
			active &= IaSEntityHelper.getTemperatureFloat(tree) <= 0.15;
			if((pile.getItemDamage() & 1) == 1)
				active &= IaSEntityHelper.getLight(tree) <= 6;
			else
				active &= IaSEntityHelper.getLight(tree) <= 5;
		}
		else {
			active = IaSEntityHelper.getBlock(tree,0,-0.1,0) == NyxBlocks.cryingObsidian;
			active &= pile.getItemDamage() < 4;
		}
		if(!active & (pile.getItemDamage() & 1) == 1)
			pile.setItemDamage(pile.getItemDamage()-1);
		else if(active & (pile.getItemDamage() & 1) == 0)
			pile.setItemDamage(pile.getItemDamage()+1);
	}


	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.block;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		if((par1ItemStack.getItemDamage() & 1) == 1)
			return pass == 0;
		return false;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if(player.dimension != IaSFlags.dim_nyx_id && count < 50)
			player.addPotionEffect(new PotionEffect(Potion.blindness.id,Math.min(50-count,25),0));
		else if(count < 40 && player.dimension == IaSFlags.dim_nyx_id)
			player.addPotionEffect(new PotionEffect(Potion.blindness.id,Math.min(50-count,25),0));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 140;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World wld,
			EntityPlayer pl) {
		if((is.getItemDamage() & 1) == 1) {
			if(pl instanceof EntityPlayerMP) {
				EntityPlayerMP plm = (EntityPlayerMP)pl;
				if (pl.dimension != IaSFlags.dim_nyx_id) {
					plm.mcServer
					.getConfigurationManager()
					.transferPlayerToDimension(
							plm,
							IaSFlags.dim_nyx_id,
							new NyxTeleporter(
									plm.mcServer
									.worldServerForDimension(IaSFlags.dim_nyx_id)));
				} else {
					if((is.getItemDamage() & 4) == 0)
						is.setItemDamage(is.getItemDamage()+4);
					plm.mcServer.getConfigurationManager()
					.transferPlayerToDimension(
							plm,
							0,
							new NyxTeleporter(plm.mcServer
									.worldServerForDimension(0)));
				}
			}
		}
		return is;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack heap, World order,
			EntityPlayer pwai) {
		if((heap.getItemDamage() & 4) == 4 && pwai.dimension == IaSFlags.dim_nyx_id) {
			IaSPlayerHelper.messagePlayer(pwai, "You find strange thoughts coming to your mind. Something about getting power from etherium cores made from merged etherium dust...");
			pwai.setItemInUse(heap,72000);
			return heap;
		}
		if((heap.getItemDamage() & 1) == 0) {
			if(pwai.dimension == IaSFlags.dim_nyx_id)
				IaSPlayerHelper.messagePlayer(pwai, "You find strange thoughts coming to your mind. Something about needing to be standing on crying obsidian...");
			else
				IaSPlayerHelper.messagePlayer(pwai, "The crystal barely responds. It seems to prefer cold and dark places.");
			pwai.setItemInUse(heap,72000);
			return heap;
		}
		pwai.setItemInUse(heap,
				this.getMaxItemUseDuration(heap));
		pwai.addPotionEffect(new PotionEffect(Potion.confusion.id,200,0));
		return heap;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_,
			EntityPlayer plai, int time) {
		if(time > 40)
			plai.removePotionEffect(Potion.confusion.id);
		super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, plai, time);
	}
}
