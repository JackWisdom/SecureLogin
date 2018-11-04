package git.jackwisdom.sponge.securelogin.cfg;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;

import java.io.IOException;
import java.nio.file.Path;

public class Config {

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConfig;

    private ConfigurationLoader<CommentedConfigurationNode> configManager;

    @Inject
    private Logger logger;
    private CommentedConfigurationNode rootNode;

    public Config() {
        configManager = HoconConfigurationLoader.builder().setPath(defaultConfig).build();
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

    // @CfgVaule
    String pwd_regex;

    private void loadVaules() {

    }
    private void setDefault(CommentedConfigurationNode rootNode) {
        rootNode = configManager.createEmptyNode();
        rootNode.getNode("nondefault").setValue(false).setComment("If this is false the whole file " +
                "will be set to default");
        rootNode.getNode("storage", "file").setValue("pwd.conf");
        rootNode.getNode("pwd").getNode("regex").setValue("[a-zA-Z0-9]{6,10}").setComment("the regex of the password");
        rootNode.getNode("reg").getNode("force").setValue(true).setComment("Force Player to register or not");
        rootNode.getNode("reg", "ip").setValue(3).setComment("max reg per ip");
        rootNode.getNode("login", "maxTries").setValue(3).setComment("max tries");
        rootNode.getNode("login", "kick").setValue(true).setComment("whether kick after max tries");
        CommentedConfigurationNode login = rootNode.getNode("login");
        login.getNode("ban").setValue(true).setComment("ban or not");
        login.getNode("banHour").setValue(1).setComment("ban ip for hours");
        login.getNode("commandWhitelist").setValue(new String[]{"login", "register"}).setComment("cmd while list /n DO NOT " +
                "FORGET TO ADD LOGIN AND RGISTER IN IT");
        CommentedConfigurationNode msg = rootNode.getNode("msg");
        msg.getNode("reg", "bad_pwd").setValue("少年 你的指令需要在6到10个字母或者数字");
        msg.getNode("reg", "not_reg_yet").setValue("少年呀 你还没注册呢 输入/register 来注册");
        msg.getNode("login", "not_login").setValue("少年 你需要使用/login 来登录");
        msg.getNode("login", "fail").setValue("密码错误或者无效");
        msg.getNode("login", "succes").setValue("成功登录");
        msg.getNode("login", "bot_kick").setValue("密码错太多了 小心被封");
        msg.getNode("login", "bot_ban").setValue("恭喜您被封IP了 是不是想盗号？");
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

    public CommentedConfigurationNode getRootNode() {
        return rootNode;
    }
}
