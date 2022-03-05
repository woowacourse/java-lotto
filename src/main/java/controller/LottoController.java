package controller;

import java.util.Arrays;

import model.Money;
import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.result.LottoResult;
import model.result.Rank;
import model.winningnumber.WinningLottoNumber;
import strategy.InputLottoNumbersGenerationStrategy;
import strategy.RandomLottoNumbersGenerationStrategy;
import utils.InputValidateUtils;
import view.InputView;
import view.OutputView;

public class LottoController {
	private static final String MONEY_BLANK_ERROR_MESSAGE = "[Error]: 금액을 입력해주세요.";
	private static final String MONEY_NUMBER_ERROR_MESSAGE = "[Error]: 금액은 숫자를 입력해주세요.";
	private static final String COUNT_BLANK_ERROR_MESSAGE = "[Error]: 갯수을 입력해주세요.";
	private static final String COUNT_NUMBER_ERROR_MESSAGE = "[Error]: 갯수는 숫자를 입력해주세요.";
	private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";

	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();
	private final InputLottoNumbersGenerationStrategy manualStrategy = new InputLottoNumbersGenerationStrategy();
	private final RandomLottoNumbersGenerationStrategy automaticStrategy = new RandomLottoNumbersGenerationStrategy();

	public void playGame() {
		Money insertedMoney = insertMoney();
		LottoCount automaticLottoCount = new LottoCount(insertedMoney.makeMoneyToCount());
		LottoResult lottoResult = new LottoResult();
		LottoCount manualLottoCount = inputManualLottoCount(automaticLottoCount);
		Lottos lottos = makeLottos(manualLottoCount, automaticLottoCount);
		printLottos(manualLottoCount, automaticLottoCount, lottos);
		WinningLottoNumber winningLottoNumber = storeWinningNumber();
		compareLottoWithWinningNumber(lottos, winningLottoNumber, lottoResult);
		showResult(insertedMoney, lottoResult);
	}

	private Money insertMoney() {
		try {
			String money = inputView.inputMoney();
			InputValidateUtils.checkInputIsBlankAndNumber(money, MONEY_BLANK_ERROR_MESSAGE,
				MONEY_NUMBER_ERROR_MESSAGE);
			return new Money(Integer.parseInt(money));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return insertMoney();
		}
	}

	private LottoCount inputManualLottoCount(LottoCount automaticLottoCount) {
		try {
			String count = inputView.inputManualLottoCount();
			InputValidateUtils.checkInputIsBlankAndNumber(count, COUNT_BLANK_ERROR_MESSAGE, COUNT_NUMBER_ERROR_MESSAGE);
			automaticLottoCount.decreaseCount(Integer.parseInt(count));
			return new LottoCount(Integer.parseInt(count));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return inputManualLottoCount(automaticLottoCount);
		}
	}

	private Lottos makeLottos(LottoCount manualLottoCount, LottoCount automaticLottoCount) {
		try {
			inputView.inputManualLottoMessage();
			return new Lottos(manualLottoCount, manualStrategy, automaticLottoCount, automaticStrategy);
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return makeLottos(manualLottoCount, automaticLottoCount);
		}
	}

	private void printLottos(LottoCount manualLottoCount, LottoCount automaticLottoCount, Lottos lottos) {
		outputView.printLottos(manualLottoCount.getCount(), automaticLottoCount.getCount(), lottos.getLottosDTO());
	}

	private WinningLottoNumber storeWinningNumber() {
		try {
			inputView.inputWinningNumbersMessage();
			LottoNumbers lottoNumbers = LottoNumbers.from(manualStrategy);
			return new WinningLottoNumber(lottoNumbers, makeBonusBall());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return storeWinningNumber();
		}
	}

	private LottoNumber makeBonusBall() {
		String input = inputView.inputBonusBall();
		InputValidateUtils.checkInputIsBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
		return LottoNumber.parseLottoNumber(input);
	}

	private void compareLottoWithWinningNumber(Lottos lottos, WinningLottoNumber winningLottoNumber,
		LottoResult lottoResult) {
		lottos.checkWithWinningNumberAndBonus(winningLottoNumber.getWinningLottoNumbersDTO(), lottoResult);
	}

	private void showResult(Money insertedMoney, LottoResult lottoResult) {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() >= 3)
			.forEach(rank -> outputView.printResult(rank.getMatchNumber(), rank.getValue(),
				lottoResult.getCountOfResult(rank),
				Rank.SECOND.getValue()));
		outputView.printRateOfReturn(lottoResult.getSumOfRewards() / insertedMoney.getMoney());
	}

}
