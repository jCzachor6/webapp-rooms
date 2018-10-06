package czachor.jakub.rooms.utils.command;

abstract class CommandFieldsLoader {
    private int maxParameters;
    protected CommandDetailsLoader detailsLoader;
    private void setLoaderMaxParams(){
        this.detailsLoader.with(maxParameters);
    }
}
