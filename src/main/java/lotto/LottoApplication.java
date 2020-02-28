package lotto;

import static lotto.domain.LottoManager.*;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoAmount;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;
import lotto.utils.LottoNumberGenerator;
import lotto.utils.NumberGenerator;
import lotto.utils.NumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		Money budget = new Money(NumberParser.parseNumber(InputView.inputAsMoney()));
		int manualLottoAmount = NumberParser.parseNumber(InputView.inputAsManualLottoAmount());
		LottoAmount purchasedLottoAmount = new LottoAmount(calculateLottoAmount(budget),
			manualLottoAmount);
		OutputView.showPurchasedLottoCount(purchasedLottoAmount);

		Lottos manualLotto = generateManualLotto(purchasedLottoAmount.getManualLottoAmount());
		Lottos autoLotto = generateAutoLottoByAmount(purchasedLottoAmount);
		Lottos totalLotto = manualLotto.concat(autoLotto);

		OutputView.showPurchasedLottoNumbers(totalLotto);
		showResultRanks(budget, totalLotto);
	}

	private static void showResultRanks(Money budget, Lottos totalLotto) {
		List<Rank> resultRanks = produceWinningNumber().matches(totalLotto);
		OutputView.showResult(budget, resultRanks);
	}

	private static Lottos generateAutoLottoByAmount(LottoAmount purchasedLottoAmount) {
		NumberGenerator autoLottoNumberGenerator = new LottoNumberGenerator();
		return generateLottoByAmount(purchasedLottoAmount.getAutoLottoAmount(),
			autoLottoNumberGenerator);
	}

	private static WinningNumber produceWinningNumber() {
		List<Integer> inputWinningNumbers = NumberParser.lottoNumberParse(InputView.inputAsWinningNumbers());
		Lotto winningNumberLotto = new Lotto(inputWinningNumbers);
		LottoNumber bonusNumber = LottoNumber.of(NumberParser.parseNumber(InputView.inputAsBonusNumber()));

		return new WinningNumber(winningNumberLotto, bonusNumber);
	}
}
