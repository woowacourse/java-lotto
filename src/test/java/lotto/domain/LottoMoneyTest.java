package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMoneyTest {
	@Test
	@DisplayName("생성자 테스트")
	void constructor() {
		assertThat(new LottoMoney(14000)).isExactlyInstanceOf(LottoMoney.class);
	}

	@Test
	@DisplayName("생성자에서 구입 금액이 1000으로 나누어 떨어지지 않는 경우")
	void constructor_구입_금액이_1000_단위로_나뉘어떨어지지않는경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new LottoMoney(14500));
	}

	@Test
	@DisplayName("구입할 수 있는 개수 테스트")
	void calculateBuyCount() {
		LottoMoney lottoMoney = new LottoMoney(14000);
		assertThat(lottoMoney.calculateBuyCount()).isEqualTo(14);
	}
}
