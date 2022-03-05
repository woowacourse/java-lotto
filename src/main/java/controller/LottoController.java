package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Money;
import model.lotto.LottoCount;
import model.lotto.Lottos;
import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.result.LottoResult;
import model.result.Rank;
import model.winningnumber.WinningLottoNumber;
import utils.InputLottoNumbersUtils;
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

	private Money insertedMoney;
	private LottoCount automaticLottoCount;
	private LottoCount manualLottoCount;
	private Lottos lottos;
	private WinningLottoNumber winningLottoNumber;
	private LottoResult lottoResult;

	public void playGame() {
		insertedMoney = insertMoney();
		automaticLottoCount = new LottoCount(insertedMoney.makeMoneyToCount());
		lottoResult = new LottoResult();
		manualLottoCount = inputManualLottoCount();
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

	private LottoCount inputManualLottoCount() {
		try {
			String count = inputView.inputPassiveLottoCount();
			InputValidateUtils.inputBlankAndNumber(count, COUNT_BLANK_ERROR_MESSAGE, COUNT_NUMBER_ERROR_MESSAGE);
			automaticLottoCount.deductCountForManual(Integer.parseInt(count));
			return new LottoCount(Integer.parseInt(count));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return inputManualLottoCount();
		}
	}

	private Lottos makeLottos() {
		Lottos lottos = new Lottos(automaticLottoCount);
		lottos.add(makeManuaalLottos());
		return lottos;
	}

	private List<LottoNumbers> makeManuaalLottos() {
		List<LottoNumbers> passiveLottos = new ArrayList<>();
		inputView.inputPassiveLottoMessage();

		while (manualLottoCount.haveRemainToMake()) {
			manualLottoCount.increaseMadeLottoCount();
			passiveLottos.add(makeOnePassiveLotto());
		}
		return passiveLottos;
	}

	private LottoNumbers makeOnePassiveLotto() {
		try {
			String input = inputView.inputLottoNumbers();
			return LottoNumbers.changeFrom(InputLottoNumbersUtils.makeLottoNumber(input));
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return makeOnePassiveLotto();
		}
	}

	private void printLottos() {
		outputView.printLottos(manualLottoCount.getCount(), automaticLottoCount.getCount(), lottos.getLottosDTO());
	}

	private WinningLottoNumber storeWinningNumber() {
		try {
			String input = inputView.inputWinningNumbers();
			return new WinningLottoNumber(InputLottoNumbersUtils.makeLottoNumber(input), makeBonusBall());
		} catch (IllegalArgumentException e) {
			outputView.printErrorMessage(e.getMessage());
			return storeWinningNumber();
		}
	}

	private LottoNumber makeBonusBall() {
		String input = inputView.inputBonusBall();
		InputValidateUtils.inputBlank(input, BONUS_BALL_BLANK_ERROR_MESSAGE);
		return LottoNumber.parseLottoNumber(input);
	}

	private void compareLottoWithWinningNumber() {
		lottos.checkWithWinningNumberAndBonus(winningLottoNumber.getWinningLottoNumbersDTO(), lottoResult);
	}

	private void showResult() {
		outputView.printResultMessage();
		Arrays.stream(Rank.values())
			.filter(rank -> rank.getMatchNumber() >= 3)
			.forEach(statistics -> outputView.printResult(statistics.getMatchNumber(), statistics.getValue(),
				lottoResult.getCountOfResult(statistics),
				Rank.SECOND.getValue()));
		outputView.printRateOfReturn(lottoResult.getSumOfRewards() / insertedMoney.getMoney());
	}

}
