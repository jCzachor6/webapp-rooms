package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

@Command(maxParameters = 0, name = "roomsignatures", daos = {SignatureDao.class})
public class RoomSignaturesCommand extends AbstractCommand {

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        MessageBuilder builder =
                new MessageBuilder()
                        .from(Consts.BOT_NAME)
                        .type(MessageType.COMMAND)
                        .target(Destination.Target.USER)
                        .targetName(helper.getSessionId());
        List<Signature> signatures;
        signatures = signatureDao.getSignaturesByRoomKey(helper.getRoomKey());
        if (signatures.isEmpty()) {
            builder.line("Room has no signatures.");
        } else {
            StringBuilder line = new StringBuilder("Room signatures: \n");
            for (int i = 0; i < signatures.size(); i++) {
                buildIndexedLine(line, i, signatures.get(i).getContent());
            }
            builder.line(line.toString());
        }
        return builder.buildAsSingletonList();
    }
}
