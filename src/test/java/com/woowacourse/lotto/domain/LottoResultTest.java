package com.woowacourse.lotto.domain;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void checkMatchResult() {
		Map<LottoRank, Integer> expectedLottoMatchResult = new TreeMap<>();
		{
			expectedLottoMatchResult.put(LottoRank.ZERO, 1);
			expectedLottoMatchResult.put(LottoRank.FIFTH, 0);
			expectedLottoMatchResult.put(LottoRank.FOURTH, 0);
			expectedLottoMatchResult.put(LottoRank.THIRD, 0);
			expectedLottoMatchResult.put(LottoRank.SECOND, 1);
			expectedLottoMatchResult.put(LottoRank.FIRST, 1);
		}
		assertThat(actualLottoResult.getLottoResult()).isEqualTo(expectedLottoMatchResult);
	}

	@Test
	void checkEarningsRate() {
		assertThat(actualLottoResult.calculateEarningsRate()).isEqualTo((long) 67_666_666);
	}
}