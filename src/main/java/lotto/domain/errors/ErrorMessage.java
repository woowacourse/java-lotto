package lotto.domain.errors;

public enum ErrorMessage {
    NOT_NUMBER("\n====================================\nERROR: 숫자를 입력해주세요.\n===================================="),
    DUPLICATE_NUMBER("\n====================================\nERROR: 중복된 숫자가 존재합니다.\n===================================="),
    OVER_SCOPE("\n====================================\nERROR: 1 ~ 45의 숫자를 입력해주세요.\n===================================="),
    NUMBER_COUNT_NOT_SIX("\n====================================\nERROR: 6개의 숫자를 입력해주세요.\n===================================="),
    CAN_NOT_DIVIDE_BY_PRICE_UNIT("\n====================================\nERROR: 1000원 단위로 입력해주세요.\n====================================");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
