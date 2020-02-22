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
 * 클래스 이름 : WinningLottoTest.java
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
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(10),
						LottoNumber.of(8),
						LottoNumber.of(44)
				)
		));
	}

	@Test
	void isContain_주어진_로또번호_포함시_true_반환() {
		assertThat(winningLotto.isContain(LottoNumber.of(10))).isTrue();
	}
}
