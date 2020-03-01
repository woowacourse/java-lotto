package lotto.domain.lotto;

import java.util.List;
import java.util.Objects;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottostrategy.LottoFactory;
import lotto.domain.result.Rank;
import lotto.util.StringUtils;

/**
 * 로또를 상속받은 우승로또, 사용자로부터 입력을 받은대로 생성
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLotto {
	private Lotto winningLotto;
	private LottoNumber bonusLottoNumber;

	public WinningLotto(final String inputLottoNumbers, final String inputBonusLottoNumber) {
		List<LottoNumber> winningLottoNumbers = StringUtils.splitIntoLottoNumbers(inputLottoNumbers);
		this.winningLotto = LottoFactory.createManualLotto(LottoType.WINNING_LOTTO, winningLottoNumbers);
		LottoNumber bonusLottoNumber = LottoNumber.of(StringUtils.parseToInteger(inputBonusLottoNumber));
		if (this.winningLotto.isContain(bonusLottoNumber)) {
			throw new InvalidLottoNumberException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
		}
		this.bonusLottoNumber = bonusLottoNumber;
	}

	public Rank getRank(final Lotto lotto) {
		int numberOfContain = this.calculateMatchCount(lotto);
		boolean hasBonusLottoNumber = lotto.isContain(bonusLottoNumber);
		return Rank.getRank(numberOfContain, hasBonusLottoNumber);
	}

	public int calculateMatchCount(Lotto lotto) {
		Objects.requireNonNull(lotto, "매개변수가 null 입니다.");
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
