package iceandshadow2.nyx.world.gen.ruins;

import iceandshadow2.IaSFlags;
import iceandshadow2.IceAndShadow2;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class GenRuins extends WorldGenerator {
	protected int damage = 1;

	/**
	 * Generates the basic structure of the building. May also even out terrain
	 * that the building is on.
	 */
	public abstract void buildPass(World w, Random r, int x, int y, int z);

	/**
	 * Determines whether or not ruins can be generated here. Does not do any
	 * building.
	 */
	public abstract boolean canGenerateHere(World w, Random r, int x, int y, int z);

	/**
	 * "Ruins" the basic structure and adds a few decorative and functional
	 * touches to the building, like ladders, doorways, and spawners.
	 */
	public abstract void damagePass(World w, Random r, int x, int y, int z);

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		final boolean cGH = canGenerateHere(w, r, x, y, z); 
		if(cGH) {
			if (IaSFlags.flag_report_ruins_gen)
				IceAndShadow2.getLogger()
					.info("[DEV] Generating " + getLowercaseName() + " @ (" + x + "," + y + "," + z + ").");
			buildPass(w, r, x, y, z);
			for(int i = 0; i < damage; ++i)
				damagePass(w, r, x, y, z);
			if(damage <= 1)
				rewardPass(w, r, x, y, z);
		}
		return cGH;
	}

	public abstract String getLowercaseName();

	/**
	 * Adds primarily reward chests. Not all ruins will have rewards, but most
	 * will and a coder is free to have this return instantly.
	 */
	public abstract void rewardPass(World w, Random r, int x, int y, int z);

}
