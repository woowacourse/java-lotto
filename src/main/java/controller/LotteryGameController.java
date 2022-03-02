package controller;

import java.util.Collections;
import java.util.List;

import domain.LotteryGame;
import domain.Result;
import domain.generatestrategy.LotteryNumberGenerator;
import domain.lottery.Lotteries;
import domain.lottery.LotteryGenerator;
import domain.lottery.WinningLottery;
import view.InputView;
import view.OutputView;

public class LotteryGameController {

	private final InputView inputView;
	private final OutputView outputView;
	private LotteryGame lotteryGame;

	public LotteryGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void startLotteryGame() {
		initLotteryGame();
		final Lotteries lotteries = purchaseLottery();
		final WinningLottery winningLottery = createWinningLottery();
		outputView.printLotteries(lotteries.getLotteries());
		makeResult(lotteries, winningLottery);
	}

	private void initLotteryGame() {
		try {
			lotteryGame = LotteryGame.of(inputMoney(), new LotteryGenerator(), new LotteryNumberGenerator());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			initLotteryGame();
		}
	}

	private Lotteries purchaseLottery() {
		try {
			lotteryGame = lotteryGame.putNumOfManualLottery(inputNumOfManualLottery());
			return lotteryGame.createLottery(inputManualLotteryNumbers());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return purchaseLottery();
		}
	}

	private List<List<Integer>> inputManualLotteryNumbers() {
		return inputView.inputManualLotteryNumber(lotteryGame.getTheNumberOfLottery());
	}

	private int inputNumOfManualLottery() {
		return inputView.inputValidNumOfManualLottery();
	}

	private WinningLottery createWinningLottery() {
		try {
			return lotteryGame.createWinningLottery(inputWinningNumber(), inputBonusBall());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return createWinningLottery();
		}
	}

	private void makeResult(final Lotteries lotteries, final WinningLottery winningLottery) {
		final Result result = Result.makeResult(lotteries, winningLottery, lotteryGame.getTheNumberOfLottery());
		outputView.printStatistics(result);
	}

	private int inputBonusBall() {
		return inputView.inputValidBonusNumber();
	}

	private int inputMoney() {
		return inputView.inputValidMoney();
	}

	private List<Integer> inputWinningNumber() {
		return inputView.inputValidLotteryNumber();
	}
}
