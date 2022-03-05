package controller;

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
		printLotteries(lotteries);
		makeResult(lotteries, winningLottery);
	}

	private void initLotteryGame() {
		try {
			final int inputMoney = inputMoney();
			final int numOfManualLottery = inputNumOfManualLottery();
			lotteryGame = LotteryGame.of(inputMoney, numOfManualLottery, new LotteryGenerator(), new LotteryNumberGenerator());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			initLotteryGame();
		}
	}

	private Lotteries purchaseLottery() {
		try {
			return lotteryGame.createLottery(inputManualLotteryNumbers());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return purchaseLottery();
		}
	}

	private WinningLottery createWinningLottery() {
		try {
			return lotteryGame.createWinningLottery(inputWinningNumber(), inputBonusBall());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return createWinningLottery();
		}
	}

	private void printLotteries(Lotteries lotteries) {
		outputView.printLotteries(lotteries.getLotteries());
	}

	private void makeResult(final Lotteries lotteries, final WinningLottery winningLottery) {
		final Result result = Result.makeResult(lotteries, winningLottery, lotteryGame.getTheNumberOfLottery());
		outputView.printStatistics(result);
	}

	private int inputMoney() {
		return inputView.inputValidMoney();
	}

	private List<List<Integer>> inputManualLotteryNumbers() {
		return inputView.inputManualLotteryNumber(lotteryGame.getTheNumberOfLottery());
	}

	private int inputNumOfManualLottery() {
		return inputView.inputValidNumOfManualLottery();
	}

	private List<Integer> inputWinningNumber() {
		return inputView.inputValidLotteryNumber();
	}

	private int inputBonusBall() {
		return inputView.inputValidBonusNumber();
	}
}
