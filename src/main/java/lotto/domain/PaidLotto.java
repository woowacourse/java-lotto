package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class PaidLotto extends Lotto {
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "매개변수가 null 입니다.";

	public PaidLotto(final List<LottoNumber> inputLottoNumbers) {
		super(inputLottoNumbers);
	}

	public Rank getRank(final WinningLotto winningLotto, final BonusLottoNumber bonusLottoNumber) {
		int numberOfContain = this.calculateMatchCount(winningLotto);
		boolean hasBonusLottoNumber = this.isContain(bonusLottoNumber.getBonusLottoNumber());
		return Rank.getRank(numberOfContain, hasBonusLottoNumber);
	}

	public int calculateMatchCount(WinningLotto winningLotto) {
		Objects.requireNonNull(winningLotto, NULL_INPUT_EXCEPTION_MESSAGE);
		return (int) winningLotto.getLottoNumbers()
				.stream()
				.filter(this::isContain)
				.count();
	}
}
