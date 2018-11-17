package git.jackwisdom.sponge.securelogin.cfg;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;


public class TextConfig {
    @ConfigSerializable
    public class Register {
        @Setting
        private String bad_pwd = "少年 你的密码需要在6到10个字母或者数字";
        @Setting
        private String not_reg = "少年呀 你还没注册呢 输入/register 来注册";

    }

    @ConfigSerializable
    public class Login {
        @Setting
        private String fail = "登录失败密码错误或者无效";
        @Setting
        private String success = "登录成功";
        @Setting
        private String bot_kick = "密码错太多了 小心被封";
        @Setting
        private String bot_ban = "恭喜您被封IP了 是不是想盗号";
    }
}
