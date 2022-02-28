package controller;

import domain.AnswerLotto;
import domain.LottoNumbers;
import domain.LottoTickets;
import domain.Money;
import util.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {
	LottoTickets lottoTickets = new LottoTickets();

	public void run() {
		try {
			purchaseLotto(new Money(inputMoney()), inputManualLottoCount());
			AnswerLotto answerLotto = AnswerLotto.of(inputAnsNumbers(), inputBonusNumber());
			printResults(this.lottoTickets.generateResult(answerLotto));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private void purchaseLotto(Money money, int manualCount) {
		money.canPurchase(manualCount);
		int randomCount = money.calculateCount() - manualCount;

		purchaseManualLotto(manualCount);
		purchaseRandomLotto(randomCount);

		printLottoTickets(manualCount, randomCount, this.lottoTickets);
	}

	private void purchaseRandomLotto(int count) {
		RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
		lottoTickets.purchase(randomLottoNumberGenerator.generate(count));
	}

	private void purchaseManualLotto(int count) {
		List<LottoNumbers> manualLottoNumbers = new ArrayList<>();
		for (List<Integer> inputNumbers : inputManualNumbers(count)) {
			manualLottoNumbers.add(LottoNumbers.of(inputNumbers));
		}

		lottoTickets.purchase(manualLottoNumbers);
	}

}
