package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
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
	void createLotteries(final int inputMoney) {
		//given
		final LotteryGame lotteryGame = LotteryGame.of(inputMoney, new LotteryGenerator(),
			new LotteryNumberGenerator());
		Lotteries lotteries = lotteryGame.createAutoLottery();
		final int lotteriesToCreate = inputMoney / 1000;
		//when
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}
}
