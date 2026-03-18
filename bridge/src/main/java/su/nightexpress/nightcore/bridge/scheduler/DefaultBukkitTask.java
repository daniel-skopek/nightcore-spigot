package su.nightexpress.nightcore.bridge.scheduler;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DefaultBukkitTask implements AdaptedTask {

    private final WrappedTask backend;

    public DefaultBukkitTask(@NotNull WrappedTask backend) {
        this.backend = backend;
    }

    @Override
    public void cancel() {
        this.backend.cancel();
    }

    @Override
    public boolean isCancelled() {
        return this.backend.isCancelled();
    }

    @Override
    @NotNull
    public Plugin getOwningPlugin() {
        return this.backend.getOwningPlugin();
    }

    @Override
    public boolean isCurrentlyRunning() {
        return !this.backend.isCancelled();
    }

    @Override
    public boolean isRepeatingTask() {
        return false;
    }
}
