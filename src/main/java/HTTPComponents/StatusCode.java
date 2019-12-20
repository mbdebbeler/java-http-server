package HTTPComponents;

public enum StatusCode {
    OK(200), BAD_REQUEST(400), NOT_FOUND(404), NOT_ALLOWED(405);

    private final int value;

    StatusCode(int value) {
        this.value = value;
    }

    public String getReason() {
        switch (this) {
            case OK:
                return "OK";
            case BAD_REQUEST:
                return "Bad Request";
            case NOT_FOUND:
                return "Not Found";
            case NOT_ALLOWED:
                return "Method Not Allowed";
            default:
                return "Unknown";
        }
    }

    public String getValueAsString() {
        return String.valueOf(value);
    }
}
