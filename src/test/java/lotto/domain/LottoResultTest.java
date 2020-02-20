package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

public class LottoResultTest {
	@Test
	void findTest() {
		LottoResult lottoResult = LottoResult.of(5);
		assertThat(lottoResult == LottoResult.THREE).isTrue();
	}
}
