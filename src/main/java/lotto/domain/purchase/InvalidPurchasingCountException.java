package lotto.domain.purchase;

public class InvalidPurchasingCountException extends IllegalArgumentException {

	public static final String INVALID = "구매 가능한 숫자가 아닙니다.";
	public static final String NOT_INTEGER = "입력이 숫자가 아닙니다.";
	public static final String MANUAL_OVER = "수동 구매 개수가 전체 구매 개수를 초과하였습니다.";

	public InvalidPurchasingCountException(String s) {
		super(s);
	}

}
