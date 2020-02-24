package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 클래스 이름 : Lottos.java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottosTest {
	@Test
	void Lottos_생성자_올바른_동작_확인() {
		List<Lotto> inputLottos = Arrays.asList(
				LottoFactory.createLottoAuto(),
				LottoFactory.createLottoAuto(),
				LottoFactory.createLottoAuto()
		);
		assertThat(new Lottos(inputLottos)).isInstanceOf(Lottos.class);
	}

	@ParameterizedTest
	@NullSource
	void Lottos_null_입력시_예외처리(List<Lotto> nullInput) {
		assertThatThrownBy(() -> {
			new Lottos(nullInput);
		}).isInstanceOf(NullPointerException.class)
				.hasMessage("입력이 null일 수 없습니다.");
	}

	@ParameterizedTest
	@EmptySource
	void Lottos_빈_리스트_입력시_예외처리(List<Lotto> emptyInput) {
		assertThatThrownBy(() -> {
			new Lottos(emptyInput);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("로또는 한 장 이상 사야합니다.");
	}
}
