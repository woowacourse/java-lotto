package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumber {
	private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "보너스 넘버와 중복될수 없습니다.";

	private Lotto winningNumber;
	private LottoNumber bonusNumber;

	public WinningNumber(Lotto winningNumber, LottoNumber bonusNumber) {
		this.winningNumber = winningNumber;

		if (bonusNumberDuplicatedWithWinningNumber(bonusNumber)) {
			throw new LottoNumberDuplicatedException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
		}
		this.bonusNumber = bonusNumber;
	}

	private boolean bonusNumberDuplicatedWithWinningNumber(LottoNumber bonusNumber) {
		return winningNumber.contains(bonusNumber);
	}

	public List<Rank> matches(Lottos lottos) {
		return lottos.stream()
			.map(this::match)
			.collect(Collectors.toList());
	}

	private Rank match(Lotto lotto) {
		return Rank.getRank(countHitNumber(lotto), hasBonusNumber(lotto));
	}

	public int countHitNumber(Lotto lotto) {
		return winningNumber.containCount(lotto);
	}

	public boolean hasBonusNumber(Lotto lotto) {
		return lotto.contains(bonusNumber);
	}
}
