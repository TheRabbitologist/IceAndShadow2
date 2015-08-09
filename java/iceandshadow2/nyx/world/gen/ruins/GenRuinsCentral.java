package iceandshadow2.nyx.world.gen.ruins;

import iceandshadow2.IaSFlags;
import iceandshadow2.IceAndShadow2;
import iceandshadow2.ias.items.tools.IaSTools;
import iceandshadow2.nyx.NyxBlocks;
import iceandshadow2.nyx.NyxItems;
import iceandshadow2.nyx.items.tools.NyxItemBow;
import iceandshadow2.util.gen.Sculptor;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenRuinsCentral extends GenRuins {

	@Override
	public boolean canGenerateHere(World var1, Random r, int x, int y, int z) {
		return true;
	}

	@Override
	public void buildPass(World w, Random r, int x, int y, int z) {
		y = 156;
		for(int xit = -6; xit <= 6; xit += 2) {
			for(int zit = -6; zit <= 6; zit += 2)
				y = Math.min(w.getPrecipitationHeight(x+xit, z+zit), y);
		}
		Sculptor.sphere(w, x, y, z, 16, NyxBlocks.permafrost, 0);
		Sculptor.cylinder(w, x, y, z, 16, 256-y, Blocks.air, 0);
		Sculptor.cylinder(w, x, y, z, 12, 1, NyxBlocks.brickPale, 0);
		Sculptor.cube(w, x-7, y+4, z-1, x+7, y+4, z+1, Blocks.obsidian, 0);
		Sculptor.cube(w, x-1, y+4, z-7, x+1, y+4, z+7, Blocks.obsidian, 0);
		Sculptor.cube(w, x-2, y+4, z-2, x+2, y+4, z+2, Blocks.obsidian, 0);
		Sculptor.cube(w, x-1, y+4, z-1, x+1, y+4, z+1, NyxBlocks.cryingObsidian, 1);
		Sculptor.walls(w, x-7, y, z-7, x+7, y+9, z+7, Blocks.obsidian, 0);
		Sculptor.walls(w, x-6, y+10, z-6, x+6, y+10, z+6, Blocks.obsidian, 0);
		Sculptor.cube(w, x-7, y+5, z, x+7, y+6, z, Blocks.air, 0);
		Sculptor.cube(w, x, y+5, z-7, x, y+6, z+7, Blocks.air, 0);
		for(int i = 0; i < 3; ++i) {
			final int maxradi = 2+(i==0?1:0);
			Sculptor.blast(w, 
					x-6+r.nextInt(3), 
					y+5+r.nextInt(4), 
					z-7+i*5+r.nextInt(3), 2+r.nextInt(maxradi));
			Sculptor.blast(w, 
					x+8-r.nextInt(3), 
					y+5+r.nextInt(4), 
					z-7+i*5+r.nextInt(3), 2+r.nextInt(maxradi));
			Sculptor.blast(w,
					x-7+i*5+r.nextInt(3),
					y+5+r.nextInt(4),
					z-6+r.nextInt(3), 2+r.nextInt(maxradi));
			Sculptor.blast(w, 
					x-7+i*5+r.nextInt(3), 
					y+5+r.nextInt(4), 
					z+8-r.nextInt(3), 2+r.nextInt(maxradi));
		}
		for(int xit=-4; xit<=4; xit += 8) {
			for(int zit=-4; zit<=4; zit += 8)
				Sculptor.cube(w, x+xit, y, z+zit, x+xit, y+4, z+zit, Blocks.obsidian, 0);
		}
	}

	@Override
	public void damagePass(World w, Random r, int x, int y, int z) {
	}


	@Override
	public void rewardPass(World w, Random r, int x, int y, int z) {
		
		int ropechest = r.nextInt(4);
		int hookchest = r.nextInt(4);
		int bootchest = r.nextInt(4);
		int tightropeA = r.nextInt(6); //Deliberate, may not spawn.
		int tightropeB = r.nextInt(6); //Deliberate, may not spawn.
		int lorepages = r.nextInt(4);
		y = w.getPrecipitationHeight(x, z);
		for(int chestpos = 0; chestpos <= 3; ++chestpos) {
			// Create a chest.
			int xloc = x, zloc = z, chestf = 0;
			if (chestpos == 0) {
				zloc -= 4;
				xloc -= 4;
				chestf = 0x3;
			} else if (chestpos == 1) {
				zloc += 4;
				xloc -= 4;
				chestf = 0x4;
			} else if (chestpos == 2) {
				zloc += 4;
				xloc += 4;
				chestf = 0x2;
			} else {
				zloc -= 4;
				xloc += 4;
				chestf = 0x5;
			}
			w.setBlock(xloc, y, zloc, Blocks.chest, chestf, 0x2);

			// Start filling the chest with goodies.
			final TileEntityChest chestent = (TileEntityChest) w.getTileEntity(
					xloc, y, zloc);
			int quantity = 2 + r.nextInt(3);
			for(int i = 0; i < quantity; ++i) {
				ItemStack itemz = new ItemStack(Items.ender_pearl,2+r.nextInt(4));
				int rewardid = r.nextInt(100);

				// Sword or armor!
				if (rewardid < 10) {
					if (r.nextInt(3) == 0) {
						itemz = new ItemStack(Items.diamond_sword);
						itemz.addEnchantment(Enchantment.smite, 1 + r.nextInt(2));
					} else {
						switch (r.nextInt(6)) {
						case 0:
						case 1:
							itemz = new ItemStack(Items.diamond_chestplate);
							break;
						case 2:
						case 3:
							itemz = new ItemStack(Items.diamond_leggings);
							break;
						case 4:
							itemz = new ItemStack(Items.diamond_helmet);
							itemz.addEnchantment(Enchantment.thorns,
									1 + r.nextInt(2));
							break;
						case 5:
							itemz = new ItemStack(Items.diamond_boots);
							itemz.addEnchantment(Enchantment.featherFalling,
									1 + r.nextInt(2));
							break;
						}
						itemz.addEnchantment(Enchantment.protection,
								1 + r.nextInt(2));
					}
				}
				
				// Echir Ingots
				else if (rewardid < 25) {
					itemz = new ItemStack(NyxItems.echirIngot, 3+r.nextInt(5));
				}

				// Sanctified Bone
				else if (rewardid < 45) {
					itemz = new ItemStack(NyxItems.boneSanctified);
				}

				// Berries!
				else if (rewardid < 75) {
					itemz = new ItemStack(NyxItems.silkBerries,
							4 + r.nextInt(8));
				}

				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						itemz);
			}
			if(chestpos == tightropeA) {
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						new ItemStack(NyxItems.kitTightrope));
			}
			if(chestpos == tightropeB) {
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						new ItemStack(NyxItems.kitTightrope));
			}
			if(chestpos == lorepages) {
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						new ItemStack(NyxItems.page));
			}
			if(chestpos == hookchest) {
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						new ItemStack(NyxBlocks.hookClimbing));
			}
			if(chestpos == ropechest) {
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						new ItemStack(NyxItems.rope));
			}
			if(chestpos == bootchest) {
				ItemStack is = new ItemStack(Items.diamond_boots);
				is.addEnchantment(Enchantment.featherFalling, 2+r.nextInt(2));
				is.addEnchantment(Enchantment.unbreaking, 1+r.nextInt(2));
				chestent.setInventorySlotContents(
						1 + r.nextInt(chestent.getSizeInventory() - 1), 
						is);
			}
		}
	}

	@Override
	public String getLowercaseName() {
		return "central-ruins";
	}

}
