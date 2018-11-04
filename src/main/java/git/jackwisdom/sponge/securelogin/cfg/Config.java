package git.jackwisdom.sponge.securelogin.cfg;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;

import java.io.IOException;
import java.nio.file.Path;

public class Config {

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path privateConfigDir;

    @Inject
    private Logger logger;
    private CommentedConfigurationNode rootNode;

    public Config() {
        try {
            rootNode = configManager.load();
        } catch (IOException e) {
            logger.info("An error happened while loading config creating new one");
            rootNode = configManager.createEmptyNode();
            e.printStackTrace();
        }
        if ((Boolean) rootNode.getNode("nondefault").getValue() == true) {
            logger.info("Resetting configuration to default");
            setDefault(rootNode);
            logger.info("Succeed reset config");
        }

    }

    private void setDefault(CommentedConfigurationNode rootNode) {
        rootNode = configManager.createEmptyNode();
        rootNode.getNode("nondefault").setValue(false).setComment("If this is false the whole file " +
                "will be set to default");
        rootNode.getNode("pwd").getNode("regex").setValue("").setComment("the regex of the password");
        rootNode.getNode("reg").getNode("force").setValue(true).setComment("Force Player to register or not");
        rootNode.getNode("reg", "ip").setValue(3).setComment("max reg per ip");
        rootNode.getNode("login", "maxTries").setValue(3).setComment("max tries");
        rootNode.getNode("login", "kick").setValue(true).setComment("whether kick after max tries");
        CommentedConfigurationNode login = rootNode.getNode("login");
        login.getNode("ban").setValue(true).setComment("ban or not");
        login.getNode("banHour").setValue(1).setComment("ban ip for hours");
        login.getNode("commandWhitelist").setValue(new String[]{"login", "register"}).setComment("cmd while list /n DO NOT " +
                "FORGET TO ADD LOGIN AND RGISTER IN IT");
        try {
            configManager.save(rootNode);
        } catch (IOException e) {
            logger.info("AN ERROR HAPPENED WHILE SAVING DEFAULTS");
            e.printStackTrace();
        }
    }

    public void reload() throws IOException {
        rootNode = configManager.load();
    }

    public void save() throws IOException {
        configManager.save(rootNode);
    }
}
