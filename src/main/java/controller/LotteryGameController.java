package controller;

import java.util.List;
import java.util.Map;

import controller.dto.LotteriesDto;
import controller.dto.WinningResultDto;
import domain.LotteryGame;
import domain.PurchaseAmount;
import domain.PurchaseInformation;
import domain.Rank;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;

public class LotteryGameController {

	private LotteryGame lotteryGame;

	public LotteriesDto purchaseLotteries(final int money, final int theNumberOfManualLottery,
			final List<List<Integer>> manalLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);
		final PurchaseInformation purchaseInformation = new PurchaseInformation(purchaseAmount,
			manalLotteries, theNumberOfManualLottery);
		lotteryGame = new LotteryGame(purchaseInformation, new LotteryRandomGeneratorStrategy());
		return LotteriesDto.fromEntity(lotteryGame.getLotteries());
	}

	public void createWinningLottery(final List<Integer> winingNumbers, final int bonusNumber) {
		lotteryGame.createWinningLottery(winingNumbers, bonusNumber);
	}

	public WinningResultDto getWinningResult() {
		final Map<Rank, Integer> ranking = lotteryGame.makeWinner();
		final double rankingPercent = lotteryGame.makeRankingPercent(ranking);
		return WinningResultDto.fromEntity(ranking, rankingPercent);
	}
}
