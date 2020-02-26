package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lotto.domain.lotto.LottoMoney;

public enum LottoRank {
	FIRST(6, new LottoMoney(2_000_000_000)),
	SECOND(5, new LottoMoney(30_000_000)),
	THIRD(5, new LottoMoney(1_500_000)),
	FOURTH(4, new LottoMoney(50_000)),
	FIFTH(3, new LottoMoney(5_000)),
	MISS(0, new LottoMoney(0));

	private final static Map<Integer, LottoRank> LOTTO_RANK_MAP_WITHOUT_SECOND;

	static {
		LOTTO_RANK_MAP_WITHOUT_SECOND = new HashMap<>();

		Arrays.stream(values())
			.filter(lottoRank -> lottoRank.isNot(SECOND))
			.filter(lottoRank -> lottoRank.isNot(MISS))
			.forEach(lottoRank -> LOTTO_RANK_MAP_WITHOUT_SECOND.put(lottoRank.matchCount, lottoRank));
	}

	private int matchCount;
	private LottoMoney winningMoney;

	LottoRank(int matchCount, LottoMoney winningMoney) {
		this.matchCount = matchCount;
		this.winningMoney = winningMoney;
	}

	public static LottoRank of(int matchCount, boolean hasBonusNumber) {
		if (hasBonusNumber && SECOND.matchCount == matchCount) {
			return SECOND;
		}

		Optional<LottoRank> lottoRank = Optional.of(LOTTO_RANK_MAP_WITHOUT_SECOND.get(matchCount));
		return lottoRank.orElse(MISS);
	}

	public boolean isLottoRankOf(LottoRank lottoRank) {
		return this == lottoRank;
	}

	private boolean isNot(LottoRank lottoRank) {
		return lottoRank != LottoRank.SECOND;
	}

	public LottoMoney getWinningMoney() {
		return winningMoney;
	}

	public int getMatchCount() {
		return matchCount;
	}
}
