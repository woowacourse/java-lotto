package lotto.view;

public enum ErrorMessages {
    ERROR_LOTTO_NUMBER_DUPLICATED("[Error] 로또번호 라인은 중복되지 않은 6개의 숫자여야 합니다."),
    ERROR_LOTTO_NUMBER_OUT_OF_BOUND("[Error] 로또 번호는 1부터 45까지 입니다."),
    ERROR_LOTTO_MONEY_NOT_ENOUGH("[ERROR] 돈이 부족합니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
