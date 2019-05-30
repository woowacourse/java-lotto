package com.woowacourse.lotto.domain;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
	private LottoResult lottoResult;

	@BeforeEach
	void init() {
		WinningLotto winningLotto = new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
		List<Lotto> lotto = Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12, 13)));
		Lottos lottos = new Lottos(lotto);
		lottoResult = new LottoResult(winningLotto, lottos);
	}

	@Test
	void checkMatchResult() {
		Map<LottoRank, Integer> lottoMatchResult2 = new TreeMap<>();
		{
			lottoMatchResult2.put(LottoRank.FIRST, 1);
			lottoMatchResult2.put(LottoRank.SECOND, 0);
			lottoMatchResult2.put(LottoRank.THIRD, 0);
			lottoMatchResult2.put(LottoRank.FOURTH, 0);
			lottoMatchResult2.put(LottoRank.FIFTH, 0);
			lottoMatchResult2.put(LottoRank.ZERO, 1);
		}
		assertThat(lottoResult.matchLotto()).isEqualTo(lottoMatchResult2);
	}

	@Test
	void checkEarningsRate() {
		lottoResult.matchLotto();
		assertThat(lottoResult.getEarningsRate()).isEqualTo(100_000_000);
	}
}