package com.woowacourse.lotto.domain;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
	@Test
	void create() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		Lotto lotto = new Lotto(numbers);
		assertEquals(lotto, new Lotto(numbers));
	}

	@Test
	void invalidRange() {
		assertThrows(InvalidNumberException.class, () -> new Lotto(Arrays.asList(0,2,3,4,5,6)));
		assertThrows(InvalidNumberException.class, () -> new Lotto(Arrays.asList(1,2,3,4,5,46)));
	}
}