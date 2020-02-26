package exception;

public class LottoNumberDuplicateException extends RuntimeException {
	public LottoNumberDuplicateException() {
		super("로또 번호가 중복됩니다.");
	}
}
