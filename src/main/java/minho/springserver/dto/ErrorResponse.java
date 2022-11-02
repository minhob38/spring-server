package minho.springserver.dto;

public class ErrorResponse {
    private final String status = "error";
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
