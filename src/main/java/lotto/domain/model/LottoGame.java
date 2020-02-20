package lotto.domain.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.result.GameResultDto;
import lotto.domain.result.Statistic;
import lotto.exception.InvalidRankException;

public class LottoGame {
	private final Lottos lottos;
	private final WinningLotto winningLotto;
	private final PurchaseMoney money;

	public LottoGame(Lottos lottos, WinningLotto winningLotto, PurchaseMoney money) {
		this.lottos = lottos;
		this.winningLotto = winningLotto;
		this.money = money;
	}

	public List<Statistic> makeStatistics() {
		Iterator<Lotto> lottoIterator = lottos.iterator();
		while (lottoIterator.hasNext()) {
			Lotto lotto = lottoIterator.next();
			compareToWinningLotto(lotto);
		}
		return Arrays.asList(Statistic.values());
	}

	private void compareToWinningLotto(Lotto lotto) {
		try {
			Statistic statistic = winningLotto.isWinningLotto(lotto);
			statistic.count();
		} catch (Exception e) {
			throw new InvalidRankException("당첨되지 않은 번호입니다.");
		}
	}

	public double calculateProfit() {
		double profit = 0;
		for (Statistic statistic : Statistic.values()) {
			profit += statistic.getProfit();
		}
		return (profit / money.getPurchaseMoney()) * 100;
	}

	public GameResultDto getResult() {
		return new GameResultDto(makeStatistics(), calculateProfit());
	}

	;
}
