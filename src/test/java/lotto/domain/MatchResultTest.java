package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 로또 통계 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class MatchResultTest {
	private MatchResult matchResult;

	@BeforeEach
	void setup() {
		Map<LottoRank, Long> statistics = new HashMap<>();
		statistics.put(LottoRank.FIRST, 1L);
		statistics.put(LottoRank.THIRD, 3L);
		statistics.put(LottoRank.FIFTH, 2L);
		statistics.put(LottoRank.MISS, 4L);
		matchResult = new MatchResult(statistics);
	}

	@Test
	@DisplayName("생성자 테스트")
	void constructor() {
		Map<LottoRank, Long> statistics = new HashMap<>();
		statistics.put(LottoRank.FIRST, 1L);
		statistics.put(LottoRank.THIRD, 3L);
		statistics.put(LottoRank.FIFTH, 2L);
		statistics.put(LottoRank.MISS, 5L);
		assertThat(new MatchResult(statistics)).isInstanceOf(MatchResult.class);
	}

	@Test
	@DisplayName("총 수익률 연산 테스트")
	void calculateTotalProfits() {
		assertThat(matchResult.calculateTotalProfits()).isEqualTo(20045100L);
	}

	@Test
	@DisplayName("당첨자가 있을 때 몇명인지 가져오는 테스트")
	void findMatchCountByLottoRank() {
		assertThat(matchResult.findMatchCountByLottoRank(LottoRank.FIFTH)).isEqualTo(2L);
	}

	@Test
	@DisplayName("당첨자가 없을 때 기본 값을 반환하는지 테스트")
	void findMatchCountByLottoRank_DefaultRank() {
		assertThat(matchResult.findMatchCountByLottoRank(LottoRank.SECOND)).isEqualTo(0L);
	}
}
