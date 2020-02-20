package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class WinningLotto {
	private final Lotto lotto;
	private final Ball ball;

	public WinningLotto(Lotto lotto, Ball ball) {
		validateDuplication(lotto, ball);
		this.lotto = lotto;
		this.ball = ball;
	}

	private void validateDuplication(Lotto lotto, Ball ball) {
		if(lotto.contains(ball)) {
			throw new IllegalArgumentException();
		}
	}

	public TotalResult getResult(Lottos lottos) {
		Map<LottoRank, Integer> result = new LinkedHashMap<>();
		for(LottoRank lottoRank : LottoRank.values()) {
			result.put(lottoRank, 0);
		}
		for (Lotto lot: lottos) {
			int matchCount = lot.countCommonBalls(lotto);
			if (!LottoRank.isPrizeCount(matchCount)) {
				continue;
			}
			LottoRank rank = LottoRank.getRank(matchCount);
			if(rank == LottoRank.THIRD && lot.contains(ball)) {
				rank = LottoRank.SECOND;
			}
			result.put(rank, result.get(rank) + 1);
		}
		return new TotalResult(new LottoResult(result), lottos.getCount());
	}
}
