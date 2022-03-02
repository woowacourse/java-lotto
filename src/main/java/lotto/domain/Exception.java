package lotto.domain;

public enum Exception {

    ERROR_CANT_PURCHASE("[ERROR] 구입 금액으로 살 수 있는 수량이어야 합니다.");

    private final String message;

    Exception(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
