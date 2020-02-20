package lotto.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringUtilTest {
	@ParameterizedTest
	@CsvSource(value = {"6, 8, 0 :6,8,0", "1,  3,   4 :1,3,4"}, delimiter = ':')
	void 공백_제거_테스트(String value, String expected) {
		assertThat(StringUtil.removeBlank(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {"6,8,10"})
	void shouldGetDataBit(String value) {
		assertThat(StringUtil.parseToNumbers(value)).contains("6");
	}
}
