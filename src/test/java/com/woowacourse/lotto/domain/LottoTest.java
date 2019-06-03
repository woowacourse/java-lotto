package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
	private List<LottoNumber> numbers = new ArrayList<>();

	@BeforeEach
	void init() {
		for(int i=1; i<=6; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
	}

	@Test
	void create() {
		Lotto lotto = new Lotto(numbers);
		assertEquals(lotto, new Lotto(numbers));
	}

	@Test
	void checkCountOfMatchedLottoNumber() {
		List<LottoNumber> allMatchedNumbers = new ArrayList<>();
		List<LottoNumber> zeroMatchedNumbers = new ArrayList<>();

		for(int i=1; i<=6; ++i) {
			allMatchedNumbers.add(LottoNumber.getLottoNumber(i));
		}

		for(int i=7; i<=12; ++i) {
			zeroMatchedNumbers.add(LottoNumber.getLottoNumber(i));
		}

		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(allMatchedNumbers))).isEqualTo(6);
		assertThat(new Lotto(numbers).getCountOfMatchedNumber(new Lotto(zeroMatchedNumbers))).isEqualTo(0);
	}

	@Test
	void checkDuplicatedLottoNumber() {
		numbers = Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(6), LottoNumber.getLottoNumber(6));
		assertThrows(InvalidNumberException.class, () -> new Lotto(numbers));
	}
}