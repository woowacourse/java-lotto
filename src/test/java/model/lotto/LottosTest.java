package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {
	@ParameterizedTest
	@ValueSource(ints = {100, 20, 1})
	@DisplayName("생성된 로또 개수 확인")
	void checkLottos(int number) {
		LottoCount lottoCount = new LottoCount(number);
		Lottos lottos = new Lottos(lottoCount);
		assertThat(lottos.getLottoStorage()).isEqualTo(number);
	}
}
