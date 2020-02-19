package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottoTest {
	@DisplayName("Lotto 생성자에 Number List 입력이 들어올 때 객체 생성")
	@Test
	void constructor_NumberList_CreateLotto() {
		List<Number> numbers = Arrays.asList(
			Number.valueOf(1),
			Number.valueOf(2),
			Number.valueOf(3));
		assertThat(new Lotto(numbers)).isInstanceOf(Lotto.class);
	}

	@DisplayName("Lotto 생성자에 Null이나 Empty List 입력이 들어올 때 InvalidLottoException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrEmptyNumberList_ExceptionThrown(List<Number> numbers) {
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(InvalidLottoException.class)
			.hasMessage(InvalidLottoException.NULL_OR_EMPTY);
	}

	@DisplayName("Lotto 생성자에 중복된 Number를 가진 Number List 입력이 들어올 때 InvalidLottoException 발생")
	@Test
	void validateDuplication_DuplicatedNumberList_ExceptionThrown() {
		List<Number> numbers = Arrays.asList(
			Number.valueOf(1),
			Number.valueOf(1),
			Number.valueOf(3));
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(InvalidLottoException.class)
			.hasMessage(InvalidLottoException.DUPLICATION);
	}
}
