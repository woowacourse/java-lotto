package controller;

import java.util.List;
import java.util.Map;

import domain.LotteryGame;
import domain.Rank;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;
import view.InputView;
import view.OutputView;

public class LotteryGameController {

	private InputView inputView;
	private OutputView outputView;
	private LotteryGame lotteryGame;

	public LotteryGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void startLotteryGame() {
		purchaseLottery();
		createWinningLottery();
		outputView.printLotteries(lotteryGame.getLotteries());
	}

	private void purchaseLottery() {
		try {
			lotteryGame = LotteryGame.of(inputMoney(), new LotteryRandomGeneratorStrategy());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			purchaseLottery();
		}
	}

	private void createWinningLottery() {
		try {
			lotteryGame.createWinningLottery(inputWinningNumber(), inputBonusBall());
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			createWinningLottery();
		}
	}

	public void makeResult() {
		Map<Rank, Integer> ranking = lotteryGame.makeWinner();
		double incomePercent = lotteryGame.makeReturnRate(ranking);
		outputView.printStatistics(ranking, incomePercent);
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
