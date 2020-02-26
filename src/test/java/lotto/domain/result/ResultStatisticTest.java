package lotto.domain.result;

import lotto.domain.lottonumber.BonusLottoNumber;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.lotto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ResultStatistic 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatisticTest {
	private Lottos lottos;
	private WinningLotto winningLotto;
	private BonusLottoNumber bonusLottoNumber;
	private ResultStatistic resultStatistic;

	@BeforeEach
	void setUp() {
		List<Lotto> tempLottos = new ArrayList<>();

		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(4),
				LottoNumber.of(5),
				LottoNumber.of(6)
		);

		winningLotto = (WinningLotto) LottoFactory.createManualLotto(LottoType.WINNING_LOTTO, winningLottoNumbers);

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

		tempLottos.add(LottoFactory.createManualLotto(LottoType.AUTO_LOTTO, lottoNumbersFistPrize));
		tempLottos.add(LottoFactory.createManualLotto(LottoType.AUTO_LOTTO, lottoNumbersForthPrize));
		tempLottos.add(LottoFactory.createManualLotto(LottoType.AUTO_LOTTO, lottoNumbersSixthPrize));
		lottos = new Lottos(tempLottos);

		bonusLottoNumber = new BonusLottoNumber("7");

		resultStatistic = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
	}

	@Test
	void calculate_ResultStatistic_생성_확인() {
		assertThat(resultStatistic).isInstanceOf(ResultStatistic.class);
	}

	@Test
	void calculate_올바른_리턴_결과_확인() {
		Map<Rank, Integer> resultMap = resultStatistic.getResults();
		assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1);
		assertThat(resultMap.get(Rank.SECOND)).isEqualTo(0);
		assertThat(resultMap.get(Rank.THIRD)).isEqualTo(0);
		assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(1);
		assertThat(resultMap.get(Rank.FIFTH)).isEqualTo(0);
		assertThat(resultMap.get(Rank.SIXTH)).isEqualTo(1);
	}

	@Test
	void calculateRevenueRate_올바른_리턴_결과_확인() {
		MoneyForLotto moneyForLotto = new MoneyForLotto("3000");
		assertThat(resultStatistic.calculateRevenueRate(moneyForLotto))
				.isEqualTo(66668333);
	}
}
