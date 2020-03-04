package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.util.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름 : ResultStatisticTest.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatisticTest {
	private Lottos lottos;
	private WinningInformation winningInformation;

	@BeforeEach
	void setUp() {
		Lotto winningLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber bonus = LottoNumber.of(7);
		winningInformation = new WinningInformation(winningLotto, bonus);

		List<Integer> lottoNumbersFistPrize = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> lottoNumbersForthPrize = Arrays.asList(1, 2, 3, 4, 8, 9);
		List<Integer> lottoNumbersSixthPrize = Arrays.asList(10, 11, 12, 13, 8, 9);

		lottos = Lottos.createLottos(new LottoCount(new LottoMoney(3_000), 3),
			Arrays.asList(lottoNumbersFistPrize, lottoNumbersForthPrize, lottoNumbersSixthPrize)
		);
	}

	@Test
	void calculate_ResultStatistic_생성_확인() {
		ResultStatistic resultStatistic = ResultStatistic.calculate(lottos, winningInformation);
		assertThat(resultStatistic).isInstanceOf(ResultStatistic.class);
	}

	@Test
	void calculate_올바른_리턴_결과_확인() {
		ResultStatistic resultStatistic = ResultStatistic.calculate(lottos, winningInformation);
		Map<Rank, Integer> resultMap = resultStatistic.getResults();

		assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1);
		assertThat(resultMap.get(Rank.SECOND)).isEqualTo(0);
		assertThat(resultMap.get(Rank.THIRD)).isEqualTo(0);
		assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(1);
		assertThat(resultMap.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(resultMap.get(Rank.EIGHTH)).isEqualTo(1);
	}

	@Test
    void 수익률_계산() {
		final int MONEY_FOR_LOTTO = 1_000;

		ResultStatistic resultStatistic = ResultStatistic.calculate(lottos, winningInformation);

		long revenueRate = resultStatistic.calculateRevenueRate(new Money(MONEY_FOR_LOTTO));
		assertThat(revenueRate).isEqualTo(
			(long) ((Rank.FIRST.getReward() + Rank.FOURTH.getReward())) * 100 / MONEY_FOR_LOTTO
		);
	}
}
