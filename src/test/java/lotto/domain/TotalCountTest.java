package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalCountTest {
	@DisplayName("수동 구매 횟수가 총 로또 구매 갯수보다 큰 경우 예외 발생 확인")
	@Test
	void validateValidCountValueTest() {
		LottoCount total = LottoCount.valueOf(10);
		LottoCount manualCount = LottoCount.valueOf(12);
		assertThatThrownBy(() -> new TotalCount(total, manualCount))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("자동 구매 횟수 추출하기")
	@ParameterizedTest
	@CsvSource({"10,10,0", "10,0,10", "10,5,5"})
	void name(int totalCountValue, int manualCountValue, int expectedAutoCountValue) {
		TotalCount totalCount = new TotalCount(LottoCount.valueOf(totalCountValue),
			LottoCount.valueOf(manualCountValue));
		LottoCount expected = LottoCount.valueOf(expectedAutoCountValue);
		assertThat(totalCount.calculateAutoCount()).isEqualTo(expected);
	}
}