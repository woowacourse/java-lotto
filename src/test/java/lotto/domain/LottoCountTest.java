package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoCountTest {
	@Test
	void validateCount() {
		assertThatThrownBy(() -> new LottoCount(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
