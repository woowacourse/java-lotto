package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.dto.LotteriesDto;
import controller.dto.WinningResultDto;
import domain.Lotteries;
import domain.Lottery;
import domain.LotteryGame;
import domain.LotteryNumber;
import domain.PurchaseAmount;
import domain.PurchaseInformation;
import domain.Rank;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;

public class LotteryGameController {

	private LotteryGame lotteryGame;

	public LotteriesDto purchaseLotteries(final int money, final int theNumberOfManualLottery,
			final List<List<Integer>> manalLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);
		final List<Lottery> lotteries = toLotteryList(manalLotteries);
		final PurchaseInformation purchaseInformation = new PurchaseInformation(purchaseAmount,
			new Lotteries(lotteries), theNumberOfManualLottery);
		lotteryGame = new LotteryGame(purchaseInformation, new LotteryRandomGeneratorStrategy());
		return LotteriesDto.fromEntity(lotteryGame.getLotteries());
	}

	private List<Lottery> toLotteryList(final List<List<Integer>> lotteries) {
		List<Lottery> convertedLotteries = new ArrayList<>();
		for (List<Integer> lottery : lotteries) {
			final List<LotteryNumber> lotteryNumbers = lottery.stream()
				.map(LotteryNumber::new)
				.collect(Collectors.toList());
			convertedLotteries.add(new Lottery(lotteryNumbers));
		}
		return convertedLotteries;
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
