package id.dev.merapitech.afterworld.translateaw.request;

public class RequestEdit {
    private String HeaderCode;
    private String signature;
    private DataEdit data;

    public RequestEdit() {
    }

    public RequestEdit(String headerCode, String signature, DataEdit data) {
        HeaderCode = headerCode;
        this.signature = signature;
        this.data = data;
    }

    public String getHeaderCode() {
        return HeaderCode;
    }

    public void setHeaderCode(String headerCode) {
        HeaderCode = headerCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public DataEdit getData() {
        return data;
    }

    public void setData(DataEdit data) {
        this.data = data;
    }

    public static class DataEdit{
        private String idlanguage;
        private String title;

        public DataEdit(String idlanguage, String title) {
            this.idlanguage = idlanguage;
            this.title = title;
        }

        public String getIdlanguage() {
            return idlanguage;
        }

        public void setIdlanguage(String idlanguage) {
            this.idlanguage = idlanguage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
