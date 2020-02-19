package lotto.domain;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumber {
	LottoNumber bonusLottoNumber;

	BonusLottoNumber(int inputBonusLottoNumber, WinningLotto winningLotto) {
		this.bonusLottoNumber = LottoNumber.of(inputBonusLottoNumber);
		validateBonusLottoNumber(winningLotto);
	}

	private void validateBonusLottoNumber(WinningLotto winningLotto) {
		if (winningLotto.isContain(this.bonusLottoNumber)) {
			throw new WrongLottoNumberException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
		}
	}
}
