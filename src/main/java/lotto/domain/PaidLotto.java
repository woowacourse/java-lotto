package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * 로또를 상속받은 구매한 로또 객체, 우승한 로또/ 보너스 번호와 구매한 로또 객체를 비교해서 등수를 얻을 수 있다
 *
 * @author 토니, 히히
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
