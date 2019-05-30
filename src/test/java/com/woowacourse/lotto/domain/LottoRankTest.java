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
}