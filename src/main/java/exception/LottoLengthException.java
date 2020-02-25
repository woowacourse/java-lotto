package exception;

public class LottoLengthException extends RuntimeException {
	public LottoLengthException() {
		super("로또 번호 갯수가 6개가 아닙니다.");
	}
}
