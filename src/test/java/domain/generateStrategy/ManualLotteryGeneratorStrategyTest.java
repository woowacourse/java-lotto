package domain.generateStrategy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.LotteryNumber;
import domain.factory.LotteryGenerateMockFactory;
import domain.factory.LotteryNumberFactory;
import domain.generatestrategy.ManualLotteryGeneratorStrategy;
import utils.Parser;

class ManualLotteryGeneratorStrategyTest {

	final LotteryGenerateMockFactory lotteryFactory = new LotteryGenerateMockFactory();

	@Test
	@DisplayName("수동 로또가 제대로 생성되는지 확인")
	void creatManualLottery() {
		final List<List<Integer>> rawManualLotteries = createLotteries(1);
		final List<LotteryNumber> expectedLottery = lotteryFactory.of(1)
			.getNumbers();
		final ManualLotteryGeneratorStrategy manualLotteryGenerator =
			new ManualLotteryGeneratorStrategy(rawManualLotteries, new LotteryNumberFactory());

		for (int i = 0; i < 2; i++) {
			assertThat(manualLotteryGenerator.getLottery().getNumbers()).isEqualTo(expectedLottery);
		}
	}

	private List<List<Integer>> createLotteries(final int rank) {
		final List<List<Integer>> rawManualLotteries = new ArrayList<>();
		final List<LotteryNumber> numbers = lotteryFactory.of(rank)
			.getNumbers();
		rawManualLotteries.add(Parser.toIntegerList(numbers));
		rawManualLotteries.add(Parser.toIntegerList(numbers));
		return rawManualLotteries;
	}
}