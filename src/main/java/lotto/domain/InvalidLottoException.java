package lotto.domain;

public class InvalidLottoException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "null 또는 빈 리스트는 입력할 수 없습니다.";
	public static final String DUPLICATION = "리스트에 중복된 번호가 올 수 없습니다.";

	public InvalidLottoException(String s) {
		super(s);
	}
}
