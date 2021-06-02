package sample.InterfacesAnnotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnyOf {
    String[] value();
}
