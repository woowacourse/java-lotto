package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumbersTest {
	@Test
	@DisplayName("6개가 아닌 개수의 번호를 입력 한 경우 예외 발생")
	void countOfNumbersMustBeSix() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		assertThatThrownBy(() -> LottoNumbers.of(input))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("중복되는 숫자를 입력 한 경우 예외 발생")
	void duplicateInNumbers() {
		assertThatThrownBy(() -> LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 5)))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
