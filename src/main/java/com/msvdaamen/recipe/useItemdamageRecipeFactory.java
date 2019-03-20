package com.msvdaamen.recipe;

import com.google.gson.JsonObject;
import com.msvdaamen.Drawbridges;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

public class useItemdamageRecipeFactory implements IRecipeFactory {
    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
        ShapelessOreRecipe recipe = ShapelessOreRecipe.factory(context, json);
        return new useItemDamage(new ResourceLocation(Drawbridges.MODID, "use_damage_crafting"), recipe.getIngredients(), recipe.getRecipeOutput());
    }

    public static class useItemDamage extends ShapelessOreRecipe {

        useItemDamage(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result) {
            super(group, input, result);
        }

        @Override
        public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
            NonNullList<ItemStack> items = NonNullList.create();
            int index = 0;
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack stack = inv.getStackInSlot(i);

                if (!stack.isEmpty()) {
                    if(stack.getItem() instanceof damageRecipe) {
                        int damage = stack.getItemDamage();
                        int maxDamage = stack.getMaxDamage();
                        if(damage == maxDamage){
                            items.add(index, ItemStack.EMPTY);
                        }else {
                            stack.setItemDamage(damage + 1);
                            items.add(index, stack.copy());
                        }
                    }else {
                        items.add(index, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
                    }
                } else {
                    items.add(index, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
                }
                index ++;
            }
            return items;
        }
    }
}
