package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoCountTest {

	@ParameterizedTest
	@ValueSource(ints = {100, 1001, 100001})
	@DisplayName("투입 금액이 천원 단위가 아닌 경우")
	void validateNotThousandUnitInputMoney(int number) {
		assertThatThrownBy(() -> new LottoCount(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 금액은 천원 단위여야 합니다.");
	}

	@Test
	@DisplayName("투입 금액이 천원 단위가 아닌 경우(0원)")
	void validateInputMoneyZero() {
		assertThatThrownBy(() -> new LottoCount(0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 금액은 천원 단위여야 합니다.");
	}

	@Test
	@DisplayName("투입 금액이 천원 단위 경우")
	void validateThousandUnitInputMoney() {
		LottoCount lottoCount = new LottoCount(10000);

		assertThat(lottoCount.getCount()).isEqualTo(10);
	}

	@Test
	@DisplayName("로또 생성 중단")
	void checkLottoCountIsZero() {
		LottoCount lottoCount = new LottoCount(1000);
		lottoCount.reduceCountOfRemain();

		assertThat(lottoCount.haveRemainToMake()).isFalse();
	}

	@Test
	@DisplayName("로또 생성 횟수 차감")
	void checkLottoCountReduce() {
		LottoCount lottoCount = new LottoCount(100000);
		lottoCount.reduceCountOfRemain();

		assertThat(lottoCount.getCount()).isEqualTo(99);
	}
}
