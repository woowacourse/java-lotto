package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static com.woowacourse.lotto.domain.LottoRank.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {
	@Test
	void getPrice() {
		assertTrue(FIRST.getPrice() == 2000000000);
		assertTrue(SECOND.getPrice() == 1500000);
		assertTrue(THIRD.getPrice() == 50000);
		assertTrue(FOURTH.getPrice() == 5000);
		assertTrue(ZERO.getPrice() == 0);
	}
}