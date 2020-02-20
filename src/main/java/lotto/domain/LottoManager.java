package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
	private static final int MIN_WIN_COUNT = 3;

	private final List<Lotto> lotteries;
	private final WinLotto winLotto;

	public LottoManager(List<Lotto> lotteris, WinLotto winLotto) throws CloneNotSupportedException {
		this.lotteries = new ArrayList<>(lotteris);
		this.winLotto = (WinLotto)winLotto.clone();
	}

	public void compareLotto() {
		for (Lotto lotto : lotteries) {
			int count = winLotto.compare(lotto);
			boolean isBonus = winLotto.isMatchBonus(lotto);
			resultCountPlus(count, isBonus);
		}
	}

	private void resultCountPlus(int count, boolean isBonus) {
		if (count < MIN_WIN_COUNT) {
			return;
		}
		LottoResult lottoResult = LottoResult.findRank(count);
		if (isSecond(isBonus, lottoResult)) {
			lottoResult = LottoResult.SECOND;
		}
		lottoResult.countPlus();
	}

	private boolean isSecond(boolean isBonus, LottoResult lottoResult) {
		return lottoResult == LottoResult.THIRD && isBonus;
	}
}
