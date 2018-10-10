package czachor.jakub.rooms.utils.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {
    int maxParameters() default 0;

    String name();

    Class<?>[] beans() default {};
}
