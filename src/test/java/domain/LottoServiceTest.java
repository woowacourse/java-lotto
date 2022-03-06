package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generator.AutoLottoGenerator;
import service.LottoService;

public class LottoServiceTest {

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(new AutoLottoGenerator(5).creatLottos().size()).isEqualTo(5);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given
		List<String[]> manualLottos = Arrays.asList("1,2,3,4,5,6".split(","), "1,2,3,4,5,6".split(","));
		LottoService lottoService = new LottoService();
		//when
		OrderForm orderForm = new OrderForm(new Payment(5000), 2);
		List<Lotto> manualAndAutoMixLottos = lottoService
			.createLottos(manualLottos,orderForm.calculateAutoLottoCount());
		//then
		assertThat(manualAndAutoMixLottos.size()).isEqualTo(5);
	}
}
