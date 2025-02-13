package validator;

public enum ErrorMessages {
    NULL_INPUT("입력이 null 입니다."),
    EMPTY_STRING_INPUT("입력이 빈 문자열입니다."),
    WHITESPACE_ONLY_INPUT("입력이 공백으로만 구성되어 있습니다."),

    NOT_NUMBER("입력된 문자가 숫자가 아닙니다."),
    INVALID_MONEY_INPUT("천원 단위로 입력하세요."),
    LOTTO_NUMBER_OUT_OF_RANGE("로또의 숫자가 1~45의 유효 범위를 벗어납니다."),
    DUPLICATE_EXIST("중복된 내용이 존재합니다."),
    LOTTO_NUMBER_COUNT("로또의 구성 숫자는 6개여야합니다."),
    SAME_NUMBER("보너스 번호가 당첨 번호와 같습니다..");

    private static final String ERROR_SIGN = "[ERROR] ";

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_SIGN + message;
    }
}
