package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.bonusball.BonusBall;
import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.result.Rank;
import model.result.RateOfReturn;
import model.winningnumber.LottoWinningNumber;
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
			InputValidateUtils.inputBlankAndNumber(money, LOTTO_COUNT_BLANK_ERROR_MESSAGE,
				LOTTO_COUNT_NUMBER_ERROR_MESSAGE);
			storeMoneyInRateOfReturn(Integer.parseInt(money));
			lottos = new Lottos(new LottoCount(Integer.parseInt(money)));
			outputView.printLottos(lottos.getLottosDTO());
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
			lottoWinningNumber = new LottoWinningNumber(makeInputWinningNumbersToLottoNumbers(numbers));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeWinningNumber();
		}
	}

	private List<String> splitWinningNumber(String input) {
		return Arrays.stream(input.split(DELIMITER_COMMA))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private List<LottoNumber> makeInputWinningNumbersToLottoNumbers(List<String> numbers) {
		return numbers.stream()
			.map(number -> LottoNumber.parseLottoNumber(number))
			.collect(Collectors.toList());
	}

	//BonusBall
	private void storeBonusBall() {
		try {
			String input = inputView.inputBonusBall();
			InputValidateUtils.inputBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
			bonusBall = new BonusBall(LottoNumber.parseLottoNumber(input));
			lottoWinningNumber.validateReduplicationWithBonusBall(LottoNumber.valueOf(Integer.parseInt(input)));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			storeBonusBall();
		}
	}

	private void compareLottoWithWinningNumber() {
		lottos.checkWithWinningNumberAndBonus(bonusBall.getBonusBallDTO(),
			lottoWinningNumber.getWinningNumbersDTO(), rateOfReturn);
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
