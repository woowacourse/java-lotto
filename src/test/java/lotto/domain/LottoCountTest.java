package lotto.domain;

import lotto.exception.LottoCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
	@ParameterizedTest
	@ValueSource(ints = {1500, 4310, 3404, 7146, 10200})
	@DisplayName("천원 단위가 아닌 경우")
	void check1000WonUnit(int value) {
		assertThatThrownBy(() -> new LottoCount(value, 0))
				.isInstanceOf(LottoCountException.class)
				.hasMessageContaining("천 원 단위");
	}

	@ParameterizedTest
	@ValueSource(ints = {999, 0, -100})
	@DisplayName("한 장도 살 수 없는 금액인 경우")
	void checkNotEnoughMoney(int value) {
		assertThatThrownBy(() -> new LottoCount(value, 0))
				.isInstanceOf(LottoCountException.class)
				.hasMessageContaining("부족합니다");
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,2", "100000, 101"})
	@DisplayName("수동 구매 갯수가 전체 구매 가능한 장수를 초과하는 경우")
	void checkManualAmountOverTheTotalAmount(int lottoMoney, int manualAmount) {
		assertThatThrownBy(() -> new LottoCount(lottoMoney, manualAmount))
				.isInstanceOf(LottoCountException.class)
				.hasMessageContaining("장수가 올바르지 않습니다");
	}
}
