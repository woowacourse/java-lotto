package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class LottoTest {
	@Test
	void compare() {
		//given
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
		int bonus = 13;

		assertThat(lotto.compare(winningLotto, bonus)).isEqualTo(Rank.FIFTH);
	}
}