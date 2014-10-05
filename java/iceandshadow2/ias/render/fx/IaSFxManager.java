package iceandshadow2.ias.render.fx;

import iceandshadow2.IaSFlags;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IaSFxManager {
	
	public static void spawnParticle(World woild, String name,
			double x, double y, double z) {
		spawnParticle(woild, name, x, y, z, 0.0, 0.0, 0.0, false);
	}
	
	public static void spawnParticle(World woild, String name,
			double x, double y, double z, boolean unbounded) {
		spawnParticle(woild, name, x, y, z, 0.0, 0.0, 0.0, unbounded, false);
	}
	
	public static void spawnParticle(World woild, String name,
			double x, double y, double z,
			double velX, double velY, double velZ) {
		spawnParticle(woild, name, x, y, z, velX, velY, velZ, false, false);
	}
	
	public static void spawnParticle(World woild, String name,
			double x, double y, double z,
			double velX, double velY, double velZ, boolean unbounded) {
		spawnParticle(woild, name, x, y, z, velX, velY, velZ, unbounded, false);
	}

	public static void spawnParticle(World woild, String name,
			double x, double y, double z,
			double velX, double velY, double velZ, boolean unbounded, boolean isDecorative) {
		
		if(woild.isRemote)
			doParticleSpawn(woild, name, x, y, z, velX, velY, velZ, unbounded, isDecorative);
	}
	
	@SideOnly(Side.CLIENT)
	protected static void doParticleSpawn(World woild, String name,
			double x, double y, double z,
			double velX, double velY, double velZ, boolean unbounded, boolean isDecorative) {
		
		if(!Minecraft.getMinecraft().getMinecraft().isFancyGraphicsEnabled() || IaSFlags.flag_low_particles) {
			if(isDecorative)
				return;
			unbounded = false;
		}
		
		if(!unbounded && Minecraft.getMinecraft().renderViewEntity.getDistanceSq(x, y, z) > 576.0F)
			return;
		
		EntityFX effex = getParticleInstanceByName(
				woild, name, x, y, z, velX, velY, velZ);
		
		if(effex != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(effex);
	}

	@SideOnly(Side.CLIENT)
	protected static EntityFX getParticleInstanceByName(World woild, String name,
			double x, double y, double z,
			double velX, double velY, double velZ) {
		
		EntityFX efx = null;
		
		if(Minecraft.getMinecraft().getMinecraft().isFancyGraphicsEnabled()) {
			if(name == "dripPoison")
				efx = new EntityFxPoisonDroplet(woild, x, y, z);
			else if(name == "dripBlood")
				efx = new EntityFxBloodDroplet(woild, x, y, z);
			if(efx != null)
				return efx;
		}
		if(name == "blackMagic") {
			efx  = new EntitySpellParticleFX(woild, x, y, z, velX, velY, velZ);
			efx.setRBGColorF(0.01F*woild.rand.nextFloat(), 0.01F*woild.rand.nextFloat(), 0.01F*woild.rand.nextFloat());
		}
		else if(name == "cloudSmall")
			return new EntityFxFrostCloud(woild, x, y, z, velX, velY, velZ, 0.25F);
		else if(name == "cortraSmoke")
			return new EntityReddustFX(woild, x, y, z, 0.75F, 0.0F , 0.9F, 1.0F);
		else if(name == "cloud")
			return new EntityFxFrostCloud(woild, x, y, z, velX, velY, velZ, 0.75F);
		else if(name == "cloudLarge")
			return new EntityFxFrostCloud(woild, x, y, z, velX, velY, velZ, 2.25F);
		else if(name == "cloudHuge")
			return new EntityFxFrostCloud(woild, x, y, z, velX, velY, velZ, 6.75F);
		else if(name == "vanilla_portal")
			return new EntityPortalFX(woild, x, y, z, velX, velY, velZ);
		else if(name == "vanilla_spell")
			return new EntitySpellParticleFX(woild, x, y, z, velX, velY, velZ);
		else if(name == "vanilla_lava")
			return new EntityLavaFX(woild, x, y, z);
		else if(name == "vanilla_flame")
			return new EntityFlameFX(woild, x, y, z, velX, velY, velZ);
		else if(name == "vanilla_smoke")
			return new EntitySmokeFX(woild, x, y, z, velX, velY, velZ);
		else if(name == "hydriteSmoke") {
			efx = new EntityReddustFX(woild, x, y, z, 1.0F, 0.0F, 0.0F, 0.9F);
			efx.setRBGColorF(0.0F, 0.0F, 0.8F+woild.rand.nextFloat()*0.2F);
		}
		else if(name == "shadowSmokeSmall") {
			efx = new EntityReddustFX(woild, x, y, z, 2.0F, 0.005F, 0.0F, 0.0F);
			efx.setRBGColorF(0.005F, 0.0F, 0.0F);
		}
		else if(name == "shadowSmokeLarge") {
			efx = new EntityReddustFX(woild, x, y, z, 4.0F, 0.0F , 0.0F, 0.005F);
			efx.setRBGColorF(0.0F, 0.0F, 0.005F);
		}
		
		return efx;
	}
}