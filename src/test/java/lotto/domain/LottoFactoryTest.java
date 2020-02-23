package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * LottoFactory 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactoryTest {
	@Test
	void createLottoAuto_올바른_동작_확인() {
		assertThat(LottoFactory.createLottoAuto(LottoType.PAID_LOTTO))
				.isInstanceOf(PaidLotto.class);
	}

	@Test
	void createLottoManual_올바른_동작_확인() {
		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.of(3),
				LottoNumber.of(2),
				LottoNumber.of(1),
				LottoNumber.of(10),
				LottoNumber.of(8),
				LottoNumber.of(44)
		);

		assertThat(LottoFactory.createLottoManual(LottoType.WINNING_LOTTO, winningLottoNumbers))
				.isInstanceOf(WinningLotto.class);
	}

	@Test
	void createLottoManual_중복된_로또번호_예외처리() {
		List<LottoNumber> winningLottoNumbers = Arrays.asList(
				LottoNumber.of(3),
				LottoNumber.of(2),
				LottoNumber.of(1),
				LottoNumber.of(10),
				LottoNumber.of(8),
				LottoNumber.of(10)
		);

		assertThatThrownBy(() -> {
			LottoFactory.createLottoManual(LottoType.WINNING_LOTTO, winningLottoNumbers);
		}).isInstanceOf(InvalidLottoException.class)
				.hasMessage("입력 로또번호에 중복이 있습니다.");
	}
}
