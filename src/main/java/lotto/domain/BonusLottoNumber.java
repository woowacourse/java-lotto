package lotto.domain;

/**
 * BonusLottoNumber.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumber {
	private LottoNumber bonusLottoNumber;

	public BonusLottoNumber(final int inputBonusLottoNumber, final WinningLotto winningLotto) {
		this.bonusLottoNumber = LottoNumber.of(inputBonusLottoNumber);
		validateBonusLottoNumber(winningLotto);
	}

	private void validateBonusLottoNumber(final WinningLotto winningLotto) {
		if (winningLotto.isContain(this.bonusLottoNumber)) {
			throw new WrongLottoNumberException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
		}
	}

	public LottoNumber getBonusLottoNumber() {
		return this.bonusLottoNumber;
	}
}
