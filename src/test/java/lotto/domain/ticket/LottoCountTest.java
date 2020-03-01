package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoCountTest {
	@DisplayName("음수 범위로 로또 구매 횟수 객체 생성시 예외 발생 확인")
	@Test
	void validateCount() {
		assertThatThrownBy(() -> LottoCount.valueOf(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("카운트 객체의 현재 마지막 계수 상태 여부 확인")
	@ParameterizedTest
	@CsvSource(value = {"10,9,true", "10,10,false", "10,1,true"})
	void fullCountTest(int fullCount, int currentCount, boolean expected) {
		LottoCount count = LottoCount.valueOf(fullCount);
		assertThat(count.isNonFullCount(currentCount)).isEqualTo(expected);
	}

	@DisplayName("두 카운트 객체 대소 비교 확인")
	@ParameterizedTest
	@CsvSource(value = {"9,10,true", "10,10,false", "11,10,false"})
	void checkBiggerCountTest(int firstCountValue, int secondCountValue, boolean expected) {
		LottoCount firstCount = LottoCount.valueOf(firstCountValue);
		LottoCount secondCount = LottoCount.valueOf(secondCountValue);
		assertThat(firstCount.isLessThan(secondCount)).isEqualTo(expected);
	}

	@DisplayName("두 카운트 객체간 뺄셈연산")
	@ParameterizedTest
	@CsvSource({"10,9,1", "10,10,0"})
	void minusBetweenTwoLottoCountsTest(int firstCountValue, int secondCountValue, int expectedCountValue) {
		LottoCount firstCount = LottoCount.valueOf(firstCountValue);
		LottoCount secondCount = LottoCount.valueOf(secondCountValue);
		LottoCount expected = LottoCount.valueOf(expectedCountValue);
		assertThat(firstCount.minus(secondCount)).isEqualTo(expected);
	}
}
