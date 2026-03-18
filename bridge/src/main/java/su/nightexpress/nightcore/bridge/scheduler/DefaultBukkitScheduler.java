package su.nightexpress.nightcore.bridge.scheduler;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class DefaultBukkitScheduler implements AdaptedScheduler {

    private final FoliaLib foliaLib;

    public DefaultBukkitScheduler(@NotNull JavaPlugin plugin) {
        this.foliaLib = new FoliaLib(plugin);
    }

    @Override
    public void cancelTasks() {
        this.foliaLib.getScheduler().cancelAllTasks();
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTask(@NotNull Runnable runnable) {
        WrappedTask task = this.foliaLib.getScheduler().runLater(runnable, 0L);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTask(@NotNull Entity entity, @NotNull Runnable runnable) {
        WrappedTask task = this.foliaLib.getScheduler().runAtEntityLater(entity, runnable, null, 0L);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTask(@NotNull Location location, @NotNull Runnable runnable) {
        WrappedTask task = this.foliaLib.getScheduler().runAtLocationLater(location, runnable, 0L);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTask(@NotNull Chunk chunk, @NotNull Runnable runnable) {
        WrappedTask task = this.foliaLib.getScheduler().runAtLocationLater(chunk.getBlock(8, 64, 8).getLocation(), runnable, 0L);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTaskAsync(@NotNull Runnable runnable) {
        WrappedTask task = this.foliaLib.getScheduler().runLaterAsync(runnable, 0L, TimeUnit.MILLISECONDS);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTaskLater(@NotNull Runnable runnable, long delay) {
        WrappedTask task = this.foliaLib.getScheduler().runLater(runnable, delay);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTaskLaterAsync(@NotNull Runnable runnable, long delay) {
        WrappedTask task = this.foliaLib.getScheduler().runLaterAsync(runnable, delay * 50L, TimeUnit.MILLISECONDS);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTaskTimer(@NotNull Runnable runnable, long delay, long period) {
        WrappedTask task = this.foliaLib.getScheduler().runTimer(runnable, delay, period);
        return new DefaultBukkitTask(task);
    }

    @Override
    @NotNull
    public DefaultBukkitTask runTaskTimerAsync(@NotNull Runnable runnable, long delay, long period) {
        WrappedTask task = this.foliaLib.getScheduler().runTimerAsync(runnable, delay * 50L, period * 50L, TimeUnit.MILLISECONDS);
        return new DefaultBukkitTask(task);
    }
}
