package server;

public enum StatusCode {
    OK(200), BAD_REQUEST(400);

    private final int value;

    StatusCode(int value) {
        this.value = value;
    }

    String getReason() {
        switch (this) {
            case OK:
                return "OK";
            case BAD_REQUEST:
                return "Bad Request";
            default:
                return "Unknown";
        }
    }

    String getValueAsString() {
        return String.valueOf(value);
    }
}
