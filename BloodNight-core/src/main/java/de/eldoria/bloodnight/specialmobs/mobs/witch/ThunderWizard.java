package de.eldoria.bloodnight.specialmobs.mobs.witch;

import de.eldoria.bloodnight.specialmobs.SpecialMobUtil;
import org.bukkit.Particle;
import org.bukkit.entity.Witch;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ThunderWizard extends AbstractWitch {
    public ThunderWizard(Witch witch) {
        super(witch);
    }

    @Override
    public void tick() {
        SpecialMobUtil.spawnParticlesAround(getBaseEntity(), Particle.INSTANT_EFFECT, 15);
        if (canShoot(4) && getBaseEntity().getTarget() != null) {
            getBaseEntity().getLocation().getWorld().strikeLightning(getBaseEntity().getTarget().getLocation());
            shot();
        }
    }

    @Override
    public void onProjectileShoot(ProjectileLaunchEvent event) {
        event.setCancelled(true);
    }
}
