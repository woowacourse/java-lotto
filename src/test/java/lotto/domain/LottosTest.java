package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.exception.InvalidLottosException;

public class LottosTest {
	@Test
	@DisplayName("로또 여러 장이 정상적으로 생성된 경우")
	void constructor() {
		assertThat(new Lottos(Arrays.asList(
				LottoFactory.create("1,2,3,4,5,6"),
				LottoFactory.create("2,3,4,5,6,7")
		))).isInstanceOf(Lottos.class);
	}

	@Test
	@DisplayName("로또가 없는 경우")
	void constructor_로또가_없는_경우() {
		assertThatExceptionOfType(InvalidLottosException.class).isThrownBy(() -> {
			new Lottos(null);
			new Lottos(Collections.emptyList());
		}).withMessage("하나 이상의 로또가 필요합니다.");
	}

	@Test
	@DisplayName("로또들의 당첨 결과 확인")
	void match() {
		Lottos lottos = new Lottos(Arrays.asList(
				LottoFactory.create("1,2,3,4,5,6"),
				LottoFactory.create("2,3,4,5,6,7")
		));
		WinningLotto winningLotto = new WinningLotto(LottoFactory.create("1,2,3,4,5,6"), LottoNumber.of(7));
		LottoResults expected = new LottoResults(Arrays.asList(
				LottoRank.FIRST,
				LottoRank.SECOND
		));
		assertThat(lottos.match(winningLotto)).isEqualTo(expected);
	}
}
