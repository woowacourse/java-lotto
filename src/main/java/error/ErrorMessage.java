package error;

public enum ErrorMessage {
    INVALID_NUMBERS_SIZE("로또 번호 갯수가 일치하지 않습니다."),
    INVALID_DUPLICATE_NUMBER("로또 번호는 중복되어선 안 됩니다."),
    INVALID_NUMBER_RANGE("로또 번호는 1~45 사이의 숫자여야 합니다."),
    BONUS_NUMBER_ALREADY_EXIST("보너스 번호가 당첨번호에 이미 존재합니다."),
    INVALID_TICKET_PRICE("티켓은 1,000원 단위로만 구매 가능합니다."),
    BLANK_INPUT("공백을 입력했습니다."),
    ONLY_POSITIVE_NUMBER("양수만 입력 가능합니다.");

    private final String message;
    private final String prefix = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = prefix + message;
    }

    public String getMessage() {
        return message;
    }
}
