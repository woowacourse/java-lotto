package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
	@Test
	@DisplayName("로또 장수 만큼 로또를 반환하는지")
	void Lottos_Test() {
		Lottos lottos = LottoFactory.create(3);
		assertThat(lottos.getSize()).isEqualTo(3);
	}
}
