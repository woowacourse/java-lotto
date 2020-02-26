package lotto.domain.lottoMoney;

public class InvalidPurchasingCountException extends IllegalArgumentException {
	public static final String INVALID = "구매 가능한 숫자가 아닙니다.";

	public InvalidPurchasingCountException(String s) {
		super(s);
	}
}
