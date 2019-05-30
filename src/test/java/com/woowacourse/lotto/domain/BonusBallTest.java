package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusBallTest {
	@Test
	void create() {
		assertThat(new BonusBall(1)).isEqualTo(new BonusBall(1));
		assertThat(new BonusBall(45)).isEqualTo(new BonusBall(45));
	}

	@Test
	void invalidRange() {
		assertThrows(InvalidNumberException.class, () -> new BonusBall(0));
		assertThrows(InvalidNumberException.class, () -> new BonusBall(46));
	}
}
