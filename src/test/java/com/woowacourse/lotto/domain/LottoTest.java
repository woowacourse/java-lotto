package com.woowacourse.lotto.domain;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
	@Test
	void create() {
		List<LottoNumber> numbers = Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6));
		Lotto lotto = new Lotto(numbers);
		assertEquals(lotto, new Lotto(numbers));
	}

	@Test
	void duplicateCountOfLottoNumber() {
		List<LottoNumber> numbers = Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6));
		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6))))).isEqualTo(6);
		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(Arrays.asList(LottoNumber.getLottoNumber(12), LottoNumber.getLottoNumber(11), LottoNumber.getLottoNumber(10),
				LottoNumber.getLottoNumber(9), LottoNumber.getLottoNumber(8), LottoNumber.getLottoNumber(7))))).isEqualTo(0);
	}
}