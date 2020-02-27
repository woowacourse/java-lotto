package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 로또 일치 개수 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/20
 */
public class MatchCountTest {
	@Test
	@DisplayName("로또 일치 개수의 범위 안에 드는 경우 인스턴스를 생성한다")
	void of() {
		assertThat(MatchCount.of(5)).isInstanceOf(MatchCount.class);
	}

	@ParameterizedTest
	@DisplayName("로또 일치 개수의 범위 안에 들지 않는 경우 예외가 발생한다")
	@ValueSource(ints = {-1, 7})
	void of_OutOfRange(int matchCount) {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> MatchCount.of(matchCount));
	}
}
