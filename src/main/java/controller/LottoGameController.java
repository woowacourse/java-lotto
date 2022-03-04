package controller;

import domain.*;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {
	private final LottoTickets lottoTickets = new LottoTickets();

	public void run() {
		try {
			Money money = new Money(inputMoney());
			purchaseLotto(money, inputManualLottoCount());
			AnswerLotto answerLotto = AnswerLotto.of(inputAnswerNumbers(), inputBonusNumber());
			Result result = lottoTickets.generateResult(answerLotto);
			printResults(result, result.calculateProfitRate(money.getValue()));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private void purchaseLotto(Money money, int manualCount) {
		money.canPurchase(manualCount);
		int randomCount = money.calculateTotalCount() - manualCount;

		purchaseManualLotto(manualCount);
		purchaseRandomLotto(randomCount);

		printLottoTickets(manualCount, randomCount, lottoTickets);
	}

	private void purchaseManualLotto(int count) {
		List<LottoNumbers> manualLottoNumbers = new ArrayList<>();

		for (List<Integer> inputNumbers : inputManualNumbers(count)) {
			manualLottoNumbers.add(new LottoNumbers(inputNumbers));
		}

		lottoTickets.purchase(manualLottoNumbers);
	}

	private void purchaseRandomLotto(int count) {
		while (count-- > 0) {
			lottoTickets.purchase(LottoTicket.byRandom());
		}
	}

}
