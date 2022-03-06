package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generatestrategy.LotteryNumberGenerator;
import domain.lottery.Lotteries;

@DisplayName("LotteryGame 테스트")
public class LotteryMachineTest {

	@DisplayName("입력한 로또 개수 만큼 로또가 자동으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} inputMoney={0}")
	@ValueSource(ints = {1000, 100000, 50000})
	void create_lotteries_test(final int inputMoney) {
		//given
		final LotteryMachine lotteryMachine = LotteryMachine.of(inputMoney, 0, new LotteryNumberGenerator());
		final int lotteriesToCreate = inputMoney / 1000;
		//when
		Lotteries lotteries = lotteryMachine.createLottery(Collections.emptyList());
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}

	@DisplayName("수동 로또와 자동 로또가 개수만큼 정상적으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} inputMoney={0}")
	@ValueSource(ints = {3000, 100000, 50000})
	void manual_and_auto_lotteries_test(final int inputMoney) {
	    //given
	    LotteryMachine lotteryMachine = LotteryMachine.of(inputMoney, 3, new LotteryNumberGenerator());
		final int lotteriesToCreate = inputMoney / 1000;
	    //when
		Lotteries lotteries = lotteryMachine.createLottery(Arrays.asList(
			Arrays.asList(1, 2, 3, 4, 5, 6),
			Arrays.asList(2, 3, 4, 5, 6, 7),
			Arrays.asList(3, 4, 5, 6, 7, 8)
		));
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}
}
