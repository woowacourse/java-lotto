package com.woowacourse.lotto.domain;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
	private LottoResult lottoResult;

	@BeforeEach
	void init() {
		WinningLotto winningLotto = new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "6"), 7);
		List<Lotto> lotto = Arrays.asList(new Lotto(Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6))),
				new Lotto(Arrays.asList(LottoNumber.getLottoNumber(7), LottoNumber.getLottoNumber(8), LottoNumber.getLottoNumber(9),
						LottoNumber.getLottoNumber(10), LottoNumber.getLottoNumber(11), LottoNumber.getLottoNumber(12))));
		lottoResult = new LottoResult(winningLotto, new Lottos(lotto));
	}

	@Test
	void checkMatchResult() {
		Map<LottoRank, Integer> lottoMatchResult2 = new TreeMap<>();
		{
			lottoMatchResult2.put(LottoRank.ZERO, 1);
			lottoMatchResult2.put(LottoRank.FIFTH, 0);
			lottoMatchResult2.put(LottoRank.FOURTH, 0);
			lottoMatchResult2.put(LottoRank.THIRD, 0);
			lottoMatchResult2.put(LottoRank.SECOND, 0);
			lottoMatchResult2.put(LottoRank.FIRST, 1);
		}
		assertThat(lottoResult.getLottoResult()).isEqualTo(lottoMatchResult2);
	}

	@Test
	void checkEarningsRate() {
		assertThat(lottoResult.getEarningsRate()).isEqualTo((long) 100_000_000);
	}
}