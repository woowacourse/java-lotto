package exception;

public class LottoNumberRangeException extends RuntimeException {
	public LottoNumberRangeException() {
		super("로또번호는 1~45의 수가 필요합니다.");
	}
}
