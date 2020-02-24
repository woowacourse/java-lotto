package lotto.domain.model;

import java.util.Iterator;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResultDto;
import lotto.domain.result.Statistic;

public class LottoGame {
	private final Lottos lottos;
	private final WinningLotto winningLotto;
	private final PurchaseMoney money;
	private final GameResult gameResult;

	public LottoGame(Lottos lottos, WinningLotto winningLotto, PurchaseMoney money) {
		this.lottos = lottos;
		this.winningLotto = winningLotto;
		this.money = money;
		gameResult = new GameResult();
	}

	public GameResultDto getGameResult() {
		return new GameResultDto(makeStatistics(), calculateProfit());
	}

	public GameResult makeStatistics() {
		Iterator<Lotto> lottoIterator = lottos.iterator();
		while (lottoIterator.hasNext()) {
			Lotto lotto = lottoIterator.next();
			plusCount(getRank(lotto));
		}
		return gameResult;
	}

	private Statistic getRank(Lotto lotto) {
		return this.winningLotto.isWinningLotto(lotto);
	}

	private void plusCount(Statistic statistic) {
		gameResult.of(statistic).plusCount();
	}

	private double calculateProfit() {
		return gameResult.getEarningMoney(money);
	}
}
