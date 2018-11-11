package git.jackwisdom.sponge.securelogin.cfg;

import git.jackwisdom.sponge.securelogin.annotation.CfgVaule;

import java.lang.reflect.Field;

public class ConfigFactory {

    public static void loadFields(Config config) {
        for (Field f : config.getClass().getFields()) {
            CfgVaule vaule = f.getAnnotation(CfgVaule.class);
            //收集注解过的变量
            if (f == null) {
                continue;
            }
            //获取对应CFG路径
            String node;
            if (vaule.path() != null) {
                node = vaule.path();
            } else {
                node = f.getName().replaceAll("_", ".").replace("..", "_");
            }
            try {
                f.set(config.getRootNode().getNode(node).getValue(), config);
            } catch (IllegalAccessException e) {
                System.err.println("AN ERROR HAPPENED WHILE SETTING VALUE FOR \n" + node);
                e.printStackTrace();
            }

        }
    }
}
