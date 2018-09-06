package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.exceptions.RoomDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(value = RoomDoesNotExistException.class)
    public ModelAndView contactDoesNotExistException(final RoomDoesNotExistException e){
        ModelAndView mav = new ModelAndView("notfound", HttpStatus.NOT_FOUND);
        mav.addObject("errormessage", e.getMessage());
        return mav;
    }
}
