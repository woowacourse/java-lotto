package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import service.LottoMachine;

public class LottoMachineTest {

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(new LottoMachine().createLottos(2))
			.isInstanceOf(Lottos.class);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given
		Lottos manualLottos = LottoMachine.createLottos(4);
		//when
		int autoCount = 3;
		Lottos manualAndAutoMixLottos = LottoMachine.createManualAndAutoMixLottos(manualLottos, autoCount);
		//then
		assertThat(manualAndAutoMixLottos.getSize()).isEqualTo(7);
	}
}
