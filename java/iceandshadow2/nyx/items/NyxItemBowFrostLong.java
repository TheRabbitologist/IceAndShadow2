package iceandshadow2.nyx.items;

import iceandshadow2.EnumIaSModule;
import iceandshadow2.IIaSModName;
import iceandshadow2.ias.interfaces.IIaSGlowing;
import iceandshadow2.nyx.entities.projectile.EntityIceArrow;
import iceandshadow2.util.IaSRegistration;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NyxItemBowFrostLong extends NyxItemBow implements IIaSModName,
		IIaSGlowing {

	public NyxItemBowFrostLong(String par1) {
		super(par1);
		this.setMaxDamage(256);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata) {
		return 2;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		final int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
		inuse = false;

		float var7 = var6 / (35.0F-this.getSpeedModifier(par1ItemStack)*2);
		var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

		if (var7 < 0.6F)
			return;

		if (var7 > 0.95F)
			var7 = 1.0F;

		var7 = var7 * var7;

		final int var9 = EnchantmentHelper.getEnchantmentLevel(
				Enchantment.power.effectId, par1ItemStack);
		final int var10 = EnchantmentHelper.getEnchantmentLevel(
				Enchantment.punch.effectId, par1ItemStack) + 1;
		final EntityIceArrow var8 = new EntityIceArrow(par2World,
				par3EntityPlayer, var7 * 3.5F, var10 + 3, var9 * 30 + 70);

		if (var7 > 0.95F) {
			var8.setIsCritical(true);
		}

		if (var9 > 0) {
			var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
		}

		var8.setKnockbackStrength(var10);

		if (!par3EntityPlayer.capabilities.isCreativeMode)
			par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() + 1);

		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F
				/ (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(var8);
		}
	}

	@Override
	public int getTimeForIcon(int mod, int index) {
		if(index == 2)
			return 35-(mod*2);
		if(index == 1)
			return 17-mod;
		return 0;
	}

	@Override
	public int getUpgradeCost() {
		return 9;
	}
}

