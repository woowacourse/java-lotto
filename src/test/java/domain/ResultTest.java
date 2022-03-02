package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generateStrategy.LotteryNumberMockGenerator;
import domain.lottery.Lotteries;
import domain.lottery.LotteryGenerator;
import domain.lottery.WinningLottery;

public class ResultTest {

	private LotteryGame lotteryGame;

	@BeforeEach
	void lotteryGameInit() {
		lotteryGame = LotteryGame.of(6000, new LotteryGenerator(), new LotteryNumberMockGenerator());
	}

	@Test
	@DisplayName("등수가 제대로 집계되는지 확인")
	void testRankingCount() {
		//given
		Lotteries lotteries = lotteryGame.createAutoLottery();
		WinningLottery winningLottery = lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		Result result = Result.makeResult(lotteries, winningLottery, lotteryGame);
		final Map<Rank, Integer> rankResult = result.getRankResult();
		//then
		for (Rank rank : rankResult.keySet()) {
			rankResult.get(rank);
			assertThat(rankResult.get(rank)).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("수익률이 제대로 집계되는지 확인")
	void testRankingPercent() {
		//given
		Lotteries lotteries = lotteryGame.createAutoLottery();
		WinningLottery winningLottery = lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		Result result = Result.makeResult(lotteries, winningLottery, lotteryGame);
		//then
		assertThat(result.getReturnRate()).isEqualTo((double)2031555000 / (6 * 1000));
	}
}
