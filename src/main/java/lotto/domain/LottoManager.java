package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
	private final List<Lotto> lotteries;
	private final WinLotto winLotto;

	public LottoManager(List<Lotto> lotteris, WinLotto winLotto) throws CloneNotSupportedException {
		this.lotteries = new ArrayList<>(lotteris);
		this.winLotto = (WinLotto)winLotto.clone();
	}

	public List<Rank> compareLotteries() {
		List<Rank> ranks = new ArrayList<>();

		for (Lotto lotto : lotteries) {
			int count = winLotto.compare(lotto);
			boolean isBonus = winLotto.isMatchBonus(lotto);
			ranks.add(findRank(count, isBonus));
		}
		return ranks;
	}

	private Rank findRank(int count, boolean isBonus) {
		Rank lottoResult = Rank.findRank(count);
		if (lottoResult.isSecond(isBonus)) {
			lottoResult = Rank.SECOND;
		}
		return lottoResult;

	}

}
