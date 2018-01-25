package iceandshadow2.nyx.entities.util;

import iceandshadow2.ias.util.IaSBlockHelper;
import iceandshadow2.nyx.entities.mobs.EntityNyxWightToxic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityWightTeleport extends EntityThrowable {

	private EntityLivingBase target;
	private double deltaX, deltaZ;

	public EntityWightTeleport(World par1World) {
		super(par1World);
		deltaX = Double.MAX_VALUE;
		deltaZ = Double.MAX_VALUE;
	}

	public EntityWightTeleport(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		deltaX = Double.MAX_VALUE;
		deltaZ = Double.MAX_VALUE;
	}

	public EntityWightTeleport(World par1World, EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		deltaX = Double.MAX_VALUE;
		deltaZ = Double.MAX_VALUE;
	}

	public EntityWightTeleport(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase target) {
		this(par1World, par2EntityLivingBase);
		this.target = target;
		deltaX = Double.MAX_VALUE;
		deltaZ = Double.MAX_VALUE;
	}

	@Override
	protected float func_70182_d() {
		return 2.0F;
	}

	@Override
	protected float func_70183_g() {
		return 0.0F;
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	@Override
	protected float getGravityVelocity() {
		return 0.02F;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition pom) {
		if (pom.typeOfHit == MovingObjectType.BLOCK) {
			final Block bl = worldObj.getBlock(pom.blockX, pom.blockY, pom.blockZ);
			if (bl.getMaterial() == Material.leaves)
				return;
			if (bl.isReplaceable(worldObj, pom.blockX, pom.blockY, pom.blockZ))
				return;
			worldObj.playSoundAtEntity(this, "IceAndShadow2:mob_nyxwight_tele_arrive", 0.8F,
					rand.nextFloat() * 0.1F + 0.9F);
			if (!worldObj.isRemote) {
				final EntityMob spawn = new EntityNyxWightToxic(worldObj);
				final ForgeDirection dir = ForgeDirection.getOrientation(pom.sideHit);
				final int posBlockX = pom.blockX + dir.offsetX;
				final int posBlockY = pom.blockY + (dir.offsetY == 1 ? 0 : -2 + dir.offsetY);
				final int posBlockZ = pom.blockZ + dir.offsetZ;
				Block bl0 = worldObj.getBlock(posBlockX, posBlockY, posBlockZ);
				Block bl1 = worldObj.getBlock(posBlockX, posBlockY + 1, posBlockZ);
				Block bl2 = worldObj.getBlock(posBlockX, posBlockY + 2, posBlockZ);
				if (!IaSBlockHelper.isAir(bl0) && IaSBlockHelper.isAir(bl1) && IaSBlockHelper.isAir(bl2)) {
					pom.blockX = posBlockX;
					pom.blockY = posBlockY;
					pom.blockZ = posBlockZ;
				} else {
					boolean rise = false;
					if (dir.offsetY == 0) {
						final int posBlockY2 = posBlockY + 1;
						bl0 = worldObj.getBlock(posBlockX, posBlockY2, posBlockZ);
						bl1 = worldObj.getBlock(posBlockX, posBlockY2 + 1, posBlockZ);
						bl2 = worldObj.getBlock(posBlockX, posBlockY2 + 2, posBlockZ);
						if (!IaSBlockHelper.isAir(bl0) && IaSBlockHelper.isAir(bl1) && IaSBlockHelper.isAir(bl2)) {
							rise = true;
							pom.blockX = posBlockX;
							pom.blockY = posBlockY2;
							pom.blockZ = posBlockZ;
						}
					}
					if (rise) {
						bl1 = worldObj.getBlock(pom.blockX, pom.blockY + 1, pom.blockZ);
						bl2 = worldObj.getBlock(pom.blockX, pom.blockY + 2, pom.blockZ);
						while (!IaSBlockHelper.isAir(bl1) && !IaSBlockHelper.isAir(bl2)) {
							pom.blockY += 1;
							bl1 = worldObj.getBlock(pom.blockX, pom.blockY + 1, pom.blockZ);
							bl2 = worldObj.getBlock(pom.blockX, pom.blockY + 2, pom.blockZ);
						}
					}
				}
				spawn.setPositionAndRotation(pom.blockX + 0.5, pom.blockY + 1, pom.blockZ + 0.5,
						worldObj.rand.nextFloat() * 360F, 0.0F);
				if (target != null && !target.isDead) {
					spawn.setTarget(target);
				}
				worldObj.spawnEntityInWorld(spawn);
			}
			setDead();
		}
	}

	@Override
	public void onUpdate() {
		if (target != null) {
			final double cDeltaX = Math.abs(target.posX - posX);
			final double cDeltaZ = Math.abs(target.posZ - posZ);
			deltaX = Math.min(deltaX, cDeltaX);
			deltaZ = Math.min(deltaZ, cDeltaZ);
			if (cDeltaX - deltaX > 3.0 || cDeltaZ - deltaZ > 3.0) {
				final double vel = Math.sqrt(motionX * motionX + motionZ * motionZ);
				motionY = Math.min(0, motionY) - vel;
				motionX = 0;
				motionZ = 0;
			}
		}
		super.onUpdate();
	}
}