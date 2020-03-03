package lotto.domain.number;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberLinesOfManualLottoTest {
	private static NumberLinesOfManualLotto numberLinesOfManualLotto;

	@BeforeAll
	static void setUp() {
		numberLinesOfManualLotto = new NumberLinesOfManualLotto();
	}

	@DisplayName("add()메서드에 Null이 들어오면 InvalidManualNumberLineException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void add_Null_ThrownException(String input) {
		assertThatThrownBy(() -> numberLinesOfManualLotto.add(input))
			.isInstanceOf(InvalidManualNumberLineException.class);
	}

	@DisplayName("add()메서드에 잘못된 갯수로 구성된 로또라인(1줄)이 들어오면 InvalidManualNumberLineException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"11,12,13,14,15", "21,22,23,24,25,26,27"})
	void add_WrongSize_ThrownException(String input) {
		assertThatThrownBy(() -> numberLinesOfManualLotto.add(input))
			.isInstanceOf(InvalidManualNumberLineException.class)
			.hasMessage(InvalidManualNumberLineException.WRONG_SIZE);
	}

	@DisplayName("add()메서드에 정수로 구성되지 않은 로또라인(1줄)이 들어오면 InvalidManualNumberLineException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a,b,c,d,e,f", "@,$,!,1,&,*"})
	void add_NotInteger_ThrownException(String input) {
		assertThatThrownBy(() -> numberLinesOfManualLotto.add(input))
			.isInstanceOf(InvalidManualNumberLineException.class)
			.hasMessage(InvalidManualNumberLineException.NOT_INTEGER);
	}

	@DisplayName("add()메서드에 로또 번호 타입이 아닌 로또라인(1줄)이 들어오면 InvalidManualNumberLineException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"-1,0,1,2,3,4", "50,51,52,53,54,55"})
	void add_NotLottoNumberType_ThrownException(String input) {
		assertThatThrownBy(() -> numberLinesOfManualLotto.add(input))
			.isInstanceOf(InvalidManualNumberLineException.class)
			.hasMessage(InvalidLottoNumberException.OUT_OF_BOUND);
	}

	@DisplayName("size()를 호출했을 때 현재 numberLinesOfManualLotto의 사이즈를 반환")
	@Test
	void size_returnSize() {
		numberLinesOfManualLotto.add("1,2,3,4,5,6");
		assertThat(numberLinesOfManualLotto.size()).isEqualTo(1);
	}
}
