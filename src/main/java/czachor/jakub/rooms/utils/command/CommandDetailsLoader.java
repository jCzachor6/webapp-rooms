package czachor.jakub.rooms.utils.command;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class CommandDetailsLoader {
    private DetailsPicker picker;
    @Getter
    private String command;
    @Getter
    private String restOfLine;

    public CommandDetailsLoader(String messageLine) {
        loadCommand(messageLine);
        loadRestOfLine(messageLine);
        this.picker = new DetailsPicker(this);
    }

    public DetailsPicker with(int maxParameters){
        picker.setMaxParams(maxParameters);
        return picker;
    }


    private void loadRestOfLine(String line) {
        int index = getIndexOfFirstSpaceOrEnd(line);
        if(!isEndOfLine(line, index)){
            this.restOfLine = line.substring(index+1);
        }else{
            this.restOfLine = "";
        }
    }

    private boolean isEndOfLine(String line, int index){
        return index == line.length();
    }

    private void loadCommand(String line){
        int index = getIndexOfFirstSpaceOrEnd(line);
        this.command = line.substring(1, index);
    }

    private int getIndexOfFirstSpaceOrEnd(String line){
        if(line.contains(" ")){
            return line.indexOf(" ");
        }else{
            return line.length();
        }
    }

    class DetailsPicker{
        @Setter
        private int maxParams;
        private CommandDetailsLoader loader;
        private DetailsPicker(CommandDetailsLoader loader) {
        this.loader = loader;
        this.maxParams = 0;
        }

        public String pickFirst(){
            if(maxParams == 0){
                return "";
            }else if(maxParams == 1){
                return loader.restOfLine;
            }else{
                return nthWord(1);
            }
        }

        public String pickSecond(){
            if(maxParams <= 1){
                return "";
            }else if(maxParams == 2) {
                return getFromNthSpaceToEnd(1);
            }else {
                return nthWord(2);
            }
        }

        public String pickLast(){
            if(maxParams == 0){
                return "";
            }else if(maxParams == 1){
                return loader.restOfLine;
            }else {
                return getFromNthSpaceToEnd(maxParams-1);
            }
        }

        private String getFromNthSpaceToEnd(int n){
            int indexOfNthSpace = StringUtils.ordinalIndexOf(loader.restOfLine, " ", n);
            if(indexOfNthSpace != -1){
                return loader.restOfLine.substring(indexOfNthSpace + 1);
            }
            return "";
        }

        private String nthWord(int n){
            String[] temp = loader.restOfLine.split(" ");
            if(n-1 < temp.length){
                return temp[n - 1];
            }
            return "";
        }
    }
}
