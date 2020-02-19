package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
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
public class WinningLottoTest {
	private WinningLotto winningLotto;

	@BeforeEach
	void setUp() {
		winningLotto = new WinningLotto(new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.TEN,
						LottoNumber.EIGHT,
						LottoNumber.FORTY_FOUR
				)
		));
	}

	@Test
	void isContain_포함시_true_반환() {
		assertThat(winningLotto.isContain(LottoNumber.TEN)).isTrue();
	}

	@Test
	void getHowManyContain_올바른_동작_확인() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(
				Arrays.asList(
						LottoNumber.ONE,
						LottoNumber.TWO,
						LottoNumber.THREE,
						LottoNumber.TEN,
						LottoNumber.EIGHT,
						LottoNumber.FORTY_FOUR
				)
		);

		PaidLotto paidLotto = new PaidLotto(lottoNumbers);

		assertThat(winningLotto.getHowManyContain(paidLotto)).isEqualTo(6);
	}

	@ParameterizedTest
	@NullSource
	void getHowManyContain_매개변수_null_예외처리(PaidLotto nullLotto) {
		assertThatThrownBy(() -> {
			winningLotto.getHowManyContain(nullLotto);
		}).isInstanceOf(NullPointerException.class)
				.hasMessage("매개변수가 null 입니다.");
	}
}
