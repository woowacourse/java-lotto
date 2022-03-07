package exception;

public enum ExceptionMessage {
    INVALID_LOTTO_SIZE("로또는 6개의 중복되지 않은 숫자로 이루어져 있어야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 범위를 벗어난 숫자입니다."),
    INVALID_TICKET_COUNT("로또 티켓 수는 0 이상 이어야 합니다."),
    INVALID_MANUAL_TICKET_COUNT("수동으로 구입 가능한 티켓 수는 전체 로또 티켓 수 보다 작아야 합니다."),
    INVALID_TOTAL_TICKET_COUNT("수동 티켓 수와 자동 티켓 수의 합이 총 구매 가능한 티켓수와 다릅니다."),
    BELOW_RANGE_PAYMENT("금액이 로또 가격보다 작습니다."),
    OVER_RANGE_PAYMENT("로또는 한사람 당 10만원씩만 살 수 있습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 숫자가 로또 번호에 포함되었습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
