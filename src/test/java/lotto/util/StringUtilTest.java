package lotto.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.Number;
import lotto.exception.InvalidNumberException;

@SuppressWarnings("NonAsciiCharacters")
public class StringUtilTest {
	@ParameterizedTest
	@CsvSource(value = {"6, 8, 0 :6,8,0", "1,  3,   4 :1,3,4"}, delimiter = ':')
	void 공백_제거_테스트(String value, String expected) {
		assertThat(StringUtil.removeBlank(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {"6,8,10"})
	void parsing(String value) {
		assertThat(StringUtil.parseToNumbers(value)).contains("6");
	}

	@ParameterizedTest
	@ValueSource(strings = {"","  ","    "})
	void empty(String value) {
		assertThatThrownBy(()->{
			StringUtil.checkBlank(value);
		}).isInstanceOf(EmptyInputException.class)
			.hasMessageMatching("공백은 사용할 수 없습니다.");
	}

	@Test
	void nullTest(){
		assertThatThrownBy(()->{
			StringUtil.checkNull(null);
		}).isInstanceOf(NullPointerException.class)
			.hasMessageMatching("널은 입력되지 않습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"asd", "100a", "a100", "천원"})
	@DisplayName("수를 문자열로 입력하는 경우")
	void numberFormatTest(String value) {
		assertThatThrownBy(() -> {
			StringUtil.checkNumberFormat(value);
		}).isInstanceOf(NumberFormatException.class)
			.hasMessageMatching("문자는 사용이 불가능합니다.");
	}
}
