package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.models.dto.StatisticsDTO;
import czachor.jakub.rooms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller("statisticsController")
@RequestMapping("/stat")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET)
    ResponseEntity<StatisticsDTO> getTotalUsersJoinedIn() {
        return new ResponseEntity<>(statisticsService.getTotalUsersJoined(), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<StatisticsDTO>> getAllStatistics() {
        return new ResponseEntity<>(statisticsService.getAllStatistics(), HttpStatus.OK);
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    ResponseEntity<String> getNewUsername(){
        String username = statisticsService.generateUsername();
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
