package id.dev.merapitech.afterworld.translateaw.response;

public class ResponseBahasa {
    private String idlanguage;
    private String language_jenis;
    private String language_variable;
    private String title;


    public ResponseBahasa() {
    }

    public ResponseBahasa(String idlanguage, String language_jenis, String language_variable, String title) {
        this.idlanguage = idlanguage;
        this.language_jenis = language_jenis;
        this.language_variable = language_variable;
        this.title = title;
    }

    public String getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(String idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getLanguage_jenis() {
        return language_jenis;
    }

    public void setLanguage_jenis(String language_jenis) {
        this.language_jenis = language_jenis;
    }

    public String getLanguage_variable() {
        return language_variable;
    }

    public void setLanguage_variable(String language_variable) {
        this.language_variable = language_variable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
