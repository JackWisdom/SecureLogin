package git.jackwisdom.sponge.securelogin.cfg;

import git.jackwisdom.sponge.securelogin.annotation.CfgVaule;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class ConfigFactory {

    public static void loadFields(Logger logger, Config config) {
        for (Field f : config.getClass().getFields()) {
            CfgVaule vaule = f.getAnnotation(CfgVaule.class);
            //收集注解过的变量
            if (f == null || vaule == null) {
                continue;
            }
            f.setAccessible(true);
            //获取对应CFG路径
            String node;

            if (vaule.path() == null || vaule.path().equals("")) {
                node = f.getName().replaceAll("_", ".").replace("..", "_");
            } else {
                node = vaule.path();
            }
            try {
                /*
            if(config.getRootNode().getNode(node).getValue()==null){
                config.getRootNode().getNode(node).setValue("FU*CKYOU");
                config.save();
                break;

            }*/
                CommentedConfigurationNode cnode = config.getRootNode();
                for (String s : node.split(".")) {
                    cnode = cnode.getNode(s);
                }
                f.set(config, cnode.getValue());

            } catch (Exception e) {
                logger.error("AN ERROR HAPPENED WHILE SETTING VALUE FOR \n" + node);
                e.printStackTrace();
            }

        }
    }
}
