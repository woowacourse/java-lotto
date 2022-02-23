package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoMachineTest {

	@Test
	void 로또_발행(){
		assertThat(new LottoMachine()
			.createLottos(2))
			.isInstanceOf(Lottos.class);
	}
}
