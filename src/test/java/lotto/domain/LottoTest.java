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
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThat(new Lotto(numbers));
	}

	@Test
	@DisplayName("오작동 : 로또 하나의 사이즈 5")
	void sizeException() {
		List<Number> numbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7")
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
			Number.of("3"),
			Number.of("5"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThatThrownBy(() -> {
			new Lotto(numbers);
		}).isInstanceOf(InvalidLottoException.class)
			.hasMessageMatching("로또 번호는 중복 될 수 없습니다.");
	}

	@Test
	@DisplayName("로또 두개를 비교하여 같은 수의 개수를 반환하는지")
	void win() {
		List<Number> numbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		List<Number> win = Arrays.asList(
			Number.of("3"),
			Number.of("43"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThat(new Lotto(numbers).compare(new Lotto(win))).isEqualTo(5);
	}

	@Test
	@DisplayName("contains에 인자로 들어온 값이 있는지를 잘 반환")
	void bonus() {
		List<Number> numbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("8")
		);
		assertThat(
			new Lotto(numbers).contains(Number.of("3"))
		).isTrue();
	}
}
