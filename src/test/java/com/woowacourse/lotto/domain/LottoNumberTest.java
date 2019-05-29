package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
	@Test
	void invalidIndex() {
		assertThrows(IllegalArgumentException.class, () -> LottoNumber.getLottoNumber(-1));
		assertThrows(IllegalArgumentException.class, () -> LottoNumber.getLottoNumber(45));
	}

	@Test
	void validIndex() {
		assertDoesNotThrow(() -> LottoNumber.getLottoNumber(0));
		assertDoesNotThrow(() -> LottoNumber.getLottoNumber(44));
	}

	@Test
	void confirmSameObjectOfNumbers() {
		assertTrue(LottoNumber.getLottoNumber(0) == LottoNumber.getLottoNumber(0));
		assertTrue(LottoNumber.getLottoNumber(44) == LottoNumber.getLottoNumber(44));
	}

	@Test
	void confirmLottoNumber() {
		assertEquals(LottoNumber.getLottoNumber(0), 1);
		assertEquals(LottoNumber.getLottoNumber(44), 45);
	}
}