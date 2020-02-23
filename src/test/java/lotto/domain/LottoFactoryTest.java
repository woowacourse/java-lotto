package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class LottoFactoryTest {

	@DisplayName("사용자가 입력한 돈에 맞게 로또 생성")
	@Test
	void createLotteries1() {
		String userInputMoney = "13000";
		List<Lotto> lottoList = LottoFactory.createLotteries(new Money(userInputMoney));
		assertThat(lottoList.size()).isEqualTo(13);

		userInputMoney = "5000";
		List<Lotto> lottoList2 = LottoFactory.createLotteries(new Money(userInputMoney));
		assertThat(lottoList2.size()).isEqualTo(5);
	}

	@DisplayName("1000원 이하의 금액을 입력했을 경우")
	@Test
	void createLotteries2() {
		String userInputMoneyZero = "0";
		assertThatThrownBy(() -> LottoFactory.createLotteries(new Money(userInputMoneyZero)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(Money.ERROR_MESSAGE_MIN_MONEY);
	}

	@DisplayName("입력값이 비었을 경우")
	@Test
	void nullTest() {
		assertThatThrownBy(() -> LottoFactory.createLotteries(new Money(null)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(Money.ERROR_MESSAGE_NULL_POINT_MONEY);
	}
}
