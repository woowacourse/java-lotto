package controller;

import domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {
	private final ArrayList<Integer> LottoNumberCandidates = new ArrayList<>();
	private final LottoTickets lottoTickets = new LottoTickets();

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
		int randomCount = money.calculateTotalCount() - manualCount;

		purchaseManualLotto(manualCount);
		purchaseRandomLotto(randomCount);

		printLottoTickets(manualCount, randomCount, this.lottoTickets);
	}

	private void purchaseManualLotto(int count) {
		List<LottoNumbers> manualLottoNumbers = new ArrayList<>();
		for (List<Integer> inputNumbers : inputManualNumbers(count)) {
			manualLottoNumbers.add(LottoNumbers.of(inputNumbers));
		}

		lottoTickets.purchase(manualLottoNumbers);
	}

	private void purchaseRandomLotto(int count) {
		initCandidates();
		List<LottoNumbers> randomLottoNumbers = new ArrayList<>();

		while (count-- > 0) {
			Collections.shuffle(LottoNumberCandidates);
			randomLottoNumbers.add(LottoNumbers.of(LottoNumberCandidates.subList(0, 6)));
		}

		lottoTickets.purchase(randomLottoNumbers);
	}

	private void initCandidates() {
		for (int eachNumber = 1; eachNumber <= 45; eachNumber++) {
			LottoNumberCandidates.add(eachNumber);
		}
	}

}
