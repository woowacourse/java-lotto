package domain.generateStrategy;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lottery;
import domain.LotteryNumber;
import domain.generatestrategy.RandomLotteryGeneratorStrategy;

class RandomLotteryGeneratorStrategyTest {

	@Test
	@DisplayName("6개의 수를 가진 로또가 생성되었는지 확인")
	void createAutoLottery() {
		final RandomLotteryGeneratorStrategy lotteryGenerator = new RandomLotteryGeneratorStrategy();
		final Lottery lottery = lotteryGenerator.getLottery();

		assertThat(lottery.getNumbers().size()).isEqualTo(6);
	}

	@Test
	@DisplayName("생성된 로또 하나에 들어있는 6개의 수가 모두 다른 수인지 확인")
	void checkDuplicatedNumber() {
		final RandomLotteryGeneratorStrategy lotteryGenerator = new RandomLotteryGeneratorStrategy();
		final List<LotteryNumber> lotteryNumbers = lotteryGenerator.getLottery()
			.getNumbers();
		final List<LotteryNumber> uniqueLotteryNumbers = lotteryNumbers.stream()
			.distinct()
			.collect(Collectors.toList());

		assertThat(uniqueLotteryNumbers.size()).isEqualTo(lotteryNumbers.size());
	}

}