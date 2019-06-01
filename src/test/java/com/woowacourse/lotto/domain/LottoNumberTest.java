package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
	@Test
	void invalidIndex() {
		assertThrows(InvalidNumberException.class, () -> LottoNumber.getLottoNumber(0));
		assertThrows(InvalidNumberException.class, () -> LottoNumber.getLottoNumber(46));
	}

	@Test
	void validIndex() {
		assertDoesNotThrow(() -> LottoNumber.getLottoNumber(1));
		assertDoesNotThrow(() -> LottoNumber.getLottoNumber(45));
	}

	@Test
	void confirmSameObjectOfNumbers() {
		assertTrue(LottoNumber.getLottoNumber(1) == LottoNumber.getLottoNumber(1));
		assertTrue(LottoNumber.getLottoNumber(45) == LottoNumber.getLottoNumber(45));
	}
}