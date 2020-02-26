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
	@DisplayName("번호가 6개 일치하는 경우 1등이다")
	void of_First() {
		assertThat(LottoRank.of(MatchCount.of(6), false)).isEqualTo(LottoRank.FIRST);
	}

	@Test
	@DisplayName("번호가 5개 일치하고, 보너스 번호가 일치하는 경우 2등이다")
	void of_Second() {
		assertThat(LottoRank.of(MatchCount.of(5), true)).isEqualTo(LottoRank.SECOND);
	}

	@Test
	@DisplayName("번호가 5개 일치하고, 보너스 번호가 일치하지 않는 경우 3등이다")
	void of_Third() {
		assertThat(LottoRank.of(MatchCount.of(5), false)).isEqualTo(LottoRank.THIRD);
	}

	@Test
	@DisplayName("로또 번호가 1등 ~ 5등 사이의 조건과 일치하지 않는 경우 MISS이다")
	void of_Miss() {
		assertThat(LottoRank.of(MatchCount.of(2), true)).isEqualTo(LottoRank.MISS);
	}
}
