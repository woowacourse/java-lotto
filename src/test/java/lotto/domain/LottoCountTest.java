package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoCountTest {
	@ParameterizedTest
	@ValueSource(ints = {-1, 2})
	void 수동_로또_수_범위(int manualLottoCount) {
		assertThatThrownBy(() -> new LottoCount(new Money(1000), manualLottoCount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("범위");
	}
}
