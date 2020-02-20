package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LottoTest {
	@Test
	void isContain() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.isContain(1)).isTrue();
		assertThat(lotto.isContain(7)).isFalse();
	}
}
