package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generatestrategy.LotteryRandomGeneratorStrategy;
import domain.lottery.LotteryNumber;

@DisplayName("LotteryGenerator 클래스 테스트")
public class LotteryGeneratorTest {

	private final LotteryRandomGeneratorStrategy lotteryGenerator = new LotteryRandomGeneratorStrategy();

	@Test
	@DisplayName("생성된 6개의 수가 고유한지 확인")
	void checkDuplicatedNumber() {
		//given
		final Set<LotteryNumber> numbers = lotteryGenerator.getNumbers();
		//when
		//then
		assertThat(numbers.size()).isEqualTo(6);
	}

	@Test
	@DisplayName("생성된 6개의 수가 정렬되어 있는지 확인")
	void checkSortedNumber() {
		//given
		final Set<LotteryNumber> numbers = lotteryGenerator.getNumbers();
		//when
		final Set<LotteryNumber> sortedNumbers = numbers.stream()
			.collect(Collectors.toCollection(TreeSet::new));
		//then
		assertThat(sortedNumbers.equals(numbers)).isTrue();
	}
}
