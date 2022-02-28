package controller;

import domain.AnswerLotto;
import domain.LottoNumber;
import domain.LottoTickets;
import domain.StatisticCalculator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoGameController {
	public static void run() {
		try {
			int money = InputView.inputMoney();
			int manualLottoSize = InputView.inputManualLottoSize();
			LottoTickets lottoTickets = generateLottoTickets(money, manualLottoSize);
			List<Integer> lastWeekAnswerNumbers = InputView.inputAnsNumbers();
			LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
			AnswerLotto answerLotto = new AnswerLotto(lastWeekAnswerNumbers, bonusNumber);
			processResults(lottoTickets, answerLotto);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private static LottoTickets generateLottoTickets(int money, int manualLottoSize) {
		LottoTickets lottoTickets = new LottoTickets(money, manualLottoSize);
		OutputView.printLottoTickets(lottoTickets);
		return lottoTickets;
	}

	private static void processResults(LottoTickets lottoTickets,
									   AnswerLotto answerLotto) {
		StatisticCalculator statisticCalculator = new StatisticCalculator();
		statisticCalculator.updateResult(lottoTickets, answerLotto);
		OutputView.printStatistics(statisticCalculator.getCount());
		OutputView.printProfitRatio(statisticCalculator.calculateProfitRatio());
	}
}
