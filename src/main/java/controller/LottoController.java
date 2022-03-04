package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Money;
import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.result.Rank;
import model.result.RateOfReturn;
import model.winningnumber.WinningLottoNumber;
import utils.InputValidateUtils;
import view.InputView;
import view.OutputView;

public class LottoController {
	private static final String MONEY_BLANK_ERROR_MESSAGE = "[Error]: 금액을 입력해주세요.";
	private static final String MONEY_NUMBER_ERROR_MESSAGE = "[Error]: 금액은 숫자를 입력해주세요.";
	private static final String COUNT_BLANK_ERROR_MESSAGE = "[Error]: 갯수을 입력해주세요.";
	private static final String COUNT_NUMBER_ERROR_MESSAGE = "[Error]: 갯수는 숫자를 입력해주세요.";
	private static final String LOTTO_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 번호를 입력하세요.";
	private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
	private static final String DELIMITER_COMMA = ",";

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	private Money insertedMoney;
	private LottoCount automaticlottoCount;
	private LottoCount passiveLottoCount;
	private Lottos lottos;
	private WinningLottoNumber winningLottoNumber;
	private RateOfReturn rateOfReturn;

	public void playGame() {
		insertedMoney = insertMoney();
		automaticlottoCount = new LottoCount(insertedMoney.makeMoneyToCount());
		rateOfReturn = new RateOfReturn();
		passiveLottoCount = inputPassiveLottoCount();
		lottos = makeLottos();
		printLottos();
		winningLottoNumber = storeWinningNumber();
		compareLottoWithWinningNumber();
		showResult();
	}

	private Money insertMoney() {
		try {
			String money = inputView.inputMoney();
			InputValidateUtils.inputBlankAndNumber(money, MONEY_BLANK_ERROR_MESSAGE,
				MONEY_NUMBER_ERROR_MESSAGE);
			return new Money(Integer.parseInt(money));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return insertMoney();
		}
	}

	private LottoCount inputPassiveLottoCount() {
		try {
			String count = inputView.inputPassiveLottoCount();
			InputValidateUtils.inputBlankAndNumber(count, COUNT_BLANK_ERROR_MESSAGE, COUNT_NUMBER_ERROR_MESSAGE);
			automaticlottoCount.useCountForPassive(Integer.parseInt(count));
			return new LottoCount(Integer.parseInt(count));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return inputPassiveLottoCount();
		}
	}

	private List<LottoNumbers> makePassiveLottos() {
		List<LottoNumbers> passiveLottos = new ArrayList<>();
		while (passiveLottoCount.haveRemainToMake()) {
			passiveLottoCount.reduceCountOfRemain();
			inputView.inputPassiveLottoMessage();
			passiveLottos.add(makeOnePassiveLotto());
		}
	}

	private LottoNumbers makeOnePassiveLotto() {
		try {
			String input = inputView.inputLottoNumbers();
			return LottoNumbers.changeFrom(makeLottoNumber(input));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return makeOnePassiveLotto();
		}
	}

	private Lottos makeLottos() {
		return new Lottos(automaticlottoCount);
	}

	private void printLottos() {
		outputView.printLottos(lottos.getLottosDTO());
	}

	private WinningLottoNumber storeWinningNumber() {
		try {
			String input = inputView.inputWinningNumbers();
			return new WinningLottoNumber(makeLottoNumber(input), makeBonusBall());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return storeWinningNumber();
		}
	}

	private List<LottoNumber> makeLottoNumber(String input) {
		InputValidateUtils.inputBlank(input, LOTTO_NUMBER_BLANK_ERROR_MESSAGE);
		List<String> numbers = splitLottoNumber(input);
		return makeInputNumbersToLottoNumbers(numbers);
	}

	private LottoNumber makeBonusBall() {
		String input = inputView.inputBonusBall();
		InputValidateUtils.inputBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
		return LottoNumber.parseLottoNumber(input);
	}

	private List<String> splitLottoNumber(String input) {
		return Arrays.stream(input.split(DELIMITER_COMMA))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	private List<LottoNumber> makeInputNumbersToLottoNumbers(List<String> numbers) {
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
		outputView.printRateOfReturn(rateOfReturn.getRateOfReturn() / insertedMoney.getMoney());
	}

}
