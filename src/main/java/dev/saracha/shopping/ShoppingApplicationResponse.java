package dev.saracha.shopping;

public class ShoppingApplicationResponse {
    private String message;

    public ShoppingApplicationResponse() {
    }

    public ShoppingApplicationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
