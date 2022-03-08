package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {
	@Test
	@DisplayName("수동 로또 한도 초과")
	void makeManualLotto_X() {
		LottoCount lottoCount = new LottoCount(1);
		assertThatThrownBy(() -> lottoCount.decreaseCount(2)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동 로또 정상")
	void makeManualLotto_O() {
		LottoCount lottoCount = new LottoCount(3);
		lottoCount.decreaseCount(2);
		assertThat(lottoCount.getCount()).isEqualTo(1);
	}
}
