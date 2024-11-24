package net.kacherga07.ForSborka.Block.custom;

import net.kacherga07.ForSborka.Item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.common.Mod;





@Mod.EventBusSubscriber
public class StrawberryCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public StrawberryCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // Проверяем, вырос ли куст
        if (state.getValue(AGE) == MAX_AGE) {
            // Получаем лут (клубнику и семена) и добавляем их к инвентарю игрока
            ItemStack strawberryStack = new ItemStack(ModItems.STRAWBERRY.get(), 1); // Один клубник

            popResource(world, pos, strawberryStack);

            world.setBlock(pos, state.setValue(AGE, 2), 3); // Сбрасываем возраст
            return InteractionResult.SUCCESS; // Успешное взаимодействие
        }
        return InteractionResult.PASS; // Пропускаем взаимодействие
    }




    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.STRAWBERRY_SEEDS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.FARMLAND);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        ItemStack itemStack = new ItemStack(ModItems.STRAWBERRY.get());
        return itemStack;
    }

    public boolean isSustainable(BlockState state, Level world, BlockPos pos) {
        return state.getValue(AGE) == MAX_AGE;
    }
}
