package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.result.Rank;
import model.result.RateOfReturn;
import model.winningnumber.WinningLottoNumber;
import utils.InputValidateUtils;
import view.InputView;
import view.OutputView;

public class LottoController {
	private static final String LOTTO_COUNT_BLANK_ERROR_MESSAGE = "[Error]: 금액을 입력해주세요.";
	private static final String LOTTO_COUNT_NUMBER_ERROR_MESSAGE = "[Error]: 금액은 숫자를 입력해주세요.";
	private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
	private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
	private static final String DELIMITER_COMMA = ",";

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	private Lottos lottos;
	private WinningLottoNumber winningLottoNumber;
	private RateOfReturn rateOfReturn;

	public void playGame() {
		lottos = makeLottos();
		printLottos();
		winningLottoNumber = storeWinningNumber();
		compareLottoWithWinningNumber();
		showResult();
	}

	private Lottos makeLottos() {
		try {
			String money = inputView.inputMoney();
			InputValidateUtils.inputBlankAndNumber(money, LOTTO_COUNT_BLANK_ERROR_MESSAGE,
				LOTTO_COUNT_NUMBER_ERROR_MESSAGE);
			storeMoneyInRateOfReturn(Integer.parseInt(money));
			return new Lottos(new LottoCount(Integer.parseInt(money)));
		} catch (Exception e) {
			outputView.printErrorMessage(e.getMessage());
			return makeLottos();
		}
	}

	private void storeMoneyInRateOfReturn(int money) {
		rateOfReturn = new RateOfReturn(money);
	}

	private void printLottos() {
		outputView.printLottos(lottos.getLottosDTO());
	}

	//WinningNumber
	private WinningLottoNumber storeWinningNumber() {
		try {
			return new WinningLottoNumber(makeWinningNumber(), makeBonusBall());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return storeWinningNumber();
		}
	}

	private List<LottoNumber> makeWinningNumber() {
		String input = inputView.inputWinningNumbers();
		InputValidateUtils.inputBlank(input, WINNING_NUMBER_BLANK_ERROR_MESSAGE);
		List<String> numbers = splitWinningNumber(input);
		return makeInputWinningNumbersToWinningLottoNumbers(numbers);
	}

	private LottoNumber makeBonusBall() {
		String input = inputView.inputBonusBall();
		InputValidateUtils.inputBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
		return LottoNumber.parseLottoNumber(input);
	}

	private List<String> splitWinningNumber(String input) {
		return Arrays.stream(input.split(DELIMITER_COMMA))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private List<LottoNumber> makeInputWinningNumbersToWinningLottoNumbers(List<String> numbers) {
		return numbers.stream()
			.map(number -> LottoNumber.parseLottoNumber(number))
			.collect(Collectors.toList());
	}

	private void compareLottoWithWinningNumber() {
		lottos.checkWithWinningNumberAndBonus(winningLottoNumber.getWinningLottoNumbersDTO(), rateOfReturn);
	}

	private void showResult() {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() >= 3)
			.forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
				rateOfReturn.getCountOfResult(statistics),
				Rank.SECOND.getValue()));
		outputView.printRateOfReturn(rateOfReturn.getRateOfReturn());
	}

}
