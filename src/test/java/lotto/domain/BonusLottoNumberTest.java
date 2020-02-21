package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
		WinningLotto winningLotto = new WinningLotto(
				LottoNumber.getCache().stream()
						.limit(6)
						.collect(Collectors.toList())
		);

		BonusLottoNumber bonusLottoNumber = new BonusLottoNumber(7, winningLotto);
		assertThat(bonusLottoNumber).isInstanceOf(BonusLottoNumber.class);
	}
}
