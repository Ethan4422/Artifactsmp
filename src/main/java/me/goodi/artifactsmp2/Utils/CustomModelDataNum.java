package me.goodi.artifactsmp2.Utils;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CustomModelDataNum {

    //101-05:artifacts
    //106-07: event artifacts
    //108:starter artifacts
    //99:materials
    //80:armor
    //999:paid artifacts
    //111: keys


    public static HashMap<String, Integer> CustomModels = new HashMap<>();


    public boolean shouldGlow(ItemStack item){
        if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData()){
            for(Integer i: CustomModels.values()){
                if(item.getItemMeta().getCustomModelData() == i){
                    return true;
                }
            }
        }
        return false;
    }

    public void addIds(){

        //StarterArtifacts
        CustomModels.put("RecallGlove", 1081);
        CustomModels.put("ThirdEye", 1082);
        CustomModels.put("DemonKingsStaff", 1083);
        CustomModels.put("DicePick", 1084);

        //Artifacts
        CustomModels.put("AresHammer", 1011);
        CustomModels.put("BeelzeBub", 1012);
        CustomModels.put("CorruptedArtifact", 1013);
        CustomModels.put("DawnOfRuin", 1014);
        CustomModels.put("HolySword", 1015);
        CustomModels.put("NullBlade", 1016);
        CustomModels.put("PoisonKatana", 1017);
        CustomModels.put("SwordOfTruth", 1018);
        CustomModels.put("ThreadedBlade", 1019);
        CustomModels.put("LawlessBlade", 1020);

        //Misc

        CustomModels.put("Nuke", 9999);
        CustomModels.put("FireShard", 991);

        //Armor
        CustomModels.put("InfernoHelmet", 801);
        CustomModels.put("InfernoChestplate", 802);
        CustomModels.put("InfernoLeggings", 803);
        CustomModels.put("InfernoBoots", 804);

    }
}
