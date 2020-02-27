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

	@DisplayName("로또 객체 생성시 null 전달 테스트")
	@Test
	void Lotto2() {
		assertThatThrownBy(() -> new Lotto(null))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
