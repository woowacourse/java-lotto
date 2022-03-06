package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import service.LottoService;

public class LottoServiceTest {

	private final AutoLottoGenerator lottoGenerator = new AutoLottoGenerator();

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(lottoGenerator.createLottos(5, Lotto.SIZE))
			.isInstanceOf(Lottos.class);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given
		Lottos manualLottos = lottoGenerator.createLottos(4, Lotto.SIZE);
		//when
		OrderForm orderForm = new OrderForm(new Payment(5000), 2);
		Lottos autoLottos = new AutoLottoGenerator().createLottos(orderForm.calculateAutoLottoCount(), Lotto.SIZE);
		Lottos manualAndAutoMixLottos =  new ManualLottoGenerator().createManualLottos(manualLottos, autoLottos);
		//then
		assertThat(manualAndAutoMixLottos.getSize()).isEqualTo(7);
	}
}
