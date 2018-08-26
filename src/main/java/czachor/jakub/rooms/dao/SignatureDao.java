package czachor.jakub.rooms.dao;

import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.models.dto.SignatureDTO;

import java.util.List;

public interface SignatureDao {
    List<Signature> getSignaturesByUserNickname(String nickname);
    List<Signature> getSignaturesByRoomKey(String key);
    void addSignature(Signature signature);
    void deleteAll();
}
