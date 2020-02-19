package lotto;

import static lotto.domain.LottoManager.*;

import java.util.List;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.RankCalculator;
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
		List<Integer> winningNumber = NumberParser.winningNumberParse(InputView.inputAsWinningNumbers());
		Lotto winningNumberLotto = new Lotto(winningNumber);
		BonusNumber bonusNumber = new BonusNumber(NumberParser.parseNumber(InputView.inputAsBonusNumber()));
		WinningNumber winningNumber1 = new WinningNumber(winningNumberLotto, bonusNumber);
		List<Rank> ranks = RankCalculator.calculateMultiple(purchasedLotto, winningNumber1);
		OutputView.showStatistics(ranks);
		OutputView.showEarningRate(money, ranks);
	}
}
