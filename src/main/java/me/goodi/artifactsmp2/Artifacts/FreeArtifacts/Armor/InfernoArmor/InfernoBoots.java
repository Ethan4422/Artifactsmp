package me.goodi.artifactsmp2.Artifacts.FreeArtifacts.Armor.InfernoArmor;

import me.goodi.artifactsmp2.Artifacts.CustomMaterials.FireShard;
import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class InfernoBoots {

    Artifactsmp2 plugin;
    public InfernoBoots(Artifactsmp2 plugin){this.plugin = plugin;}

    FireShard shard = new FireShard(plugin);


    public Recipe infernoBootsRecipe(ShapedRecipe recipe){

        recipe.shape("SSS", "SNS", "SSS");
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(shard.fireShard()));
        recipe.setIngredient('N', Material.NETHERITE_BOOTS);

        return recipe;
    }

    public ItemStack infernoBoots(){
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cInferno Boots");
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("InfernoBoots"));
        item.setItemMeta(meta);

        return item;

    }

}
