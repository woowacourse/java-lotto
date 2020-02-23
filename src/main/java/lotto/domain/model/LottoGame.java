package lotto.domain.model;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResultDto;
import lotto.domain.result.RankCount;
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
			getRankAndCount(lotto);
		}
		return gameResult;
	}

	private void getRankAndCount(Lotto lotto) {
		Optional<Statistic> statistic = this.winningLotto.isWinningLotto(lotto);
		plusCount(statistic);
	}

	private void plusCount(Optional<Statistic> statistic) {
		Optional<RankCount> rank = null;
		if (Objects.nonNull(statistic) && statistic.isPresent()) {
			rank = gameResult.of(statistic);
		}
		if (Objects.nonNull(rank) && rank.isPresent()) {
			rank.get().plusCount();
		}
	}

	private double calculateProfit() {
		return gameResult.getEarningMoney(money);
	}
}
