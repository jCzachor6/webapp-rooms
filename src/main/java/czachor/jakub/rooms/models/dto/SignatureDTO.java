package czachor.jakub.rooms.models.dto;

import lombok.Data;

@Data
public class SignatureDTO {
    private String content;
    private String userNickname;
    private String roomKey;
}
