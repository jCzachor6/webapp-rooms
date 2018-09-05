package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class UserSignaturesCommand extends Command {
    private SignatureDao signatureDao;

    public UserSignaturesCommand(List<String> details, SignatureDao signatureDao) {
        super(details);
        setType(CommandType.USER_SIGNATURES);
        this.signatureDao = signatureDao;
    }

    @Override
    public Message process(String from, String roomkey) {
        List<Signature> signatures;
        String nickname;
        if (details.isEmpty()) {
            nickname = from;
        } else {
            nickname = details.get(0);
        }
        signatures = signatureDao.getSignaturesByUserNickname(nickname);
        if (signatures.isEmpty()) {
            return new Message("User " + nickname + " didn't leave any signatures.", from, roomkey);
        } else {
            StringBuilder line = new StringBuilder(nickname + " signatures: \n");
            for (int i = 0; i < signatures.size(); i++) {
                buildIndexedLine(line, i, signatures.get(i).getContent());
            }
            return new Message(line.toString(), from, roomkey);
        }
    }
}
