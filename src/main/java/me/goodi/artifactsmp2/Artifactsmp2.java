package me.goodi.artifactsmp2;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.goodi.artifactsmp2.Artifacts.CustomMaterials.*;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor.*;
import me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Item.*;
import me.goodi.artifactsmp2.Artifacts.StarterArtifacts.*;
import me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts.*;
import me.goodi.artifactsmp2.BukkitRunnables.RunnableArtifacts.Armor.InfernoArmor;
import me.goodi.artifactsmp2.Commands.Op.*;
import me.goodi.artifactsmp2.Commands.*;
import me.goodi.artifactsmp2.Config.Warps;
import me.goodi.artifactsmp2.PlayerListeners.*;
import me.goodi.artifactsmp2.PlayerListeners.InfernoArmor.damage;
import me.goodi.artifactsmp2.Listeners.ServerListener;
import me.goodi.artifactsmp2.Utils.*;
import me.goodi.artifactsmp2.WorldGenUtil.CustomChunkGenerator;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public final class Artifactsmp2 extends JavaPlugin {

    CustomModelDataNum modelDataNum = new CustomModelDataNum();
    public static Artifactsmp2 plugin;
    private final Warps warps = new Warps();
    FireShard fireShard = new FireShard(plugin);
    InfernoChestplate InfernoChesplate = new InfernoChestplate(plugin);
    InfernoBoots InfernoBoots = new InfernoBoots(plugin);
    InfernoHelmet InfernoHelmet = new InfernoHelmet(plugin);
    InfernoLeggings InfernoLeggings = new InfernoLeggings(plugin);

    public static Plugin getPlugin() {

        return plugin;
    }


    @Override
    public void onEnable() {

        setRules();
        plugin = this;


        warps.WarpsConfig();


        //events
        getServer().getPluginManager().registerEvents(new Nuke(), plugin);
        getServer().getPluginManager().registerEvents(new TexturePack(), plugin);
        getServer().getPluginManager().registerEvents(new DemonKingStaff(), plugin);
        getServer().getPluginManager().registerEvents(new FirstJoin(plugin), plugin);
        getServer().getPluginManager().registerEvents(new DicePick(), plugin);
        getServer().getPluginManager().registerEvents(new RecallGlove(), plugin);
        getServer().getPluginManager().registerEvents(new ThirdEye(), plugin);
        getServer().getPluginManager().registerEvents(new Corrupted_Artifact(plugin), plugin);
        getServer().getPluginManager().registerEvents(new HolySword_Artifact(), plugin);
        getServer().getPluginManager().registerEvents(new PoisonKatana(), plugin);
        getServer().getPluginManager().registerEvents(new DawnOfRuin(), plugin);
        getServer().getPluginManager().registerEvents(new BeelzeBub(), plugin);
        getServer().getPluginManager().registerEvents(new SwordOfTruth(plugin), plugin);
        getServer().getPluginManager().registerEvents(new NullBlade(plugin), plugin);
        getServer().getPluginManager().registerEvents(new ThreadedBlade(), plugin);
        getServer().getPluginManager().registerEvents(new AresHammer(plugin), plugin);
        getServer().getPluginManager().registerEvents(new LawlessBlade(plugin), plugin);
        getServer().getPluginManager().registerEvents(new Gui(), plugin);
        getServer().getPluginManager().registerEvents(new ServerListener(), plugin);
        getServer().getPluginManager().registerEvents(new Death(), plugin);
        getServer().getPluginManager().registerEvents(new DropItem(), plugin);
        getServer().getPluginManager().registerEvents(new damage(plugin), plugin);

        //Commands
        getServer().getPluginCommand("rules").setExecutor(new rules());
        getServer().getPluginCommand("starterArtifact").setExecutor(new getStarter());
        getServer().getPluginCommand("artifactInfo").setExecutor(new info());
        getServer().getPluginCommand("artifactInfo").setTabCompleter(new info());
        getServer().getPluginCommand("discord").setExecutor(new discord());
        getServer().getPluginCommand("warp").setExecutor(new warp());
        getServer().getPluginCommand("warp").setTabCompleter(new warp());
        getServer().getPluginCommand("deleteWarp").setExecutor(new DeleteWarp());
        getServer().getPluginCommand("createWarp").setExecutor(new CreateWarp());
        getServer().getPluginCommand("worldtp").setExecutor(new WorldTP());
        getServer().getPluginCommand("create").setExecutor(new testWorld());
        getServer().getPluginCommand("e").setExecutor(new Vanish());
        getServer().getPluginCommand("giveArtifact").setExecutor(new giveArtifact());
        getServer().getPluginCommand("giveArtifact").setTabCompleter(new giveArtifact());

        //Runnables
        BukkitTask RecallGloveRunnable = new RecallGloveRun().runTaskTimer(plugin, 0, 400);
        BukkitTask NukeRunnable = new NukeRun().runTaskTimer(plugin, 0, 10);
        BukkitTask ThirdEyeRunnable = new ThirdEyeRun().runTaskTimer(plugin, 0, 15);
        BukkitTask AresHammerEffects = new AresHammerRun().runTaskTimer(plugin, 0, 9);
        BukkitTask NullBladeEffects = new NullBladeEffects().runTaskTimer(plugin, 0, 9);
        BukkitTask SwordOfTruth = new DOT().runTaskTimer(plugin, 0, 9);
        BukkitTask HolySword = new HolySwordRun().runTaskTimer(plugin, 0, 9);
        BukkitTask infernoArmor = new InfernoArmor(plugin).runTaskTimer(plugin, 0, 9);



        //Recipes
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "fire_shard"), fireShard.fireShard());
        Bukkit.addRecipe(fireShard.fireShardRecipe(recipe));

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "inferno_helmet"), InfernoHelmet.infernoHelmet());
        Bukkit.addRecipe(InfernoHelmet.infernoHelmetRecipe(recipe));

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "inferno_chestplate"), InfernoChesplate.infernoChestplate());
        Bukkit.addRecipe(InfernoChesplate.InfernoChestplateRecipe(recipe));

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "inferno_leggings"), InfernoLeggings.infernoLeggings());
        Bukkit.addRecipe(InfernoLeggings.infernoLeggingsRecipe(recipe));

        recipe = new ShapedRecipe(new NamespacedKey(plugin, "inferno_boots"), InfernoBoots.infernoBoots());
        Bukkit.addRecipe(InfernoBoots.infernoBootsRecipe(recipe));


        modelDataNum.addIds();
    }
    @Override
    public void onDisable() {
        warps.saveWarps();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CustomChunkGenerator();
    }

    public void setRules(){
        for(World world : Bukkit.getWorlds()){
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
            world.setDifficulty(Difficulty.NORMAL);
        }
    }

}
