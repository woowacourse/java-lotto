package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.MatchResult;
import lotto.domain.Money;
import lotto.domain.RandomLottosFactory;
import lotto.domain.WinningLotto;
import lotto.view.OutputView;

/**
 *
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/20
 */
public class LottoGame {
	private final Money money;
	private final Lottos lottos;
	private final WinningLotto winningLotto;

	public LottoGame(long purchaseMoney, String winningLotto, int bonusNumber) {
		this.money = new Money(purchaseMoney);
		this.lottos = buyLottos(money);
		this.winningLotto = generateWinningLotto(winningLotto, bonusNumber);
	}

	public Lottos buyLottos(Money money) {
		RandomLottosFactory randomLottosFactory = new RandomLottosFactory();
		return randomLottosFactory.generate(money.calculateBuyCount());
	}

	public WinningLotto generateWinningLotto(String winningLotto, int number) {
		Lotto lottoNumbers = Lotto.ofComma(winningLotto);
		LottoNumber bonusNumber = LottoNumber.of(number);
		return new WinningLotto(lottoNumbers, bonusNumber);
	}

	public void play() {
		OutputView.printLottos(lottos);
		MatchResult matchResult = lottos.matchAll(winningLotto);
		long totalProfits = matchResult.calculateTotalProfits(money);
		OutputView.printStatistics(matchResult);
		OutputView.printTotalProfits(matchResult.calculateTotalProfits(money));
	}
}
