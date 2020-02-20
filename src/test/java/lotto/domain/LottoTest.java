package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
	@Test
	void isContain() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.isContain(1)).isTrue();
		assertThat(lotto.isContain(7)).isFalse();
	}

	@DisplayName("로또 번호의 개수가 6개인지 검사")
	@Test
	void Lotto1() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("6개의 숫자가 아닙니다.");
	}
}
