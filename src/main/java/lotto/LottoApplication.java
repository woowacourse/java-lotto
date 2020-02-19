package lotto;

import static lotto.controller.LottoManager.*;

import java.util.List;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.controller.RankCalculator;
import lotto.domain.WinningNumber;
import lotto.utils.NumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		Money money = new Money(InputView.inputAsMoney());
		int purchasedLottoAmount = calculateLottoAmount(money);
		OutputView.showPurchasedLottoCount(purchasedLottoAmount);

		List<Lotto> purchasedLotto = generateLottoByAmount(purchasedLottoAmount);
		OutputView.showPurchasedLottoNumbers(purchasedLotto);

		List<Integer> inputWinningNumbers = NumberParser.winningNumberParse(InputView.inputAsWinningNumbers());
		Lotto winningNumberLotto = new Lotto(inputWinningNumbers);
		BonusNumber bonusNumber = new BonusNumber(NumberParser.parseNumber(InputView.inputAsBonusNumber()));
		WinningNumber winningLottoNumber = new WinningNumber(winningNumberLotto, bonusNumber);
		List<Rank> ranks = RankCalculator.calculateMultiple(purchasedLotto, winningLottoNumber);

		OutputView.showStatistics(ranks);
		OutputView.showEarningRate(money, ranks);
	}
}
