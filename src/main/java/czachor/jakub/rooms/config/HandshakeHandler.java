package czachor.jakub.rooms.config;

import czachor.jakub.rooms.service.StatisticsService;
import czachor.jakub.rooms.utils.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class HandshakeHandler extends DefaultHandshakeHandler {
    private final StatisticsService statisticsService;

    @Autowired
    public HandshakeHandler(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        return new CustomPrincipal(statisticsService.generateUsername());
    }
}
