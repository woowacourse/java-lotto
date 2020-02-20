package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
	List<LottoNo> numbers;

	@BeforeEach
	void setUp() {
		numbers = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			numbers.add(new LottoNo(i));
		}
	}

	@Test
	void isContain() {
		Lotto lotto = new Lotto(numbers);
		assertThat(lotto.isContain(new LottoNo(1))).isTrue();
		assertThat(lotto.isContain(new LottoNo(7))).isFalse();
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
