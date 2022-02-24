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
			StatisticCalculator statisticCalculator = new StatisticCalculator();
			LottoTickets lottoTickets = generateLottoTickets();
			OutputView.printLottoTickets(lottoTickets);
			AnswerLotto answerLotto = generateAnswerLotto();
			processResults(statisticCalculator, lottoTickets, answerLotto);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private static LottoTickets generateLottoTickets() {
		return new LottoTickets(InputView.inputMoney());
	}

	private static AnswerLotto generateAnswerLotto() {
		List<Integer> lastWeekAnswerNumbers = InputView.inputAnsNumbers();
		LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
		return new AnswerLotto(lastWeekAnswerNumbers, bonusNumber);
	}

	private static void processResults(StatisticCalculator statisticCalculator, LottoTickets lottoTickets,
									   AnswerLotto answerLotto) {
		statisticCalculator.updateResult(lottoTickets, answerLotto);
		OutputView.printStatistics(statisticCalculator.getCount());
		OutputView.printProfitRatio(statisticCalculator.calculateProfitRatio());
	}
}
