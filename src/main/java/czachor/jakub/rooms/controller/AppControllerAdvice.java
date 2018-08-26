package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.exceptions.RoomDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(value = RoomDoesNotExistException.class)
    public ResponseEntity contactDoesNotExistException(final RoomDoesNotExistException e){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
