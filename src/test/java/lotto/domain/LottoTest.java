package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class LottoTest {
	List<LottoNo> numbers;

	@BeforeEach
	void setUp() {
		numbers = IntStream.range(1, 7)
				.boxed()
				.map(LottoNo::new)
				.collect(Collectors.toList());
	}

	@Test
	void isContain() {
		Lotto lotto = new Lotto(numbers);
		assertThat(lotto.contains(new LottoNo(1))).isTrue();
		assertThat(lotto.contains(new LottoNo(7))).isFalse();
	}

	@DisplayName("로또 번호의 개수가 6개인지 검사")
	@Test
	void Lotto1() {
		numbers.add(new LottoNo(8));
		assertThatThrownBy(() -> new Lotto(numbers))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("6개의 숫자가 아닙니다.");
	}
}
