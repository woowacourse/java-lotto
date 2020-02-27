package lotto.domain.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.exception.InvalidWinningLottoException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

public class WinningLotto {
	private static final int INIT_COUNT = 0;
	private static final int SUM_UNIT = 1;

	private final Lotto winningLotto;
	private final LottoNumber bonusLottoNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusLottoNumber) {
		validateDuplicate(winningLotto, bonusLottoNumber);
		this.winningLotto = winningLotto;
		this.bonusLottoNumber = bonusLottoNumber;
	}

	private void validateDuplicate(Lotto winningLotto, LottoNumber bonusLottoNumber) {
		if (winningLotto.contains(bonusLottoNumber)) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.DUPLICATE_NUMBER);
		}
	}

	public WinningResult getWinningResult(Lottos lottos) {
		Map<LottoRank, Integer> lottoRankMap = new TreeMap<>(Collections.reverseOrder());

		Arrays.stream(LottoRank.values())
			.forEach(rank -> lottoRankMap.put(rank, INIT_COUNT));

		for (Lotto lotto : lottos) {
			LottoRank rank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.contains(bonusLottoNumber));
			lottoRankMap.replace(rank, lottoRankMap.get(rank) + SUM_UNIT);
		}

		return new WinningResult(lottoRankMap);
	}
}
