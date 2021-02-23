package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.WinningLotto.DUPLICATE_BONUS_NUMBER_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {
	private WinningLotto winingLotto;

	@BeforeEach
	void setUp() {
		winingLotto = new WinningLotto(
				new Lotto(Arrays.asList(
						new LottoNumber(1),
						new LottoNumber(2),
						new LottoNumber(3),
						new LottoNumber(4),
						new LottoNumber(5),
						new LottoNumber(6))),
				new LottoNumber(20));
	}

	@DisplayName("보너스가 당첨 번호와 중복 되는 것이 있는지 검증")
	@Test
	void validateDuplicatesWithWinningNumbers() {
		assertThatThrownBy(() -> new WinningLotto(
				new Lotto(Arrays.asList(
						new LottoNumber(1),
						new LottoNumber(2),
						new LottoNumber(3),
						new LottoNumber(4),
						new LottoNumber(5),
						new LottoNumber(6))),
				new LottoNumber(5)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(DUPLICATE_BONUS_NUMBER_ERROR);
	}

	@DisplayName("당첨 번호와 로또와의 매칭 확인 제대로 하는지")
	@Test
	void countMatchingNumbers() {
		assertThat(winingLotto.countMatchingNumbersWith(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(13),
				new LottoNumber(14),
				new LottoNumber(16)))).isEqualTo(3);
	}

	@DisplayName("보너스 번호와 로또와의 매칭 확인 제대로 하는지")
	@Test
	void hasBonusMatch() {
		assertThat(winingLotto.hasBonusMatchWith(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(13),
				new LottoNumber(14),
				new LottoNumber(20)))).isTrue();
	}
}