package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumberTest {

	@Test
	void BonusLottoNumber_올바른_동작_확인() {
		WinningLotto winningLotto = new WinningLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.TEN,
						LottoNumber.EIGHT,
						LottoNumber.FORTY_FOUR
				)
		));

		BonusLottoNumber bonusLottoNumber = new BonusLottoNumber(4, winningLotto);
		assertThat(bonusLottoNumber).isInstanceOf(BonusLottoNumber.class);
	}
}
