package iceandshadow2.nyx.world.biome;

import iceandshadow2.nyx.NyxBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class NyxBiomeForest extends NyxBiome {
	
	public NyxBiomeForest(int par1, boolean register, float heightRoot,
			float heightVari, boolean isRare) {
		super(par1, register, heightRoot, heightVari, isRare);
		this.setBlocks(Blocks.snow, Blocks.snow);
	}

	/*
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		super.decorate(par1World, par2Random, par3, par4);
        
        for (int i = 0; i < 10; ++i)
        {
            int x = par3 + this.randomGenerator.nextInt(16) + 8;
            int z = par4 + this.randomGenerator.nextInt(16) + 8;
            int y = 255;
            for(y = 255; y > 0; --y) {
            	int bid = par1World.getBlockId(x, y, z);
            	if(bid == Block.snow.blockID)
            		break;
            	if(bid != 0) {
            		++y;
            		break;
            	}
            }
            if(y == 0)
            	continue;
            WorldGenerator var5 = this.getRandomWorldGenForTrees(this.randomGenerator);
            var5.generate(par1World, this.randomGenerator, x, y, z);
    		par1World.updateAllLightTypes(x, y, z);
        }
        randomGenerator = null;
    }
	
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return new NyxWorldGenPoisonTrees(false);
	}
	*/
}