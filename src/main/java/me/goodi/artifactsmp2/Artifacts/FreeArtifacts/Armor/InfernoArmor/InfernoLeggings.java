package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor;

import me.goodi.artifactsmp2.Artifacts.CustomMaterials.FireShard;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import me.goodi.artifactsmp2.Artifactsmp2;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class InfernoLeggings {
    Artifactsmp2 plugin;
    public InfernoLeggings(Artifactsmp2 plugin){this.plugin = plugin;}
    FireShard shard = new FireShard(plugin);

    public Recipe infernoLeggingsRecipe(ShapedRecipe recipe){

        recipe.shape("SSS", "SNS", "SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(shard.fireShard()));
        recipe.setIngredient('N', Material.NETHERITE_LEGGINGS);

        return recipe;
    }

    public ItemStack infernoLeggings(){
        ItemStack item = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cInferno Leggings");
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("InfernoLeggings"));
        item.setItemMeta(meta);

        return item;

    }
}
