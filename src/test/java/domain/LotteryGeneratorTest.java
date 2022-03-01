package domain;

import org.junit.jupiter.api.DisplayName;

import domain.lottery.LotteryGenerator;

@DisplayName("LotteryGenerator 클래스 테스트")
public class LotteryGeneratorTest {

	private final LotteryGenerator lotteryGenerator = new LotteryGenerator();
	//
	// @Test
	// @DisplayName("생성된 6개의 수가 고유한지 확인")
	// void checkDuplicatedNumber() {
	// 	//given
	// 	final Set<LotteryNumber> numbers = lotteryGenerator.getNumbers();
	// 	//when
	// 	//then
	// 	assertThat(numbers.size()).isEqualTo(6);
	// }
}
