package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import controller.strategy.RandomLottoNumbersGenerationStrategy;
import controller.strategy.StringInputLottoNumbersGenerationStrategy;
import model.Money;
import model.lotto.Lotto;
import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.result.LottoResult;
import model.result.Rank;
import model.winningnumber.WinningLottoNumber;
import utils.InputValidateUtils;
import view.LottoControllerInputView;
import view.LottoControllerOutputView;

public class LottoController {
	private static final String NUMBER_ERROR_MESSAGE = "[Error]: 입력은 숫자여야 합니다.";
	private static final String REGEX_NUMBER = "[0-9]+";

	private final LottoControllerInputView inputView = new LottoControllerInputView();
	private final LottoControllerOutputView outputView = new LottoControllerOutputView();
	private final StringInputLottoNumbersGenerationStrategy manualStrategy = new StringInputLottoNumbersGenerationStrategy();
	private final RandomLottoNumbersGenerationStrategy automaticStrategy = new RandomLottoNumbersGenerationStrategy();

	public void playGame() {
		Money insertedMoney = insertMoney();
		LottoCount automaticLottoCount = new LottoCount(insertedMoney.makeMoneyToCount());
		LottoCount manualLottoCount = inputManualLottoCount(automaticLottoCount);
		Lottos lottos = makeLottos(manualLottoCount, automaticLottoCount);
		printLottos(manualLottoCount, automaticLottoCount, lottos);
		WinningLottoNumber winningLottoNumber = storeWinningNumber();
		LottoResult lottoResult = compareLottoWithWinningNumber(lottos, winningLottoNumber);
		showResult(insertedMoney, lottoResult);
	}

	private Money insertMoney() {
		try {
			String money = inputView.inputMoney();
			InputValidateUtils.checkInputIsBlank(money);
			checkInputIsNumber(money);
			return new Money(Integer.parseInt(money));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return insertMoney();
		}
	}

	private LottoCount inputManualLottoCount(LottoCount automaticLottoCount) {
		try {
			String count = inputView.inputManualLottoCount();
			InputValidateUtils.checkInputIsBlank(count);
			checkInputIsNumber(count);
			int manualCount = Integer.parseInt(count);
			automaticLottoCount.decreaseCount(manualCount);
			return new LottoCount(manualCount);
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return inputManualLottoCount(automaticLottoCount);
		}
	}

	private Lottos makeLottos(LottoCount manualLottoCount, LottoCount automaticLottoCount) {
		try {
			inputView.requireManualLottoMessage();
			return new Lottos(manualLottoCount, manualStrategy, automaticLottoCount, automaticStrategy);
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return makeLottos(manualLottoCount, automaticLottoCount);
		}
	}

	private void printLottos(LottoCount manualLottoCount, LottoCount automaticLottoCount, Lottos lottos) {
		outputView.printLottos(manualLottoCount.getCount(), automaticLottoCount.getCount(),
			translateLottosForPrint(lottos));
	}

	private List<List<Integer>> translateLottosForPrint(Lottos lottos) {
		return lottos.getLottoStorage().stream()
			.map(Lotto::getNumbers)
			.map(LottoNumbers::getNumbers)
			.map(this::mapLottoNumbersToInts)
			.collect(Collectors.toList());
	}

	private List<Integer> mapLottoNumbersToInts(List<LottoNumber> lottoNumbers) {
		return lottoNumbers.stream()
			.map(LottoNumber::getNumber)
			.collect(Collectors.toList());
	}

	private WinningLottoNumber storeWinningNumber() {
		try {
			inputView.requireWinningNumbersMessage();
			LottoNumbers lottoNumbers = LottoNumbers.from(manualStrategy);
			return new WinningLottoNumber(lottoNumbers, makeBonusBall());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return storeWinningNumber();
		}
	}

	private LottoNumber makeBonusBall() {
		String input = inputView.inputBonusBall();
		InputValidateUtils.checkInputIsBlank(input);
		return LottoNumber.parseLottoNumber(input);
	}

	private LottoResult compareLottoWithWinningNumber(Lottos lottos, WinningLottoNumber winningLottoNumber) {
		return lottos.getResultOfLottos(winningLottoNumber.getWinningLottoNumbersDTO());
	}

	private void showResult(Money insertedMoney, LottoResult lottoResult) {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.filter(Rank::hasReward)
			.forEach(rank -> outputView.printResult(rank.getMatchNumber(), rank.getValue(),
				lottoResult.getCountOfResult(rank),
				Rank.SECOND.getValue()));
		outputView.printRateOfReturn(lottoResult.calculateSumOfRewards() / insertedMoney.getMoney());
	}

	private static void checkInputIsNumber(String number) {
		if (!number.matches(REGEX_NUMBER)) {
			throw new IllegalArgumentException(NUMBER_ERROR_MESSAGE);
		}
	}
}
