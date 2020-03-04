package lotto.exceptions;

import lotto.domain.number.LottoNumber;

import java.util.Set;

public class NotSixSizeException extends IllegalArgumentException {
	private static String MESSAGE = "의 사이즈가 6이 아닙니다.";

	public NotSixSizeException(Set<LottoNumber> illegalLottoNumbers) {
		super(illegalLottoNumbers.toString() + MESSAGE);
	}
}
