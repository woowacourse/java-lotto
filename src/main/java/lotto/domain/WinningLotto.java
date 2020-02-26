package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import lotto.domain.exception.InvalidWinningLottoException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoMoney;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

public class WinningLotto {
	private static final int SUM_UNIT = 1;
	private static final int INIT_COUNT = 0;

	private Lotto winningLotto;
	private LottoNumber bonusLottoNumber;

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

	public Map<LottoRank, Integer> getWinningLottoCount(Lottos lottos) {
		Map<LottoRank, Integer> lottoRankCount = new TreeMap<>(Collections.reverseOrder());

		Arrays.stream(LottoRank.values())
			.forEach(lottoRank -> lottoRankCount.put(lottoRank, INIT_COUNT));

		for (Lotto lotto : lottos) {
			LottoRank lottoRank = LottoRank.of(lotto.getMatchCount(winningLotto), lotto.contains(bonusLottoNumber));
			lottoRankCount.replace(lottoRank, lottoRankCount.get(lottoRank) + SUM_UNIT);
		}
		return lottoRankCount;
	}

	public int getWinningRatio(Map<LottoRank, Integer> lottoRankCount, LottoMoney inputLottoMoney) {
		LottoMoney initLottoMoney = LottoRank.MISS.getWinningMoney();
		for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
			initLottoMoney = initLottoMoney.add(lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return initLottoMoney.getWinningRatio(inputLottoMoney);
	}
}
