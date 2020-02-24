package lotto.exceptions;

public class LottoNumberIllegalArgumentException extends IllegalArgumentException {
	private static final String MESSAGE
			= " 번호는 유효한 숫자가 아닙니다. 공의 숫자는 1 이상 60 이하여야합니다.";

	private final int invalidLottoNumber;

	public LottoNumberIllegalArgumentException(final int invalidLottoNumber) {
		super(invalidLottoNumber + MESSAGE);
		this.invalidLottoNumber = invalidLottoNumber;
	}
}
