package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.models.dto.RoomDTO;
import czachor.jakub.rooms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("roomController")
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/{key}")
    public ResponseEntity<RoomDTO> getRoomByKey(@PathVariable("key") String key){
        RoomDTO roomDTO = roomService.getRoomByKey(key);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }
}
