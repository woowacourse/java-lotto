import domain.AnswerLotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerLottoTest {

	@Test
	void countOfNumbersMustBeSix() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		assertThatThrownBy(() -> new AnswerLotto(input, 8))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void numberMoreThanUpperBound() {
		List<Integer> input = new ArrayList<>(Arrays.asList(46, 47, 48, 49, 50, 51));
		assertThatThrownBy(() -> new AnswerLotto(input, 8))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void numberLowerThanLowerBound() {
		List<Integer> input = new ArrayList<>(Arrays.asList(0, -1, -2, -3, -4, -5));
		assertThatThrownBy(() -> new AnswerLotto(input, 8))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 46})
	void bonusNumberInRange(int bonusNumber) {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThatThrownBy(() -> new AnswerLotto(numbers, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void duplicateInNumbers() {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 5, 6));
		assertThatThrownBy(() -> new AnswerLotto(numbers, 7))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void duplicateInBonusNumber() {
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThatThrownBy(() -> new AnswerLotto(numbers, 6))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
