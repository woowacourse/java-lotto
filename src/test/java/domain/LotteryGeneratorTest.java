package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generatestrategy.RandomLotteryGeneratorStrategy;

@DisplayName("LotteryGenerator 클래스 테스트")
public class LotteryGeneratorTest {

	RandomLotteryGeneratorStrategy lotteryGenerator = new RandomLotteryGeneratorStrategy();

	@Test
	@DisplayName("생성된 6개의 수가 고유한지 확인")
	void checkDuplicatedNumber() {
		final List<LotteryNumber> numbers = lotteryGenerator.getLottery().getNumbers();

		final List<LotteryNumber> uniqueNumbers = numbers.stream()
			.distinct()
			.collect(Collectors.toList());

		assertThat(numbers.size()).isEqualTo(uniqueNumbers.size());
	}

	@Test
	@DisplayName("생성된 6개의 수가 정렬되어 있는지 확인")
	void checkSortedNumber() {
		final List<LotteryNumber> numbers = lotteryGenerator.getLottery().getNumbers();

		final List<LotteryNumber> sortedNumbers = numbers.stream()
			.sorted()
			.collect(Collectors.toList());

		assertThat(numbers.equals(sortedNumbers)).isTrue();
	}
}
