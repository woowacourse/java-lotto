package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.strategy.RandomLottoNumbersGenerationStrategy;
import controller.strategy.TestLottoNumberGenerationStrategy;

public class LottosTest {

	@ParameterizedTest
	@ValueSource(ints = {3, 2, 1})
	@DisplayName("생성된 로또 개수 확인")
	void checkLottos(int number) {
		LottoCount lottoCount = new LottoCount(number);
		LottoCount lottoCount2 = new LottoCount(number);
		Lottos lottos = new Lottos(lottoCount, new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)),
			lottoCount2, new RandomLottoNumbersGenerationStrategy());
		System.out.println(lottos.getLottoStorage().size());
		assertThat(lottos.getLottoStorage().size()).isEqualTo(number * 2);
	}
}
