package controller;

import java.util.List;
import java.util.Map;

import domain.LotteryGame;
import domain.Rank;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;
import view.InputView;
import view.OutputView;

public class LotteryGameController {

	LotteryGame lotteryGame;

	public void startLotteryGame() {
		purchaseLottery();
		createWinningLottery();
		OutputView.printLotteries(lotteryGame.getLotteries());
	}

	private void purchaseLottery() {
		try {
			lotteryGame = new LotteryGame(inputMoney(), new LotteryRandomGeneratorStrategy());
		} catch (IllegalArgumentException exception) {
			OutputView.printException(exception.getMessage());
			purchaseLottery();
		}
	}

	private void createWinningLottery() {
		try {
			lotteryGame.createWinningLottery(inputWinningNumber(), inputBonusBall());
		} catch (IllegalArgumentException exception) {
			OutputView.printException(exception.getMessage());
			createWinningLottery();
		}
	}

	public void makeResult() {
		Map<Rank, Integer> ranking = lotteryGame.makeWinner();
		double incomePercent = lotteryGame.makeReturnRate(ranking);
		OutputView.printStatistics(ranking, incomePercent);
	}

	private int inputBonusBall() {
		return InputView.inputValidBonusNumber();
	}

	private int inputMoney() {
		return InputView.inputValidMoney();
	}

	private List<Integer> inputWinningNumber() {
		return InputView.inputValidLotteryNumber();
	}
}
