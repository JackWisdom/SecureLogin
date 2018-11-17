package git.jackwisdom.sponge.securelogin.cfg.settings;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Arrays;
import java.util.List;

@ConfigSerializable
public class MainConfig {
    //general
    @Setting
    boolean nondefault = true;
    @Setting
    String storage = "pwd.conf";
    //reg
    @Setting
    String pwdRegex = "[a-zA-Z0-9]{6,10}";
    @Setting
    int regPerIp = 3;
    @Setting
    boolean forceReg = true;
    //login
    @Setting
    int maxTries = 3;
    @Setting(comment = "After max tries, 0 means nothing,1 means kick,2 means ban,3 means banIP")
    int robot = 1;
    @Setting
    int hour = 3;
    @Setting
    List<String> commandWhiteList = Arrays.asList(new String[]{"login", "register"});
}
