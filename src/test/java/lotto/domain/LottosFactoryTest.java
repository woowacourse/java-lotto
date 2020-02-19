package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottosFactoryTest {

	@Test
	void makeLottos() {
		Lottos lottos = LottosFactory.createLottosByCount(5);
		assertThat(lottos.isRightSize(5)).isTrue();
	}
}
