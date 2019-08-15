package id.dev.merapitech.afterworld.translateaw.response;

public class ResponseJenis {
    private String idlanguage_change;
    private String title;

    public ResponseJenis() {
    }

    public ResponseJenis(String idlanguage_change, String title) {
        this.idlanguage_change = idlanguage_change;
        this.title = title;
    }

    public String getIdlanguage_change() {
        return idlanguage_change;
    }

    public void setIdlanguage_change(String idlanguage_change) {
        this.idlanguage_change = idlanguage_change;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
