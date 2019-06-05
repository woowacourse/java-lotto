package com.woowacourse.lotto.domain;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.woowacourse.lotto.domain.LottoRank.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
	private LottoResult actualLottoResult;

	@BeforeEach
	void init() {
		WinningLotto winningLotto = new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "6"), 7);
		List<LottoNumber> numbers = new ArrayList<>();
		for (int i = 1; i <= 6; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
		Lotto FirstLotto = new Lotto(numbers);

		numbers = new ArrayList<>();
		for (int i = 7; i <= 12; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
		Lotto SecondLotto = new Lotto(numbers);

		numbers = new ArrayList<>();
		for (int i = 2; i <= 7; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
		Lotto ThirdLotto = new Lotto(numbers);


		Lottos lottos = new Lottos(Arrays.asList(FirstLotto, SecondLotto, ThirdLotto));
		actualLottoResult = new LottoResult(winningLotto, lottos);
	}

	@Test
	void checkSum() {
		assertThat(actualLottoResult.sum()).isEqualTo(2_030_000_000);
	}

	@Test
	void getRanks() {
		List<LottoRank> ranks = new LinkedList<>(Arrays.asList(LottoRank.values()));
		ranks.remove(ZERO);
		assertThat(actualLottoResult.getRanks()).isEqualTo(ranks);
	}

	@Test
	void checkValueOf() {
		assertThat(actualLottoResult.valueOf(LottoRank.FIRST)).isEqualTo(1);
		assertThat(actualLottoResult.valueOf(LottoRank.SECOND)).isEqualTo(1);
		assertThat(actualLottoResult.valueOf(LottoRank.THIRD)).isEqualTo(0);
		assertThat(actualLottoResult.valueOf(LottoRank.FOURTH)).isEqualTo(0);
		assertThat(actualLottoResult.valueOf(LottoRank.FIFTH)).isEqualTo(0);
		assertThat(actualLottoResult.valueOf(ZERO)).isEqualTo(1);

	}
}