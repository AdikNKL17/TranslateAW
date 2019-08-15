package id.dev.merapitech.afterworld.translateaw.request;

public class RequestJenis {
    private String HeaderCode;
    private String signature;

    public RequestJenis() {
    }

    public RequestJenis(String headerCode, String signature) {
        HeaderCode = headerCode;
        this.signature = signature;
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
}
