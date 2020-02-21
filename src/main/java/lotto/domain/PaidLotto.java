package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * 클래스 이름 : PaidLotto.java
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
		int numberOfContain = this.calculateMatchCount(winningLotto);
		boolean hasBonusLottoNumber = this.isContain(bonusLottoNumber.getBonusLottoNumber());

		return Rank.getRank(numberOfContain, hasBonusLottoNumber);
	}

	public int calculateMatchCount(WinningLotto winningLotto) {
		Objects.requireNonNull(winningLotto, "매개변수가 null 입니다.");
		return (int) winningLotto.getLottoNumbers()
				.stream()
				.filter(this::isContain)
				.count();
	}
}
