package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
	@ParameterizedTest
	@ValueSource(ints = {1500, 4310, 3404, 7146, 10200})
	@DisplayName("천원 단위가 아닌 경우")
	void check1000WonUnit(int value) {
		assertThatThrownBy(() -> new LottoCount(value))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("천 원 단위");
	}

	@ParameterizedTest
	@ValueSource(ints = {999, 0, -100})
	@DisplayName("한 장도 살 수 없는 금액인 경우")
	void checkNotEnoughMoney(int value) {
		assertThatThrownBy(() -> new LottoCount(value))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("부족합니다");
	}
}
