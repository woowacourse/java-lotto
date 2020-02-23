package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoCountTest {
	@ParameterizedTest
	@ValueSource(ints = {1500, 4310, 3404, 7146, 10200})
	void 천원_단위가_아닌_경우(int value) {
		assertThatThrownBy(() -> new LottoCount(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("천 원 단위");
	}

	@ParameterizedTest
	@ValueSource(ints = {999, 0, -100})
	void 한_장도_살_수_없는_경우(int value) {
		assertThatThrownBy(() -> new LottoCount(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("부족합니다");
	}
}
