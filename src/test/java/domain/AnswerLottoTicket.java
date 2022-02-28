package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerLottoTicket {

	@Test
	@DisplayName("지난주 당첨번호 : 6개가 아닌 개수의 번호를 입력 한 경우 예외 발생")
	void countOfNumbersMustBeSix() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		assertThatThrownBy(() -> AnswerLotto.of(input, 8))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("지난주 당첨번호 : 최대 숫자 이상의 숫자를 압력 한 경우 예외 발생")
	void numberMoreThanUpperBound() {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 46), 6))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("지난주 당첨번호 : 최소 숫자 이하의 숫자를 입력 한 경우 예외 발생")
	void numberLowerThanLowerBound() {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 0), 6))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("지난주 당첨번호 : 중복되는 숫자를 입력 한 경우 예외 발생")
	void duplicateInNumbers() {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 5), 6))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	@DisplayName("보너스 번호 : 가능 범위 이외의 숫자를 입력 한 경우 예외 발생")
	void bonusNumberInRange(int bonusNumber) {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 6), bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("보너스 번호 : 지난주 당첨 번호와 중복되는 숫자를 입력 한 경우 예외 발생")
	void duplicateInBonusNumber() {
		assertThatThrownBy(() -> AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 6), 6))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
