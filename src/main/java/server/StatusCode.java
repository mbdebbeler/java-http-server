package server;

public enum StatusCode {
    OK(200), BAD_REQUEST(400), NOT_FOUND(404);

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
            case NOT_FOUND:
                return "Not Found";
            default:
                return "Unknown";
        }
    }

    String getValueAsString() {
        return String.valueOf(value);
    }
}
