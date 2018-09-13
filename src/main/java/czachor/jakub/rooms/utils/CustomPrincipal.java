package czachor.jakub.rooms.utils;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Scope("session")
public class CustomPrincipal implements Principal {
    @Setter
    private String nickname;

    public CustomPrincipal(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getName() {
        return nickname;
    }
}
