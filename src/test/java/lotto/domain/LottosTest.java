package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
	@Test
	@DisplayName("로또 장수 만큼 로또를 반환하는지")
	void Lottos_Test() {
		Lottos lottos = LottoFactory.create(3);
		assertThat(lottos.getSize()).isEqualTo(3);
	}

	@Test
	@DisplayName("불변객체임을 증")
	void immutableTest() {
		Lottos lottos = LottoFactory.create(3);
		Iterator<Lotto> iterator = lottos.iterator();
		iterator.next();
		iterator.remove();
		assertThat(lottos.getSize()).isEqualTo(2);
	}

}
