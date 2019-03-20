package com.msvdaamen.items;

import com.msvdaamen.Drawbridges;

public class StaticItem extends baseItem {


    public StaticItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(Drawbridges.MODID + '.' + name);
    }
}
