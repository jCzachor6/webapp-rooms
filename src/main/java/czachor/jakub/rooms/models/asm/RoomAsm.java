package czachor.jakub.rooms.models.asm;

import czachor.jakub.rooms.controller.RoomController;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.dto.RoomDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RoomAsm extends ResourceAssemblerSupport<Room, RoomDTO> {
    private ModelMapper modelMapper;

    public RoomAsm() {
        super(RoomController.class, RoomDTO.class);
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    @Override
    public RoomDTO toResource(Room room) {
        RoomDTO roomDTO = map(room);
        roomDTO.add(
                linkTo(methodOn(RoomController.class).getRoomByKey(roomDTO.getKey())).withSelfRel()
        );
        return roomDTO;
    }

    public RoomDTO map(Room room){
        return modelMapper.map(room, RoomDTO.class);
    }

    public Room map(RoomDTO roomDTO){
        return modelMapper.map(roomDTO, Room.class);
    }
}
