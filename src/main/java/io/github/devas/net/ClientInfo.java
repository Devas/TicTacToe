package io.github.devas.net;

public class ClientInfo {

    private int port = 6789;
    private String locale;

    public ClientInfo(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
