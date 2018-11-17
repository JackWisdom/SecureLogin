package git.jackwisdom.sponge.securelogin.cfg;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;

import java.nio.file.Path;

@Singleton
public class Config {
    private ConfigurationLoader<CommentedConfigurationNode> configManager;
    private Logger logger;
    @Inject
    public Config(Logger logger, @ConfigDir(sharedRoot = false) Path path) {
        this.logger = logger;
        this.configManager = HoconConfigurationLoader.builder().setPath(path).build();
    }


}
