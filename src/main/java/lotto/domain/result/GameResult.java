package lotto.domain.result;

import java.util.Iterator;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class GameResult {
	private final Lottos lottos;
	private final WinningLotto winningLotto;

	public GameResult(Lottos lottos, WinningLotto winningLotto) {
		this.lottos = lottos;
		this.winningLotto = winningLotto;
	}

	public void makeStatistics() {
		Iterator<Lotto> lottoIterator = lottos.iterator();
		while (lottoIterator.hasNext()) {
			Lotto lotto = lottoIterator.next();
			Statistic statistic = winningLotto.isWinningLotto(lotto);
			statistic.count();
		}
	}
}
