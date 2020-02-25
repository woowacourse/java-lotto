package exception;

public class LottoNumberDuplicateException extends RuntimeException {
	public LottoNumberDuplicateException() {
		super("로또 번호가 중복되거나, 6개를 입력하지 않았습니다.");
	}
}
