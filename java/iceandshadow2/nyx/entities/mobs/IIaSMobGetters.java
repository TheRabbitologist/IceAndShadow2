package iceandshadow2.nyx.entities.mobs;

import iceandshadow2.api.EnumIaSAspect;
import iceandshadow2.api.IIaSAspect;
import iceandshadow2.nyx.entities.ai.senses.IIaSSensate;
import net.minecraft.entity.EntityLivingBase;

public interface IIaSMobGetters extends IIaSAspect, IIaSSensate {
	/***
	 * Whether or not this mob has pseduo-flight. Used by anything that normally
	 * checks for boots on an entity. If true, the entity is treated as if their
	 * feet are not directly touching the block. If false, a check for boots is
	 * performed first.
	 */
	public boolean couldFlyFasterWithBoots();

	public double getMoveSpeed();

	public double getScaledMaxHealth();

	public EntityLivingBase getSearchTarget();

	public void setSearchTarget(EntityLivingBase ent);

	//Whether or not the entity should attack entities bearing this aspect.
	public boolean hates(EnumIaSAspect aspect);
}
