package controller;

import domain.AnswerLotto;
import domain.LottoMachine;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {

	public void run() {
		try {
			int money = inputMoney();
			int manualCount = inputmanualCount();

			LottoMachine lottoMachine = new LottoMachine(money, inputTotalManualNumbers(manualCount));
			printLottoTickets(manualCount, lottoMachine.getLottoTickets());

			printResult(lottoMachine.generateResult(AnswerLotto.of(inputAnswerNumbers(), inputBonusNumber())));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private List<List<Integer>> inputTotalManualNumbers(int manualCount) {
		if (manualCount == 0) {
			return new ArrayList<>();
		}
		return inputManualNumbers(manualCount);
	}

}
