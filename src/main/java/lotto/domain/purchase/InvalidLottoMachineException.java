package lotto.domain.purchase;

public class InvalidLottoMachineException extends IllegalArgumentException {
	public static final String INVALID = "입력한 수동 로또의 개수가 일치하지 않습니다.";

	public InvalidLottoMachineException(String s) {
		super(s);
	}
}
