package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static com.woowacourse.lotto.domain.LottoRank.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {
	@Test
	void getPrice() {
		assertTrue(FIRST.getPrice() == 2_000_000_000);
		assertTrue(SECOND.getPrice() == 30_000_000);
		assertTrue(THIRD.getPrice() == 1_500_000);
		assertTrue(FOURTH.getPrice() == 50_000);
		assertTrue(FIFTH.getPrice() == 5_000);
		assertTrue(ZERO.getPrice() == 0);
	}

	@Test
	void getCount() {
		assertTrue(FIRST.getCount() == 6);
		assertTrue(SECOND.getCount() == 5);
		assertTrue(THIRD.getCount() == 5);
		assertTrue(FOURTH.getCount() == 4);
		assertTrue(FIFTH.getCount() == 3);
		assertTrue(ZERO.getCount() == 0);
	}

	@Test
	void checkValueOf() {
		assertTrue(LottoRank.valueOf(6, false) == FIRST);
		assertTrue(LottoRank.valueOf(5, true) == SECOND);
		assertTrue(LottoRank.valueOf(5, false) == THIRD);
		assertTrue(LottoRank.valueOf(4, true) == FOURTH);
		assertTrue(LottoRank.valueOf(4, false) == FOURTH);
		assertTrue(LottoRank.valueOf(3, true) == FIFTH);
		assertTrue(LottoRank.valueOf(3, false) == FIFTH);
		assertTrue(LottoRank.valueOf(2, true) == ZERO);
		assertTrue(LottoRank.valueOf(2, false) == ZERO);
		assertTrue(LottoRank.valueOf(1, true) == ZERO);
		assertTrue(LottoRank.valueOf(1, false) == ZERO);
	}
}