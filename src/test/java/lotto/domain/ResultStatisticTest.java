package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class ResultStatisticTest {
	private Lottos lottos;
	private WinningLotto winningLotto;
	private BonusLottoNumber bonusLottoNumber;

	@BeforeEach
	void setUp() {
		List<Lotto> tempLottos = new ArrayList<>();

		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.ONE,
				LottoNumber.TWO,
				LottoNumber.THREE,
				LottoNumber.FOUR,
				LottoNumber.FIVE,
				LottoNumber.SIX
		);

		winningLotto = (WinningLotto) LottoFactory.createLottoManual(LottoType.WINNING_LOTTO, winningLottoNumbers);

		List<LottoNumber> lottoNumbersFistPrize = Arrays.asList(
				LottoNumber.ONE,
				LottoNumber.TWO,
				LottoNumber.THREE,
				LottoNumber.FOUR,
				LottoNumber.FIVE,
				LottoNumber.SIX
		);
		List<LottoNumber> lottoNumbersForthPrize = Arrays.asList(
				LottoNumber.ONE,
				LottoNumber.TWO,
				LottoNumber.THREE,
				LottoNumber.FOUR,
				LottoNumber.EIGHT,
				LottoNumber.NINE
		);

		List<LottoNumber> lottoNumbersSixthPrize = Arrays.asList(
				LottoNumber.TEN,
				LottoNumber.ELEVEN,
				LottoNumber.TWELVE,
				LottoNumber.THIRTEEN,
				LottoNumber.EIGHT,
				LottoNumber.NINE
		);

		tempLottos.add(LottoFactory.createLottoManual(LottoType.PAID_LOTTO, lottoNumbersFistPrize));
		tempLottos.add(LottoFactory.createLottoManual(LottoType.PAID_LOTTO, lottoNumbersForthPrize));
		tempLottos.add(LottoFactory.createLottoManual(LottoType.PAID_LOTTO, lottoNumbersSixthPrize));
		lottos = new Lottos(tempLottos);

		bonusLottoNumber = new BonusLottoNumber(7, winningLotto);
	}

	@Test
	void calculate_ResultStatistic_생성_확인() {
		ResultStatistic resultStatistic = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		assertThat(resultStatistic).isInstanceOf(ResultStatistic.class);
	}

	@Test
	void calculate_올바른_리턴_결과_확인() {
		ResultStatistic resultStatistic = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		Map<Rank, Integer> resultMap = resultStatistic.getResults();

		assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1); // TODO 이거 한번에 안되나??
		assertThat(resultMap.get(Rank.SECOND)).isEqualTo(0);
		assertThat(resultMap.get(Rank.THIRD)).isEqualTo(0);
		assertThat(resultMap.get(Rank.FOURTH)).isEqualTo(1);
		assertThat(resultMap.get(Rank.FIFTH)).isEqualTo(0);
		assertThat(resultMap.get(Rank.SIXTH)).isEqualTo(1);
	}
}
