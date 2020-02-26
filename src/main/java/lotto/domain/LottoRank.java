package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum LottoRank {
	FIRST(6, 2_000_000_000),
	SECOND(5, 30_000_000),
	THIRD(5, 1_500_000),
	FOURTH(4, 50_000),
	FIFTH(3, 5_000),
	MISS(0, 0);

	private static final Map<Integer, LottoRank> LOTTO_RANK_MAP_WITHOUT_SECOND;

	static {
		LOTTO_RANK_MAP_WITHOUT_SECOND = new HashMap<>();

		Arrays.stream(values())
			.filter(rank -> !rank.isLottoRankOf(SECOND))
			.filter(rank -> !rank.isLottoRankOf(MISS))
			.forEach(rank -> LOTTO_RANK_MAP_WITHOUT_SECOND.put(rank.matchCount, rank));
	}

	private int matchCount;
	private int winningMoney;

	LottoRank(int matchCount, int winningMoney) {
		this.matchCount = matchCount;
		this.winningMoney = winningMoney;
	}

	public static LottoRank of(int matchCount, boolean hasBonusNumber) {
		if (SECOND.matchCount == matchCount && hasBonusNumber) {
			return SECOND;
		}

		Optional<LottoRank> lottoRank = Optional.ofNullable(LOTTO_RANK_MAP_WITHOUT_SECOND.get(matchCount));
		return lottoRank.orElse(MISS);
	}

	public boolean isLottoRankOf(LottoRank lottoRank) {
		return this == lottoRank;
	}

	public int getWinningMoney() {
		return winningMoney;
	}

	public int getMatchCount() {
		return matchCount;
	}
}
