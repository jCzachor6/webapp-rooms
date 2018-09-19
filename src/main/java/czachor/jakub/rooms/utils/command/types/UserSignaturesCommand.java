package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class UserSignaturesCommand extends Command {
    private SignatureDao signatureDao;

    public UserSignaturesCommand(List<String> details, SignatureDao signatureDao) {
        super(CommandType.USER_SIGNATURES, details);
        this.signatureDao = signatureDao;
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        List<Signature> signatures;
        String nickname;
        if (details.isEmpty()) {
            nickname = helper.getUser().getUsername();
        } else {
            nickname = details.get(0);
        }
        Message message = new Message(Consts.BOT_NAME, MessageType.COMMAND);
        signatures = signatureDao.getSignaturesByUserNickname(nickname);
        if (signatures.isEmpty()) {
            message.setLine("User " + nickname + " didn't leave any signatures.");
        } else {
            StringBuilder line = new StringBuilder(nickname + " signatures: \n");
            for (int i = 0; i < signatures.size(); i++) {
                buildIndexedLine(line, i, signatures.get(i).getContent());
            }
            message.setLine(line.toString());
        }
        return message;
    }
}
