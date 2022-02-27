package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Set;

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
}
