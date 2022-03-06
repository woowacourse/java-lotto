package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import service.LottoService;

public class LottoServiceTest {

	private final LottoService lottoService = new LottoService();

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(lottoService.createLottos(5, Lotto.SIZE))
			.isInstanceOf(Lottos.class);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given
		Lottos manualLottos = lottoService.createLottos(4, Lotto.SIZE);
		//when
		OrderForm orderForm = new OrderForm(new Payment(5000), 2);
		Lottos manualAndAutoMixLottos = lottoService.createLottos(manualLottos, orderForm);
		//then
		assertThat(manualAndAutoMixLottos.getSize()).isEqualTo(7);
	}
}
