package git.jackwisdom.sponge.securelogin.cfg;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import git.jackwisdom.sponge.securelogin.annotation.CfgVaule;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.DefaultConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

@Singleton
public class Config {
    private ConfigurationLoader<CommentedConfigurationNode> configManager;
    private Logger logger;
    private CommentedConfigurationNode rootNode;

    @Inject
    public Config(Logger logger, @DefaultConfig(sharedRoot = false) Path path) {
        this.logger = logger;
        this.configManager = HoconConfigurationLoader.builder().setPath(path).build();
        init();
    }


    private void init() {
        try {
            rootNode = configManager.load();
        } catch (NullPointerException | IOException e) {
            logger.info("An error happened while loading config creating new one");
            rootNode = configManager.createEmptyNode();
        }

        if (rootNode.getNode("nondefault").getValue() == null || ((Boolean) rootNode.getNode("nondefault").getValue()) != true) {
            logger.info("Resetting configuration to default");
            setDefault(rootNode);
            logger.info("Succeed reset config");
        }
    }


    @CfgVaule
    public String storage_file;
    @CfgVaule
    public String pwd_regex;
    @CfgVaule
    public boolean reg_force;
    @CfgVaule
    public int reg_ip;
    @CfgVaule
    public int login_maxTries;
    @CfgVaule
    public boolean login_kick;
    @CfgVaule
    public boolean login_ban;
    @CfgVaule
    public int login_banHour;
    @CfgVaule
    public ArrayList<String> commandWhitelist;
    @CfgVaule
    public String msg_bad__pwd;
    @CfgVaule
    public String msg_reg_not__reg__yet;
    @CfgVaule
    public String msg_login_not__login;
    @CfgVaule
    public String msg_login_fail;
    @CfgVaule
    public String msg_login_success;
    @CfgVaule
    public String msg_login_bot__kick;
    @CfgVaule
    public String msg_login_bot__ban;

    private void setDefault(CommentedConfigurationNode rootNode) {
        rootNode = configManager.createEmptyNode();
        rootNode.getNode("nondefault").setValue(true).setComment("If this is false the whole file " +
                "will be set to default");
        rootNode.getNode("storage", "file").setValue("pwd.conf");
        rootNode.getNode("pwd").getNode("regex").setValue("[a-zA-Z0-9]{6,10}").setComment("the regex of the password");
        rootNode.getNode("reg").getNode("force").setValue(true).setComment("Force Player to register or not");
        rootNode.getNode("reg", "ip").setValue(3).setComment("max reg per ip");
        rootNode.getNode("login", "maxTries").setValue(3).setComment("max tries");
        rootNode.getNode("login", "kick").setValue(true).setComment("whether kick after max tries");
        CommentedConfigurationNode login = rootNode.getNode("login");
        login.getNode("ban").setValue(true).setComment("ban ip or not after max tries");
        login.getNode("banHour").setValue(1).setComment("ban ip for hours");
        login.getNode("commandWhitelist").setValue(Arrays.asList(new String[]{"login", "register"})).setComment("cmd while list /n DO NOT " +
                "FORGET TO ADD LOGIN AND RGISTER IN IT");
        CommentedConfigurationNode msg = rootNode.getNode("msg");
        msg.getNode("reg", "bad_pwd").setValue("少年 你的指令需要在6到10个字母或者数字");
        msg.getNode("reg", "not_reg_yet").setValue("少年呀 你还没注册呢 输入/register 来注册");
        msg.getNode("login", "not_login").setValue("少年 你需要使用/login 来登录");
        msg.getNode("login", "fail").setValue("密码错误或者无效");
        msg.getNode("login", "success").setValue("成功登录");
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
