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
		Lotteries lotteries = purchaseLottery();
		WinningLottery winningLottery = createWinningLottery();
		outputView.printLotteries(lotteries.getLotteries());
		makeResult(lotteries, winningLottery);
	}

	private Lotteries purchaseLottery() {
		try {
			lotteryGame = LotteryGame.of(inputMoney(), new LotteryGenerator(), new LotteryNumberGenerator());
			return lotteryGame.createAutoLottery();
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

	private void makeResult(Lotteries lotteries, WinningLottery winningLottery) {
		Result result = new Result();
		result.makeWinner(lotteries, winningLottery);
		result.makeReturnRate(lotteryGame);
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
