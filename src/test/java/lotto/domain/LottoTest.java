package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidLottoException;

public class LottoTest {

	@Test
	@DisplayName("정상작동 : 로또 하나의 사이즈 6")
	void size() {
		List<Number> numbers = Arrays.asList(
			new Number("3"),
			new Number("8"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);
		assertThat(new Lotto(numbers));
	}

	@Test
	@DisplayName("정상작동 : 로또 하나의 사이즈 6")
	void 로또_팩토리_생성() {
		assertThat(LottoFactory.create());
	}

	@Test
	@DisplayName("오작동 : 로또 하나의 사이즈 5")
	void sizeException() {
		List<Number> numbers = Arrays.asList(
			new Number("3"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);
		assertThatThrownBy(() -> {
			new Lotto(numbers);
		}).isInstanceOf(InvalidLottoException.class)
			.hasMessageMatching("로또는 6개의 수를 가져야 합니다.");
	}

	@Test
	@DisplayName("로또 번호는 중복될 수 없다.")
	void numberDuplication() {
		List<Number> numbers = Arrays.asList(
			new Number("3"),
			new Number("3"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);
		assertThatThrownBy(() -> {
			new Lotto(numbers);
		}).isInstanceOf(InvalidLottoException.class)
			.hasMessageMatching("로또 번호는 중복 될 수 없습니다.");
	}

	@Test
	void win() {
		List<Number> numbers = Arrays.asList(
			new Number("3"),
			new Number("8"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);
		List<Number> win = Arrays.asList(
			new Number("3"),
			new Number("43"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);
		assertThat(new Lotto(numbers).compareLotto(new WinningNumbers(win))).isEqualTo(5);
	}

	@Test
	void bonus() {
		List<Number> numbers = Arrays.asList(
			new Number("3"),
			new Number("8"),
			new Number("6"),
			new Number("4"),
			new Number("7"),
			new Number("9")
		);

		assertThat(
			new Lotto(numbers).contains(new BonusNumber("8"))
		).isTrue();

	}

}
