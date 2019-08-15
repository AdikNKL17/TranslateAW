package id.dev.merapitech.afterworld.translateaw.request;

public class RequestDetail {
    private String HeaderCode;
    private String signature;
    private DataDetail data;

    public RequestDetail() {
    }

    public RequestDetail(String headerCode, String signature, DataDetail data) {
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

    public DataDetail getData() {
        return data;
    }

    public void setData(DataDetail data) {
        this.data = data;
    }

    public static class DataDetail{
        private String idlanguage;

        public DataDetail(String idlanguage) {
            this.idlanguage = idlanguage;
        }

        public String getIdlanguage() {
            return idlanguage;
        }

        public void setIdlanguage(String idlanguage) {
            this.idlanguage = idlanguage;
        }
    }
}
