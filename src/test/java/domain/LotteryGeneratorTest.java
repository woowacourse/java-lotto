package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generatestrategy.LotteryRandomGeneratorStrategy;
import domain.lottery.LotteryNumber;

@DisplayName("LotteryGenerator 클래스 테스트")
public class LotteryGeneratorTest {

	LotteryRandomGeneratorStrategy lotteryGenerator = new LotteryRandomGeneratorStrategy();

	@Test
	@DisplayName("생성된 6개의 수가 고유한지 확인")
	void checkDuplicatedNumber() {
		//given
		final List<LotteryNumber> numbers = lotteryGenerator.getNumbers();
		//when
		final List<LotteryNumber> uniqueNumbers = numbers.stream()
			.distinct()
			.collect(Collectors.toList());
		//then
		assertThat(numbers.size()).isEqualTo(uniqueNumbers.size());
	}

	@Test
	@DisplayName("생성된 6개의 수가 정렬되어 있는지 확인")
	void checkSortedNumber() {
		//given
		final List<LotteryNumber> numbers = lotteryGenerator.getNumbers();
		//when
		final List<LotteryNumber> sortedNumbers = numbers.stream()
			.sorted(Comparator.comparing(LotteryNumber::getNumber))
			.collect(Collectors.toList());
		//then
		assertThat(numbers.equals(sortedNumbers)).isTrue();
	}
}
