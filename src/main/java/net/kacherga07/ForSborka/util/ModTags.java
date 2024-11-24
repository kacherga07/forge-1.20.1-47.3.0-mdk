package net.kacherga07.ForSborka.util;

import net.kacherga07.ForSborka.ForSborka;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");

        public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = tag("needs_sapphire_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ForSborka.MOD_ID, name));
        }
    }

    private static class Item {
        private static TagKey<net.minecraft.world.item.Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ForSborka.MOD_ID, name));
        }
    }
}
