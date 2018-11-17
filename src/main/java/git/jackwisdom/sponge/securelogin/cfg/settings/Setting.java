package git.jackwisdom.sponge.securelogin.cfg.settings;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;

import java.nio.file.Path;

public abstract class Setting {
    private Path dir;
    private Logger logger;
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    public Setting(Logger logger, @ConfigDir(sharedRoot = false) Path dir) {
        this.dir = dir;
        this.logger = logger;
        logger = HoconConfigurationLoader.builder().build();
    }

    public Logger getLogger() {
        return logger;
    }

    public ConfigurationLoader<CommentedConfigurationNode> getLoader() {
        return loader;
    }

    public Path getRootDir() {
        return dir;
    }

    public abstract String getFilePath();

}
