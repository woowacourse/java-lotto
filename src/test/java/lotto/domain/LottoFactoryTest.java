package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름 : LottoFactoryTest.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactoryTest {
	@Test
	void createLottoAuto_올바른_동작_확인() {
		assertThat(LottoFactory.createLottoAuto())
			.isInstanceOf(Lotto.class);
	}

	@Test
	void createLottoManual_올바른_동작_확인() {
		List<Integer> winningLottoNumbers = Arrays.asList(3, 2, 1, 10, 8, 44);

		assertThat(LottoFactory.createLottoManual(winningLottoNumbers))
				.isInstanceOf(Lotto.class);
	}

	@Test
	void createLottoManual_중복된_로또번호_예외처리() {
		List<Integer> winningLottoNumbers = Arrays.asList(3, 2, 1, 10, 8, 10);

		assertThatThrownBy(() -> {
			LottoFactory.createLottoManual(winningLottoNumbers);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("입력 리스트에 중복이 있습니다.");
	}
}
