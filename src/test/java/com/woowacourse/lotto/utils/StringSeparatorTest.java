package com.woowacourse.lotto.utils;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSeparatorTest {
	@Test
	void splitStringWithMark() {
		assertEquals(StringSeparator.splitString("1,2,3,4,5,6"), Arrays.asList("1", "2", "3", "4", "5", "6"));
	}
}