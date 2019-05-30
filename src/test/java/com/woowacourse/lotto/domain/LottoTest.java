package com.woowacourse.lotto.domain;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
	private List<LottoNumber> numbers;

	@BeforeEach
	void init() {
		numbers = Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6));
	}

	@Test
	void create() {
		Lotto lotto = new Lotto(numbers);
		assertEquals(lotto, new Lotto(numbers));
	}

	@Test
	void checkCountOfMatchedLottoNumber() {
		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6))))).isEqualTo(6);
		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(Arrays.asList(LottoNumber.getLottoNumber(12), LottoNumber.getLottoNumber(11), LottoNumber.getLottoNumber(10),
				LottoNumber.getLottoNumber(9), LottoNumber.getLottoNumber(8), LottoNumber.getLottoNumber(7))))).isEqualTo(0);
	}

	@Test
	void duplicateLottoNumber() {
		numbers = Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(6), LottoNumber.getLottoNumber(6));
		assertThrows(InvalidNumberException.class, () -> new Lotto(numbers));
	}
}