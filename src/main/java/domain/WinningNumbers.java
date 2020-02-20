package domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
	private final Lotto winningLotto;
	private final LottoNumber bonusNumber;

	public WinningNumbers(List<LottoNumber> sixNumbers, LottoNumber bonusNumber) {
		this.winningLotto = new Lotto(sixNumbers);
		this.bonusNumber = bonusNumber;
	}

	public List<Rank> compareLottos(List<Lotto> lottos) {
		List<Rank> ranks = new ArrayList<>();

		for (Lotto lotto : lottos) {
			compareLotto(ranks, lotto);
		}
		return ranks;
	}

	private void compareLotto(List<Rank> ranks, Lotto lotto) {
		Rank rank = lotto.compare(winningLotto, bonusNumber);
		if (rank != null) {
			ranks.add(rank);
		}
	}
}
