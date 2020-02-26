package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WinningLotto 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class WinningLottoTest {
	private WinningLotto winningLotto;

	@BeforeEach
	void setUp() {
		winningLotto = new WinningLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(10),
						LottoNumber.of(8),
						LottoNumber.of(44)
				)
		),
				LottoNumber.of(4));
	}

	@Test
	void isContain_주어진_로또번호_포함시_true_반환() {
		assertThat(
				winningLotto.isContain(LottoNumber.of(10))
		).isTrue();
	}
}
