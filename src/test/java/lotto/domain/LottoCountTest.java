package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoCountTest {
	@DisplayName("음수 범위로 로또 구매 횟수 객체 생성시 예외 발생 확인")
	@Test
	void validateCount() {
		assertThatThrownBy(() -> {
			LottoCount lottoCount = LottoCount.valueOf(-1);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("카운트 객체의 현재 마지막 계수 상태 여부 확인")
	@ParameterizedTest
	@CsvSource(value = {"10,9,true", "10,10,false", "10,1,true"})
	void fullCountTest(int fullCount, int currentCount, boolean expected) {
		LottoCount count = LottoCount.valueOf(fullCount);
		assertThat(count.isNonFullCount(currentCount)).isEqualTo(expected);
	}
}
