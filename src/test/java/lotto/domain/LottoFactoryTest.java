package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoFactoryTest {
	@Test
	void create() {
		Lotto actual = LottoFactory.create("1,2,3,4,5,6");
		Lotto expected = Lotto.of(1,2,3,4,5,6);
		assertThat(actual).isEqualTo(expected);
	}
}
