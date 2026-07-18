package su.nightexpress.nightcore.integration.item.impl;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.nightcore.integration.item.adapter.impl.VanillaItemAdapter;
import su.nightexpress.nightcore.util.ItemTag;

public class AdaptedVanillaStack extends AdaptedItemStack<ItemTag> {

    private ItemStack itemStack;
    private boolean parsed;

    public AdaptedVanillaStack(@NotNull ItemTag itemTag) {
        super(VanillaItemAdapter.INSTANCE, itemTag);
    }

    @NotNull
    public static AdaptedVanillaStack of(@NotNull ItemStack itemStack) {
        ItemTag tag = ItemTag.of(itemStack);

        AdaptedVanillaStack stack = new AdaptedVanillaStack(tag);
        stack.itemStack = new ItemStack(itemStack);
        stack.parsed = true;
        return stack;
    }

    @Nullable
    private synchronized ItemStack resolve() {
        if (!this.parsed) {
            this.itemStack = this.adapter.toItemStack(this.data);
            this.parsed = true;
        }
        return this.itemStack;
    }

    @Override
    public boolean isValid() {
        return this.resolve() != null;
    }

    @Override
    public int getAmount() {
        ItemStack itemStack = this.resolve();

        return itemStack == null ? 0 : itemStack.getAmount();
    }

    @Override
    @Nullable
    public ItemStack getItemStack() {
        ItemStack itemStack = this.resolve();

        return itemStack == null ? null : new ItemStack(itemStack);
    }

    @Override
    public boolean isSimilar(@NotNull ItemTag other) {
        ItemStack itemStack = this.resolve();
        if (itemStack == null) return false;

        ItemStack otherStack = this.adapter.toItemStack(other);
        return otherStack != null && otherStack.isSimilar(itemStack);
    }
}
