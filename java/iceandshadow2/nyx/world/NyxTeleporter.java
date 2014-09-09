package iceandshadow2.nyx.world;

import iceandshadow2.nyx.NyxBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class NyxTeleporter extends Teleporter {
	private final WorldServer world;
	private final Random random;

	public NyxTeleporter(WorldServer par1WorldServer) {
		super(par1WorldServer);
		this.world = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {

		int xcoord = MathHelper.floor_double(par1Entity.posX);
		int ycoord = MathHelper.floor_double(par1Entity.posY
				+ MathHelper.sqrt_double(MathHelper
						.sqrt_double(256.0 - par1Entity.posY)
						* (par1Entity.posY))) - 1;
		int zcoord = MathHelper.floor_double(par1Entity.posZ);

		if(this.world.provider.dimensionId == 0)
			this.placeInOverworld(par1Entity, xcoord, ycoord, zcoord);
		else if (!placeOnExistingPlatform(par1Entity, xcoord, ycoord, zcoord)) {
			placeInNyx(par1Entity, xcoord, ycoord, zcoord);
			par1Entity.setLocationAndAngles(xcoord, ycoord,
					zcoord, par1Entity.rotationYaw, 0.0F);
		}
		par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	}
	
	private void placeInNyx(Entity par1Entity, int x, int y, int z) {
		int ycoord = -10;
		for(int yit = 0; y-yit > 0 || y+yit <= 255; ++yit) {
			ycoord = 0;
			if(y-yit > 0) {
				if(!world.isAirBlock(x, y-yit, z))
					ycoord = y-yit;
			}
			if(ycoord == 0 && y+yit <= 255) {
				if(!world.isAirBlock(x, y+yit, z))
					ycoord = y+yit;
			}
			if(ycoord != 0) {
				if(world.isAirBlock(x, ycoord+1, z) && world.isAirBlock(x, ycoord+2, z))
					break;
			}
		}
		if(world.getBlock(x, ycoord, z) == Blocks.water)
			world.setBlock(x, ycoord, z, Blocks.ice);
		else if(world.getBlock(x, ycoord, z) == Blocks.lava)
			world.setBlock(x, ycoord, z, Blocks.cobblestone);
		else if(world.getBlock(x, ycoord, z) == Blocks.cactus)
			world.setBlock(x, ycoord, z, Blocks.sandstone);
		else if(world.getBlock(x, ycoord, z) == Blocks.fire)
			world.setBlock(x, ycoord, z, Blocks.air);
		
		par1Entity.setLocationAndAngles((x)+0.5, ycoord+1.0,
				(z)+0.5, this.world.rand.nextFloat()*360.0F, 0.0F);
	}

	private void placeInOverworld(Entity par1Entity, int x, int y, int z) {
		int ycoord = -10;
		for(int yit = 0; y-yit > 0 || y+yit <= 255; ++yit) {
			ycoord = 0;
			if(y-yit > 0) {
				if(!world.isAirBlock(x, y-yit, z))
					ycoord = y-yit;
			}
			if(ycoord == 0 && y+yit <= 255) {
				if(!world.isAirBlock(x, y+yit, z))
					ycoord = y+yit;
			}
			if(ycoord != 0) {
				if(world.isAirBlock(x, ycoord+1, z) && world.isAirBlock(x, ycoord+2, z))
					break;
			}
		}
		if(world.getBlock(x, ycoord, z) == Blocks.water)
			world.setBlock(x, ycoord, z, Blocks.ice);
		else if(world.getBlock(x, ycoord, z) == Blocks.lava)
			world.setBlock(x, ycoord, z, Blocks.cobblestone);
		else if(world.getBlock(x, ycoord, z) == Blocks.cactus)
			world.setBlock(x, ycoord, z, Blocks.sandstone);
		else if(world.getBlock(x, ycoord, z) == Blocks.fire)
			world.setBlock(x, ycoord, z, Blocks.air);
		
		par1Entity.setLocationAndAngles((x)+0.5, ycoord+1.0,
				(z)+0.5, this.world.rand.nextFloat()*360.0F, 0.0F);
	}

	public boolean placeOnExistingPlatform(Entity par1Entity, int x, int y,
			int z) {
		for (int xi = 0; xi < 32; ++xi) {
			for (int yi = 0; yi < 32; ++yi) {
				for (int zi = 0; zi < 32; ++zi) {
					for (byte flip = 0; flip < 8; ++flip) {
						int xfactor = (flip | 0x1) > 0 ? -1 : 1;
						int yfactor = (flip | 0x2) > 0 ? -1 : 1;
						int zfactor = (flip | 0x4) > 0 ? -1 : 1;

						int ycalc = (y + yi * yfactor);
						ycalc = ycalc > 255 ? 255 : ycalc;
						ycalc = ycalc < 1 ? 1 : ycalc;

						int xvalue = x + xi * xfactor;
						int yvalue = ycalc;
						int zvalue = z + zi * zfactor;
						Block bid = this.world.getBlock(xvalue, yvalue,
								zvalue);
						int bmet = this.world.getBlockMetadata(xvalue,
								yvalue, zvalue);

							if (bid == NyxBlocks.cryingObsidian && bmet == 1) {
								bid = this.world.getBlock(xvalue,
										yvalue + 1, zvalue);
								Block bid2 = this.world.getBlock(xvalue,
										yvalue + 2, zvalue);
								if (bid == Blocks.air && bid2 == Blocks.air) {
									par1Entity.setLocationAndAngles(
											xvalue,
											yvalue + 1,
											zvalue,
											par1Entity.rotationYaw, 0.0F);
									return true;
								}
							}
						}
					}
				}
			}
		return false;
	}
}
