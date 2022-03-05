package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import strategy.InputLottoNumbersGenerationStrategy;
import strategy.RandomLottoNumbersGenerationStrategy;

public class LottosTest {
	class Strategy extends InputLottoNumbersGenerationStrategy {
		@Override
		public String inputNumbers() {
			return "1,2,3,4,5,6";
		}
	}

	@ParameterizedTest
	@ValueSource(ints = {3, 2, 1})
	@DisplayName("생성된 로또 개수 확인")
	void checkLottos(int number) {
		LottoCount lottoCount = new LottoCount(number);
		LottoCount lottoCount2 = new LottoCount(number);
		Lottos lottos = new Lottos(lottoCount, new Strategy(), lottoCount2, new RandomLottoNumbersGenerationStrategy());
		System.out.println(lottos.getLottoStorage().size());
		assertThat(lottos.getLottoStorage().size()).isEqualTo(number * 2);
	}
}
