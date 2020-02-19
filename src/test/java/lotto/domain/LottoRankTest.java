package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 로또 순위 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoRankTest {
	@Test
	@DisplayName("로또 순위를 정상적으로 반환한 경우")
	void of() {
		assertThat(LottoRank.of(3, false)).isEqualTo(LottoRank.FIFTH);
		assertThat(LottoRank.of(5, false)).isEqualTo(LottoRank.THIRD);
		assertThat(LottoRank.of(5, true)).isEqualTo(LottoRank.SECOND);
		assertThat(LottoRank.of(6, false)).isEqualTo(LottoRank.FIRST);
	}

	@ParameterizedTest
	@DisplayName("로또 순위권내에 없는 경우")
	@ValueSource(ints = {-1, 7})
	void of_순위권내에_없는_경우(int rank) {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> LottoRank.of(rank, false));
	}
}
