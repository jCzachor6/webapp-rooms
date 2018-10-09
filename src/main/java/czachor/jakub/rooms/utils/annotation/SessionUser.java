package czachor.jakub.rooms.utils.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component(value = "user")
@Scope(
        scopeName = "websocket",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface SessionUser {
}
