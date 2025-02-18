package lotto.common;

public enum ErrorMessage {

    ONLY_LOTTO_NUMBER("1~45사이의 숫자만 입력하세요."),
    INVALID_LOTTO_INPUT("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)"),
    INVALID_MONEY_INPUT("1000원 단위로 입력해주세요!"),
    USE_SEPARATOR("구분자(,)를 활용해주세요! (ex. 1, 2, 3, 4, 5, 6)"),
    INVALID_LOTTO_NUM_SIZE("로또 번호 6개를 입력해주세요."),
    DUPLICATE_LOTTO_NUMBER("로또 번호가 중복됩니다."),
    DUPLICATE_BONUS_NUMBER("보너스 넘버가 당첨 번호에 중복됩니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
