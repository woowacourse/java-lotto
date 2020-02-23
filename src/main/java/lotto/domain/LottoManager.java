package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
	private final List<Lotto> lotteries;
	private final WinLotto winLotto;

	public LottoManager(List<Lotto> lotteris, WinLotto winLotto) {
		this.lotteries = new ArrayList<>(lotteris);
		this.winLotto = winLotto;
	}

	public List<Rank> compareLotteries() {
		List<Rank> ranks = new ArrayList<>();

		for (Lotto lotto : lotteries) {
			int count = winLotto.compare(lotto);
			boolean isBonus = winLotto.isMatchBonus(lotto);
			ranks.add(Rank.findRank(count, isBonus));
		}
		return ranks;
	}

}
