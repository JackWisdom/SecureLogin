package git.jackwisdom.sponge.securelogin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface CfgVaule {
    String path() default "";
}
