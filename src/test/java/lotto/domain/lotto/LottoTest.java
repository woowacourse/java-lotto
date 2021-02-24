package lotto.domain.lotto;

import lotto.domain.result.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.lotto.Lotto.DUPLICATE_ERROR;
import static lotto.domain.lotto.Lotto.LOTTO_SIZE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
	private Lotto lotto;
	private WinningLotto winningLotto;

	@BeforeEach
	void setUp() {
		lotto = new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(20),
				new LottoNumber(21),
				new LottoNumber(40)));

		winningLotto = new WinningLotto(
				new Lotto(Arrays.asList(
						new LottoNumber(1),
						new LottoNumber(2),
						new LottoNumber(3),
						new LottoNumber(4),
						new LottoNumber(5),
						new LottoNumber(6))),
				new LottoNumber(10));
	}

	@DisplayName("로또의 사이즈를 제대로 검증 해주는지")
	@Test
	void validateLottoSize() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6),
				new LottoNumber(7)
		))).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(LOTTO_SIZE_ERROR);
	}

	@DisplayName("로또 숫자가 중복되는 것이 있는지 제대로 검증 해주는지")
	@Test
	void validateDuplicates() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(5)
		))).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DUPLICATE_ERROR);

	}

	@DisplayName("매칭되는 숫자를 올바로 카운트 하는 지")
	@Test
	void countMatchingNumbers() {
		int matches = winningLotto.countMatchingNumbersWith(lotto);

		assertThat(matches).isEqualTo(3);
	}
}
