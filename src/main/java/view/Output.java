package view;

public enum Output {
    PURCHASE_MESSAGE("%d개를 구매했습니다.");

    private String message;

    private Output(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
