package controller;

import domain.AnswerLotto;
import domain.LottoMachine;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {
	private LottoMachine lottoMachine;

	public void run() {
		try {
			lottoMachine = new LottoMachine(inputMoney());
			purchase();
			printResult(lottoMachine.generateResult(AnswerLotto.of(inputAnswerNumbers(), inputBonusNumber())));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private void purchase() {
		int manualCount = inputmanualCount();
		lottoMachine.purchase(inputTotalManualNumbers(manualCount));
		printLottoTickets(manualCount, lottoMachine.getLottoTickets());
	}

	private List<List<Integer>> inputTotalManualNumbers(int manualCount) {
		if (manualCount == 0) {
			return new ArrayList<>();
		}
		return inputManualNumbers(manualCount);
	}

}
