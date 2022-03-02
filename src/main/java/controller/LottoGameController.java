package controller;

import domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstant.*;
import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {
	private final List<Integer> LottoNumberCandidates = new ArrayList<>();
	private final LottoTickets lottoTickets = new LottoTickets();

	public void run() {
		try {
			purchaseLotto(new Money(inputMoney()), inputManualLottoCount());
			AnswerLotto answerLotto = AnswerLotto.of(inputAnswerNumbers(), inputBonusNumber());
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
			manualLottoNumbers.add(new LottoNumbers(inputNumbers));
		}

		lottoTickets.purchase(manualLottoNumbers);
	}

	private void purchaseRandomLotto(int count) {
		initCandidates();
		List<LottoNumbers> randomLottoNumbers = new ArrayList<>();

		while (count-- > 0) {
			Collections.shuffle(LottoNumberCandidates);
			randomLottoNumbers.add(new LottoNumbers(LottoNumberCandidates.subList(0, NUMBER_OF_NUMBERS)));
		}

		lottoTickets.purchase(randomLottoNumbers);
	}

	private void initCandidates() {
		for (int eachNumber = MIN_NUMBER; eachNumber <= MAX_NUMBER; eachNumber++) {
			LottoNumberCandidates.add(eachNumber);
		}
	}

}
