package domain;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.dto.LottoResultDto;
import domain.generator.AutoLottoGenerator;
import service.LottoService;

public class LottosTest {

	@DisplayName("로또 별 등수 계산 성공")
	@Test
	void count_rank_success() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[] {"6", "5", "4", "3", "2", "1"}),
			Lotto.of(new String[] {"11", "5", "4", "3", "2", "1"}));
		Lotto winningLotto = Lotto.of(new String[] {"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = LottoNumber.of(7);
		Lottos lottos = new Lottos(lotto);
		//when
		LottoResultDto lottoResult = new LottoService()
			.createLottoResult(lottos, new WinningLotto(winningLotto, bonusNumber));
		//then
		assertThat(lottoResult.getRanks()).containsAnyOf(entry(Rank.FIFTH, 1L), entry(Rank.THIRD, 1L));
	}

	@DisplayName("아무것도 맞지 않을 경우를 미포함 한 결과가 출력 되는지")
	@Test
	void nothing_count_rank_success() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[] {"6", "5", "4", "3", "2", "1"}),
			Lotto.of(new String[] {"8", "9", "10", "11", "12", "13"}));
		Lotto winningLotto = Lotto.of(new String[] {"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = LottoNumber.of(7);
		Lottos lottos = new Lottos(lotto);
		//when
		LottoResultDto lottoResult = new LottoService()
			.createLottoResult(lottos, new WinningLotto(winningLotto, bonusNumber));
		//then
		assertThat(lottoResult.getRanks()).containsAnyOf(entry(Rank.FIRST, 1L));
	}

	@DisplayName("사이즈 체크")
	@ParameterizedTest
	@CsvSource(value = {"5:true", "6:false"}, delimiter = ':')
	void check_Lottos_size(int size, boolean expected) {
		//given, when
		List<Lotto> lottos = new AutoLottoGenerator(5).creatLottos();
		//then
		assertThat(lottos.size() == size).isEqualTo(expected);
	}
}
