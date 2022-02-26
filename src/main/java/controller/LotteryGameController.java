package controller;

import java.util.List;
import java.util.Map;

import domain.LotteryGame;
import domain.PurchaseAmount;
import domain.Rank;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;
import view.InputView;
import view.OutputView;

public class LotteryGameController {

	LotteryGame lotteryGame;

	public void startPurchaseLotteries() {
		lotteryGame = new LotteryGame(new PurchaseAmount(inputMoney()), new LotteryRandomGeneratorStrategy());
		lotteryGame.createWinningLottery(inputWinningNumber(), inputBonusBall());
		OutputView.printLotteries(lotteryGame.getLotteries());
	}

	public void makeResult() {
		Map<Rank,Integer> ranking = lotteryGame.makeWinner();
		double incomePercent = lotteryGame.makeRankingPercent(ranking);
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
