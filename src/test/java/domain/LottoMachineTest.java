package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import service.LottoMachine;

public class LottoMachineTest {

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(LottoMachine.getInstance()
			.createLottos(2))
			.isInstanceOf(Lottos.class);
	}
}
