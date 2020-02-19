package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactoryTest {
	@Test
	void getLottoAuto_올바른_동작_확인() {
		assertThat(LottoFactory.getLottoAuto(LottoType.PAID_LOTTO))
				.isInstanceOf(PaidLotto.class);
	}

	@Test
	void getWinningLotto_올바른_동작_확인() {
		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.THREE,
				LottoNumber.TWO,
				LottoNumber.ONE,
				LottoNumber.TEN,
				LottoNumber.EIGHT,
				LottoNumber.FORTY_FOUR
		);

		assertThat(LottoFactory.getWinningLotto(LottoType.WINNING_LOTTO, winningLottoNumbers))
				.isInstanceOf(WinningLotto.class);
	}

	@Test
	void getWinningLotto_중복된_로또번호_예외처리() {
		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.THREE,
				LottoNumber.TWO,
				LottoNumber.ONE,
				LottoNumber.TEN,
				LottoNumber.EIGHT,
				LottoNumber.TEN
		);

		assertThatThrownBy(() -> {
			LottoFactory.getWinningLotto(LottoType.WINNING_LOTTO, winningLottoNumbers);
		}).isInstanceOf(IllegalArgumentException.class)
		.hasMessage("입력 리스트에 중복이 있습니다.");
	}
}
