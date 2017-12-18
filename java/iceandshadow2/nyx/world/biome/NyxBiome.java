package iceandshadow2.nyx.world.biome;

import iceandshadow2.ias.util.IaSBlockHelper;
import iceandshadow2.ias.util.IaSWorldHelper;
import iceandshadow2.nyx.NyxBlocks;
import iceandshadow2.nyx.entities.mobs.EntityNyxSkeleton;
import iceandshadow2.nyx.world.gen.GenOre;
import iceandshadow2.nyx.world.gen.WorldGenNyxOre;
import iceandshadow2.nyx.world.gen.ruins.GenRuins;
import iceandshadow2.nyx.world.gen.ruins.GenRuinsGatestone;
import iceandshadow2.nyx.world.gen.ruins.GenRuinsTowerLookout;
import iceandshadow2.styx.Styx;

import java.util.Random; //Fuck you, Scala.

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class NyxBiome extends BiomeGenBase {

	private final boolean rare;

	WorldGenNyxOre genDevora, genEchir, genCortra, genDraconium, genGemstone, genUnstableIce;

	protected boolean doGenNifelhium;
	protected boolean doGenDevora;
	protected boolean doGenUnstableIce;

	public NyxBiome(int par1, boolean register, float heightRoot, float heightVari, boolean isRare) {
		super(par1, register);
		setHeight(new Height(heightRoot, heightVari));
		setTemperatureRainfall(0.0F, 0.0F);
		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		topBlock = NyxBlocks.snow;
		fillerBlock = NyxBlocks.permafrost;

		doGenDevora = true;
		doGenNifelhium = true;
		doGenUnstableIce = true;

		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityNyxSkeleton.class, 50, 1, 2));

		setBiomeName("Nyx");
		rare = isRare;

		setColor(255 << 16 | 255 << 8 | 255);
	}

	@Override
	public void decorate(World par1World, Random par2Random, int xchunk, int zchunk) {

		genEchir = new WorldGenNyxOre(NyxBlocks.oreEchir, 12);
		genCortra = new WorldGenNyxOre(NyxBlocks.oreCortra, 10);
		genDraconium = new WorldGenNyxOre(NyxBlocks.oreDraconium, 8);
		genGemstone = new WorldGenNyxOre(NyxBlocks.oreGemstone, 4);

		if (doGenDevora)
			genDevora = new WorldGenNyxOre(NyxBlocks.oreDevora, 8);

		// Unstable ice.
		if (doGenUnstableIce) {
			genUnstableIce = new WorldGenNyxOre(NyxBlocks.unstableIce, 36);
			GenOre.genOreStandard(genUnstableIce, par1World, xchunk, zchunk, 48, 128, 10);
		}

		if (doGenDevora)
			GenOre.genOreStandard(genDevora, par1World, xchunk, zchunk, 96, 255, 20);

		GenOre.genOreStandard(genEchir, par1World, xchunk, zchunk, 160, 255, 4);
		GenOre.genOreStandard(genEchir, par1World, xchunk, zchunk, 128, 255, 6);
		GenOre.genOreStandard(genCortra, par1World, xchunk, zchunk, 128, 225, 8);
		GenOre.genOreStandard(genDraconium, par1World, xchunk, zchunk, 225, 255, 3);
		GenOre.genOreStandard(genGemstone, par1World, xchunk, zchunk, 96, 192, 10);

		if (doGenNifelhium)
			GenOre.genOreSurface(NyxBlocks.oreNifelhium, par1World, xchunk, zchunk);

		GenOre.genOreWater(NyxBlocks.oreExousium, par1World, xchunk, zchunk, 1 + par2Random.nextInt(3));

		genStructures(par1World, par2Random, xchunk, zchunk);

		for (int xit = 0; xit < 16; ++xit)
			for (int zit = 0; zit < 16; ++zit)
				if (par2Random.nextInt(24) == 0) {
					boolean inair = false;
					for (int yit = IaSBlockHelper.getHeight(par1World, xchunk + xit, zchunk + zit) - 1; yit > 63; --yit)
						if (!inair && IaSBlockHelper.isTransient(par1World, xchunk + xit, yit, zchunk + zit))
							inair = true;
						else if (inair && !IaSBlockHelper.isTransient(par1World, xchunk + xit, yit, zchunk + zit)) {
							if (par1World.isSideSolid(xchunk + xit, yit, zchunk + zit, ForgeDirection.UP)
									&& par2Random.nextBoolean()) {
								par1World.setBlock(xchunk + xit, yit + 1, zchunk + zit, NyxBlocks.icicles);
								break;
							}
							inair = false;
						}
				}

		genFoliage(par1World, par2Random, xchunk, zchunk);

		final int x = xchunk + par1World.rand.nextInt(16);
		final int z = zchunk + par1World.rand.nextInt(16);
		final int y = IaSBlockHelper.getHeight(par1World, x, z);
		if (y >= 230 && IaSWorldHelper.getRegionLevel(par1World, x, y, z) > 0) {
			boolean makestone = true;
			for (int xit = -32; xit <= 32 && makestone; ++xit)
				for (int zit = -32; zit <= 32 && makestone; ++zit)
					for (int yit = 230; yit <= 255 && makestone; ++yit)
						if (par1World.getBlock(x + xit, yit, z + zit) == NyxBlocks.crystalBloodstone)
							makestone = false;
			if (makestone)
				par1World.setBlock(x, y, z, NyxBlocks.crystalBloodstone);
		}
	}

	protected void genFoliage(World par1World, Random par2Random, int xchunk, int zchunk) {

	}

	protected void genStructures(World par1World, Random par2Random, int xchunk, int zchunk) {
		// Gatestone generation.
		if (xchunk % 128 == 0 && zchunk % 128 == 0) {
			final int x = xchunk + 8;
			final int z = zchunk + 8;
			final int y = par1World.getTopSolidOrLiquidBlock(x, z);
			(new GenRuinsGatestone()).generate(par1World, par2Random, x, y, z);
		} else {
			final int x = xchunk + 8;
			final int z = zchunk + 8;
			final int y = par1World.getTopSolidOrLiquidBlock(x, z);
			GenRuins gengen = null;
			if (hasTowers() && par2Random.nextInt(16) == 0)
				gengen = new GenRuinsTowerLookout();
			if (gengen == null)
				gengen = supplyRuins();
			if (gengen != null)
				gengen.generate(par1World, par2Random, x, y, z);
		}
	}

	@Override
	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] meta, int a, int b, double c) {
		// byte b0 = (byte) (field_150604_aj & 255);
		int k = 0;
		// final int l = (int) (c / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		final int i1 = a & 15;
		final int j1 = b & 15;
		final int k1 = blocks.length / 256;

		final int xzmod = (j1 * 16 + i1) * k1;
		blocks[xzmod + 0] = Styx.ground;
		blocks[xzmod + 1] = Styx.air;
		blocks[xzmod + 2] = Styx.air;
		blocks[xzmod + 3] = Styx.ground;
		blocks[xzmod + 4] = Blocks.bedrock;
		if (rand.nextBoolean())
			blocks[xzmod + 5] = Blocks.bedrock;
		if (blocks[xzmod + 64] == null || blocks[xzmod + 64].getMaterial() == Material.air) {
			blocks[xzmod + 63] = NyxBlocks.exousicIce;
			blocks[xzmod + 62] = NyxBlocks.exousicWater;
			meta[xzmod + 62] = 15;
		}

		for (int yit = 255; yit >= 63; --yit) {
			final int index = xzmod + yit;
			final Block current = blocks[index];
			if (current == NyxBlocks.stone) {
				switch (k) {
				case 0:
					blocks[index] = topBlock;
					break;
				case -1:
					blocks[index] = fillerBlock;
					break;
				case -2:
				case -3:
					if (rand.nextInt(5 + k) != 0)
						blocks[index] = fillerBlock;
					// PRESERVE FALLTHROUGH;
				default:
					k = -4;
					break;
				}
				--k;
			} else
				k = Math.max(k, -1);
		}
	}

	protected boolean hasTowers() {
		return true;
	}

	public boolean isRare() {
		return rare;
	}

	public NyxBiome setBlocks(Block top, Block filler) {
		topBlock = top;
		fillerBlock = filler;
		return this;
	}

	protected GenRuins supplyRuins() {
		return null;
	}

}
