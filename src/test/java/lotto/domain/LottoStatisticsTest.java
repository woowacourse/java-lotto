package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.WinningLottoTest.createCustomWinningLotto;
import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {
	private WinningLotto winningLotto;
	private Lottos lottos;

	@BeforeEach
	void setUp() {
		Lotto lotto1 = new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(20),
				new LottoNumber(21),
				new LottoNumber(40))); //FIFTH

		Lotto lotto2 = new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(20))); //SECOND

		lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto2));
		winningLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "20");
	}

	@DisplayName("결과 값을 통계 리스트로 반환")
	@Test
	void getWinCountByRank() {
		List<Rank> ranks = lottos.getResults(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(3000));

		List<Integer> numberOfWinByRank = lottoStatistics.getWinCountByRank();
		assertThat(numberOfWinByRank).isEqualTo(Arrays.asList(1, 0, 0, 2, 0));
	}

	@DisplayName("총 수익률 계산")
	@Test
	void getTotalProfit() {
		List<Rank> ranks = lottos.getResults(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(3000));

		assertThat(lottoStatistics.getProfitRate()).isEqualTo((float) 60_005_000 / 3000);
	}
}