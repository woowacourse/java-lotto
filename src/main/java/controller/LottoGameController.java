package controller;

import domain.AnswerLotto;
import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoTickets;
import domain.StatisticCalculator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoGameController {
	public static void run() {
		try {
			LottoTickets lottoTickets = generateLottoTickets(generateMoney(), generateManualLottoSize());
			AnswerLotto answerLotto = generateAnswerLotto();
			processResults(lottoTickets, answerLotto);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private static int generateMoney() {
		InputView.noticeMoneyInput();
		return InputView.inputSingleNumber();
	}

	private static int generateManualLottoSize() {
		InputView.noticeManualLottoSizeInput();
		return InputView.inputSingleNumber();
	}

	private static LottoTickets generateLottoTickets(int money, int manualLottoSize) {
		LottoTickets lottoTickets = new LottoTickets(money, manualLottoSize);
		if (lottoTickets.getManualLottoSize() > 0) {
			generateManualLottos(lottoTickets);
		}
		generateAutoLottos(lottoTickets);
		OutputView.printLottoTickets(lottoTickets);
		return lottoTickets;
	}

	private static AnswerLotto generateAnswerLotto() {
		InputView.noticeAnswerLottoNumbersInput();
		List<Integer> lastWeekAnswerNumbers = InputView.inputMultipleNumber();
		InputView.noticeBonusNumberInput();
		LottoNumber bonusNumber = new LottoNumber(InputView.inputSingleNumber());
		return new AnswerLotto(lastWeekAnswerNumbers, bonusNumber);
	}


	private static void processResults(LottoTickets lottoTickets,
									   AnswerLotto answerLotto) {
		StatisticCalculator statisticCalculator = new StatisticCalculator();
		statisticCalculator.updateResult(lottoTickets, answerLotto);
		OutputView.printStatistics(statisticCalculator.getCount());
		OutputView.printProfitRatio(statisticCalculator.calculateProfitRatio());
	}

	private static void generateManualLottos(LottoTickets lottoTickets) {
		InputView.noticeManualLottoNumbersInput();
		for (int index = 0; index < lottoTickets.getManualLottoSize(); index++) {
			lottoTickets.add(
				new Lotto(LottoGenerator.generateUserInputLottoNumbers(InputView.inputMultipleNumber())));
		}
	}

	private static void generateAutoLottos(LottoTickets lottoTickets) {
		for (int index = 0; index < lottoTickets.getLottoTicketsCapacity() - lottoTickets.getManualLottoSize();
			 index++) {
			lottoTickets.add(new Lotto(LottoGenerator.generateRandomLottoNumbers()));
		}
	}
}
