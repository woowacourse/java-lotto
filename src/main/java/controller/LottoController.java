package controller;

import java.util.Arrays;

import model.bonusball.BonusBall;
import model.lotto.LottoCount;
import model.lotto.LottoStorage;
import model.result.Rank;
import model.result.RateOfReturn;
import model.winningnumber.LottoWinningNumber;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	private LottoStorage lottoStorage;
	private LottoWinningNumber lottoWinningNumber;
	private BonusBall bonusBall;
	private RateOfReturn rateOfReturn;

	public void playGame() {
		makeLottos();
		storeWinningNumber();
		storeBonusBall();
		compareLottoWithWinningNumber();
		showResult();
	}

	private void makeLottos() {
		try {
			LottoCount lottoCount = new LottoCount(inputView.inputMoney());
			storeMoneyInRateOfReturn(lottoCount);
			lottoStorage = new LottoStorage(lottoCount);
			outputView.printLottos(lottoStorage.getLottoStorageDTO());
		} catch (Exception e) {
			outputView.printErrorMessage(e.getMessage());
			makeLottos();
		}
	}

	// private void makeLottos2() {
	// 	try {
	// 		LottoCount lottoCount = new LottoCount(inputView.inputMoney2());
	// 		storeMoneyInRateOfReturn(lottoCount);
	// 		lottoStorage = new LottoStorage(lottoCount);
	// 		outputView.printLottos(lottoStorage.getLottoStorageDTO());
	// 	} catch (IllegalArgumentException e) {
	// 		outputView.printErrorMessage(e.getMessage());
	// 		makeLottos2();
	// 	}
	// }

	private void storeMoneyInRateOfReturn(LottoCount lottoCount) {
		rateOfReturn = new RateOfReturn(lottoCount);
	}

	private void storeWinningNumber() {
		try {
			lottoWinningNumber = new LottoWinningNumber(inputView.inputWinningNumbers());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeWinningNumber();
		}
	}

	private void storeBonusBall() {
		try {
			String input = inputView.inputBonusBall();
			bonusBall = new BonusBall(input);
			lottoWinningNumber.validateReduplicationWithBonusBall(input);
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeBonusBall();
		}
	}
	//
	// private void storeBonusBall2() {
	// 	try {
	// 		int input = inputView.inputBonusBall2();
	// 		bonusBall = new BonusBall(input);
	// 		lottoWinningNumber.validateReduplicationWithBonusBall2(input);
	// 	} catch (IllegalArgumentException e) {
	// 		outputView.printErrorMessage(e.getMessage());
	// 		storeBonusBall2();
	// 	}
	// }

	private void compareLottoWithWinningNumber() {
		lottoStorage.checkWithWinningNumberAndBonus(bonusBall.getBonusBallDTO(),
			lottoWinningNumber.getWinningNumbersDTO(), rateOfReturn);
	}

	private void showResult() {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
				rateOfReturn.countStatistics(statistics),
				Rank.BONUS.getValue()));
		outputView.printRateOfReturn(rateOfReturn.getRateOfReturn());
	}

}
