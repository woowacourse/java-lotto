package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 로또 순위 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoRankTest {
	@Test
	@DisplayName("로또 1등을 정상적으로 반환한 경우")
	void of_1등() {
		assertThat(LottoRank.of(MatchCount.of(6), false)).isEqualTo(LottoRank.FIRST);
	}

	@Test
	@DisplayName("로또 2등을 정상적으로 반환한 경우")
	void of_2등() {
		assertThat(LottoRank.of(MatchCount.of(5), true)).isEqualTo(LottoRank.SECOND);
	}

	@Test
	@DisplayName("로또 3등을 정상적으로 반환한 경우")
	void of_3등() {
		assertThat(LottoRank.of(MatchCount.of(5), false)).isEqualTo(LottoRank.THIRD);
	}

	@Test
	@DisplayName("로또 불일치를 정상적으로 반환한 경우")
	void of_MISS() {
		assertThat(LottoRank.of(MatchCount.of(2), true)).isEqualTo(LottoRank.MISS);
	}
}
