package lotto.view;

public enum ErrorMessages {
    ERROR_LOTTO_NUMBER_DUPLICATED("[Error] 로또번호 라인은 중복되지 않은 6개의 숫자여야 합니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
