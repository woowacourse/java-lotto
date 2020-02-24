package lotto.domain.lottoRank;

public class InvalidMatchCountException extends IllegalArgumentException {
	public static final String OUT_OF_BOUND = "로또 번호의 일치하는 개수가 유효하지 않습니다.";

	public InvalidMatchCountException(String s) {
		super(s);
	}
}
