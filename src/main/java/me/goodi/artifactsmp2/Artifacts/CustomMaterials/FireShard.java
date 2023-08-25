package me.goodi.artifactsmp2.Artifacts.CustomMaterials;

import me.goodi.artifactsmp2.Artifactsmp2;
import me.goodi.artifactsmp2.Utils.CustomModelDataNum;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class FireShard {

    Artifactsmp2 plugin;
    public FireShard(Artifactsmp2 plugin){this.plugin = plugin;}

    public Recipe fireShardRecipe(ShapedRecipe recipe){

        recipe.shape("BBB", "BSB", "BBB");
        recipe.setIngredient('S', Material.AMETHYST_SHARD);
        recipe.setIngredient('B', Material.BLAZE_ROD);

        return recipe;
    }

    public ItemStack fireShard(){
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(CustomModelDataNum.CustomModels.get("FireShard"));
        meta.setDisplayName("§6Fire shard");
        item.setItemMeta(meta);

        return item;
    }

}
