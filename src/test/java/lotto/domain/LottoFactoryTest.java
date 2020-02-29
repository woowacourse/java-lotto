package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LottoFactoryTest {
	@Test
	void createLotto() {
		Lotto actual = LottoFactory.createLotto("1,2,3,4,5,6");
		Lotto expected = new Lotto(Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(4),
				LottoNumber.of(5),
				LottoNumber.of(6)
		));
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void createWinningLotto() {
		WinningLotto actual = LottoFactory.createWinningLotto("1,2,3,4,5,6", "7");
		WinningLotto expected = new WinningLotto(LottoFactory.createLotto("1,2,3,4,5,6"), LottoNumber.of(7));
		assertThat(actual).isEqualTo(expected);
	}
}
