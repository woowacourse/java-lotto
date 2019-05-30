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
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Lotto lotto = new Lotto(numbers);
		assertEquals(lotto, new Lotto(numbers));
	}

	@Test
	void invalidRange() {
		assertThrows(InvalidNumberException.class, () -> new Lotto(Arrays.asList(0, 2, 3, 4, 5, 6)));
		assertThrows(InvalidNumberException.class, () -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 46)));
	}

	@Test
	void duplicateCountOfLottoNumber() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)))).isEqualTo(6);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)))).isEqualTo(5);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 8, 7)))).isEqualTo(4);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 2, 3, 9, 8, 7)))).isEqualTo(3);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 2, 10, 9, 8, 7)))).isEqualTo(2);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(1, 11, 10, 9, 8, 7)))).isEqualTo(1);
		assertThat(new Lotto(numbers).duplicateNumber(new Lotto(Arrays.asList(12, 11, 10, 9, 8, 7)))).isEqualTo(0);
	}
}