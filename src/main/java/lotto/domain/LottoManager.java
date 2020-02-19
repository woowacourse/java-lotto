package lotto.domain;

import java.util.List;

public class LottoManager {
	private List<Lotto> lotteries;

	public LottoManager(int count) {
		lotteries = LottoFactory.createLotteries(count);
	}

	public void 통계(WinLotto winLotto) {
		for (Lotto lotto : lotteries) {
			int count = winLotto.compare(lotto);
			boolean isBonus = winLotto.isMatchBonus(lotto);
			if (count >= 3) {
				resultCountPlus(count, isBonus);
			}
		}
	}

	private void resultCountPlus(int count, boolean isFalg) {
		Result result = Result.of(count);
		if (result == Result.THREE && isFalg) {
			result = Result.TWO;
		}
		result.countPlus();
	}

}
