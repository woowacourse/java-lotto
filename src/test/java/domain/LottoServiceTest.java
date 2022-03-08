package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.dto.BuyingInfoDto;
import controller.dto.LottoResultDto;
import domain.generator.AutoLottoGenerator;
import domain.generator.ManualLottoGenerator;
import service.LottoService;

public class LottoServiceTest {
	private final LottoService lottoService = new LottoService();

	@DisplayName("로또를 발행한다")
	@Test
	void createLotto() {
		assertThat(new AutoLottoGenerator(5).creatLottos().size()).isEqualTo(5);
	}

	@DisplayName("수동 로또와 자동 로또를 섞어서 발행")
	@Test
	void createManualAndAutoLottos() {
		//given

		String payment = "5000";
		int manualCountValue = 2;
		List<String[]> manualLottos = getManualLottos();
		//when
		BuyingInfoDto buyingInfoDto = lottoService.buy(payment, manualCountValue, manualLottos);
		//then
		assertThat(buyingInfoDto.getTotalLottos().size()).isEqualTo(5);
	}

	@DisplayName("로또 결과를 맞춤")
	@Test
	void showResult() {
		//given
		LottoResultDto resultDto = getLottoResultDto();
		//then
		assertThat(resultDto.toRank()).containsKey(Rank.FIRST);
	}

	@DisplayName("이율 계산 ")
	@Test
	void profitRate() {
		double rate = lottoService.createProfitRate(getLottoResultDto().toRank(), "2000");
		assertThat(rate).isEqualTo(1000000);
	}

	List<String[]> getManualLottos() {
		return Arrays.asList("1,2,3,4,5,6".split(","), "11,12,13,14,15,16".split(","));
	}

	LottoResultDto getLottoResultDto() {
		Lottos totalLottos = new Lottos(new ManualLottoGenerator(getManualLottos()).creatLottos());
		WinningLotto winningLotto = new WinningLotto(Lotto.of("1,2,3,4,5,6".split(",")), LottoNumber.of(7));
		//when
		return lottoService.createLottoResult(totalLottos, winningLotto);
	}
}
