package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generateStrategy.LotteryNumberMockGenerator;
import domain.generatestrategy.LotteryNumberGenerator;
import domain.lottery.Lotteries;
import domain.lottery.LotteryGenerator;
import domain.lottery.WinningLottery;

@DisplayName("LotteryGame 테스트")
public class LotteryGameTest {

	@DisplayName("입력한 로또 개수 만큼 로또가 자동으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} inputMoney={0}")
	@ValueSource(ints = {1000, 100000, 50000})
	void create_lotteries_test(final int inputMoney) {
		//given
		final LotteryGame lotteryGame = LotteryGame.of(inputMoney, new LotteryGenerator(),
			new LotteryNumberGenerator());
		final int lotteriesToCreate = inputMoney / 1000;
		//when
		Lotteries lotteries = lotteryGame.createLottery(Collections.emptyList());
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}

	@DisplayName("수동 로또와 자동 로또가 개수만큼 정상적으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} inputMoney={0}")
	@ValueSource(ints = {3000, 100000, 50000})
	void manual_and_auto_lotteries_test(final int inputMoney) {
	    //given
	    LotteryGame lotteryGame = LotteryGame.of(inputMoney, new LotteryGenerator(), new LotteryNumberGenerator());
		final int lotteriesToCreate = inputMoney / 1000;
	    //when
		lotteryGame = lotteryGame.putNumOfManualLottery(3);
		Lotteries lotteries = lotteryGame.createLottery(Arrays.asList(
			Arrays.asList(1, 2, 3, 4, 5, 6),
			Arrays.asList(2, 3, 4, 5, 6, 7),
			Arrays.asList(3, 4, 5, 6, 7, 8)
		));
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}
}
