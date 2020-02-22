package lotto.domain.lottoNumber;

public class InvalidLottoNumberException extends IllegalArgumentException {
	public static final String OUT_OF_BOUND_LOTTO_NUMBER = "로또 번호는 1 이상 45 이하입니다.\n";
	public static final String NOT_INTEGER = "로또 번호는 정수를 입력해주세요.\n";

	public InvalidLottoNumberException(String s) {
		super(s);
	}
}
