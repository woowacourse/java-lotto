package lotterymachine.model;

public enum ErrorMessage {
    IS_NOT_NUMBER("숫자만 입력할 수 있습니다."),
    IS_NOT_INTEGER("음수가 아닌 정수를 입력해야합니다."),
    NOT_PURCHASABLE_COUNT("구매가능한 로또 개수를 초과하였습니다."),
    NOT_PURCHASABLE("1000보다 큰 금액을 입력해야합니다."),
    OUT_OF_RANGE("로또 범위에 맞지 않는 숫자가 있습니다."),
    INVALID_SIZE("로또 숫자는 여섯개를 입력해야합니다."),
    DUPLICATE_NUMBER("중복된 숫자가 입력되었습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 볼이 당첨 번호와 중복됩니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
