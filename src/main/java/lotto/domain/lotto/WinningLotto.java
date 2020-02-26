package lotto.domain.lotto;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.Rank;

import java.util.List;
import java.util.Objects;

/**
 * 로또를 상속받은 우승로또, 사용자로부터 입력을 받은대로 생성
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLotto {
	private static final String BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE = "보너스 번호는 당첨번호와 중복될 수 없습니다.";
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "매개변수가 null 입니다.";

	Lotto winningLotto;
	LottoNumber bonusLottoNumber;

	public WinningLotto(final List<LottoNumber> inputLottoNumbers, LottoNumber inputBonusLottoNumber) {
		this.winningLotto = LottoFactory.createManualLotto(LottoType.WINNING_LOTTO, inputLottoNumbers);		if (winningLotto.isContain(inputBonusLottoNumber)) {
			throw new InvalidLottoNumberException(BONUS_NUMBER_OVERLAP_EXCEPTION_MESSAGE);
		}
		this.bonusLottoNumber = inputBonusLottoNumber;
	}

	public Rank getRank(final Lotto lotto) {
		int numberOfContain = this.calculateMatchCount(lotto);
		boolean hasBonusLottoNumber = lotto.isContain(bonusLottoNumber);
		return Rank.getRank(numberOfContain, hasBonusLottoNumber);
	}

	public int calculateMatchCount(Lotto lotto) {
		Objects.requireNonNull(lotto, NULL_INPUT_EXCEPTION_MESSAGE);
		return (int) lotto.getLottoNumbers()
				.stream()
				.filter(this::isContain)
				.count();
	}

	public boolean isContain(LottoNumber lottoNumber) {
		return winningLotto.getLottoNumbers().stream()
				.anyMatch(value -> value.equals(lottoNumber));

	}
}
