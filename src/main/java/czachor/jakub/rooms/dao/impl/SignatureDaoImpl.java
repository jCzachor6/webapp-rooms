package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.dao.AbstractDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.models.dto.SignatureDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Repository("signatureDao")
public class SignatureDaoImpl extends AbstractDao<Long, Signature> implements SignatureDao {
    @Override
    public List<Signature> getSignaturesByUserNickname(String nickname) {
        Query query = createQuery("from Signature s where s.user.nickname =:nickname");
        query.setParameter("nickname", nickname);
        List list = query.getResultList();
        if(list != null){
            return list;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Signature> getSignaturesByRoomKey(String key) {
        Query query = createQuery("from Signature s where s.room.key =:key");
        query.setParameter("key", key);
        List list = query.getResultList();
        if(list != null){
            return list;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public void addSignature(Signature signature) {
        persist(signature);
    }
}
