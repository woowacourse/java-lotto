package lotto.domain;

import java.util.List;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class PaidLotto extends Lotto {

	public PaidLotto(final List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}

	public Rank getRank(final WinningLotto winningLotto, final BonusLottoNumber bonusLottoNumber) {
		int numberOfContain = winningLotto.calculateMatchCount(this);
		boolean hasBonusLottoNumber = this.isContain(bonusLottoNumber.getBonusLottoNumber());

		return Rank.getRank(numberOfContain, hasBonusLottoNumber);
	}
}
