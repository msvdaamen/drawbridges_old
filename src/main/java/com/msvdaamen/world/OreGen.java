package com.msvdaamen.world;

import com.msvdaamen.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    private WorldGenMinable oreCopper;
    private WorldGenMinable oreTin;

    public OreGen() {
        oreCopper = new WorldGenMinable(ModBlocks.oreCopper.getDefaultState(), 5);
        oreTin = new WorldGenMinable(ModBlocks.oreTin.getDefaultState(), 5);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            //overworld
            case 0: {
                runGenerator(oreCopper, world, random, chunkX, chunkZ, 45, 5, 50);
                runGenerator(oreTin, world, random, chunkX, chunkZ, 45, 5, 50);
            }

                break;
            //end
            case 1:

                break;
            //Nether
            case -1:

                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {

        if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore Generated Out of Bounds");
        int heighDiff = maxHeight - minHeight + 1;

        for(int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heighDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
