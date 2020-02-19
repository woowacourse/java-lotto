package lotto.domain;

import java.util.HashMap;
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

	public Map<LottoRank, Integer> getResult(Lottos lottos) {
		Map<LottoRank, Integer> result = new HashMap<>();
		for(LottoRank lottoRank : LottoRank.values()) {
			result.put(lottoRank, 0);
		}
		for (Lotto lot: lottos) {
			// 대기
		}
		return result;
	}
}
