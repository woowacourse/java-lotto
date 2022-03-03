package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.bonusball.BonusBall;
import model.lotto.LottoCount;
import model.lotto.LottoStorage;
import model.result.Rank;
import model.result.RateOfReturn;
import model.winningnumber.LottoWinningNumber;
import utils.InputValidateUtils;
import view.InputView;
import view.OutputView;

public class LottoController {
	private static final String WINNING_NUMBER_ERROR_MESSAGE = "[Error]: 당첨 번호는 숫자여야 합니다.";
	private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
	private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
	private static final String BONUS_BALL_NUMBER_ERROR_MESSAGE = "[Error]: 보너스 볼은 숫자여야 합니다.";

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
			String money = inputView.inputMoney();
			LottoCount lottoCount = new LottoCount(money);
			storeMoneyInRateOfReturn(Integer.parseInt(money));
			lottoStorage = new LottoStorage(lottoCount);
			outputView.printLottos(lottoStorage.getLottoStorageDTO());
		} catch (Exception e) {
			outputView.printErrorMessage(e.getMessage());
			makeLottos();
		}
	}

	private void storeMoneyInRateOfReturn(int money) {
		rateOfReturn = new RateOfReturn(money);
	}

	//WinningNumber
	private void storeWinningNumber() {
		try {
			String input = inputView.inputWinningNumbers();
			InputValidateUtils.inputBlank(input, WINNING_NUMBER_BLANK_ERROR_MESSAGE);
			List<String> numbers = splitWinningNumber(input);
			InputValidateUtils.inputNumber(String.join("", numbers), WINNING_NUMBER_ERROR_MESSAGE);
			lottoWinningNumber = new LottoWinningNumber(makeInputWinningNumbersToNumbers(numbers));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeWinningNumber();
		}
	}

	private List<String> splitWinningNumber(String input) {
		return Arrays.stream(input.split(","))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private List<Integer> makeInputWinningNumbersToNumbers(List<String> numbers) {
		return numbers.stream()
			.map(number -> Integer.parseInt(number))
			.collect(Collectors.toList());
	}

	//BonusBall
	private void storeBonusBall() {
		try {
			String input = inputView.inputBonusBall();
			InputValidateUtils.inputBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
			InputValidateUtils.inputNumber(input, BONUS_BALL_NUMBER_ERROR_MESSAGE);
			bonusBall = new BonusBall(Integer.parseInt(input));
			lottoWinningNumber.validateReduplicationWithBonusBall(Integer.parseInt(input));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeBonusBall();
		}
	}

	private void compareLottoWithWinningNumber() {
		lottoStorage.checkWithWinningNumberAndBonus(bonusBall.getBonusBallDTO(),
			lottoWinningNumber.getWinningNumbersDTO(), rateOfReturn);
	}

	private void showResult() {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() >= 3)
			.forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
				rateOfReturn.countStatistics(statistics),
				Rank.BONUS.getValue()));
		outputView.printRateOfReturn(rateOfReturn.getRateOfReturn());
	}

}
