package iceandshadow2.nyx;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import iceandshadow2.IaSRegistry;
import iceandshadow2.api.IaSGrenadeLogic;
import iceandshadow2.ias.items.IaSBaseItemMulti;

public class NyxRecipes {

	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.teleportCrystal, 1, 2),
				new ItemStack(NyxItems.teleportCrystal, 1, 6), new ItemStack(NyxItems.alabaster));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.teleportCrystal, 1, 2),
				new ItemStack(NyxItems.teleportCrystal, 1, 6), new ItemStack(Items.nether_star));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.teleportCrystal, 1, 2),
				new ItemStack(NyxItems.teleportCrystal, 1, 4), new ItemStack(NyxItems.alabaster));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.teleportCrystal, 1, 2),
				new ItemStack(NyxItems.teleportCrystal, 1, 4), new ItemStack(Items.nether_star));

		GameRegistry.addSmelting(NyxBlocks.oreEchir, new ItemStack(NyxItems.echirIngot, 2, 1), 1);
		GameRegistry.addSmelting(NyxItems.echirDust, new ItemStack(NyxItems.echirIngot, 1, 1), 1);

		GameRegistry.addRecipe(new ItemStack(NyxItems.bread), "vvv", 'v', new ItemStack(NyxItems.vineBundle));
		GameRegistry.addRecipe(new ItemStack(Items.paper, 3), "v", "v", "v", 'v', new ItemStack(NyxItems.vineBundle));
		GameRegistry.addRecipe(new ItemStack(NyxItems.cookie, 8), "vbv", 'v', new ItemStack(NyxItems.vineBundle), 'b',
				new ItemStack(NyxItems.silkBerries));

		GameRegistry.addRecipe(new ItemStack(NyxItems.echirShears), "e ", " e", 'e',
				new ItemStack(NyxItems.echirIngot, 1, 1));

		GameRegistry.addRecipe(new ItemStack(NyxBlocks.brickFrozen, 4), "bb", "bb", 'b',
				new ItemStack(NyxBlocks.stone));

		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.poisonFruitFertile), new ItemStack(NyxItems.poisonFruit),
				new ItemStack(Items.dye, 15), new ItemStack(Items.dye, 15), new ItemStack(Items.dye, 15));

		GameRegistry.addShapelessRecipe(new ItemStack(NyxBlocks.planks, 4, 0), new ItemStack(NyxBlocks.poisonLog));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxBlocks.planks, 4, 1), new ItemStack(NyxBlocks.infestLog));

		GameRegistry.addSmelting(new ItemStack(NyxBlocks.poisonLog), new ItemStack(Blocks.log), 0); // Data
																									// values?
		GameRegistry.addSmelting(new ItemStack(NyxBlocks.infestLog), new ItemStack(Blocks.log), 0); // Data
																									// values?
		GameRegistry.addSmelting(new ItemStack(NyxBlocks.planks, 1, 0), new ItemStack(Blocks.planks, 1, 1), 0);
		GameRegistry.addSmelting(new ItemStack(NyxBlocks.planks, 1, 1), new ItemStack(Blocks.planks, 1, 2), 0);

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 1, 2), new ItemStack(NyxBlocks.planks, 1, 1),
				new ItemStack(NyxItems.exousium, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.log, 1), new ItemStack(NyxBlocks.infestLog),
				new ItemStack(NyxItems.exousium, 1, 0), new ItemStack(NyxItems.exousium, 1, 0),
				new ItemStack(NyxItems.exousium, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new ItemStack(NyxItems.toughGossamer));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 12), new ItemStack(NyxItems.rope));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.rope), new ItemStack(NyxItems.toughGossamer),
				new ItemStack(NyxItems.toughGossamer), new ItemStack(NyxItems.toughGossamer));

		GameRegistry.addRecipe(new ItemStack(NyxBlocks.hookClimbing, 2), "ee", "ee", "e ", 'e',
				new ItemStack(NyxItems.echirIngot, 1, 1));
		GameRegistry.addRecipe(new ItemStack(NyxItems.kitTightrope), " e ", "hrh", 'h',
				new ItemStack(NyxBlocks.hookClimbing), 'e', new ItemStack(NyxItems.echirIngot, 1, 1), 'r',
				new ItemStack(NyxItems.rope));

		GameRegistry.addRecipe(new ItemStack(NyxBlocks.transfusionAltar), "cdc", "coc",
				'd', new ItemStack(NyxItems.clockwork, 1, 2),
				'c', new ItemStack(NyxItems.cortra, 1, 1),
				'o', new ItemStack(NyxBlocks.sanctifiedObsidian));

		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.heat, 4, 2), new ItemStack(NyxItems.heat, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.heat, 4, 1), new ItemStack(NyxItems.heat, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.heat, 4, 0), new ItemStack(NyxItems.heat, 1, 1));

		/*
		 * GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.crystalVial,
		 * 1, 1), new ItemStack(NyxItems.crystalVial, 1), new
		 * ItemStack(NyxItems.draconium), new ItemStack(NyxItems.echirIngot, 1,
		 * 1));
		 *
		 * GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.crystalVial,
		 * 1, 1), new ItemStack(NyxItems.extractorPoison, 1, 13));
		 */

		// GameRegistry.addRecipe(new ItemStack(NyxItems.magicRepo,1,0), " c ",
		// "cac", " c ",
		// 'a', new ItemStack(NyxItems.alabaster),
		// 'c', new ItemStack(NyxItems.cortraIngot,1,1));

		GameRegistry.addSmelting(new ItemStack(NyxBlocks.permafrost), new ItemStack(NyxBlocks.dirt), 0);
		GameRegistry.addSmelting(new ItemStack(NyxBlocks.dirt, 1, 1), new ItemStack(NyxBlocks.dirt), 0);

		GameRegistry.addSmelting(NyxItems.alabaster, new ItemStack(Items.nether_star), 1);
		for (int i = 0; i < ((IaSBaseItemMulti) NyxItems.alabaster).getSubtypeCount(); ++i) {
			GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.alabasterShard, 9, i),
					new ItemStack(NyxItems.alabaster, 1, i));
		}

		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.toxicCore, 1, 1), new ItemStack(NyxItems.poisonFruit));
		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.salt, 1, 1), "ss", "ss", 's', new ItemStack(NyxItems.salt, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow, 4), new ItemStack(NyxItems.leaf),
				new ItemStack(NyxItems.icicle), new ItemStack(Items.stick));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow, 4), new ItemStack(NyxItems.leaf),
				new ItemStack(NyxItems.salt, 1, 1), new ItemStack(Items.stick));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow, 4), new ItemStack(NyxItems.leaf),
				new ItemStack(NyxItems.icicle), new ItemStack(Items.feather));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow, 4), new ItemStack(NyxItems.leaf),
				new ItemStack(NyxItems.salt, 1, 1), new ItemStack(Items.feather));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow, 4), new ItemStack(NyxItems.leaf),
				new ItemStack(Items.flint), new ItemStack(Items.stick));

		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.flask, 3), "s s", " s ", 's', new ItemStack(NyxItems.salt, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.salt, 1, 1), new ItemStack(NyxItems.flask));;

		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.rail, 24), "e e", "ese", "e e",
			'e', new ItemStack(NyxItems.echirIngot, 1, 1),
			's', new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.railPowered, 9), "e e", "ese", "ece",
				'e', new ItemStack(NyxItems.echirIngot, 1, 1),
				's', new ItemStack(Items.stick),
				'c', new ItemStack(NyxItems.cortra, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.clockwork, 1, 1), new ItemStack(NyxItems.clockwork, 1, 0),
				new ItemStack(NyxItems.cortraIngot, 1, 1), new ItemStack(NyxItems.cortraIngot, 1, 1),
				new ItemStack(NyxItems.cortraIngot, 1, 1), new ItemStack(NyxItems.cortraIngot, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.clockwork, 1, 2), new ItemStack(NyxItems.clockwork, 1, 0),
				new ItemStack(NyxItems.draconiumIngot, 1, 1), new ItemStack(NyxItems.draconiumIngot, 1, 1),
				new ItemStack(NyxItems.draconiumIngot, 1, 1), new ItemStack(NyxItems.draconiumIngot, 1, 1));
		GameRegistry.addSmelting(new ItemStack(NyxItems.clockwork, 1, 0), new ItemStack(NyxItems.echirIngot, 4, 1), 13);
		GameRegistry.addSmelting(new ItemStack(NyxItems.clockwork, 1, 1), new ItemStack(NyxItems.cortraIngot, 4, 1), 13);
		GameRegistry.addSmelting(new ItemStack(NyxItems.clockwork, 1, 2), new ItemStack(NyxItems.draconiumIngot, 4, 1), 13);
		
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.clockworkSmall, 4, 1), new ItemStack(NyxItems.clockwork, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.clockworkSmall, 1, 1), new ItemStack(NyxItems.clockwork, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.amberNugget, 4), new ItemStack(NyxItems.amber));
		
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.cursedPowder, 3), new ItemStack(NyxItems.boneCursed));
		
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.remoteDetonator, 1), new ItemStack(NyxItems.remote, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.remote, 1), new ItemStack(NyxItems.remoteDetonator, 1, 0));
		
		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.grenadeHandle, 2, 0)
				, "c ", "de", " e"
				, 'c', new ItemStack(NyxItems.cortraIngot, 1, 3)
				, 'd', new ItemStack(NyxItems.devora, 1, 1)
				, 'e', new ItemStack(NyxItems.echirIngot, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.grenadeHandle, 2, 1)
				, "c ", "dc", " c"
				, 'c', new ItemStack(NyxItems.cortraIngot, 1, 3)
				, 'd', new ItemStack(NyxItems.devora, 1, 1));
		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.grenadeHandle, 2, 0)
				, "d ", " n", " n"
				, 'n', new ItemStack(NyxItems.cortraIngot, 1, 3)
				, 'd', new ItemStack(NyxBlocks.detonator, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.grenadeHandle, 2, 1)
				, "d ", " n", " n"
				, 'n', new ItemStack(NyxItems.echirIngot, 1, 3)
				, 'd', new ItemStack(NyxBlocks.detonator, 1, 0));
		
		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.antenna, 2)
				, " c ", "ece", "cCr"
				, 'c', new ItemStack(NyxItems.cortraIngot, 1, 1)
				, 'e', new ItemStack(NyxItems.echirIngot, 1, 1)
				, 'C', new ItemStack(NyxItems.clockworkSmall)
				, 'r', new ItemStack(Items.redstone));
		
		GameRegistry.addShapedRecipe(new ItemStack(NyxItems.remote), "r", "b", "b"
				, 'r', new ItemStack(NyxBlocks.antenna)
				, 'b', new ItemStack(NyxItems.echirIngot, 1, 3));
		

		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.detonator, 1), "c", "d"
				, 'c', new ItemStack(NyxItems.cortraIngot, 1, 3)
				, 'd', new ItemStack(NyxItems.devora, 1, 1));
		GameRegistry.addShapedRecipe(new ItemStack(NyxBlocks.stableDevora, 2), "dgd", "grg", "dgd"
				, 'r', new ItemStack(NyxItems.resin, 1, 0)
				, 'd', new ItemStack(NyxItems.devora, 1, 0)
				, 'g', new ItemStack(NyxBlocks.gravel, 1, 0));

	}
	
	public static void lateInit() {
		for(int i = 0; i < IaSRegistry.getGrenadeLogicCount(); ++i) {
			final IaSGrenadeLogic evanescenceAndReason = IaSRegistry.getGrenadeLogic(i);
			final ItemStack
				a = evanescenceAndReason.getCraftingStack(false),
				b = evanescenceAndReason.getCraftingStack(true);
			if(a == null || b == null)
				continue;
			for(int j = 0; j <= 1; ++j) {
				GameRegistry.addShapelessRecipe(new ItemStack(NyxItems.grenade, 1, j|(i<<1))
					, new ItemStack(NyxItems.grenadeHandle, 1, j)
					, new ItemStack(NyxItems.flask, 1, 0)
					, a, b);
			}
		}
	}
}
