package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
	@Test
	void create() {
		List<String> numbers = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
		assertThat(new WinningLotto(numbers, new BonusBall(7))).isEqualTo(new WinningLotto(numbers, new BonusBall(7)));
	}

	@Test
	void validateDuplicateNumber() {
		assertThrows(InvalidNumberException.class, () -> new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "1"), new BonusBall(7)));
	}

	@Test
	void validateNumber() {
		assertThrows(InvalidNumberException.class, () -> new WinningLotto(Arrays.asList("1", "2", "3", "4", "5", "46"), new BonusBall(7)));
		assertThrows(InvalidNumberException.class, () -> new WinningLotto(Arrays.asList("0", "2", "3", "4", "5", "45"), new BonusBall(7)));
	}

	@Test
	void validateSize() {
		assertThrows(InvalidNumberException.class, () -> new WinningLotto(Arrays.asList("1", "2", "3", "4", "5"), new BonusBall(7)));
	}

	@Test
	void duplicateBonusBall() {
		assertThrows(IllegalArgumentException.class, () -> new WinningLotto(Arrays.asList("1", "2", "3", "4", "5"), new BonusBall(5)));
	}
}
