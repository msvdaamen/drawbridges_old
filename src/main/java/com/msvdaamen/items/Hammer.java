package com.msvdaamen.items;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.recipe.damageRecipe;

public class Hammer extends baseItem implements damageRecipe {

    public Hammer() {
        setRegistryName("hammer");
        setUnlocalizedName(Drawbridges.MODID + ".hammer");
        setMaxDamage(50);
        setNoRepair();
        setMaxStackSize(1);
    }
}
