package lotto.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottos.Lottos;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.money.MoneyForLotto;

/**
 * ResultStatistic 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatisticTest {
	private ResultStatistic resultStatistic;

	@BeforeEach
	void setUp() {
		WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");

		List<LottoNumber> lottoNumbersFistPrize = Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(4),
				LottoNumber.of(5),
				LottoNumber.of(6)
		);
		List<LottoNumber> lottoNumbersForthPrize = Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(4),
				LottoNumber.of(8),
				LottoNumber.of(9)
		);

		List<LottoNumber> lottoNumbersSixthPrize = Arrays.asList(
				LottoNumber.of(10),
				LottoNumber.of(11),
				LottoNumber.of(12),
				LottoNumber.of(13),
				LottoNumber.of(8),
				LottoNumber.of(9)
		);
		List<Lotto> tempLottos = new ArrayList<>();
		tempLottos.add(LottoFactory.createManualLotto(lottoNumbersFistPrize));
		tempLottos.add(LottoFactory.createManualLotto(lottoNumbersForthPrize));
		tempLottos.add(LottoFactory.createManualLotto(lottoNumbersSixthPrize));
		resultStatistic = ResultStatistic.calculate(new Lottos(tempLottos), winningLotto);
	}

	@Test
	void calculate_ResultStatistic_생성_확인() {
		assertThat(resultStatistic).isInstanceOf(ResultStatistic.class);
	}

	@Test
	void calculate_랭크에_해당하는_장수가_올바르게_저장되는지_확인() {
		Map<Rank, Long> resultMap = resultStatistic.getResult();
		assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1);
		assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(1);
		assertThat(resultMap.get(Rank.SIXTH)).isEqualTo(1);
	}

	@Test
	void calculateRevenueRate_올바른_리턴_결과_확인() {
		MoneyForLotto moneyForLotto = new MoneyForLotto("3000");
		assertThat(resultStatistic.calculateRevenueRate(moneyForLotto))
				.isEqualTo(66668333);
	}
}
