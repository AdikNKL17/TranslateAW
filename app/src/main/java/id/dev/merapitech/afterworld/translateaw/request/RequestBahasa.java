package id.dev.merapitech.afterworld.translateaw.request;

public class RequestBahasa {
    private String HeaderCode;
    private String signature;
    private DataBahasa data;

    public RequestBahasa() {
    }

    public RequestBahasa(String headerCode, String signature, DataBahasa data) {
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

    public DataBahasa getData() {
        return data;
    }

    public void setData(DataBahasa data) {
        this.data = data;
    }

    public static class DataBahasa{
        private String idlanguage_change;

        public DataBahasa(String idlanguage_change) {
            this.idlanguage_change = idlanguage_change;
        }

        public String getIdlanguage_change() {
            return idlanguage_change;
        }

        public void setIdlanguage_change(String idlanguage_change) {
            this.idlanguage_change = idlanguage_change;
        }
    }
}
