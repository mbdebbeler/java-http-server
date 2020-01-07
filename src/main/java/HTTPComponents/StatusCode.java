package HTTPComponents;

public enum StatusCode {
    OK(200), NO_CONTENT(204), BAD_REQUEST(400), NOT_FOUND(404), NOT_ALLOWED(405), MOVED_PERMANENTLY(301);

    private final int value;

    StatusCode(int value) {
        this.value = value;
    }

    public String getReason() {
        switch (this) {
            case OK:
                return "OK";
            case NO_CONTENT:
                return "No Content";
            case BAD_REQUEST:
                return "Bad Request";
            case NOT_FOUND:
                return "Not Found";
            case NOT_ALLOWED:
                return "Method Not Allowed";
            case MOVED_PERMANENTLY:
                return "Moved Permanently";
            default:
                return "Unknown";
        }
    }

    public String getValueAsString() {
        return String.valueOf(value);
    }
}
