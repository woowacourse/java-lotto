package lotto.domain.Lotto;

public class InvalidLottoException extends IllegalArgumentException {
    public static final String NULL = "null은 입력할 수 없습니다.";
    public static final String DUPLICATION = "로또 번호는 중복된 번호가 올 수 없습니다.";
    public static final String WRONG_SIZE = "로또 번호의 개수가 올바르지 않습니다.";

    InvalidLottoException(String s) {
        super(s);
    }
}
