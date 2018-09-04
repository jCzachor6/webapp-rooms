package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.models.dto.RoomDTO;
import czachor.jakub.rooms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("roomController")
@RequestMapping({"/room", "/r"})
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRoomByKey(@RequestParam(value = "key", required = true) String key){

        RoomDTO roomDTO = roomService.getRoomByKey(key);
        ModelAndView mav = new ModelAndView("room", HttpStatus.OK);
        mav.addObject("room", roomDTO);
        return mav;
    }
}
