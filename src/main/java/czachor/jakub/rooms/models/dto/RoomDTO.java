package czachor.jakub.rooms.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoomDTO extends ResourceSupport{
    private String name;
    private String info;
    private String key;
}
