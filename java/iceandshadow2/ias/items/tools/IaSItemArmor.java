package iceandshadow2.ias.items.tools;

import iceandshadow2.EnumIaSModule;
import iceandshadow2.IIaSModName;
import iceandshadow2.api.EnumIaSAspect;
import iceandshadow2.api.IIaSAspect;
import iceandshadow2.ias.interfaces.IIaSGlowing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class IaSItemArmor extends ItemArmor implements IIaSModName, IIaSAspect, IIaSGlowing {

	protected IaSArmorMaterial mat;
	protected String armorTexString;

	public IaSItemArmor(IaSArmorMaterial arm, int renderIndex, int type, String body) {
		super(arm.getArmorStats(), renderIndex, type);
		mat = arm;
		armorTexString = body;
		setUnlocalizedName("iasArmor" + arm.getArmorStats().name() + armorType);
		setTextureName("IceAndShadow2:armor/iasArmor" + arm.getArmorStats().name() + armorType);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		if (entity.isInvisible())
			return "IceAndShadow2:textures/armor/the_invisible_man.png";
		if (slot == 2)
			return armorTexString + "_2.png";
		return armorTexString + "_1.png";
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return mat.getRenderPasses()>1;
	}



	@Override
	public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
		return itemIcon;
	}

	@Override
	public int getRenderPasses(int metadata) {
		return mat.getRenderPasses();
	}

	@Override
	public EnumIaSAspect getAspect() {
		return mat.getAspect();
	}

	@Override
	public int getDamage(ItemStack stack) {
		return !mat.isBreakable() ? 0 : super.getDamage(stack);
	}

	public IaSArmorMaterial getIaSArmorMaterial() {
		return mat;
	}

	@Override
	public EnumIaSModule getIaSModule() {
		return EnumIaSModule.IAS;
	}

	@Override
	public String getModName() {
		return this.getUnlocalizedName().substring(5);
	}

	@Override
	public EnumRarity getRarity(ItemStack is) {
		return mat.getRarity();
	}

	@Override
	@Deprecated
	public String getTextureName() {
		return "IceAndShadow2:armor/" + getModName();
	}

	@Override
	public boolean isDamageable() {
		return mat.isBreakable();
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return !mat.isBreakable() ? false : super.isDamaged(stack);
	}

	@Override
	public void onUpdate(ItemStack is, World w, Entity e, int i, boolean isHeld) {
		if (w.isRemote || !(e instanceof EntityLivingBase))
			return;
		final EntityLivingBase elb = (EntityLivingBase) e;
		final ItemStack armor = elb.getEquipmentInSlot(1 + armorType);
		if (armor == null || armor.getItem() != this)
			return;
		mat.onTick(elb, 1, false);
		super.onUpdate(is, w, e, i, isHeld);
	}

	@Override
	public int getFirstGlowPass(ItemStack is) {
		return mat.getFirstGlowPass(is);
	}

	@Override
	public boolean usesDefaultGlowRenderer() {
		return true;
	}
}