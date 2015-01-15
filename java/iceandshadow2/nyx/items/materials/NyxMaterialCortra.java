package iceandshadow2.nyx.items.materials;

import iceandshadow2.api.IaSEntityKnifeBase;
import iceandshadow2.api.IaSToolMaterial;
import iceandshadow2.nyx.NyxItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class NyxMaterialCortra extends IaSToolMaterial {

	private static ResourceLocation knife_tex = new ResourceLocation("iceandshadow2:textures/entity/nyxknife_cortra.png");

	@Override
	public String getMaterialName() {
		return "Cortra";
	}

	@Override
	public float getBaseSpeed() {
		return 6;
	}

	@Override
	public int getBaseLevel() {
		return 8;
	}

	@Override
	public int getDurability(ItemStack is) {
		return 256;
	}

	@Override
	public ResourceLocation getKnifeTexture(IaSEntityKnifeBase knife) {
		return knife_tex;
	}
	
	@Override
	public boolean isRepairable(ItemStack tool, ItemStack mat) {
		return (mat.getItem() == NyxItems.echirIngot && mat.isItemDamaged());
	}

	@Override
	protected Item getMaterialItem() {
		return NyxItems.cortraDust;
	}
}
