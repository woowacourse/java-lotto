package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.Lottos;

public class LottosTest {

	@ParameterizedTest
	@ValueSource(ints = {3,13,30})
	@DisplayName("로또 장수 만큼 로또를 반환하는지")
	void Lottos_Test(int value){
		Lottos lottos = new Lottos(value);
		assertThat(lottos.getSize()).isEqualTo(value);
	}
}
