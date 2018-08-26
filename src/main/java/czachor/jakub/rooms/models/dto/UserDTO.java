package czachor.jakub.rooms.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String nickname;
    private Integer points;
    private List<SignatureDTO> signatures;
}
