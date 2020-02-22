package lotto.domain;

/**
 * 클래스 이름 : BonusLottoNumber.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumber {
	private static final String BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE = "보너스 번호는 당첨번호와 중복될 수 없습니다.";
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력 보너스 번호가 정수가 아닙니다.";

	LottoNumber bonusLottoNumber;

	public BonusLottoNumber(final String inputBonusLottoNumber, final WinningLotto winningLotto) {
		this.bonusLottoNumber = LottoNumber.of(parseToInteger(inputBonusLottoNumber));
		validateBonusLottoNumber(winningLotto);
	}

	private int parseToInteger(final String inputBonusLottoNumber) {
		try {
			return Integer.parseInt(inputBonusLottoNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	private void validateBonusLottoNumber(final WinningLotto winningLotto) {
		if (winningLotto.isContain(this.bonusLottoNumber)) {
			throw new InvalidLottoNumberException(BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE);
		}
	}

	public LottoNumber getBonusLottoNumber() {
		return this.bonusLottoNumber;
	}
}
