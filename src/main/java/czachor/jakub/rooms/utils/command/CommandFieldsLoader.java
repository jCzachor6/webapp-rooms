package czachor.jakub.rooms.utils.command;

abstract class CommandFieldsLoader {
    protected int maxParameters;
    protected CommandDetailsLoader detailsLoader;
    private void setLoaderMaxParams(){
        this.detailsLoader.with(maxParameters);
    }
}
