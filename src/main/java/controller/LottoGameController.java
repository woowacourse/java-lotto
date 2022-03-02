package controller;

import domain.AnswerLotto;
import domain.Lotto;
import domain.AutoGenerator;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoTickets;
import domain.ManualGenerator;
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
		LottoTickets lottoTickets = new LottoTickets(money);
		if (manualLottoSize > 0) {
			generateManualLottos(lottoTickets, manualLottoSize);
		}
		generateAutoLottos(lottoTickets);
		OutputView.printLottoTickets(lottoTickets, manualLottoSize);
		return lottoTickets;
	}

	private static AnswerLotto generateAnswerLotto() {
		InputView.noticeAnswerLottoNumbersInput();
		List<Integer> lastWeekAnswerNumbers = InputView.inputMultipleNumber();
		InputView.noticeBonusNumberInput();
		LottoNumber bonusNumber = new LottoNumber(InputView.inputSingleNumber());
		LottoGenerator manualGenerator = new ManualGenerator(lastWeekAnswerNumbers);
		return new AnswerLotto(manualGenerator.generateLottoNumbers(), bonusNumber);
	}


	private static void processResults(LottoTickets lottoTickets,
									   AnswerLotto answerLotto) {
		StatisticCalculator statisticCalculator = new StatisticCalculator();
		statisticCalculator.updateResult(lottoTickets, answerLotto);
		OutputView.printStatistics(statisticCalculator.getCount());
		OutputView.printProfitRatio(statisticCalculator.calculateProfitRatio());
	}

	private static void generateManualLottos(LottoTickets lottoTickets, int manualLottoSize) {
		InputView.noticeManualLottoNumbersInput();
		for (int index = 0; index < manualLottoSize; index++) {
			LottoGenerator manualGenerator = new ManualGenerator(InputView.inputMultipleNumber());
			lottoTickets.add(
				new Lotto(manualGenerator.generateLottoNumbers()));
		}
	}

	private static void generateAutoLottos(LottoTickets lottoTickets) {
		for (int index = 0; index < lottoTickets.calculateRemainLottoCount(); index++) {
			LottoGenerator autoGenerator = new AutoGenerator();
			lottoTickets.add(new Lotto(autoGenerator.generateLottoNumbers()));
		}
	}
}
