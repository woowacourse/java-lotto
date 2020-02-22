package lotto.domain;

/**
 * 클래스 이름 : BonusLottoNumber.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumber extends LottoNumber {
	private static final String BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE = "보너스 번호는 당첨번호와 중복될 수 없습니다.";

	public BonusLottoNumber(final String inputBonusLottoNumber) {
		super(inputBonusLottoNumber);
	}

	public static void validateBonusLottoNumber(final String inputBonusLottoNumber, final WinningLotto winningLotto) {
		int IntegerBonusLottoNumber = LottoNumber.parseToInteger(inputBonusLottoNumber);
		if (winningLotto.isContain(LottoNumber.of(IntegerBonusLottoNumber))) {
			throw new InvalidLottoNumberException(BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE);
		}
	}

	public LottoNumber getBonusLottoNumber() {
		return this;
	}
}
