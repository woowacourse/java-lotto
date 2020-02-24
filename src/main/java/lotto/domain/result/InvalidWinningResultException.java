package lotto.domain.result;

public class InvalidWinningResultException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "결과가 존재하지 않습니다.\n";

	public InvalidWinningResultException(String s) {
		super(s);
	}
}
