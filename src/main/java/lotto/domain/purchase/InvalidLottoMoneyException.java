package lotto.domain.purchase;

public class InvalidLottoMoneyException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "null 또는 빈 문자열이 입력되었습니다.\n";
	public static final String NOT_INTEGER = "구매 금액을 숫자로 입력해주세요.\n";
	public static final String NEGATIVE = "구매 불가능한 금액입니다.\n";
	public static final String INVALID_UNIT = "구매는 1,000원 단위로 가능합니다.\n";

	public InvalidLottoMoneyException(String s) {
		super(s);
	}
}
