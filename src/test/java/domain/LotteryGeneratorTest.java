package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generatestrategy.LotteryNumberGenerator;
import domain.generatestrategy.LotteryNumberGeneratorStrategy;
import domain.lottery.Lottery;
import domain.lottery.LotteryGenerator;
import domain.lottery.LotteryNumber;

@DisplayName("LotteryGenerator 클래스 테스트")
public class LotteryGeneratorTest {

	private final LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = new LotteryNumberGenerator();

	@Test
	@DisplayName("생성된 6개의 수가 고유한지 확인")
	void checkDuplicatedNumber() {
		//given
		final Lottery lottery = LotteryGenerator.generateLottery(
			lotteryNumberGeneratorStrategy.generateNumbers());
		//when
		//then
		assertThat(lottery.getNumbers().size()).isEqualTo(6);
	}
}
