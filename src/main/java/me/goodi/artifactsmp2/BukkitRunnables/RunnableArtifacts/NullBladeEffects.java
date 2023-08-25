package me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts;

import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item.NullBlade;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class NullBladeEffects extends BukkitRunnable {
    @Override
    public void run() {
        for (Player people : Bukkit.getOnlinePlayers()) {
            if (NullBlade.TimePaused.containsKey(people.getUniqueId())) {
                long timeElasped = (System.currentTimeMillis() - NullBlade.TimePaused.get(people.getUniqueId()));
                if ((15000 - timeElasped) / 1000 <= 0) {
                    NullBlade.TimePaused.remove(people.getUniqueId());
                    people.removePotionEffect(PotionEffectType.DARKNESS);
                    people.removePotionEffect(PotionEffectType.BLINDNESS);
                    people.removePotionEffect(PotionEffectType.SLOW);
                    people.removePotionEffect(PotionEffectType.JUMP);
                } else {
                    if (NullBlade.TimePaused.containsKey(people.getUniqueId())) {
                        people.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 35, 10));
                        people.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 35, 10));
                        people.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35, 10));
                        people.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 35, -10));
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 100);
                        people.spawnParticle(Particle.REDSTONE, people.getLocation().add(0, 1.5, 0), 5, dustOptions);
                    }
                }
            }
        }
    }
}
