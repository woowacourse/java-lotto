package lotto.domain;

import static lotto.domain.LottoNumberParser.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberParserTest {
	@DisplayName("parseToLottoNumberSet에 번호 6개를 입력하면 LottoNumberSet 생성")
	@Test
	void parseToLottoNumberSet_StringInputWinningLotto_WinningLottoNumberSet() {
		Set<LottoNumber> winningLottoNumbers = parseToLottoNumberSet("1, 2, 3, 4, 5, 6");

		Set<LottoNumber> expected = new HashSet<>();
		expected.add(LottoNumber.valueOf(1));
		expected.add(LottoNumber.valueOf(2));
		expected.add(LottoNumber.valueOf(3));
		expected.add(LottoNumber.valueOf(4));
		expected.add(LottoNumber.valueOf(5));
		expected.add(LottoNumber.valueOf(6));

		assertThat(winningLottoNumbers).isEqualTo(expected);
	}

	@DisplayName("parseToLottoNumberSet에 null이나 빈 문자열을 입력하면 InvalidWinningLottoException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void parseToLottoNumberSet_NullAndEmptyString_ExceptionThrown(String input) {
		assertThatThrownBy(() -> parseToLottoNumberSet(input))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.NULL_AND_EMPTY_STRING);
	}

	@DisplayName("parseToLottoNumberSet에 정수가 아닌 값을 입력하면 InvalidWinningLottoException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a,a,a,a,a,a", "1.1,2.2,3.3,4.4,5.5,6.6"})
	void parseToLottoNumberSet_NotInteger_WinningLottoNumberSet(String input) {
		assertThatThrownBy(() -> parseToLottoNumberSet(input))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.NOT_INTEGER);
	}

	@DisplayName("parseToLottoNumberSet에 6개가 아닌 값을 입력하면 InvalidWinningLottoException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
	void parseToLottoNumberSet_InvalidCount_WinningLottoNumberSet(String input) {
		assertThatThrownBy(() -> parseToLottoNumberSet(input))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.INVALID_COUNT);
	}
}
