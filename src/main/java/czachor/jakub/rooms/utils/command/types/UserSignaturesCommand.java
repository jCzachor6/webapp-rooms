package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;

import java.util.List;

public class UserSignaturesCommand extends Command {
    private SignatureDao signatureDao;

    public UserSignaturesCommand(String author, List<String> details, SignatureDao signatureDao) {
        super(author, details);
        setType(CommandType.USER_SIGNATURES);
        this.signatureDao = signatureDao;
    }

    @Override
    public Message process() {
        List<Signature> signatures;
        String nickname = who();
        signatures = signatureDao.getSignaturesByUserNickname(who());
        if (signatures.isEmpty()) {
            return new Message("User " + nickname + " didn't leave any signatures.", author);
        } else {
            StringBuilder line = new StringBuilder(nickname + " signatures: \n");
            for (int i = 0; i < signatures.size(); i++) {
                buildLine(line, i, signatures.get(i).getContent());
            }
            return new Message(line.toString(), author);
        }
    }

    private String who() {
        if (details.isEmpty()) {
            return this.author;
        } else {
            return details.get(0);
        }
    }

    private void buildLine(StringBuilder builder, int index, String content) {
        builder.append(index + 1);
        builder.append(". ");
        builder.append(content);
        builder.append("\n");
    }
}
