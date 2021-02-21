package lotto.view.messages;

public enum ErrorMessages {

    LOTTO_NUMBER_RANGE_ERROR("[ERROR] 로또 번호는 1부터 45까지 숫자입니다."),
    LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR("[Error] 로또번호 라인은 중복되지 않은 6개의 숫자여야 합니다."),
    LOTTO_PURCHASE_PRICE_ERROR("[Error] 로또 구입 금액은 1,000원 이상 입니다.(로또 1개 당 1,000원)"),
    LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR("[Error] 로또 번호와 보너스 번호가 중복됩니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
