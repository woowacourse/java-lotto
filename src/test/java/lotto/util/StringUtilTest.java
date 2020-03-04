package lotto.util;

import lotto.exception.EmptyInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringUtilTest {
	@ParameterizedTest
	@CsvSource(value = {"6, 8, 0 :6,8,0", "1,  3,   4 :1,3,4"}, delimiter = ':')
	void removeBlankTest(String value, String expected) {
		assertThat(StringUtil.removeBlank(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {"6,8,10"})
	void parseTest(String value) {
		assertThat(StringUtil.parseByComma(value)).contains("6");
	}

	@ParameterizedTest
	@ValueSource (strings = {" ", "", "     "})
	void checkBlankTest(String input){
		assertThatThrownBy(()->StringUtil.checkBlank(input)).isInstanceOf(EmptyInputException.class)
				.hasMessageMatching("공백은 사용할 수 없습니다.");
	}

	@Test
	void checkNullTest(){
		assertThatThrownBy(()->StringUtil.checkNull(null)).isInstanceOf(NullPointerException.class)
				.hasMessageMatching("널문자는 사용할 수 없습니다.");
	}

	@ParameterizedTest
	@ValueSource (strings = {"a", "으악", "34,"})
	void checkNumberFormatTest(String input){
		assertThatThrownBy(()->StringUtil.checkNumberFormat(input)).isInstanceOf(NumberFormatException.class)
				.hasMessageMatching("문자열은 사용할 수 없습니다.");
	}
}
