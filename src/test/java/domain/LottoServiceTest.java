package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generator.AutoLottoGenerator;
import domain.generator.ManualLottoGenerator;

public class LottoServiceTest {

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(new AutoLottoGenerator(5, Lotto.SIZE).creatLottos())
			.isInstanceOf(Lottos.class);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given
		Lottos manualLottos = new AutoLottoGenerator(4, Lotto.SIZE).creatLottos();
		//when
		OrderForm orderForm = new OrderForm(new Payment(5000), 2);
		Lottos manualAndAutoMixLottos = new ManualLottoGenerator(
			new AutoLottoGenerator(orderForm.calculateAutoLottoCount(), Lotto.SIZE), manualLottos)
			.creatLottos();
		//then
		assertThat(manualAndAutoMixLottos.getSize()).isEqualTo(7);
	}
}
