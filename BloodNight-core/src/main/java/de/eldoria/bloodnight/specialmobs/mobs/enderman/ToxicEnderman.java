package de.eldoria.bloodnight.specialmobs.mobs.enderman;

import de.eldoria.bloodnight.specialmobs.SpecialMobUtil;
import de.eldoria.bloodnight.specialmobs.effects.PotionCloud;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Enderman;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ToxicEnderman extends AbstractEnderman {
    public ToxicEnderman(Enderman enderman) {
        super(enderman);
    }

    @Override
    public void tick() {
        super.tick();
        SpecialMobUtil.spawnParticlesAround(getBaseEntity().getLocation(), Particle.DUST, new Particle.DustOptions(Color.GREEN, 2), 5);
    }

    @Override
    public void onTeleport(EntityTeleportEvent event) {
        Location from = event.getFrom();
        PotionCloud.builder(from.subtract(0, 1, 0))
                .setPotionType(new PotionData(PotionType.POISON, false, true))
                .ofColor(Color.GREEN)
                .setDuration(10)
                .withRadius(4)
                .setRadiusPerTick(0.01f)
                .build();
    }
}
