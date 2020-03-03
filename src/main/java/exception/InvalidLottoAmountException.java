package exception;

public class InvalidLottoAmountException extends RuntimeException {
	public final static String NEGATIVE_NUMBER = "로또의 갯수는 음수가 올 수 없습니다.";
	public final static String EXCESS_SELF_LOTTO_AMOUNT = "수동복권이 구매금액을 초과했습니다.";

	public InvalidLottoAmountException(String message) {
		super(message);
	}
}
