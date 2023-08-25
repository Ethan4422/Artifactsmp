package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Artifacts.CustomMaterials.FireShard;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;

public class InfernoHelmet {

    Artifactsmp2 plugin;
    public InfernoHelmet(Artifactsmp2 plugin){this.plugin = plugin;}
    FireShard shard = new FireShard(plugin);

    public Recipe infernoHelmetRecipe(ShapedRecipe recipe){


        recipe.shape("SSS", "SNS", "SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(shard.fireShard()));
        recipe.setIngredient('N', Material.NETHERITE_HELMET);

        return recipe;
    }

    public ItemStack infernoHelmet(){
        ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cInferno Helmet");
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("InfernoHelmet"));
        item.setItemMeta(meta);

        return item;

    }
}
