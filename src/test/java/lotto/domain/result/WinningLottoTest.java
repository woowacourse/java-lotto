package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.result.WinningLotto.DUPLICATE_BONUS_NUMBER_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {
	private WinningLotto winingLotto;

	@BeforeEach
	void setUp() {
		Lotto winningLotto = createCustomLotto(1, 2, 3, 4, 5, 6);
		winingLotto = new WinningLotto(winningLotto, new LottoNumber(20));
	}

	@DisplayName("보너스가 당첨 번호와 중복 되는 것이 있는지 검증")
	@Test
	void validateDuplicatesWithWinningNumbers() {
		Lotto winningLotto = createCustomLotto(1, 2, 3, 4, 5, 6);
		assertThatThrownBy(() -> new WinningLotto(winningLotto, new LottoNumber(5))).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DUPLICATE_BONUS_NUMBER_ERROR);
	}

	@DisplayName("당첨 번호와 로또와의 매칭 확인 제대로 하는지")
	@Test
	void countMatchingNumbers() {
		Lotto lotto = createCustomLotto(1, 2, 3, 13, 14, 16);
		assertThat(winingLotto.countMatchingNumbersWith(lotto)).isEqualTo(3);
	}

	@DisplayName("보너스 번호와 로또와의 매칭 확인 제대로 하는지")
	@Test
	void hasBonusMatch() {
		Lotto lotto = createCustomLotto(1, 2, 3, 13, 14, 20);
		assertThat(winingLotto.hasBonusMatchWith(lotto)).isTrue();
	}

	private Lotto createCustomLotto(final int... numbers) {
		return new Lotto(Arrays.asList(
				new LottoNumber(numbers[0]),
				new LottoNumber(numbers[1]),
				new LottoNumber(numbers[2]),
				new LottoNumber(numbers[3]),
				new LottoNumber(numbers[4]),
				new LottoNumber(numbers[5])));
	}
}