package me.goodi.artifactsmp2.Utils;

import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;


public class ParticlesUtil {

    public static void drawCircle(Player pLayer, float radius) {

        ArmorStand stand = (ArmorStand) pLayer.getWorld().spawnEntity(pLayer.getLocation(), EntityType.ARMOR_STAND);

        Location center = pLayer.getLocation();



        stand.setVisible(false);
        stand.setSmall(true);
        stand.setGravity(false);
        stand.setCollidable(false);
        stand.setMarker(false);
        stand.setBasePlate(false);
        stand.setArms(false);
        stand.setDisabledSlots(EquipmentSlot.HAND);

        final float radPerSec = (float) 1.5;
        final float radPerTick = radPerSec / 20f;

        new BukkitRunnable(){
            @Override
            public void run() {
                stand.setItemInHand(randomizeArtifact());
            }
        }.runTaskTimer(Artifactsmp2.plugin,0L,40L);

        new BukkitRunnable() {

            int tick = 0;

            public void run() {
                ++tick;

                Location loc = getLocationAroundCircle(pLayer.getLocation(), radius, radPerTick * tick);


                stand.setVelocity(new Vector(1, 0, 0));
                stand.teleport(loc);



            }
        }.runTaskTimer(Artifactsmp2.plugin, 0L, 1L);

    }

    public static Location getLocationAroundCircle(Location center, double radius, double angleInRadian) {
        double x = center.getX() + radius * Math.cos(angleInRadian);
        double z = center.getZ() + radius * Math.sin(angleInRadian);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector()); // this sets the returned location's direction toward the center of the circle
        loc.setDirection(difference);

        return loc;
    }

    public static ItemStack randomizeArtifact(){
        Random random = new Random();

        ArtifactUtil.Artifacts[] name = {ArtifactUtil.Artifacts.HOLYSWORD, ArtifactUtil.Artifacts.ARESAXE, ArtifactUtil.Artifacts.SWORDOFTRUTH, ArtifactUtil.Artifacts.POISONKATANA, ArtifactUtil.Artifacts.NULLBLADE, ArtifactUtil.Artifacts.DAWNOFRUIN, ArtifactUtil.Artifacts.CORRUPTEDARTIFACT, ArtifactUtil.Artifacts.BEELZEBUB, ArtifactUtil.Artifacts.THREADEDBLADE, ArtifactUtil.Artifacts.DICEPICK, ArtifactUtil.Artifacts.LAWLESSBLADE};

        ItemStack item =ArtifactUtil.getArtifactByName(ArtifactUtil.Artifacts.valueOf(String.valueOf(name[random.nextInt(9)])));
        return item;
    }

    public void LawSlash(Location from, Location to){

    }
}
