package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class UserSignaturesCommand extends Command {
    private SignatureDao signatureDao;

    public UserSignaturesCommand(CommandDetailsLoader loader, SignatureDao signatureDao) {
        super(1, loader);
        this.signatureDao = signatureDao;
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        MessageBuilder builder =
                new MessageBuilder()
                        .from(Consts.BOT_NAME)
                        .type(MessageType.COMMAND)
                        .target(Destination.Target.USER)
                        .targetName(helper.getSessionId());
        List<Signature> signatures;
        String nickname;
        if (firstParam().equals("")) {
            nickname = helper.getUser().getUsername();
        } else {
            nickname = firstParam();
        }
        signatures = signatureDao.getSignaturesByUserNickname(nickname);
        if (signatures.isEmpty()) {
            builder.line("User " + nickname + " didn't leave any signatures.");
        } else {
            StringBuilder line = new StringBuilder(nickname + " signatures: \n");
            for (int i = 0; i < signatures.size(); i++) {
                buildIndexedLine(line, i, signatures.get(i).getContent());
            }
            builder.line(line.toString());
        }
        return builder.buildAsSingletonList();
    }
}
