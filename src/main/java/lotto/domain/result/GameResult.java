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

	// TODO: 2020/02/19 리턴 만들기 (통계 values) 
	public void makeStatistics() {
		Iterator<Lotto> lottoIterator = lottos.iterator();
		while (lottoIterator.hasNext()) {
			Lotto lotto = lottoIterator.next();
			Rank statistic = winningLotto.isWinningLotto(lotto);
			statistic.count();
		}
	}
}
