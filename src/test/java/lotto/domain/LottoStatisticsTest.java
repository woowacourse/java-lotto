package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {
	private WinningLotto winningLotto;
	private Lottos lottos;

	@BeforeEach
	void setUp() {
		Lotto lotto1 = createCustomLotto(1, 2, 3, 20, 21, 40); // FIFTH
		Lotto lotto2 = createCustomLotto(1, 2, 3, 4, 5, 20); // SECOND
		Lotto lotto3 = createCustomLotto(1, 2, 3, 4, 5, 17);  // THIRD

		lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));

		winningLotto = new WinningLotto(
				createCustomLotto(1, 2, 3, 4, 5, 6),
				new LottoNumber(20));
	}

	private Lotto createCustomLotto(final int... numbers) {
		return new Lotto(Arrays.asList(
				new LottoNumber(numbers[0]),
				new LottoNumber(numbers[1]),
				new LottoNumber(numbers[2]),
				new LottoNumber(numbers[3]),
				new LottoNumber(numbers[4]),
				new LottoNumber(numbers[5])));
	}

	@DisplayName("결과 값을 통계 리스트로 반환")
	@Test
	void getWinCountByRank() {
		List<Rank> ranks = lottos.getResultsBasedOn(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(3000));

		List<Integer> numberOfWinByRank = lottoStatistics.getWinCountByRank();
		assertThat(numberOfWinByRank).isEqualTo(Arrays.asList(1, 0, 1, 1, 0));
	}

	@DisplayName("총 수익률 계산")
	@Test
	void getTotalProfit() {
		List<Rank> ranks = lottos.getResultsBasedOn(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(3000));

		assertThat(lottoStatistics.getProfitRate()).isEqualTo((float) (30_000_000 + 1_500_000 + 5_000) / 3000);
	}
}